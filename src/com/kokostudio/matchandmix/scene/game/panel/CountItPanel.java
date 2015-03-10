package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class CountItPanel extends BaseScene {

	public static int questionSet;
	
	private TiledSprite back;
	private Sprite clueBox;
	private Sprite correctClue;
	private Sprite otherClue;
	
	private Sprite[] object1;
	private Sprite[] object2;
	
	private Sprite question;
	private Sprite plank;
	private TiledSprite correctSprite;
	private Sprite c1, c2, c3, c4;
	
	private ITextureRegion r;
	private int pos, cnt;
	private int numCount;
	
	private myDatabase db;
	
	private Sprite thatsCorrect;
	private Sprite thatsWrong;
	
	private int x;
	
	private int lives;
	private float totalLives;
	private float liveSpent;
	private float rate;
	
	private Sprite sLife;
	private TiledSprite sLifeValue;
	
	private TiledSprite OK;
	
	private CameraScene tryScene;
	private Sprite tryMsg;
	
	private CameraScene htpScene;
	private TiledSprite htp;
	private Sprite blank;
	
	private int currentTile;
	
	private TiledSprite next;
	private TiledSprite prev;
	
	// congrats scene
	private CameraScene congratsScene;
	private Sprite congratsPanel;
	private Sprite pop;
	private Sprite stars;
	private TiledSprite viewProg;


	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		totalLives = 3;
		lives = 3;
		liveSpent = 0;
		createBackground();
		createQuestions();
		createButtons();
		createChoices();
		createCorrectObjects();
		createOtherObjects();
		checkAudioStatus();
		checkStatus();
		createTryAgainScene();
		
		if(db.checkIsFirstTime(3).compareTo("true")==0) {
			createHowToScene();
			CountItPanel.this.setChildScene(htpScene, false, true, true);
		}
		
		thatsCorrect.setAlpha(0f);
		thatsWrong.setAlpha(0f);
		thatsCorrect.setZIndex(2);
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_COUNTITPANEL;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// ---------------------------------------------------------
	// ENTITIES
	// ---------------------------------------------------------
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.countItBGTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		});
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 430, resourcesManager.backTiledTextureRegion, vbom){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					disposeScene();
					resourcesManager.click.play();
					
					if(db.isBGMOn().compareTo("true")==0) {
						engine.getMusicManager().setMasterVolume(0.20f);
					}
					
					SceneManager.getInstance().loadCountItScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
		
		thatsCorrect = new Sprite(410, 445, resourcesManager.thatsCorrectTexture, vbom);
		attachChild(thatsCorrect);
		thatsCorrect.setZIndex(1);
		thatsWrong = new Sprite(410, 445, resourcesManager.thatsWrongTexture, vbom);
		attachChild(thatsWrong);
		thatsWrong.setZIndex(1);
		
		
		// LIFE SPRITE AND TEXT
		sLife = new Sprite(400, 445, resourcesManager.lifeTexture, vbom);
		sLife.setAlpha(1.0f);
		attachChild(sLife);
		sLife.setZIndex(0);
		sLifeValue = new TiledSprite(460, 450, resourcesManager.lifeValueTexture, vbom);
		sLifeValue.setCurrentTileIndex(lives);
		attachChild(sLifeValue);
		sLifeValue.setZIndex(0);
		
		sortChildren();
	}
	
	private void createQuestions() { // ALSO CLUE BOX AND CLUES WILL BE CREATED HERE
		// clue box
		clueBox = new Sprite(680, 320, resourcesManager.countItClueBox, vbom);
		attachChild(clueBox);
		
		// clues
		correctClue = new Sprite(680, 370, correctClue(), vbom);
		attachChild(correctClue);

		otherClue = new Sprite(680, 270, otherClue(), vbom);
		attachChild(otherClue);
		
		// plank
		plank = new Sprite(400, 130, resourcesManager.countItQuestionPad, vbom);
		attachChild(plank);
		
		// questions
		question = new Sprite(400, 130, question(), vbom);
		attachChild(question);
	}
	
	private void createChoices() {
		
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
					update(questionSet, "true");
					updateIsFirstTime();
					lock();
					thatsCorrect.setAlpha(1.0f);
					nextQuestion();
					
					db.updateRate(3, computeRate());
					db.updateTry(3, 1);
					thatsCorrect.setAlpha(1.0f);
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(correctSprite);
		attachChild(correctSprite);
		
		c1 = new Sprite(setChoice1Position(), 50, choice1(), vbom) {
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
					liveSpent++;
					checkLives();
					sLifeValue.setCurrentTileIndex(lives);
					thatsWrong.registerEntityModifier(new AlphaModifier(1.8f, 1.0f, 0));
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c1);
		attachChild(c1);
		
		c2 = new Sprite(setChoice2Position(), 50, choice2(), vbom){
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
					liveSpent++;
					sLifeValue.setCurrentTileIndex(lives);
					thatsWrong.registerEntityModifier(new AlphaModifier(1.8f, 1.0f, 0));
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c2);
		attachChild(c2);
		
		c3 = new Sprite(setChoice3Position(), 50, choice3(), vbom){
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
					liveSpent++;
					sLifeValue.setCurrentTileIndex(lives);
					thatsWrong.registerEntityModifier(new AlphaModifier(1.8f, 1.0f, 0));
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c3);;
		attachChild(c3);
		
		c4 = new Sprite(setChoice4Position(), 50, choice4(), vbom){
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
					liveSpent++;
					sLifeValue.setCurrentTileIndex(lives);
					thatsWrong.registerEntityModifier(new AlphaModifier(1.8f, 1.0f, 0));
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c4);;
		attachChild(c4);
		
	}
	
	private void createCorrectObjects() {
		int[] posX = {172,410,454,123,180,308,350,110,543};
		int[] posY = {219,318,217,208,265,205,205,225,366};
		
		object1 = new Sprite[setHowManyCorrectObject()];
		numCount = 0;
		for(int i = 0; i < object1.length; i++) {
			final int index = i;
			object1[i] = new Sprite(posX[i], posY[i], correctObject(), vbom) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						object1[index].setScale(0.9f);
						break;
					case TouchEvent.ACTION_UP:
						object1[index].setScale(1.8f);
						playNextSound();
						unregisterTouchArea(object1[index]);
						object1[index].registerUpdateHandler(new TimerHandler(6f, new ITimerCallback() {				
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								
								numCount = 0;
								for(int ctr = 0; ctr<object1.length; ctr++) {
									object1[ctr].unregisterUpdateHandler(pTimerHandler);
									object1[ctr].setScale(1.0f);
									registerTouchArea(object1[ctr]);
								}								
							}
						}));
						
						break;
					}
					return true;
				}
				
			};
			
			engine.runOnUpdateThread(new Runnable() {
				
				@Override
				public void run() {
					attachChild(object1[index]);
					registerTouchArea(object1[index]);
				}
			});
		}
		
	}
	
	private void createOtherObjects() {
		int[] posX = {305,239,190,300,412,222};
		int[] posY = {213,200,330,412,391,235};
		object2 = new Sprite[setHowManyOtherObject()];
		for(int i = 0; i < object2.length; i++) {
			final int index = i;
			object2[i] = new Sprite(posX[i], posY[i], otherObject(), vbom);
			
			engine.runOnUpdateThread(new Runnable() {
				
				@Override
				public void run() {
					attachChild(object2[index]);
					
				}
			});
		}
	}
	
	
	
	
	// --------------------------------------------------------
	// DATABASE
	// --------------------------------------------------------
	
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		Log.d("Coordinates", "X " + pSceneTouchEvent.getX());
		Log.d("Coordinates", "Y " + pSceneTouchEvent.getY());
		return super.onSceneTouchEvent(pSceneTouchEvent);
	}

	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	private void checkLives() {
		if(lives == 0) {
			db.updateRate(3, computeRate());
			db.updateTry(3, 1);
			resourcesManager.sorry.play();
			CountItPanel.this.setChildScene(tryScene, false, true, true);
		}
	}
	
	private float computeRate() {
		rate = (liveSpent / totalLives);
		return rate;
	}
	
	private void lock() {
		correctSprite.setCurrentTileIndex(1);
		unregisterTouchArea(correctSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
	}
	
	private void createTryAgainScene() {
		tryScene = new CameraScene(camera);
		
		tryMsg = new Sprite(400, 240, resourcesManager.tryAgainWarningMsg, vbom);
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
					CountItPanel.this.clearChildScene();
					SceneManager.getInstance().loadCountItScene();
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
		
		blank = new Sprite(400, 240, resourcesManager.blankBG, vbom){
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		htpScene.attachChild(blank);
		
		htp = new TiledSprite(400, 240, resourcesManager.countHTP, vbom);
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
						/*
						next.setVisible(false);
						htpScene.unregisterTouchArea(next);
						
						OK.setVisible(true);
						htpScene.registerTouchArea(OK); */
					} else if  (currentTile == 3) {
						next.setVisible(false);
						htpScene.unregisterTouchArea(next);
						
						OK.setVisible(true);
						htpScene.registerTouchArea(OK);
					}
					resourcesManager.click.play();
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
					if (currentTile == 2 | currentTile == 1) {
						OK.setVisible(false);
						htpScene.unregisterTouchArea(OK);
					}
					resourcesManager.click.play();
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
					CountItPanel.this.clearChildScene();
					break;
				}
				return true;
			}
			
		};
		OK.setVisible(false);
		htpScene.attachChild(OK);	
		
		htpScene.setBackgroundEnabled(false);
	}
	
	private void playNextSound() {
		Sound[] numbers = 
			{ resourcesManager.number1, resourcesManager.number2, resourcesManager.number3, resourcesManager.number4, resourcesManager.number5,resourcesManager.number6,resourcesManager.number7 ,resourcesManager.number8,resourcesManager.number9};
		numbers[numCount].play();
		numCount++;
	}
	
	private void checkAudioStatus() {
		if(db.isBGMOn().compareTo("true")==0) {
			engine.getMusicManager().setMasterVolume(0.15f);
		}
		if(db.isSFXOn().compareTo("true")==0) {
			engine.getSoundManager().setMasterVolume(1.6f);
		}
	}
	
	private void update(int id, String s) {
		db.updateCountIt(id, s);
		db.close();
	}
	
	private void checkStatus() {
		String cmp = db.countItIsAnswered(questionSet);
		if(cmp.compareTo("true")==0) {
			lock();
		}
	}
	
	private void updateIsFirstTime() {
		db.updateIsFirstTime(3, "false");
		db.close();
	}
	
	private void nextQuestion() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				x = 0;
				if(db.countGetAnswered()==25) {
					resourcesManager.congratulations.play();
					createCongratsScene();
					CountItPanel.this.setChildScene(congratsScene, false, true, true);
				} else {
					if(questionSet == 28 || questionSet+x>=28) {
						while(db.countItIsAnswered(0+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(0+x);
					} else {
						while(db.countItIsAnswered(questionSet+x).compareTo("true")==0) {
							x++;
						}
						getQuestionIndex(questionSet+x);
					}
					SceneManager.getInstance().loadCountItPanelScene();	
				}
					
			}
		}));
	}
	
	private void createCongratsScene() {
		congratsScene = new CameraScene(camera);
		
		congratsPanel = new Sprite(400, 240, resourcesManager.congratsPanel, vbom);
		congratsScene.attachChild(congratsPanel);
		congratsPanel.setZIndex(0);
		
		stars = new Sprite(80, 370, resourcesManager.congratsStars, vbom);
		congratsScene.attachChild(stars);
		stars.setZIndex(1);
		stars.registerEntityModifier(new ScaleModifier(0.3f, 0f, 1.0f) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				pop = new Sprite(700, 170, resourcesManager.congratsPop, vbom);
				congratsScene.attachChild(pop);
				pop.setZIndex(1);
				pop.registerEntityModifier(new ScaleModifier(0.3f, 0f, 1.0f) {
					@Override
					protected void onModifierFinished(IEntity pItem) {
						
						viewProg = new TiledSprite(400, 100, resourcesManager.viewProgress, vbom) {
							@Override
							public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
								switch (pSceneTouchEvent.getAction()) {
								case TouchEvent.ACTION_DOWN:
									this.setScale(0.9f);
									this.setCurrentTileIndex(1);
									break;

								case TouchEvent.ACTION_UP:
									resourcesManager.click.play();
									SceneManager.getInstance().loadProgressScene();
									break;
								}
								return true;
							}
							
						};
						congratsScene.registerTouchArea(viewProg);
						congratsScene.attachChild(viewProg);
						viewProg.setZIndex(1);
						viewProg.registerEntityModifier(new ScaleModifier(0.3f, 0f, 1.0f));
					}			
				}); 
			}		
		});
		
		congratsScene.sortChildren();
		congratsScene.setBackgroundEnabled(false);
	}
	
	private int setHowManyCorrectObject() {
		if(questionSet == 0) cnt = 3;
		
		else if(questionSet == 1) cnt = 4;
		
		else if(questionSet == 2) cnt = 4;
		
		else if(questionSet == 3) cnt = 4;
		
		else if(questionSet == 4) cnt = 5;
		
		else if(questionSet == 6) cnt = 2;
		
		else if(questionSet == 7) cnt = 2;
		
		else if(questionSet == 8) cnt = 3;
		
		else if(questionSet == 9) cnt = 3;
		
		else if(questionSet == 10) cnt = 4;
		//
		
		else if(questionSet == 12) cnt = 5;
		
		else if(questionSet == 13) cnt = 4;
		
		else if(questionSet == 14) cnt = 1;
		
		else if(questionSet == 15) cnt = 5;
		
		else if(questionSet == 16) cnt = 3;
		
		//
		
		else if(questionSet == 18) cnt = 4;
		
		else if(questionSet == 19) cnt = 6;
		
		else if(questionSet == 20) cnt = 3;
		
		else if(questionSet == 21) cnt = 8;
		
		else if(questionSet == 22) cnt = 1;
		
		//
		
		else if(questionSet == 24) cnt = 8;
		
		else if(questionSet == 25) cnt = 9;
		
		else if(questionSet == 26) cnt = 5;
		
		else if(questionSet == 27) cnt = 4;
		
		else if(questionSet == 28) cnt = 8;
		return cnt;
	}
	
	private int setHowManyOtherObject() { 
		if(questionSet == 0) cnt = 2;
		
		else if(questionSet == 1) cnt = 5;
		
		else if(questionSet == 2) cnt = 1;
		
		else if(questionSet == 3) cnt = 4;
		
		else if(questionSet == 4) cnt = 2;
		//
		
		else if(questionSet == 6) cnt = 2;
		else if(questionSet == 7) cnt = 6;
		else if(questionSet == 8) cnt = 4;
		else if(questionSet == 9) cnt = 3;
		else if(questionSet == 10) cnt = 2;
		//
		
		else if(questionSet == 12) cnt = 2;
		else if(questionSet == 13) cnt = 4;
		else if(questionSet == 14) cnt = 5;
		else if(questionSet == 15) cnt = 1;
		else if(questionSet == 16) cnt = 2;
		
		//
		
		else if(questionSet == 18) cnt = 4;
		else if(questionSet == 19) cnt = 6;
		else if(questionSet == 20) cnt = 2;
		else if(questionSet == 21) cnt = 6;
		else if(questionSet == 22) cnt = 3;
		
		//
		else if(questionSet == 24) cnt = 4;
		else if(questionSet == 25) cnt = 2;
		else if(questionSet == 26) cnt = 1;
		else if(questionSet == 27) cnt = 4;
		else if(questionSet == 28) cnt = 3;
		return cnt;
	}
	
	// TEXTURES
	private ITextureRegion question() {
		if(questionSet == 0) r = resourcesManager.countItQuestion1;
		
		else if (questionSet == 1) r = resourcesManager.countItQuestion2;
		
		else if (questionSet == 2) r = resourcesManager.countItQuestion3;
		
		else if (questionSet == 3) r = resourcesManager.countItQuestion4;
		
		else if (questionSet == 4) r = resourcesManager.countItQuestion5;
		
		//
		
		else if (questionSet == 6) r = resourcesManager.countItQuestion6;
		else if (questionSet == 7) r = resourcesManager.countItQuestion7;
		else if (questionSet == 8) r = resourcesManager.countItQuestion8;
		else if (questionSet == 9) r = resourcesManager.countItQuestion9;
		else if (questionSet == 10) r = resourcesManager.countItQuestion10;
		//
		
		else if (questionSet == 12) r = resourcesManager.countItQuestion11;
		else if (questionSet == 13) r = resourcesManager.countItQuestion12;
		else if (questionSet == 14) r = resourcesManager.countItQuestion13;
		else if (questionSet == 15) r = resourcesManager.countItQuestion14;
		else if (questionSet == 16) r = resourcesManager.countItQuestion15;
		//
		
		else if (questionSet == 18) r = resourcesManager.countItQuestion16;
		else if (questionSet == 19) r = resourcesManager.countItQuestion17;
		else if (questionSet == 20) r = resourcesManager.countItQuestion18;
		else if (questionSet == 21) r = resourcesManager.countItQuestion19;
		else if (questionSet == 22) r = resourcesManager.countItQuestion20;
		//
		
		else if (questionSet == 24) r = resourcesManager.countItQuestion21;
		else if (questionSet == 25) r = resourcesManager.countItQuestion22;
		else if (questionSet == 26) r = resourcesManager.countItQuestion23;
		else if (questionSet == 27) r = resourcesManager.countItQuestion24;
		else if (questionSet == 28) r = resourcesManager.countItQuestion25;
		
			
		return r;
	}
	
	private ITextureRegion correctClue() {
		if(questionSet == 0) r = resourcesManager.countItClueTriangleTexture;
		
		else if (questionSet == 1) r = resourcesManager.countItClueCirceTexture;
		
		else if(questionSet == 2) r = resourcesManager.countItClueSquareTexture;
		
		else if(questionSet == 3) r = resourcesManager.countItClueRectangleTexture;
		
		else if(questionSet == 4) r = resourcesManager.countItClueDiamondTexture;
		//
		else if(questionSet == 6) r = resourcesManager.countItClueLollipopTexture;
		
		else if(questionSet == 7) r = resourcesManager.countItClueMoonTexture;
		
		else if(questionSet == 8) r = resourcesManager.countItClueButterflyTexture;
		
		else if(questionSet == 9) r = resourcesManager.countItClueAppleTexure;
		
		else if(questionSet == 10) r = resourcesManager.countItClueBananaTexture;
		
		//
		
		else if(questionSet == 12) r = resourcesManager.countItClueTurtleTexture;
		
		else if(questionSet == 13) r = resourcesManager.countItClueCookieTexture;
		
		else if(questionSet == 14) r = resourcesManager.countItClueFanTexture;
		
		else if(questionSet == 15) r = resourcesManager.countItClueCarTexure;
		
		else if(questionSet == 16) r = resourcesManager.countItClueRacconTexture;
		
		//
		// 18 change to bees
		else if(questionSet == 18) r = resourcesManager.countItClueFanTexture;
		
		else if(questionSet == 19) r = resourcesManager.countItClueEggTexture;
		
		else if(questionSet == 20) r = resourcesManager.countItClueKiteTexture;
		
		else if(questionSet == 21) r = resourcesManager.countItClueMilkTexture;
		//22 change to carabao
		else if(questionSet == 22) r = resourcesManager.countItClueCarabaoTexture;
		
		else if(questionSet == 24) r = resourcesManager.countItClueSnowmanTexture;
		
		else if(questionSet == 25) r = resourcesManager.countItClueRibonTexture;
		
		else if(questionSet == 26) r = resourcesManager.countItClueElephantTexture;
		//27 change to dolphin
		else if(questionSet == 27) r = resourcesManager.countItClueDolphinTexture;
		
		else if(questionSet == 28) r = resourcesManager.countItClueCakeTexture;
		return r;
	}
	
	private ITextureRegion otherClue() {
		if(questionSet == 0) r = resourcesManager.countItClueSquareTexture;
		else if(questionSet == 1) r = resourcesManager.countItClueSquareTexture;
		else if(questionSet == 2) r = resourcesManager.countItClueFanTexture;
		else if(questionSet == 3) r = resourcesManager.countItClueSquareTexture;
		else if(questionSet == 4) r = resourcesManager.countItClueSquareTexture;
		//
		else if(questionSet == 6) r = resourcesManager.countItClueCandyTexture;
		else if(questionSet == 7) r = resourcesManager.countItClueStarTexture;
		else if(questionSet == 8) r = resourcesManager.countItClueBugTexture;
		else if(questionSet == 9) r = resourcesManager.countItClueStrawberryTexture;
		else if(questionSet == 10) r = resourcesManager.countItClueMangoTexture;
		//
		else if(questionSet == 12) r = resourcesManager.countItClueFrogTexture;
		else if(questionSet == 13) r = resourcesManager.countItClueIcecreamTexture;
		else if(questionSet == 14) r = resourcesManager.countItClueHorseTexture;
		else if(questionSet == 15) r = resourcesManager.countItClueAirplaneTexture;
		else if(questionSet == 16) r = resourcesManager.countItClueKoalaTexture;
		//
		else if(questionSet == 18) r = resourcesManager.countItClueBeesTexture;
		else if(questionSet == 19) r = resourcesManager.countItClueFanTexture;
		else if(questionSet == 20) r = resourcesManager.coutItClueBirdTexture;
		//milk to BREAD <-- ako nagpalit. 
		else if(questionSet == 21) r = resourcesManager.countItClueBreadTexture;
		else if(questionSet == 22) r = resourcesManager.countItClueHorseTexture;
		//
		else if(questionSet == 24) r = resourcesManager.countItClueIglooTexture;
		else if(questionSet == 25) r = resourcesManager.countItCluePencilTexture;
		else if(questionSet == 26) r = resourcesManager.countItClueLionTexture;
		else if(questionSet == 27) r = resourcesManager.countItClueCakeTexture;
		else if(questionSet == 28) r = resourcesManager.countItClueBreadTexture;
		
		return r;
	}
	
	private ITextureRegion correctObject() {
		if(questionSet == 0) r = resourcesManager.countItObjectTriangle;
		else if(questionSet == 1) r = resourcesManager.countItObjectCircle;
		else if(questionSet == 2) r = resourcesManager.countItObjectSquare;
		else if(questionSet == 3) r = resourcesManager.countItObjectRectangle;
		else if(questionSet == 4) r = resourcesManager.countItObjectDiamond;
		//
		else if(questionSet == 6) r = resourcesManager.countItObjectLollipop;
		else if(questionSet == 7) r = resourcesManager.countItObjectMoon;
		else if(questionSet == 8) r = resourcesManager.countItObjectButterfly;
		else if(questionSet == 9) r = resourcesManager.countItObjectApple;
		else if(questionSet == 10) r = resourcesManager.countItObjectBanana;
		//
		else if(questionSet == 12) r = resourcesManager.countItObjectTurtle;
		else if(questionSet == 13) r = resourcesManager.countItObjectCookie;
		else if(questionSet == 14) r = resourcesManager.countItObjectFan;
		else if(questionSet == 15) r = resourcesManager.countItObjectCar;
		else if(questionSet == 16) r = resourcesManager.countItObjectRaccoon;
		//
		else if(questionSet == 18) r = resourcesManager.countItObjectBee;
		else if(questionSet == 19) r = resourcesManager.countItObjectEgg;
		else if(questionSet == 20) r = resourcesManager.countItObjectKite;
		else if(questionSet == 21) r = resourcesManager.countItObjectMilk;
		else if(questionSet == 22) r = resourcesManager.countItObjectCarabao;
		//
		else if(questionSet == 24) r = resourcesManager.countItObjectSnowman;
		else if(questionSet == 25) r = resourcesManager.countItObjectRibbon;
		else if(questionSet == 26) r = resourcesManager.countItObjectElephant;
		else if(questionSet == 27) r = resourcesManager.countItObjectDolphin;
		else if(questionSet == 28) r = resourcesManager.countItObjectCake;
		

		
		return r;
	}
	
	private ITextureRegion otherObject() {
		if(questionSet == 0) r = resourcesManager.countItObjectSquare;
		
		else  if(questionSet == 1) r = resourcesManager.countItObjectSquare;
		
		else  if(questionSet == 2) r = resourcesManager.countItObjectFan;

		else if(questionSet == 3) r = resourcesManager.countItObjectSquare;
		else if(questionSet == 4) r = resourcesManager.countItObjectSquare;
		//
		else if(questionSet == 6) r = resourcesManager.countItObjectCandy;
		else if(questionSet == 7) r = resourcesManager.countItObjectStar;
		else if(questionSet == 8) r = resourcesManager.countItObjectBugs;
		else if(questionSet == 9) r = resourcesManager.countItObjectStrawberry;
		else if(questionSet == 10) r = resourcesManager.countItObjectMango;
		//
		else if(questionSet == 12) r = resourcesManager.countItObjectFrog;
		else if(questionSet == 13) r = resourcesManager.countItObjectIcecream;
		else if(questionSet == 14) r = resourcesManager.countItObjectHat;
		else if(questionSet == 15) r = resourcesManager.countItObjectAirplane;
		else if(questionSet == 16) r = resourcesManager.countItObjectKoala;
		//
		else if(questionSet == 18) r = resourcesManager.countItObjectFan;
		else if(questionSet == 19) r = resourcesManager.countItObjectFan;
		else if(questionSet == 20) r = resourcesManager.countItObjectBird;
		//bird to cow
		else if(questionSet == 21) r = resourcesManager.countItObjectBread;
		else if(questionSet == 22) r = resourcesManager.countItObjectHat;
		//
		else if(questionSet == 24) r = resourcesManager.countItObjectIgloo;
		else if(questionSet == 25) r = resourcesManager.countItObjectPencil;
		else if(questionSet == 26) r = resourcesManager.countItObjectLion;
		else if(questionSet == 27) r = resourcesManager.countItObjectCake;
		else if(questionSet == 28) r = resourcesManager.countItObjectBread;
		
		return r;
	}
	
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion tr = null;
		if(questionSet == 0) tr = resourcesManager.texture3;
		else if (questionSet == 1) tr = resourcesManager.texture4;
		else if (questionSet == 2) tr = resourcesManager.texture4;
		else if (questionSet == 3) tr = resourcesManager.texture4;
		else if (questionSet == 4) tr = resourcesManager.texture5;
		//
		else if (questionSet == 6) tr = resourcesManager.texture2;
		else if (questionSet == 7) tr = resourcesManager.texture2;
		else if (questionSet == 8) tr = resourcesManager.texture3;
		else if (questionSet == 9) tr = resourcesManager.texture3;
		else if (questionSet == 10) tr = resourcesManager.texture4;
		//
		else if (questionSet == 12) tr = resourcesManager.texture5;
		else if (questionSet == 13) tr = resourcesManager.texture4;
		else if (questionSet == 14) tr = resourcesManager.texture1;
		else if (questionSet == 15) tr = resourcesManager.texture5;
		else if (questionSet == 16) tr = resourcesManager.texture3;
		//
		else if (questionSet == 18) tr = resourcesManager.texture4;
		else if (questionSet == 19) tr = resourcesManager.texture6;
		else if (questionSet == 20) tr = resourcesManager.texture3;
		else if (questionSet == 21) tr = resourcesManager.texture8;
		else if (questionSet == 22) tr = resourcesManager.texture1;
		////
		else if (questionSet == 24) tr = resourcesManager.texture8;
		else if (questionSet == 25) tr = resourcesManager.texture9;
		else if (questionSet == 26) tr = resourcesManager.texture5;
		else if (questionSet == 27) tr = resourcesManager.texture4;
		else if (questionSet == 28) tr = resourcesManager.texture8;
		
		
		return tr;
	}
	
	private ITextureRegion choice1() {
		if(questionSet == 0) r = resourcesManager.texture4;
		else if(questionSet == 1) r = resourcesManager.texture6;
		else if(questionSet == 2) r = resourcesManager.texture5;
		else if(questionSet == 3) r = resourcesManager.texture20;
		else if(questionSet == 4) r = resourcesManager.texture17;
		//
		else if(questionSet == 6) r = resourcesManager.texture12;
		else if(questionSet == 7) r = resourcesManager.texture8;
		else if(questionSet == 8) r = resourcesManager.texture4;
		else if(questionSet == 9) r = resourcesManager.texture9;
		else if(questionSet == 10) r = resourcesManager.texture16;
		//
		else if(questionSet == 12) r = resourcesManager.texture13;
		else if(questionSet == 13) r = resourcesManager.texture11;
		else if(questionSet == 14) r = resourcesManager.texture19;
		else if(questionSet == 15) r = resourcesManager.texture20;
		else if(questionSet == 16) r = resourcesManager.texture19;
		//
		else if(questionSet == 18) r = resourcesManager.texture14;
		else if(questionSet == 19) r = resourcesManager.texture16;
		else if(questionSet == 20) r = resourcesManager.texture19;
		else if(questionSet == 21) r = resourcesManager.texture13;
		else if(questionSet == 22) r = resourcesManager.texture5;
		//
		else if(questionSet == 24) r = resourcesManager.texture2;
		else if(questionSet == 25) r = resourcesManager.texture4;
		else if(questionSet == 26) r = resourcesManager.texture20;
		else if(questionSet == 27) r = resourcesManager.texture9;
		else if(questionSet == 28) r = resourcesManager.texture20;
		
		return r;
	}
	
	private ITextureRegion choice2() {
		if(questionSet == 0) r = resourcesManager.texture1;
		else if(questionSet == 1) r = resourcesManager.texture2;
		else if(questionSet == 2) r = resourcesManager.texture3;
		else if(questionSet == 3) r = resourcesManager.texture5;
		else if(questionSet == 4) r = resourcesManager.texture3;
		//
		else if(questionSet == 6) r = resourcesManager.texture11;
		else if(questionSet == 7) r = resourcesManager.texture18;
		else if(questionSet == 8) r = resourcesManager.texture15;
		else if(questionSet == 9) r = resourcesManager.texture17;
		else if(questionSet == 10) r = resourcesManager.texture10;
		//
		else if(questionSet == 12) r = resourcesManager.texture9;
		else if(questionSet == 13) r = resourcesManager.texture8;
		else if(questionSet == 14) r = resourcesManager.texture6;
		else if(questionSet == 15) r = resourcesManager.texture4;
		else if(questionSet == 16) r = resourcesManager.texture7;
		//
		else if(questionSet == 18) r = resourcesManager.texture12;
		else if(questionSet == 19) r = resourcesManager.texture18;
		else if(questionSet == 20) r = resourcesManager.texture15;
		else if(questionSet == 21) r = resourcesManager.texture16;
		else if(questionSet == 22) r = resourcesManager.texture13;
		//
		else if(questionSet == 24) r = resourcesManager.texture14;
		else if(questionSet == 25) r = resourcesManager.texture15;
		else if(questionSet == 26) r = resourcesManager.texture16;
		else if(questionSet == 27) r = resourcesManager.texture20;
		else if(questionSet == 28) r = resourcesManager.texture12;
		return r;
	}
	
	private ITextureRegion choice3() {
		if(questionSet == 0) r = resourcesManager.texture2;
		
		else if(questionSet == 1) r = resourcesManager.texture3;
		
		else if(questionSet == 2) r = resourcesManager.texture6;
		
		else if(questionSet == 3) r = resourcesManager.texture8;
		else if(questionSet == 4) r = resourcesManager.texture7;
		//
		else if(questionSet == 6) r = resourcesManager.texture6;
		else if(questionSet == 7) r = resourcesManager.texture3;
		else if(questionSet == 8) r = resourcesManager.texture2;
		else if(questionSet == 9) r = resourcesManager.texture6;
		else if(questionSet == 10) r = resourcesManager.texture3;
		//
		else if(questionSet == 12) r = resourcesManager.texture7;
		else if(questionSet == 13) r = resourcesManager.texture2;
		else if(questionSet == 14) r = resourcesManager.texture14;
		else if(questionSet == 15) r = resourcesManager.texture15;
		else if(questionSet == 16) r = resourcesManager.texture9;
		//
		else if(questionSet == 18) r = resourcesManager.texture11;
		else if(questionSet == 19) r = resourcesManager.texture19;
		else if(questionSet == 20) r = resourcesManager.texture16;
		else if(questionSet == 21) r = resourcesManager.texture13;
		else if(questionSet == 22) r = resourcesManager.texture11;
		//
		else if(questionSet == 24) r = resourcesManager.texture11;
		else if(questionSet == 25) r = resourcesManager.texture16;
		else if(questionSet == 26) r = resourcesManager.texture11;
		else if(questionSet == 27) r = resourcesManager.texture11;
		else if(questionSet == 28) r = resourcesManager.texture4;
		
		return r;
	}
	
	private ITextureRegion choice4() {
		if(questionSet == 0) r = resourcesManager.texture6;
		
		else if(questionSet == 1) r = resourcesManager.texture6;
		
		else if(questionSet == 2) r = resourcesManager.texture8;
		
		else if(questionSet == 3) r = resourcesManager.texture1;
		else if(questionSet == 4) r = resourcesManager.texture8;
		//
		else if(questionSet == 6) r = resourcesManager.texture5;
		else if(questionSet == 7) r = resourcesManager.texture8;
		else if(questionSet == 8) r = resourcesManager.texture5;
		else if(questionSet == 9) r = resourcesManager.texture6;
		else if(questionSet == 10) r = resourcesManager.texture17;
		//
		else if(questionSet == 12) r = resourcesManager.texture19;
		else if(questionSet == 13) r = resourcesManager.texture3;
		else if(questionSet == 14) r = resourcesManager.texture4;
		else if(questionSet == 15) r = resourcesManager.texture2;
		else if(questionSet == 16) r = resourcesManager.texture8;
		//
		else if(questionSet == 18) r = resourcesManager.texture2;
		else if(questionSet == 19) r = resourcesManager.texture8;
		else if(questionSet == 20) r = resourcesManager.texture5;
		else if(questionSet == 21) r = resourcesManager.texture10;
		else if(questionSet == 22) r = resourcesManager.texture3;
		//
		else if(questionSet == 24) r = resourcesManager.texture4;
		else if(questionSet == 25) r = resourcesManager.texture5;
		else if(questionSet == 26) r = resourcesManager.texture1;
		else if(questionSet == 27) r = resourcesManager.texture2;
		else if(questionSet == 28) r = resourcesManager.texture2;
		return r;
	}
	
	// POSTIONS
	/*
	 * FROM LEFT TO RIGHT:
	 * 200, 300, 400, 500, 600
	 */
	
	private int correctSpritePosition() {
		if(questionSet == 0) pos = 200;
		
		else if(questionSet == 1) pos = 400;
		
		else if(questionSet == 2) pos = 600;
		
		else if(questionSet == 3) pos = 200;
		else if(questionSet == 4) pos = 200;
		//
		else if(questionSet == 6) pos = 400;
		else if(questionSet == 7) pos = 200;
		else if(questionSet == 8) pos = 300;
		else if(questionSet == 9) pos = 200;
		else if(questionSet == 10) pos = 500;
		//
		else if(questionSet == 12) pos = 200;
		else if(questionSet == 13) pos = 300;
		else if(questionSet == 14) pos = 500;
		else if(questionSet == 15) pos = 600;
		else if(questionSet == 16) pos = 200;
		//
		else if(questionSet == 18) pos = 200;
		else if(questionSet == 19) pos = 400;
		else if(questionSet == 20) pos = 300;
		else if(questionSet == 21) pos = 400;
		else if(questionSet == 22) pos = 200;
		
		else if(questionSet == 24) pos = 400;
		else if(questionSet == 25) pos = 300;
		else if(questionSet == 26) pos = 200;
		else if(questionSet == 27) pos = 600;
		else if(questionSet == 28) pos = 200;
		
		return pos;
	}
	
	private int setChoice1Position() {
		if(questionSet == 0) pos = 300;
		
		else if(questionSet == 1) pos = 300;

		else if(questionSet == 2) pos = 300;
		
		else if(questionSet == 3) pos = 300;
		else if(questionSet == 4) pos = 300;
		//
		else if(questionSet == 6) pos = 300;
		else if(questionSet == 7) pos = 300;
		else if(questionSet == 8) pos = 200;
		else if(questionSet == 9) pos = 300;
		else if(questionSet == 10) pos = 300;
		//
		else if(questionSet == 12) pos = 300;
		else if(questionSet == 13) pos = 200;
		else if(questionSet == 14) pos = 300;
		else if(questionSet == 15) pos = 300;
		else if(questionSet == 16) pos = 300;
		//
		else if(questionSet == 18) pos = 300;
		else if(questionSet == 19) pos = 300;
		else if(questionSet == 20) pos = 400;
		else if(questionSet == 21) pos = 200;
		else if(questionSet == 22) pos = 300;
		
		else if(questionSet == 24) pos = 300;
		else if(questionSet == 25) pos = 200;
		else if(questionSet == 26) pos = 300;
		else if(questionSet == 27) pos = 300;
		else if(questionSet == 28) pos = 300;
		return pos;
	}
	
	private int setChoice2Position() {
		if(questionSet == 0) pos = 400;
		
		else if(questionSet == 1) pos = 200;
		
		else if(questionSet == 2) pos = 400;
		
		else if(questionSet == 3) pos = 400;
		else if(questionSet == 4) pos = 400;
		//
		else if(questionSet == 6) pos = 200;
		else if(questionSet == 7) pos = 400;
		else if(questionSet == 8) pos = 400;
		else if(questionSet == 9) pos = 400;
		else if(questionSet == 10) pos = 400;
		//
		else if(questionSet == 12) pos = 400;
		else if(questionSet == 13) pos = 400;
		else if(questionSet == 14) pos = 400;
		else if(questionSet == 15) pos = 400;
		else if(questionSet == 16) pos = 400;
		//
		else if(questionSet == 18) pos = 400;
		else if(questionSet == 19) pos = 200;
		else if(questionSet == 20) pos = 200;
		else if(questionSet == 21) pos = 300;
		else if(questionSet == 22) pos = 400;
		
		else if(questionSet == 24) pos = 200;
		else if(questionSet == 25) pos = 400;
		else if(questionSet == 26) pos = 400;
		else if(questionSet == 27) pos = 400;
		else if(questionSet == 28) pos = 400;
		
		return pos;
	}
	
	private int setChoice3Position() {
		if(questionSet == 0) pos = 500;
		
		else if(questionSet == 1) pos = 500;
		
		else if(questionSet == 2) pos = 500;
		
		else if(questionSet == 3) pos = 500;
		else if(questionSet == 4) pos = 500;
		//
		else if(questionSet == 6) pos = 500;
		else if(questionSet == 7) pos = 500;
		else if(questionSet == 8) pos = 500;
		else if(questionSet == 9) pos = 500;
		else if(questionSet == 10) pos = 200;
		//
		else if(questionSet == 12) pos = 500;
		else if(questionSet == 13) pos = 500;
		else if(questionSet == 14) pos = 200;
		else if(questionSet == 15) pos = 500;
		else if(questionSet == 16) pos = 500;
		//
		else if(questionSet == 18) pos = 500;
		else if(questionSet == 19) pos = 500;
		else if(questionSet == 20) pos = 500;
		else if(questionSet == 21) pos = 500;
		else if(questionSet == 22) pos = 500;
		
		else if(questionSet == 24) pos = 500;
		else if(questionSet == 25) pos = 500;
		else if(questionSet == 26) pos = 500;
		else if(questionSet == 27) pos = 200;
		else if(questionSet == 28) pos = 500;
		
		return pos;
	}
	
	private int setChoice4Position() {
		if(questionSet == 0) pos = 600;
		
		else if(questionSet == 1) pos = 600;
		
		else if(questionSet == 2) pos = 200;
		
		else if(questionSet == 3) pos = 600;
		else if(questionSet == 4) pos = 600;
		//
		else if(questionSet == 6) pos = 600;
		else if(questionSet == 7) pos = 600;
		else if(questionSet == 8) pos = 600;
		else if(questionSet == 9) pos = 600;
		else if(questionSet == 10) pos = 600;
		//
		else if(questionSet == 12) pos = 600;
		else if(questionSet == 13) pos = 600;
		else if(questionSet == 14) pos = 600;
		else if(questionSet == 15) pos = 200;
		else if(questionSet == 16) pos = 600;
		//
		else if(questionSet == 18) pos = 600;
		else if(questionSet == 19) pos = 600;
		else if(questionSet == 20) pos = 600;
		else if(questionSet == 21) pos = 600;
		else if(questionSet == 22) pos = 600;
		
		else if(questionSet == 24) pos = 600;
		else if(questionSet == 25) pos = 600;
		else if(questionSet == 26) pos = 600;
		else if(questionSet == 27) pos = 500;
		else if(questionSet == 28) pos = 600;
		
		return pos;
	}
}
