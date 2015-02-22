package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GuessTheMissingLetterPanel extends BaseScene {
	
	// variable in which what set of question will the DB will retrieve
	private static int questionSet;
	
	// Sprites
	private TiledSprite back;
	
	private ITextureRegion r;
	private int pos;
	
	// QUESTIONS AND CHOICES
	private Sprite questionImage;
	private TiledSprite question;
	private Sprite plank;
	private Sprite c1, c2, c3, c4;
	private TiledSprite correctSprite;
	
	private myDatabase db;
	
	int x;
	
	private int lives;
	
	private CameraScene triviaScene;
	private Sprite triviaPanel;
	private Sprite trivia;
	private TiledSprite OK;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		lives = 3;
		createBackground();
		createButtons();	
		createQuestion();
		createChoices();
		checkStatus();
		checkAudioStatus();
		playSound();
		showTrivia();
		questionImage.setScale(0.8f);
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GTMLPANEL;
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// ======================================================================================
	// CLASS LOGIC
	// ======================================================================================
	
	private void createBackground() {
		attachChild(new Sprite(400, 240,resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		});
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 430, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					
					if(db.isBGMOn().compareTo("true")==0) {
						engine.getMusicManager().setMasterVolume(0.70f);
					}
					
					// unload the PANEL'S TEXUTRES / RESOURCES
					//resourcesManager.unloadGTMLPanelTextures();
					// then set the SCENE to GuessTheMissingLetter
					SceneManager.getInstance().loadGTMLScene();
					break;
				}
				return true;
			}		
		};
		registerTouchArea(back);
		attachChild(back);
		
	}
	
	public void createQuestion() {
		// create the plank
		plank = new Sprite(400, 130, resourcesManager.gtmlQuestionPad, vbom);
		attachChild(plank);
		
		// create question image
		questionImage = new Sprite(400, 290, questionImage(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					questionImage.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					questionImageSound().play();
					questionImage.setScale(0.8f);
					
					Log.d("test", "index+X = " + questionSet+x);
					Log.d("index == 29", "0+x = " + 0+x);
				}
				return true;
			}
			
		};
		registerTouchArea(questionImage);
		attachChild(questionImage);
		
		// create the question (WORD with "?" mark)
		question = new TiledSprite(400, 130, question(), vbom);
		attachChild(question);
	}
	
	public void createChoices() {
		correctSprite = new TiledSprite(correctSpritePosition(), 50, correctAnswerSprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					correctSprite.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					correctSprite.setScale(1.0f);
					resourcesManager.correct.play();
					questionImageSound().play();
					update(questionSet, "true");
					lock();
					GuessTheMissingLetterPanel.this.setChildScene(triviaScene, false, true, true);
					//nextQuestion(); // comment mo to pag kinoment out mo yung nsa taas, tska coment out mo yung showTrivia() sa may createScene()
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(correctSprite);
		attachChild(correctSprite);
		
		c1 = new Sprite(letter1Position(),  50, letter1Sprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c1.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					c1.setScale(1.0f);
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c1);
		attachChild(c1);
		
		c2 = new Sprite(letter2Position(),  50, letter2Sprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c2.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					c2.setScale(1.0f);
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c2);
		attachChild(c2);
		
		c3 = new Sprite(letter3Position(),  50, letter3Sprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c3.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					c3.setScale(1.0f);
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c3);
		attachChild(c3);
		
		c4 = new Sprite(letter4Position(),  50, letter4Sprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c4.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					c4.setScale(1.0f);
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c4);
		attachChild(c4);
		
	}
	
	private void lock() {
		correctSprite.setCurrentTileIndex(1);
		question.setCurrentTileIndex(1);
		unregisterTouchArea(correctSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
	}
	
	// ======================================================================================
	// DATABASE SECTION
	// ======================================================================================
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	private void checkLives() {
		if(lives == 0) {
			SceneManager.getInstance().loadGTMLScene();
		}
	}
	private void checkAudioStatus() {
		if(db.isBGMOn().compareTo("true")==0) {
			engine.getMusicManager().setMasterVolume(0.50f);
		}
		if(db.isSFXOn().compareTo("true")==0) {
			engine.getSoundManager().setMasterVolume(1.5f);
		}
	}
	
	private void update(int id, String s) {
		db.updateGTML(id, s);
		db.close();
	}
	
	private void checkStatus() {
		String cmp = db.gtmlIsAnswered(questionSet);
		if(cmp.compareTo("true") == 0) {
			lock();
		}
		else return;
	}
	
	private void nextQuestion() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				x = 0;
				if(db.gtmlGetAnswered()==25) {
					SceneManager.getInstance().loadGTMLScene();
				} else {
					if(questionSet == 28 || questionSet+x>=28) {
						while(db.gtmlIsAnswered(0+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(0+x);
					} else {
						while(db.gtmlIsAnswered(questionSet+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(questionSet+x);
					}
				
				}
				SceneManager.getInstance().loadGTMLPanelScene();		
			}
		}));
	}
	
	private void playSound() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {	
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
				questionImageSound().play();
			}
			
		}));
	}
	
	private void showTrivia() {
		triviaScene = new CameraScene(camera);
		
		triviaPanel = new Sprite(400, 240, resourcesManager.triviaPanel, vbom);
		triviaScene.attachChild(triviaPanel);
		triviaPanel.setZIndex(0);
		
		trivia = new Sprite(400, 240, trivia(), vbom); 
		triviaScene.attachChild(trivia);
		trivia.setZIndex(1);
		
		OK = new TiledSprite(400, 100, resourcesManager.triviaOK, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					OK.setScale(0.9f);
					OK.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					GuessTheMissingLetterPanel.this.clearChildScene();
					nextQuestion();
					break;
				}
				return true;
			}
			
		};
		triviaScene.attachChild(OK);
		triviaScene.registerTouchArea(OK);
		OK.setZIndex(1);
		
		triviaScene.setBackgroundEnabled(false);
		triviaScene.sortChildren();
	}
	
	private ITextureRegion trivia() {
		ITextureRegion t = null;
		if(questionSet == 0) t = resourcesManager.appleTriva;
		else if(questionSet == 1) t = resourcesManager.avocadoTriva;
		else if(questionSet == 2) t = resourcesManager.mangoTriva;
		else if(questionSet == 3) t = resourcesManager.orangeTriva;
		else if(questionSet == 4) t = resourcesManager.GrapesTriva;
		
		else if(questionSet == 6) t = resourcesManager.strawberryTriva;
		else if(questionSet == 7) t = resourcesManager.pumpkinTriva;
		else if(questionSet == 8) t = resourcesManager.zebreaTriva;
		else if(questionSet == 9) t = resourcesManager.DiamondTriva;
		else if(questionSet == 10) t = resourcesManager.CastleTriva;
		
		else if(questionSet == 12) t = resourcesManager.MouseTriva;
		else if(questionSet == 13) t = resourcesManager.BoatTriva;
		else if(questionSet == 14) t = resourcesManager.CupcakeTriva;
		else if(questionSet == 15) t = resourcesManager.PigTriva;
		else if(questionSet == 16) t = resourcesManager.turtleTriva;
		
		else if(questionSet == 18) t = resourcesManager.rabbitTriva;
		else if(questionSet == 19) t = resourcesManager.flagTriva;
		else if(questionSet == 20) t = resourcesManager.elephantTriva;
		else if(questionSet == 21) t = resourcesManager.monkeyTriva;
		else if(questionSet == 22) t = resourcesManager.pandaTriva;
		
		else if(questionSet == 24) t = resourcesManager.snakeTriva;
		else if(questionSet == 25) t = resourcesManager.MoonTriva;
		else if(questionSet == 26) t = resourcesManager.IglooTriva;
		else if(questionSet == 27) t = resourcesManager.GiraffeTriva;
		else if(questionSet == 28) t = resourcesManager.OwlTriva;
		
		return t;
	}
	
	// SOUND
	private Sound questionImageSound() {
		Sound s = null;
		
		if(questionSet == 0) s = resourcesManager.apple;
		
		else if(questionSet == 1) s = resourcesManager.avocado;
		
		else if(questionSet == 2) s = resourcesManager.mango;
		
		else if(questionSet == 3) s = resourcesManager.orange;
		
		else if(questionSet == 4) s = resourcesManager.grapes;
		
		else if(questionSet == 6) s = resourcesManager.strawberry;
		
		else if(questionSet == 7) s = resourcesManager.pumpkin;
		
		else if(questionSet == 8) s = resourcesManager.Zebra;
		
		else if(questionSet == 9) s = resourcesManager.Diamond;
		
		else if(questionSet == 10) s = resourcesManager.Castle;
		
		else if(questionSet == 12) s = resourcesManager.Mouse;
		
		else if(questionSet == 13) s = resourcesManager.Boat;
		
		else if(questionSet == 14) s = resourcesManager.Cupcake;
		
		else if(questionSet == 15) s = resourcesManager.Pig;
		
		else if(questionSet == 16) s = resourcesManager.Turtle;
		
		else if(questionSet == 18) s = resourcesManager.Rabbit;
		
		else if(questionSet == 19) s = resourcesManager.flag;

		else if(questionSet == 20) s = resourcesManager.Elephant;
		
		else if(questionSet == 21) s = resourcesManager.Monkey;
		
		else if(questionSet == 22) s = resourcesManager.Panda;
		
		else if(questionSet == 24) s = resourcesManager.Snake;
		
		else if(questionSet == 25) s = resourcesManager.Moon;
		
		else if(questionSet == 26) s = resourcesManager.Igloo;
		
		else if(questionSet == 27) s = resourcesManager.Giraffe;
		
		else if(questionSet == 28) s = resourcesManager.Owl;
		return s;
	}
	
	// TEXTURE REGIONS
	private ITextureRegion questionImage() {
		
		if(questionSet == 0) r = resourcesManager.appleTexture;
		
		else if (questionSet == 1) r = resourcesManager.avocadoTexture;
		
		else if (questionSet == 2) r = resourcesManager.mangoTexture;
		
		else if (questionSet == 3) r = resourcesManager.orangesTexture;
		
		else if (questionSet == 4) r = resourcesManager.grapesTexture;
		
		else if (questionSet == 6) r = resourcesManager.strawberryTexture;
		
		else if (questionSet == 7) r = resourcesManager.pumpkinTexture;
		
		else if (questionSet == 8) r = resourcesManager.zebraTexture;
		
		else if (questionSet == 9) r = resourcesManager.diamondTexture;
		
		else if (questionSet == 10) r = resourcesManager.castleTexture;
		
		else if (questionSet == 12) r = resourcesManager.mouseTexture;
		
		else if (questionSet == 13) r = resourcesManager.BoatTexture;
		
		else if (questionSet == 14) r = resourcesManager.CupcakeTexture;
		
		else if (questionSet == 15) r = resourcesManager.pigTexture;
		
		else if (questionSet == 16) r = resourcesManager.turtleTexture;
		
		else if (questionSet == 18) r = resourcesManager.rabbitTexture;
		
		else if (questionSet == 19) r = resourcesManager.flagTexture;
		
		else if (questionSet == 20) r = resourcesManager.elephantTexture;
		
		else if (questionSet == 21) r = resourcesManager.monkeyTexture;
		
		else if (questionSet == 22) r = resourcesManager.pandaTexture;
		
		else if (questionSet == 24) r = resourcesManager.snakeTexture;
		
		else if (questionSet == 25) r = resourcesManager.MoonTexture;
		
		else if (questionSet == 26) r = resourcesManager.iglooTexture;
		
		else if (questionSet == 27) r = resourcesManager.giraffeTexture;
		
		else if (questionSet == 28) r = resourcesManager.owlTexture;
		return r;
	}
	
	private TiledTextureRegion question() {
		TiledTextureRegion r = null;
		
		if(questionSet == 0) r = resourcesManager.appleQuestionTexture;
		
		else if (questionSet == 1) r = resourcesManager.avocadoQuestionTexture;
		
		else if (questionSet == 2) r = resourcesManager.mangoQuestionTexture;
		
		else if (questionSet == 3) r = resourcesManager.orangeQuestionTexture;
		
		else if (questionSet == 4) r = resourcesManager.GrapesQuestionTexture;
		
		else if (questionSet == 6) r = resourcesManager.strawberryQuestionTexture;
		
		else if (questionSet == 7) r = resourcesManager.pumpkinQuestionTexture;
		
		else if (questionSet == 8) r = resourcesManager.zebreaQuestionTexture;
		
		else if (questionSet == 9) r = resourcesManager.DiamondQuestionTexture;
		
		else if (questionSet == 10) r = resourcesManager.CastleQuestionTexture;
		
		else if (questionSet == 12) r = resourcesManager.MouseQuestionTexture;
		
		else if (questionSet == 13) r = resourcesManager.BoatQuestionTexture;
		
		else if (questionSet == 14) r = resourcesManager.CupcakeQuestionTexture;
		
		else if (questionSet == 15) r = resourcesManager.PigQuestionTexture;
		
		else if (questionSet == 16) r = resourcesManager.turtleQuestionTexture;
		
		else if (questionSet == 18) r = resourcesManager.rabbitQuestionTexture;
		
		else if (questionSet == 19) r = resourcesManager.flagQuestionTexture;
		
		else if (questionSet == 20) r = resourcesManager.elephantQuestionTexture;
		
		else if (questionSet == 21) r = resourcesManager.monkeyQuestionTexture;
		
		else if (questionSet == 22) r = resourcesManager.pandaQuestionTexture;
		
		else if (questionSet == 24) r = resourcesManager.snakeQuestionTexture;
	
		else if (questionSet == 25) r = resourcesManager.MoonQuestionTexture;
		
		else if (questionSet == 26) r = resourcesManager.IglooQuestionTexture;
		
		else if (questionSet == 27) r = resourcesManager.GiraffeQuestionTexture;
		
		else if (questionSet == 28) r = resourcesManager.OwlQuestionTexture;
		
		return r;
	}
	
	// choices
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		
		if(questionSet == 0) r = resourcesManager.aTexture;
		
		else if (questionSet == 1) r = resourcesManager.dTexture;
		
		else if (questionSet == 2) r = resourcesManager.mTexture;
		
		else if (questionSet == 3) r = resourcesManager.oTexture;
		
		else if (questionSet == 4) r = resourcesManager.gTexture;
		
		else if (questionSet == 6) r = resourcesManager.sTexture;
		
		else if (questionSet == 7) r = resourcesManager.uTexture;
		
		else if (questionSet == 8) r = resourcesManager.zTexture;
		
		else if (questionSet == 9) r = resourcesManager.nTexture;
		
		else if (questionSet == 10) r = resourcesManager.tTexture;
		
		else if (questionSet == 12) r = resourcesManager.eTexture;
		
		else if (questionSet == 13) r = resourcesManager.bTexture;
		
		else if (questionSet == 14) r = resourcesManager.cTexture;
		
		else if (questionSet == 15) r = resourcesManager.iTexture;
		
		else if (questionSet == 16) r = resourcesManager.lTexture;
		
		else if (questionSet == 18) r = resourcesManager.rTexture;
		
		else if (questionSet == 19) r = resourcesManager.fTexture;
		
		else if (questionSet == 20) r = resourcesManager.hTexture;
		
		else if (questionSet == 21) r = resourcesManager.mTexture;
		
		else if (questionSet == 22) r = resourcesManager.pTexture;
		
		else if (questionSet == 24) r = resourcesManager.kTexture;
		
		else if (questionSet == 25) r = resourcesManager.mTexture;
		
		else if (questionSet == 26) r = resourcesManager.iTexture;
		
		else if (questionSet == 27) r = resourcesManager.fTexture;
		
		else if (questionSet == 28) r = resourcesManager.wTexture;
		return r;
	}
	
	private ITextureRegion letter1Sprite() {
		
		if(questionSet == 0) r = resourcesManager.hTexture;
		
		else if (questionSet == 1) r = resourcesManager.gTexture;
		
		else if (questionSet == 2) r = resourcesManager.bTexture;
		
		else if (questionSet == 3) r = resourcesManager.wTexture;
		
		else if (questionSet == 4) r = resourcesManager.hTexture;
		
		else if (questionSet == 6) r = resourcesManager.bTexture;
		
		else if (questionSet == 7) r = resourcesManager.aTexture;
		
		else if (questionSet == 8) r = resourcesManager.tTexture;
		
		else if (questionSet == 9) r = resourcesManager.wTexture;
		
		else if (questionSet == 10) r = resourcesManager.lTexture;
		
		else if (questionSet == 12) r = resourcesManager.zTexture;
		
		else if (questionSet == 13) r = resourcesManager.gTexture;
		
		else if (questionSet == 14) r = resourcesManager.hTexture;
		
		else if (questionSet == 15) r = resourcesManager.uTexture;
		
		else if (questionSet == 16) r = resourcesManager.kTexture;
		
		else if (questionSet == 18) r = resourcesManager.cTexture;
		
		else if (questionSet == 19) r = resourcesManager.lTexture;
		
		else if (questionSet == 20) r = resourcesManager.pTexture;
		
		else if (questionSet == 21) r = resourcesManager.dTexture;
		
		else if (questionSet == 22) r = resourcesManager.gTexture;
		
		else if (questionSet == 24) r = resourcesManager.cTexture;
		
		else if (questionSet == 25) r = resourcesManager.cTexture;
		
		else if (questionSet == 26) r = resourcesManager.hTexture;
		
		else if (questionSet == 27) r = resourcesManager.vTexture;
		
		else if (questionSet == 28) r = resourcesManager.uTexture;
		return r;
	}
	
	private ITextureRegion letter2Sprite() {
		
		if(questionSet == 0) r = resourcesManager.oTexture;
		
		else if(questionSet == 1) r = resourcesManager.hTexture;
		
		else if (questionSet == 2) r = resourcesManager.hTexture;
		
		else if (questionSet == 3) r = resourcesManager.vTexture;
		
		else if (questionSet == 4) r = resourcesManager.dTexture;
		
		else if (questionSet == 6) r = resourcesManager.rTexture;
		
		else if (questionSet == 7) r = resourcesManager.oTexture;
		
		else if (questionSet == 8) r = resourcesManager.kTexture;
		
		else if (questionSet == 9) r = resourcesManager.dTexture;
		
		else if (questionSet == 10) r = resourcesManager.sTexture;
		
		else if (questionSet == 12) r = resourcesManager.tTexture;
		
		else if (questionSet == 13) r = resourcesManager.vTexture;
		
		else if (questionSet == 14) r = resourcesManager.kTexture;
		
		else if (questionSet == 15) r = resourcesManager.oTexture;
		
		else if (questionSet == 16) r = resourcesManager.sTexture;
		
		else if (questionSet == 18) r = resourcesManager.hTexture;
		
		else if (questionSet == 19) r = resourcesManager.pTexture;
		
		else if (questionSet == 20) r = resourcesManager.fTexture;
		
		else if (questionSet == 21) r = resourcesManager.aTexture;
		
		else if (questionSet == 22) r = resourcesManager.bTexture;
		
		else if (questionSet == 24) r = resourcesManager.jTexture;
		
		else if (questionSet == 25) r = resourcesManager.gTexture;
		
		else if (questionSet == 26) r = resourcesManager.eTexture;
		
		else if (questionSet == 27) r = resourcesManager.mTexture;
		
		else if (questionSet == 28) r = resourcesManager.hTexture;
		
		return r;
	}
	
	private ITextureRegion letter3Sprite() {
		
		if(questionSet == 0) r = resourcesManager.eTexture;
		
		else if (questionSet == 1) r = resourcesManager.zTexture;
		
		else if (questionSet == 2) r = resourcesManager.gTexture;
		
		else if (questionSet == 3) r = resourcesManager.eTexture;
		
		else if (questionSet == 4) r = resourcesManager.cTexture;
		
		else if (questionSet == 6) r = resourcesManager.cTexture;
		
		else if (questionSet == 7) r = resourcesManager.hTexture;
		
		else if (questionSet == 8) r = resourcesManager.cTexture;
		
		else if (questionSet == 9) r = resourcesManager.lTexture;
		
		else if (questionSet == 10) r = resourcesManager.zTexture;
		
		else if (questionSet == 12) r = resourcesManager.sTexture;
		
		else if (questionSet == 13) r = resourcesManager.cTexture;
		
		else if (questionSet == 14) r = resourcesManager.lTexture;
		
		else if (questionSet == 15) r = resourcesManager.eTexture;
		
		else if (questionSet == 16) r = resourcesManager.hTexture;
		
		else if (questionSet == 18) r = resourcesManager.fTexture;
		
		else if (questionSet == 19) r = resourcesManager.bTexture;
		
		else if (questionSet == 20) r = resourcesManager.mTexture;
		
		else if (questionSet == 21) r = resourcesManager.oTexture;
		
		else if (questionSet == 22) r = resourcesManager.hTexture;
		
		else if (questionSet == 24) r = resourcesManager.tTexture;
		
		else if (questionSet == 25) r = resourcesManager.pTexture;
		
		else if (questionSet == 26) r = resourcesManager.aTexture;
		
		else if (questionSet == 27) r = resourcesManager.cTexture;
		
		else if (questionSet == 28) r = resourcesManager.eTexture;
		return r;
	}
	
	private ITextureRegion letter4Sprite() {
		
		if(questionSet == 0) r = resourcesManager.iTexture;
		
		else if (questionSet == 1) r = resourcesManager.tTexture; 
		
		else if (questionSet == 2) r = resourcesManager.tTexture;
		
		else if (questionSet == 3) r = resourcesManager.aTexture;
		
		else if (questionSet == 4) r = resourcesManager.pTexture;
		
		else if (questionSet == 6) r = resourcesManager.zTexture;
		
		else if (questionSet == 7) r = resourcesManager.pTexture;
		
		else if (questionSet == 8) r = resourcesManager.hTexture;
		
		else if (questionSet == 9) r = resourcesManager.rTexture;
		
		else if (questionSet == 10) r = resourcesManager.kTexture;
		
		else if (questionSet == 12) r = resourcesManager.cTexture;
		
		else if (questionSet == 13) r = resourcesManager.fTexture;
		
		else if (questionSet == 14) r = resourcesManager.pTexture;
		
		else if (questionSet == 15) r = resourcesManager.aTexture;
		
		else if (questionSet == 16) r = resourcesManager.gTexture;
		
		else if (questionSet == 18) r = resourcesManager.sTexture;
		
		else if (questionSet == 19) r = resourcesManager.cTexture;
		
		else if (questionSet == 20) r = resourcesManager.bTexture;
		
		else if (questionSet == 21) r = resourcesManager.hTexture;
		
		else if (questionSet == 22) r = resourcesManager.tTexture;
		
		else if (questionSet == 24) r = resourcesManager.sTexture;
		
		else if (questionSet == 25) r = resourcesManager.fTexture;
		
		else if (questionSet == 26) r = resourcesManager.nTexture;
		
		else if (questionSet == 27) r = resourcesManager.pTexture;
		
		else if (questionSet == 28) r = resourcesManager.aTexture;
		return r;
	}
	
	// POSISTIONS
	/*
	 *  FROM LEFT TO RIGHT OF THE SCREEN YOU ARE LOOKING
	 *  200, 300, 400, 500, 600
	 * 
	 */
	private int correctSpritePosition() {
		
		if(questionSet == 0) pos = 400;
		
		else if (questionSet == 1) pos = 300;
		
		else if (questionSet == 2) pos = 300;
		
		else if (questionSet == 3) pos = 400;
		
		else if (questionSet == 4) pos = 500;
		
		else if (questionSet == 6) pos = 600;
		
		else if (questionSet == 7) pos = 300;
		
		else if (questionSet == 8) pos = 200;
		
		else if (questionSet == 9) pos = 300;
		
		else if (questionSet == 10) pos = 500;
		
		else if (questionSet == 12) pos = 300;
		
		else if (questionSet == 13) pos = 400;
		
		else if (questionSet == 14) pos = 200;
		
		else if (questionSet == 15) pos = 600;
		
		else if (questionSet == 16) pos = 400;
		
		else if (questionSet == 18) pos = 600;
		
		else if (questionSet == 19) pos = 400;
		
		else if (questionSet == 20) pos = 400;
		
		else if (questionSet == 21) pos = 200;
		
		else if (questionSet == 22) pos = 200;
		
		else if (questionSet == 24) pos = 300;
		
		else if (questionSet == 25) pos = 400;
		
		else if (questionSet == 26) pos = 500;
		
		else if (questionSet == 27) pos = 200;
		
		else if (questionSet == 28) pos = 500;
			
		return pos;
	}
	
	private int letter1Position() {
		
		if(questionSet == 0) pos = 200;
		
		else if (questionSet == 1) pos = 200;
		
		else if (questionSet == 2) pos = 200;
		
		else if (questionSet == 3) pos = 200;
		
		else if (questionSet == 4) pos = 200;
		
		else if (questionSet == 6) pos = 200;
		
		else if (questionSet == 7) pos = 200;
		
		else if (questionSet == 8) pos = 300;
		
		else if (questionSet == 9) pos = 200;
		
		else if (questionSet == 10) pos = 200;
		
		else if (questionSet == 12) pos = 200;
		
		else if (questionSet == 13) pos = 300;
		
		else if (questionSet == 14) pos = 300;
		
		else if (questionSet == 15) pos = 300;
		
		else if (questionSet == 16) pos = 300;
		
		else if (questionSet == 18) pos = 300;
		
		else if (questionSet == 19) pos = 200;
		
		else if (questionSet == 20) pos = 300;
		
		else if (questionSet == 21) pos = 300;
		
		else if (questionSet == 22) pos = 300;
		
		else if (questionSet == 24) pos = 200;
		
		else if (questionSet == 25) pos = 300;

		else if (questionSet == 26) pos = 300;
		
		else if (questionSet == 27) pos = 300;
		
		else if (questionSet == 28) pos = 300;
		return pos;
	}
	
	private int letter2Position() {
		
		if(questionSet == 0) pos = 300;
		
		else if (questionSet == 1) pos = 400;
		
		else if (questionSet == 2) pos = 400;
		
		else if (questionSet == 3) pos = 300;
		
		else if (questionSet == 4) pos = 300;
		
		else if (questionSet == 6) pos = 300;
		
		else if (questionSet == 7) pos = 400;
		
		else if (questionSet == 8) pos = 400;
		
		else if (questionSet == 9) pos = 400;
		
		else if (questionSet == 10) pos = 300;
		
		else if (questionSet == 12) pos = 400;
		
		else if (questionSet == 13) pos = 500;
		
		else if (questionSet == 14) pos = 400;
		
		else if (questionSet == 15) pos = 400;
		
		else if (questionSet == 16) pos = 200;
		
		else if (questionSet == 18) pos = 400;
		
		else if (questionSet == 19) pos = 300;
		
		else if (questionSet == 20) pos = 200;
		
		else if (questionSet == 21) pos = 400;
		
		else if (questionSet == 22) pos = 400;
		
		else if (questionSet == 24) pos = 400;
		
		else if (questionSet == 25) pos = 500;
		
		else if (questionSet == 26) pos = 400;
		
		else if (questionSet == 27) pos = 400;
		
		else if (questionSet == 28) pos = 400;
		return pos;
	}
	
	private int letter3Position() {
		
		if(questionSet == 0) pos = 500;
		
		else if (questionSet == 1) pos = 500;
		
		else if (questionSet == 2) pos = 500;
		
		else if (questionSet == 3) pos = 500;
		
		else if (questionSet == 4) pos = 400;
		
		else if (questionSet == 6) pos = 400;
		
		else if (questionSet == 7) pos = 500;
		
		else if (questionSet == 8) pos = 500;
		
		else if (questionSet == 9) pos = 500;
		
		else if (questionSet == 10) pos = 400;
		
		else if (questionSet == 12) pos = 500;
		
		else if (questionSet == 13) pos = 200;
		
		else if (questionSet == 14) pos = 500;
		
		else if (questionSet == 15) pos = 500;
		
		else if (questionSet == 16) pos = 500;
		
		else if (questionSet == 18) pos = 500;
		
		else if (questionSet == 19) pos = 500;
		
		else if (questionSet == 20) pos = 500;
		
		else if (questionSet == 21) pos = 500;
		
		else if (questionSet == 22) pos = 500;
		
		else if (questionSet == 24) pos = 500;
		
		else if (questionSet == 25) pos = 200;
		
		else if (questionSet == 26) pos = 200;
		
		else if (questionSet == 27) pos = 500;
		
		else if (questionSet == 28) pos = 200;
		return pos;
	}
	
	private int letter4Position() {
		
		if(questionSet == 0) pos = 600;
		
		else if (questionSet == 1) pos = 600;
		
		else if (questionSet == 2) pos = 600;
		
		else if (questionSet == 3) pos = 600;
		
		else if (questionSet == 4) pos = 600;
		
		else if (questionSet == 6) pos = 500;
		
		else if (questionSet == 7) pos = 600;
		
		else if (questionSet == 8) pos = 600;
		
		else if (questionSet == 9) pos = 600;
		
		else if (questionSet == 10) pos = 600;
		
		else if (questionSet == 12) pos = 600;
		
		else if (questionSet == 13) pos = 600;
		
		else if (questionSet == 14) pos = 600;
		
		else if (questionSet == 15) pos = 200;
		
		else if (questionSet == 16) pos = 600;
		
		else if (questionSet == 18) pos = 200;
		
		else if (questionSet == 19) pos = 600;
		
		else if (questionSet == 20) pos = 600;
		
		else if (questionSet == 21) pos = 600;
		
		else if (questionSet == 22) pos = 600;
		
		else if (questionSet == 24) pos = 600;
		
		else if (questionSet == 25) pos = 600;
		
		else if (questionSet == 26) pos = 600;
		
		else if (questionSet == 27) pos = 600;
		
		else if (questionSet == 28) pos = 600;
		return pos;
	}
	
}
