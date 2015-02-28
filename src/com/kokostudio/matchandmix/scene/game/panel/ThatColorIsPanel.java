package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class ThatColorIsPanel extends BaseScene {
	
	// variable in which what set of question will be retrieve
	private static int questionSet;
	
	private ITextureRegion r;
	private int pos;
	private int lives;
	private float totalLives;
	private float liveSpent;
	private float rate;
	
	// SPRITES
	private Sprite question;
	private Sprite c1, c2, c3, c4;
	private TiledSprite correctSprite;
	
	private Sprite BG;
	private Sprite questionPlank;
	private TiledSprite back;
	
	// DATABASE
	private myDatabase db;
	
	private int x;
	
	private TiledSprite OK;
	
	private CameraScene tryScene;
	private Sprite tryMsg;
	
	private Sprite thatsWrong;
	private Sprite thatsCorrect;
	
	private Sprite sLife;
	private TiledSprite sLifeValue;
	
	private int currentTile;
	
	private CameraScene htpScene;
	private TiledSprite htp;
	private TiledSprite next;
	private TiledSprite prev;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		totalLives = 3;
		lives = 3;
		liveSpent = 0;
		createBackground();
		createButtons();
		createChoices();
		createQuestions();
		checkStatus();
		createTryAgainScene();
		
		if(db.checkIsFirstTime(2).compareTo("true")==0) {
			createHowToScene();	
			ThatColorIsPanel.this.setChildScene(htpScene, false, true, true);
		}
		
		thatsCorrect.setAlpha(0f);
		thatsWrong.setAlpha(0f);
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
		return SceneType.SCENE_THATCOLORISPANEL;
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// -----------------------------------------------------------------------------
	// CLASS LOGIC
	// -----------------------------------------------------------------------------
	private void createBackground() {
		BG = new Sprite(400, 230, resourcesManager.thatColorIsBGTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		questionPlank = new Sprite(250, 70, resourcesManager.color_questionPlankTextureRegion, vbom);
		attachChild(BG);
		attachChild(questionPlank);
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
					
					// unload that color is panel textures
					//ResourcesManager.getInstance().unloadThatColorIsPanelTextures();
					disposeScene();
					// set the scene
					SceneManager.getInstance().loadThatColorIsScene();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(back);
		attachChild(back);
		
		thatsCorrect = new Sprite(410, 445, resourcesManager.thatsCorrectTexture, vbom);
		attachChild(thatsCorrect);
		thatsWrong = new Sprite(410, 445, resourcesManager.thatsWrongTexture, vbom);
		attachChild(thatsWrong);
		
		sLife = new Sprite(690, 445, resourcesManager.lifeTexture, vbom);
		attachChild(sLife);
		sLifeValue = new TiledSprite(750, 450, resourcesManager.lifeValueTexture, vbom);
		sLifeValue.setCurrentTileIndex(lives);
		attachChild(sLifeValue);
	}
	
	private void createQuestions() {
		question = new Sprite(250, 230, question(), vbom);
		attachChild(question);
	}
	
	private void createChoices() {
		correctSprite = new TiledSprite(650, correctSpritePosition(), correctAnswerSprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					correctSprite.setScale(1.2f);
					break;
				case TouchEvent.ACTION_UP:
					correctSprite.setScale(1.0f);
					// PLAY THE CORRECT SOUND THEN SWITCH TO THE NEXT TILEINDEX
					resourcesManager.correct.play();
					correctSprite.setCurrentTileIndex(1);
					update(questionSet, "true");
					updateIsFirstTime();
					lock();	
					nextQuestion();
					db.updateRate(2, computeRate());
					db.updateTry(2, 1);
					thatsCorrect.setAlpha(1.0f);
					break;
				}
				return true;
			}
		};
		
		c1 = new Sprite(650, setColor1Position(), color1(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c1.setScale(1.2f);
					break;
				case TouchEvent.ACTION_UP:
					c1.setScale(1.0f);
					// PLAY THE INCORRECT SOUND
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					liveSpent++;
					sLifeValue.setCurrentTileIndex(lives);
					thatsWrong.registerEntityModifier(new AlphaModifier(0.8f, 1.0f, 0));
					break;
				}
				return true;
			}
			
		};
		c2 = new Sprite(650, setColor2Position(), color2(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c2.setScale(1.2f);
					break;
				case TouchEvent.ACTION_UP:
					c2.setScale(1.0f);
					// PLAY THE INCORRECT SOUND
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					sLifeValue.setCurrentTileIndex(lives);
					liveSpent++;
					thatsWrong.registerEntityModifier(new AlphaModifier(0.8f, 1.0f, 0));
					break;
				}
				return true;
			}
		};
		c3 = new Sprite(650, setColor3Position(), color3(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c3.setScale(1.2f);
					break;
				case TouchEvent.ACTION_UP:
					c3.setScale(1.0f);
					// PLAY THE INCORRECT SOUND
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					sLifeValue.setCurrentTileIndex(lives);
					liveSpent++;
					thatsWrong.registerEntityModifier(new AlphaModifier(0.8f, 1.0f, 0));
					break;
				}
				return true;
			}
		};
		c4 = new Sprite(650, setColor4Position(), color4(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c4.setScale(1.2f);
					break;
				case TouchEvent.ACTION_UP:
					c4.setScale(1.0f);
					// PLAY THE INCORRECT SOUND
					resourcesManager.wrong.play();
					lives--;
					checkLives();
					sLifeValue.setCurrentTileIndex(lives);
					liveSpent++;
					thatsWrong.registerEntityModifier(new AlphaModifier(0.8f, 1.0f, 0));
					break;
				}
				return true;
			}
		};
		registerTouchArea(c1);
		registerTouchArea(c2);
		registerTouchArea(c3);
		registerTouchArea(c4);
		registerTouchArea(correctSprite);
		
		attachChild(c1);
		attachChild(c2);
		attachChild(c3);
		attachChild(c4);
		attachChild(correctSprite);
	}
	
	// ============================================================================================
	// DATABASE SECTION
	// ============================================================================================
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	private void createTryAgainScene() {
		tryScene = new CameraScene(camera);
		
		tryMsg = new Sprite(400, 230, resourcesManager.tryAgainWarningMsg, vbom);
		tryScene.setZIndex(0);
		tryScene.attachChild(tryMsg);
		
		OK = new TiledSprite(400, 100, resourcesManager.triviaOK, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(0.9f);
					this.setCurrentTileIndex(1);
					break;

				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					ThatColorIsPanel.this.clearChildScene();
					SceneManager.getInstance().loadThatColorIsScene();
					break;
				}
				return true;
			}
			
		};
		
		OK.setZIndex(1);
		tryScene.registerTouchArea(OK);
		tryScene.attachChild(OK);
		tryScene.setBackgroundEnabled(false);
	}
	
	private void createHowToScene() {
		htpScene = new CameraScene(camera);
		currentTile = 0;
		
		htp = new TiledSprite(400, 240, resourcesManager.colorHTP, vbom);
		htpScene.attachChild(htp);
		
		next = new TiledSprite(660, 240, resourcesManager.nextTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {	
				if(pSceneTouchEvent.isActionUp()) {
					currentTile++;
					htp.setCurrentTileIndex(currentTile);
					
					if(currentTile == 1) {
						
						prev.setVisible(true);
						htpScene.registerTouchArea(prev);
						
					} else if (currentTile == 2) {
						
						next.setVisible(false);
						htpScene.unregisterTouchArea(next);
						
						OK.setVisible(true);
						htpScene.registerTouchArea(OK); 
					} /*else if  (currentTile == 3) {
						next.setVisible(false);
						htpScene.unregisterTouchArea(next);
						
						OK.setVisible(true);
						htpScene.registerTouchArea(OK);
					}*/
					
					next.setScale(1.0f);
					next.setCurrentTileIndex(0);
				} else if (pSceneTouchEvent.isActionDown()) {
					next.setScale(0.9f);
					next.setCurrentTileIndex(1);
				} 
				return true;
			}
			
		};
		htpScene.registerTouchArea(next);
		htpScene.attachChild(next);
		
		prev = new TiledSprite(120, 240, resourcesManager.prevTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					currentTile--;
					htp.setCurrentTileIndex(currentTile);
					
					if(currentTile == 0) {
						next.setVisible(true);
						htpScene.registerTouchArea(next);
						
						prev.setVisible(false);
						htpScene.unregisterTouchArea(prev);
						OK.setVisible(false);
						htpScene.unregisterTouchArea(OK);
					}
					
					prev.setScale(1.0f);
					prev.setCurrentTileIndex(0);
				} else if (pSceneTouchEvent.isActionDown()) {
					prev.setScale(0.9f);
					prev.setCurrentTileIndex(1);
				}
				return true;
			}
		};
		prev.setVisible(false);
		htpScene.attachChild(prev);
		
		OK = new TiledSprite(400, 50, resourcesManager.triviaOK, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(0.9f);
					this.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					ThatColorIsPanel.this.clearChildScene();
					break;
				}
				return true;
			}
			
		};
		OK.setVisible(false);
		htpScene.attachChild(OK);	
		
		htpScene.setBackgroundEnabled(false);
	}
	
	private void checkLives() {
		if(lives == 0) {
			db.updateRate(2, computeRate());
			db.updateTry(2, 1);
			ThatColorIsPanel.this.setChildScene(tryScene, false, true, true);
		}
	}
	
	private float computeRate() {
		rate = (liveSpent / totalLives);
		return rate;
	}
	
	private void update(int id, String s) {
		db.updateThatColorIs(id, s);
		db.close();
	}
	
	private String isAnswred(int id) {
		String s = db.colorIsAnswered(id);
		db.close();
		return s;
	}
	
	private void updateIsFirstTime() {
		db.updateIsFirstTime(2, "false");
		db.close();
	}
	
	private void checkStatus() {
		String cmp = db.colorIsAnswered(questionSet);
		if(cmp.compareTo("true") == 0) {
			lock();
		}
		else return;
	}
	
	private void lock() {
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
		unregisterTouchArea(correctSprite);
		correctSprite.setCurrentTileIndex(1);
	}
	
	private void nextQuestion() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				x = 0;
				if(db.colorGetAnswered() == 25) {
					SceneManager.getInstance().loadThatColorIsScene();
				} else {
					if(questionSet == 28 || questionSet+x>=28) {
						while(db.colorIsAnswered(0+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(0+x);
					} else {
						while(db.colorIsAnswered(questionSet+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(questionSet+x);
					}
				
				}
				SceneManager.getInstance().loadThatColorIsPanelScene();		
			}
		}));
	}
	
	/* positions
	 * 400 pos1
	 * 310 pos2
	 * 230 pos3
	 * 150 pos4
	 * 70 pos5
	 */
	
	private int correctSpritePosition() {
		if(questionSet == 0) pos = 150;
		
		else if (questionSet == 1) pos = 310;
		
		else if (questionSet == 2) pos = 230;
		
		else if (questionSet == 3) pos = 150;
		
		else if (questionSet == 4) pos = 70;
		
		else if (questionSet == 6) pos = 390;
		
		else if (questionSet == 7) pos = 310;
		
		else if (questionSet == 8) pos = 310;
		
		else if (questionSet == 9) pos = 70;
		
		else if (questionSet == 10) pos = 230;
		
		else if (questionSet == 12) pos = 230;
		
		else if (questionSet == 13) pos = 390;
		
		else if (questionSet == 14) pos = 150;
		
		else if (questionSet == 15) pos = 310;
		
		else if (questionSet == 16) pos = 70;
		
		else if (questionSet == 18) pos = 390;
		
		else if (questionSet == 19) pos = 150;
		
		else if (questionSet == 20) pos = 230;
		
		else if (questionSet == 21) pos = 70;
		
		else if (questionSet == 22) pos = 230;
		
		else if (questionSet == 24) pos = 70;
		
		else if (questionSet == 25) pos = 310;
		
		else if (questionSet == 26) pos = 230;
		
		else if (questionSet == 27) pos = 390;
		
		else if (questionSet == 28) pos = 390;
		return pos;
	}
	private int setColor1Position() {
		if(questionSet == 0) pos = 390;
		
		else if (questionSet == 1) pos = 390;
		
		else if (questionSet == 2) pos = 390;
		
		else if (questionSet == 3) pos = 390;
		
		else if (questionSet == 4) pos = 150;
		
		else if (questionSet == 6) pos = 310;
		
		else if (questionSet == 7) pos = 390;
		
		else if (questionSet == 8) pos = 390;
		
		else if (questionSet == 9) pos = 310;
		
		else if (questionSet == 10) pos = 150;
		
		else if (questionSet == 12) pos = 310;
		
		else if (questionSet == 13) pos = 310;
		
		else if (questionSet == 14) pos = 390;
		
		else if (questionSet == 15) pos = 390;
		
		else if (questionSet == 16) pos = 310;
		
		else if (questionSet == 18) pos = 310;
		
		else if (questionSet == 19) pos = 390;
		
		else if (questionSet == 20) pos = 310;
		
		else if (questionSet == 21) pos = 310;
		
		else if (questionSet == 22) pos = 70;
		
		else if (questionSet == 24) pos = 310;
		
		else if (questionSet == 25) pos = 390;
		
		else if (questionSet == 26) pos = 310;
		
		else if (questionSet == 27) pos = 310;
		
		else if (questionSet == 28) pos = 310;
		return pos;
	}
	private int setColor2Position() {
		if(questionSet == 0) pos = 310;
		
		else if (questionSet == 1) pos = 230;
		
		else if (questionSet == 2) pos = 310;
		
		else if (questionSet == 3) pos = 230;
		
		else if (questionSet == 4) pos = 310;
		
		else if (questionSet == 6) pos = 230;
		
		else if (questionSet == 7) pos = 230;
		
		else if (questionSet == 8) pos = 230;
		
		else if (questionSet == 9) pos = 230;
		
		else if (questionSet == 10) pos = 310;
		
		else if (questionSet == 12) pos = 390;
		
		else if (questionSet == 13) pos = 230;
		
		else if (questionSet == 14) pos = 310;
		
		else if (questionSet == 15) pos = 230;
		
		else if (questionSet == 16) pos = 230;
		
		else if (questionSet == 18) pos = 230;
		
		else if (questionSet == 19) pos = 310;
		
		else if (questionSet == 20) pos = 390;
		
		else if (questionSet == 21) pos = 230;
		
		else if (questionSet == 22) pos = 150;
		
		else if (questionSet == 24) pos = 230;

		else if (questionSet == 25) pos = 150;
		
		else if (questionSet == 26) pos = 390;
		
		else if (questionSet == 27) pos = 230;
		
		else if (questionSet == 28) pos = 230;
		return pos;
	}
	private int setColor3Position() {
		if(questionSet == 0) pos = 230;
		
		else if (questionSet == 1) pos = 150;
		
		else if (questionSet == 2) pos = 150;
		
		else if (questionSet == 3) pos = 310;
		
		else if (questionSet == 4) pos = 230;
		
		else if (questionSet == 6) pos = 150;
		
		else if (questionSet == 7) pos = 150;
		
		else if (questionSet == 8) pos = 150;
		
		else if (questionSet == 9) pos = 150;
		
		else if (questionSet == 10) pos = 390;
		
		else if (questionSet == 12) pos = 150;
		
		else if (questionSet == 13) pos = 150;
		
		else if (questionSet == 14) pos = 230;
		
		else if (questionSet == 15) pos = 150;
		
		else if (questionSet == 16) pos = 150;
		
		else if (questionSet == 18) pos = 150;
		
		else if (questionSet == 19) pos = 230;
		
		else if (questionSet == 20) pos = 150;
		
		else if (questionSet == 21) pos = 150;
		
		else if (questionSet == 22) pos = 310;
		
		else if (questionSet == 24) pos = 70;
		
		else if (questionSet == 25) pos = 230;
		
		else if (questionSet == 26) pos = 150;
		
		else if (questionSet == 27) pos = 150;
		
		else if (questionSet == 28) pos = 150;
		return pos;
	}
	private int setColor4Position() {
		if(questionSet == 0) pos = 70;
		
		else if (questionSet == 1) pos = 70;
		
		else if (questionSet == 2) pos = 70;
		
		else if (questionSet == 3) pos = 70;
		
		else if (questionSet == 4) pos = 390;
		
		else if (questionSet == 6) pos = 70;
		
		else if (questionSet == 7) pos = 70;
		
		else if (questionSet == 8) pos = 70;
		
		else if (questionSet == 9) pos = 390;
		
		else if (questionSet == 10) pos = 70;
		
		else if (questionSet == 12) pos = 70;
		
		else if (questionSet == 13) pos = 70;
		
		else if (questionSet == 14) pos = 70;
		
		else if (questionSet == 15) pos = 70;
		
		else if (questionSet == 16) pos = 390;
		
		else if (questionSet == 18) pos = 70;
		
		else if (questionSet == 19) pos = 70;
		
		else if (questionSet == 20) pos = 70;
		
		else if (questionSet == 21) pos = 390;
		
		else if (questionSet == 22) pos = 390;
		
		else if (questionSet == 24) pos = 390;
		
		else if (questionSet == 25) pos = 70;
		
		else if (questionSet == 26) pos = 70;
		
		else if (questionSet == 27) pos = 70;
		
		else if (questionSet == 28) pos = 70;
		return pos;
	}
	
	// TEXTURES
	private ITextureRegion question() {
		ITextureRegion questionRegion = null;
		if(questionSet == 0) questionRegion = resourcesManager.heartTexture;
		
		else if (questionSet == 1) questionRegion = resourcesManager.starTexture;
		
		else if (questionSet == 2) questionRegion = resourcesManager.squareTexture;
		
		else if (questionSet == 3) questionRegion = resourcesManager.beanTexture;
		
		else if (questionSet == 4) questionRegion = resourcesManager.orangesTexture;
		
		else if (questionSet == 6) questionRegion = resourcesManager.snakeTexture;
		
		else if (questionSet == 7) questionRegion = resourcesManager.balloonTexture;
		
		else if (questionSet == 8) questionRegion = resourcesManager.candyTexture;
		
		else if (questionSet == 9) questionRegion = resourcesManager.boneTexture;
		
		else if (questionSet == 10) questionRegion = resourcesManager.stoneTexture;
		
		else if (questionSet == 12) questionRegion = resourcesManager.bananaTexture;
		
		else if (questionSet == 13) questionRegion = resourcesManager.eggplantTexture;
		
		else if (questionSet == 14) questionRegion = resourcesManager.papayaTexture;
		
		else if (questionSet == 15) questionRegion = resourcesManager.ribbonTexture;
		
		else if (questionSet == 16) questionRegion = resourcesManager.grapesTexture;
		
		else if (questionSet == 18) questionRegion = resourcesManager.iglooTexture;
		
		else if (questionSet == 19) questionRegion = resourcesManager.garlicTexture;
		
		else if (questionSet == 20) questionRegion = resourcesManager.ballTexture;
		
		else if (questionSet == 21) questionRegion = resourcesManager.nutTexture;
		
		else if (questionSet == 22) questionRegion = resourcesManager.cheeseTexture;
		
		else if (questionSet == 24) questionRegion = resourcesManager.flowerTexture;
		
		else if (questionSet == 25) questionRegion = resourcesManager.swordTexture;
		
		else if (questionSet == 26) questionRegion = resourcesManager.bearTexture;
		
		else if (questionSet == 27) questionRegion = resourcesManager.cookieTexture;
		
		else if (questionSet == 28) questionRegion = resourcesManager.hatTexture;
		
		return questionRegion;
	}
	
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		if(questionSet == 0) r = resourcesManager.redTextureRegion;
		else if (questionSet == 1) r = resourcesManager.yellowTextureRegion;
		else if (questionSet == 2) r = resourcesManager.blueTextureRegion;
		else if (questionSet == 3) r = resourcesManager.greenTextureRegion;
		else if (questionSet == 4) r = resourcesManager.orangeTextureRegion;
		else if (questionSet == 6) r = resourcesManager.greenTextureRegion;
		else if (questionSet == 7) r = resourcesManager.blueTextureRegion;
		else if (questionSet == 8) r = resourcesManager.purpleTextureRegion;
		else if (questionSet == 9) r = resourcesManager.whiteTextureRegion;
		else if (questionSet == 10) r = resourcesManager.grayTextureRegion;
		else if (questionSet == 12) r = resourcesManager.yellowTextureRegion;
		else if (questionSet == 13) r = resourcesManager.violetTextureRegion;
		else if (questionSet == 14) r = resourcesManager.orangeTextureRegion;
		else if (questionSet == 15) r = resourcesManager.redTextureRegion;
		else if (questionSet == 16) r = resourcesManager.violetTextureRegion;
		else if (questionSet == 18) r = resourcesManager.whiteTextureRegion;
		else if (questionSet == 19) r = resourcesManager.whiteTextureRegion;
		else if (questionSet == 20) r = resourcesManager.orangeTextureRegion;
		else if (questionSet == 21) r = resourcesManager.brownTextureRegion;
		else if (questionSet == 22) r = resourcesManager.yellowTextureRegion;
		else if (questionSet == 24) r = resourcesManager.pinkTextureRegion;
		else if (questionSet == 25) r = resourcesManager.grayTextureRegion;
		else if (questionSet == 26) r = resourcesManager.brownTextureRegion;
		else if (questionSet == 27) r = resourcesManager.brownTextureRegion;
		else if (questionSet == 28) r = resourcesManager.blackTextureRegion;
		return r;
	}
	
	private ITextureRegion color1() {
		if(questionSet == 0) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 1)  r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 3) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 4) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 6) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 7) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 8) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 9) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 10) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 12) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 13) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 14) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 15) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 16) r = resourcesManager.whiteTextureRegion;
		
		else if (questionSet == 18) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 19) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 20) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 21) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 22) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 24) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 25) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 26) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 27) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 28) r = resourcesManager.blueTextureRegion;
		return r;
	}
	
	private ITextureRegion color2() {
		if(questionSet == 0) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 3) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 4) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 6) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 7) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 8) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 9) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 10) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 12) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 13) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 14) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 15) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 16) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 18) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 19) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 20) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 21) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 22) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 24) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 25) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 26) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 27) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 28) r = resourcesManager.redTextureRegion;
		return r;
	}
	
	private ITextureRegion color3() {
		if(questionSet == 0) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 3) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 4) r = resourcesManager.whiteTextureRegion;
		
		else if (questionSet == 6) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 7) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 8) r = resourcesManager.whiteTextureRegion;
		
		else if (questionSet == 9) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 10) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 12) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 13) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 14) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 15) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 16) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 18) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 19) r = resourcesManager.violetTextureRegion;
		
		else if (questionSet == 20) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 21) r = resourcesManager.purpleTextureRegion;
		
		else if (questionSet == 22) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 24) r = resourcesManager.whiteTextureRegion;
		
		else if (questionSet == 25) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 26) r = resourcesManager.whiteTextureRegion;
		
		else if (questionSet == 27) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 28) r = resourcesManager.greenTextureRegion;
		return r;
	}
	
	private ITextureRegion color4() {
		if(questionSet == 0) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 3) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 4) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 6) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 7) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 8) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 9) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 10) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 12) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 13) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 14) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 15) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 16) r = resourcesManager.blackTextureRegion;
		
		else if (questionSet == 18) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 19) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 20) r = resourcesManager.brownTextureRegion;
		
		else if (questionSet == 21) r = resourcesManager.grayTextureRegion;
		
		else if (questionSet == 22) r = resourcesManager.grayTextureRegion;
		
		else if (questionSet == 24) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 25) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 26) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 27) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 28) r = resourcesManager.yellowTextureRegion;
		
		return r;
	}
	
}