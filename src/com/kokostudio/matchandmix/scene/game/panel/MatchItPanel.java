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

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class MatchItPanel extends BaseScene {
	
	// POSITIONS 
	/*
	 * (590,370) | (715,370)
	 * (590,240) | (715,240)
	 * (590,115) | (715,115)
	 * */
	
	private static int questionSet;
	
	private ITextureRegion r;
	private int pos;
	
	private float totalLives;
	private int lives;
	private float liveSpent;
	private float rate;
	
	// SPRITES
	private TiledSprite question;
	private Sprite c1, c2, c3, c4, c5;
	private Sprite choiceBox;
	private Sprite correctSprite;
	
	private Sprite BG;
	private Sprite questionPlank;
	private TiledSprite back;
	
	private myDatabase db;
	
	private int x;
	
	private Sprite thatsCorrect;
	private Sprite thatsWrong;
	
	private Sprite sLife;
	private TiledSprite sLifeValue;
	
	private CameraScene triviaScene;
	private Sprite triviaPanel;
	private Sprite trivia;
	private TiledSprite OK;
	
	private CameraScene tryScene;
	private Sprite tryMsg;
	
	private CameraScene htpScene;
	private TiledSprite htp;
	
	private int currentTile;
	
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
		sortChildren();
		createTryAgainScene();
		showTrivia();
		
		if(db.checkIsFirstTime(0).compareTo("true")==0) {
			createHowToScene();
			MatchItPanel.this.setChildScene(htpScene, false, true, true);
		}
		
		thatsCorrect.setAlpha(0f);
		thatsWrong.setAlpha(0f);
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MATCHITPANEL;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		
	}
	
	// =======================================================================================
	// CLASS LOGIC
	// =======================================================================================
	private void createBackground() {
		BG = new Sprite(400, 240, resourcesManager.matchItBGTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		questionPlank = new Sprite(260, 60, resourcesManager.match_questionPlank, vbom);
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
					
					// unload MATCH IT panel textures
					//ResourcesManager.getInstance().unloadMatchItPanelTextures();
					// SET THE SCENE TO MATCH IT
					SceneManager.getInstance().loadMatchItScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);	
		
		thatsCorrect = new Sprite(410, 445, resourcesManager.thatsCorrectTexture, vbom);
		thatsCorrect.setZIndex(2);
		attachChild(thatsCorrect);
		thatsWrong = new Sprite(410, 445, resourcesManager.thatsWrongTexture, vbom);
		thatsWrong.setZIndex(2);
		attachChild(thatsWrong);
		// LIFE SPRITE AND TEXT
		sLife = new Sprite(690, 445, resourcesManager.lifeTexture, vbom);
		attachChild(sLife);
		sLifeValue = new TiledSprite(750, 450, resourcesManager.lifeValueTexture, vbom);
		sLifeValue.setCurrentTileIndex(lives);
		attachChild(sLifeValue);
	}
	
	private void createQuestions() {
		question = new TiledSprite(250, 250, question(), vbom);
		attachChild(question);
		question.setZIndex(0);
	}
	
	private void createChoices() {	
		// CHOICE BOX
		choiceBox = new Sprite(650, 240, resourcesManager.matchItChoiceBox, vbom);
		attachChild(choiceBox);
		
		// CHOICES
		correctSprite = new Sprite(correctSpriteXPosition(), correctSpriteYPosition(), correctAnswerSprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				correctSprite.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					correctSprite.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					correctSprite.setScale(1.0f);
					checkPosition(correctSprite, pSceneTouchEvent.getX(), pSceneTouchEvent.getY(), correctSpriteXPosition(), correctSpriteYPosition());
					break;
				}
				return true;
			}
		};
		registerTouchArea(correctSprite);
		attachChild(correctSprite);
		correctSprite.setZIndex(1);
		
		c1 = new Sprite(setChoice1XPosition(), setChoice1YPosition() ,Choice1(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c1.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c1.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c1.setScale(1.0f);
					checkPosition(c1, 0, 0, setChoice1XPosition(), setChoice1YPosition());
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c1);
		attachChild(c1);
		c1.setZIndex(1);
		
		c2 = new Sprite(setChoice2XPosition(), setChoice2YPosition() ,Choice2(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c2.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c2.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c2.setScale(1.0f);
					checkPosition(c2, 0, 0, setChoice2XPosition(), setChoice2YPosition());
					break;
				}
				return true;
			}
		};
		registerTouchArea(c2);
		attachChild(c2);
		c2.setZIndex(1);
		
		c3 = new Sprite(setChoice3XPosition(), setChoice3YPosition() ,Choice3(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c3.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c3.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c3.setScale(1.0f);
					checkPosition(c3, 0, 0, setChoice3XPosition(), setChoice3YPosition());
					break;
				}
				return true;
			}
		};
		registerTouchArea(c3);
		attachChild(c3);
		c3.setZIndex(1);
		
		c4 = new Sprite(setChoice4XPosition(), setChoice4YPosition() ,Choice4(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c4.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c4.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c4.setScale(1.0f);
					checkPosition(c4, 0, 0, setChoice4XPosition(), setChoice4YPosition());
					break;
				}
				return true;
			}
		};
		registerTouchArea(c4);
		attachChild(c4);
		c4.setZIndex(1);
		
		c5 = new Sprite(setChoice5XPosition(), setChoice5YPosition() ,Choice5(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c5.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c5.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c5.setScale(1.0f);
					checkPosition(c5, 0, 0, setChoice5XPosition(), setChoice5YPosition());
					break;
				}
				return true;
			}
		};
		registerTouchArea(c5);
		attachChild(c5);
		c5.setZIndex(1);
		
		
	}
	
	// ========================================================================================
	// DATABASE SECTION 
	// ========================================================================================
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	private void nextQuestion() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
					x = 0;
					if(db.matchGetAnswered()==25) {
						SceneManager.getInstance().loadMatchItScene();
					} else {
						if(questionSet == 28 || questionSet+x>=28) {
							while(db.matchItIsAnswered(0+x).compareTo("true")==0) {
								x++;
							}
							getQuestionIndex(0+x);
						} else {
							while(db.matchItIsAnswered(questionSet+x).compareTo("true")==0) {
								x++;
							}
							getQuestionIndex(questionSet+x);
						}
						SceneManager.getInstance().loadMatchItPanelScene();	
					
					}
						
				}
		}));
		
	}
	
	private void updateIsFirstTime() {
		db.updateIsFirstTime(0, "false");
		db.close();
	}
	
	
	// BACK TO POSITION
	private void checkPosition(Sprite sprite, float touchX, float touchY, int posX, int posY) {
		if(touchX < 300 && touchX  > 200 && touchY < 300 && touchY > 200) {
			sprite.detachSelf();
			lock();
			resourcesManager.correct.play();
			update(questionSet, "true");
			updateIsFirstTime();
			db.updateRate(0, computeRate());
			db.updateTry(0, 1);
			thatsCorrect.setAlpha(1.0f);
			MatchItPanel.this.setChildScene(triviaScene, false, true, true);
			
		} else { 
			// Back to Original Position
			sprite.setPosition(posX, posY);
			resourcesManager.wrong.play();
			lives--;
			liveSpent++;
			checkLives();
			sLifeValue.setCurrentTileIndex(lives);
			thatsWrong.registerEntityModifier(new AlphaModifier(0.8f, 1.0f, 0));
		}
	}
	
	private void checkLives() {
		if(lives == 0) {
			db.updateTry(0, 1);
			db.updateRate(0, computeRate());
			resourcesManager.sorry.play();
			MatchItPanel.this.setChildScene(tryScene, false, true, true);
		}
	}
	
	private float computeRate() {
		rate = (liveSpent / totalLives);
		return rate;
	}
	
	private void createHowToScene() {
		htpScene = new CameraScene(camera);
		currentTile = 0;
		
		htp = new TiledSprite(400, 240, resourcesManager.matchHTP, vbom);
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
					if (currentTile == 2 | currentTile ==1) {
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
					MatchItPanel.this.clearChildScene();
					break;
				}
				return true;
			}
			
		};
		OK.setVisible(false);
		htpScene.attachChild(OK);	
		
		htpScene.setBackgroundEnabled(false);
	}
	
	private void showTrivia() {
		triviaScene = new CameraScene(camera);
		
		triviaPanel = new Sprite(400, 240, resourcesManager.triviaPanel, vbom);
		triviaScene.attachChild(triviaPanel);
		triviaPanel.setZIndex(0);
		
		trivia = new Sprite(400, 240, trivia(), vbom); 
		triviaScene.attachChild(trivia);
		trivia.setZIndex(1);
		
		OK = new TiledSprite(400, 80, resourcesManager.triviaOK, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(0.9f);
					this.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					updateIsFirstTime();
					MatchItPanel.this.clearChildScene();
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
					MatchItPanel.this.clearChildScene();
					SceneManager.getInstance().loadMatchItScene();
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
	
	private String isAnswered(int id) {
		String s = db.matchItIsAnswered(id);
		db.close();
		return s;
	}
	
	private void update(int id, String s) {
		db.updateMatchIt(id, s);
		db.close();
	}
	
	private void checkStatus() {
		String cmp = db.matchItIsAnswered(questionSet);
		if(cmp.compareTo("true") == 0) {
			lock();
		} else return;
	}
	
	private void lock() {
		question.setCurrentTileIndex(1);
		correctSprite.detachSelf();
		unregisterTouchArea(correctSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
		unregisterTouchArea(c5);
	}
	
	// POSITIONS 
		/* (X, Y)	 | (X, Y)
		 * (590,375) | (715,370)
		 * (590,240) | (715,240)
		 * (590,115) | (715,115)
		 * */
	
		// CORRECT SPRITE X AND Y POSITION
		public int correctSpriteXPosition() {	
			if(questionSet == 0) pos = 715;
			
			else if(questionSet == 1) pos = 715;
			
			else if(questionSet == 2) pos = 715;
			
			else if(questionSet == 3) pos = 715;
			
			else if(questionSet == 4) pos = 590;
			
			else if(questionSet == 6) pos = 715;
			
			else if(questionSet == 7) pos = 715;
			
			else if(questionSet == 8) pos = 590;
			
			else if(questionSet == 9) pos = 590;
			
			else if(questionSet == 10) pos = 715;
			
			else if(questionSet == 12) pos = 590;
			
			else if(questionSet == 13) pos = 590;
			
			else if(questionSet == 14) pos = 715;
			
			else if(questionSet == 15) pos = 590;
			
			else if(questionSet == 16) pos = 715;
			
			else if(questionSet == 18) pos = 715;
			
			else if(questionSet == 19) pos = 590;
			
			else if(questionSet == 20) pos = 715;
			
			else if(questionSet == 21) pos = 590;
			
			else if(questionSet == 22) pos = 715;
			
			else if(questionSet == 24) pos = 715;
			
			else if(questionSet == 25) pos = 590;
			
			else if(questionSet == 26) pos = 715;
			
			else if(questionSet == 27) pos = 590;
		
			else if(questionSet == 28) pos = 590;
			return pos;
		}
		public int correctSpriteYPosition() {	
			if(questionSet == 0) pos = 240;
			
			else if(questionSet == 1) pos = 370;
			
			else if(questionSet == 2) pos = 370;
			
			else if(questionSet == 3) pos = 240;
			
			else if(questionSet == 4) pos = 370;
			
			else if(questionSet == 6) pos = 115;
			
			else if(questionSet == 7) pos = 240;
			
			else if(questionSet == 8) pos = 240;
			
			else if(questionSet == 9) pos = 370;
			
			else if(questionSet == 10) pos = 240;
			
			else if(questionSet == 12) pos = 115;
			
			else if(questionSet == 13) pos = 240;
			
			else if(questionSet == 14) pos = 115;
			
			else if(questionSet == 15) pos = 240;
			
			else if(questionSet == 16) pos = 115;
			
			else if(questionSet == 18) pos = 240;
			
			else if(questionSet == 19) pos = 240;
			
			else if(questionSet == 20) pos = 115;
			
			else if(questionSet == 21) pos = 370;
			
			else if(questionSet == 22) pos = 240;
			
			else if(questionSet == 24) pos = 370;
			
			else if(questionSet == 25) pos = 115;
			
			else if(questionSet == 26) pos = 240;
			
			else if(questionSet == 27) pos = 240;
			
			else if(questionSet == 28) pos = 115;
		
			return pos;
		}
		
		// CHOICE 1
		public int setChoice1XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 590;
			
			else if(questionSet == 2) pos = 590;
			
			else if(questionSet == 3) pos = 590;
			
			else if(questionSet == 4) pos = 590;
			
			else if(questionSet == 6) pos = 715;
			
			else if(questionSet == 7) pos = 715;
			
			else if(questionSet == 8) pos = 590;
			
			else if(questionSet == 9) pos = 590;
			
			else if(questionSet == 10) pos = 715;
			
			else if(questionSet == 12) pos = 590;
			
			else if(questionSet == 13) pos = 590;
			
			else if(questionSet == 14) pos = 590;
			
			else if(questionSet == 15) pos = 715;
			
			else if(questionSet == 16) pos = 715;
			
			else if(questionSet == 18) pos = 715;
			
			else if(questionSet == 19) pos = 590;
			
			else if(questionSet == 20) pos = 590;
			
			else if(questionSet == 21) pos = 590;
			
			else if(questionSet == 22) pos = 715;
			
			else if(questionSet == 24) pos = 715;
			
			else if(questionSet == 25) pos = 590;
			
			else if(questionSet == 26) pos = 715;
			
			else if(questionSet == 27) pos = 590;
	
			else if(questionSet == 28) pos = 590;
			return pos;
		}	
		public int setChoice1YPosition() {
			if(questionSet == 0) pos = 370;
			
			else if(questionSet == 1) pos = 370;
			
			else if(questionSet == 2) pos = 370;
			
			else if(questionSet == 3) pos = 370;
			
			else if(questionSet == 4) pos = 240;
			
			else if(questionSet == 6) pos = 240;
			
			else if(questionSet == 7) pos = 115;
			
			else if(questionSet == 8) pos = 370;
			
			else if(questionSet == 9) pos = 115;
			
			else if(questionSet == 10) pos = 115;
			
			else if(questionSet == 12) pos = 370;
			
			else if(questionSet == 13) pos = 115;
			
			else if(questionSet == 14) pos = 240;
			
			else if(questionSet == 15) pos = 240;
			
			else if(questionSet == 16) pos = 240;
			
			else if(questionSet == 18) pos = 370;
			
			else if(questionSet == 19) pos = 115;
			
			else if(questionSet == 20) pos = 240;
			
			else if(questionSet == 21) pos = 115;
			
			else if(questionSet == 22) pos = 115;
			
			else if(questionSet == 24) pos = 115;
			
			else if(questionSet == 25) pos = 240;
			
			else if(questionSet == 26) pos = 115;
			
			else if(questionSet == 27) pos = 370;
			
			else if(questionSet == 28) pos = 240;
			return pos;
		}
		
		// CHOICE 2
		public int setChoice2XPosition() {
			if(questionSet == 0) pos = 715;
			
			else if(questionSet == 1) pos = 590;
			
			else if(questionSet == 2) pos = 590;
			
			else if(questionSet == 3) pos = 715;
			
			else if(questionSet == 4) pos = 590;
			
			else if(questionSet == 6) pos = 715;
			
			else if(questionSet == 7) pos = 715;
			
			else if(questionSet == 8) pos = 590;
			
			else if(questionSet == 9) pos = 590;
			
			else if(questionSet == 10) pos = 715;
			
			else if(questionSet == 12) pos = 590;
			
			else if(questionSet == 13) pos = 590;
			
			else if(questionSet == 14) pos = 715;
			
			else if(questionSet == 15) pos = 590;
			
			else if(questionSet == 16) pos = 715;
			
			else if(questionSet == 18) pos = 715;
			
			else if(questionSet == 19) pos = 590;
			
			else if(questionSet == 20) pos = 590;
			
			else if(questionSet == 21) pos = 590;
			
			else if(questionSet == 22) pos = 715;
			
			else if(questionSet == 24) pos = 715;
			
			else if(questionSet == 25) pos = 590;
			
			else if(questionSet == 26) pos = 715;
			
			else if(questionSet == 27) pos = 715;
			
			else if(questionSet == 28) pos = 590;
			return pos;
		}
		public int setChoice2YPosition() {
			if(questionSet == 0) pos = 370;
			
			else if(questionSet == 1) pos = 240;
			
			else if(questionSet == 2) pos = 240;
			
			else if(questionSet == 3) pos = 370;
			
			else if(questionSet == 4) pos = 115;
			
			else if(questionSet == 6) pos = 370;
			
			else if(questionSet == 7) pos = 370;
			
			else if(questionSet == 8) pos = 115;
			
			else if(questionSet == 9) pos = 240;
			
			else if(questionSet == 10) pos = 370;
			
			else if(questionSet == 12) pos = 240;
			
			else if(questionSet == 13) pos = 370;
			
			else if(questionSet == 14) pos = 370;
			
			else if(questionSet == 15) pos = 370;
			
			else if(questionSet == 16) pos = 370;
			
			else if(questionSet == 18) pos = 115;
			
			else if(questionSet == 19) pos = 370;
			
			else if(questionSet == 20) pos = 370;
			
			else if(questionSet == 21) pos = 240;
			
			else if(questionSet == 22) pos = 370;
			
			else if(questionSet == 24) pos = 240;
			
			else if(questionSet == 25) pos = 370;
			
			else if(questionSet == 26) pos = 370;
	
			else if(questionSet == 27) pos = 115;
			
			else if(questionSet == 28) pos = 370;
			return pos;
		}
		
		// CHOICE 3
		public int setChoice3XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 715;
			
			else if(questionSet == 2) pos = 715;
			
			else if(questionSet == 3) pos = 590;
			
			else if(questionSet == 4) pos = 715;
			
			else if(questionSet == 6) pos = 590;
			
			else if(questionSet == 7) pos = 590;
			
			else if(questionSet == 8) pos = 715;
			
			else if(questionSet == 9) pos = 715;
			
			else if(questionSet == 10) pos = 590;
			
			else if(questionSet == 12) pos = 715;
			
			else if(questionSet == 13) pos = 715;
			
			else if(questionSet == 14) pos = 590;
			
			else if(questionSet == 15) pos = 590;
			
			else if(questionSet == 16) pos = 590;
			
			else if(questionSet == 18) pos = 590;
			
			else if(questionSet == 19) pos = 715;
			
			else if(questionSet == 20) pos = 590;
			
			else if(questionSet == 21) pos = 715;
			
			else if(questionSet == 22) pos = 590;
			
			else if(questionSet == 24) pos = 590;
			
			else if(questionSet == 25) pos = 715;
			
			else if(questionSet == 26) pos = 590;
			
			else if(questionSet == 27) pos = 590;
			
			else if(questionSet == 28) pos = 715;
			
			return pos;
		}
		public int setChoice3YPosition() {
			if(questionSet == 0) pos = 240;
			
			else if(questionSet == 1) pos = 240;
			
			else if(questionSet == 2) pos = 240;
			
			else if(questionSet == 3) pos = 240;
			
			else if(questionSet == 4) pos = 370;
			
			else if(questionSet == 6) pos = 370;
			
			else if(questionSet == 7) pos = 115;
			
			else if(questionSet == 8) pos = 370;
			
			else if(questionSet == 9) pos = 370;
			
			else if(questionSet == 10) pos = 115;
			
			else if(questionSet == 12) pos = 115;
			
			else if(questionSet == 13) pos = 115;
			
			else if(questionSet == 14) pos = 370;
			
			else if(questionSet == 15) pos = 115;
			
			else if(questionSet == 16) pos = 115;
			
			else if(questionSet == 18) pos = 115;
			
			else if(questionSet == 19) pos = 115;
			
			else if(questionSet == 20) pos = 115;
			
			else if(questionSet == 21) pos = 115;
			
			else if(questionSet == 22) pos = 115;
			
			else if(questionSet == 24) pos = 115;
			
			else if(questionSet == 25) pos = 115;
			
			else if(questionSet == 26) pos = 115;
			
			else if(questionSet == 27) pos = 115;
			
			else if(questionSet == 28) pos = 115;
			return pos;
		}
		
		// CHOICE 4
		public int setChoice4XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 590;
			
			else if(questionSet == 2) pos = 590;
			
			else if(questionSet == 3) pos = 590;
			
			else if(questionSet == 4) pos = 715;
			
			else if(questionSet == 6) pos = 590;
			
			else if(questionSet == 7) pos = 590;
			
			else if(questionSet == 8) pos = 715;
			
			else if(questionSet == 9) pos = 715;
			
			else if(questionSet == 10) pos = 590;
			
			else if(questionSet == 12) pos = 715;
			
			else if(questionSet == 13) pos = 715;
			
			else if(questionSet == 14) pos = 590;
			
			else if(questionSet == 15) pos = 715;
			
			else if(questionSet == 16) pos = 590;
			
			else if(questionSet == 18) pos = 590;
			
			else if(questionSet == 19) pos = 715;
			
			else if(questionSet == 20) pos = 715;
			
			else if(questionSet == 21) pos = 715;
			
			else if(questionSet == 22) pos = 590;
			
			else if(questionSet == 24) pos = 590;
			
			else if(questionSet == 25) pos = 715;
			
			else if(questionSet == 26) pos = 590;
			
			else if(questionSet == 27) pos = 715;
			
			else if(questionSet == 28) pos = 715;
			return pos;
		}
		public int setChoice4YPosition() {
			if(questionSet == 0) pos = 115;
			
			else if(questionSet == 1) pos = 115;
				
			else if(questionSet == 2) pos = 115;
			else if(questionSet == 3) pos = 115;
			
			else if(questionSet == 4) pos = 240;
			
			else if(questionSet == 6) pos = 240;
			
			else if(questionSet == 7) pos = 240;
			
			else if(questionSet == 8) pos = 240;
			
			else if(questionSet == 9) pos = 240;
			
			else if(questionSet == 10) pos = 240;
			
			else if(questionSet == 12) pos = 240;
			
			else if(questionSet == 13) pos = 240;
			
			else if(questionSet == 14) pos = 115;
			
			else if(questionSet == 15) pos = 370;
			
			else if(questionSet == 16) pos = 240;
			
			else if(questionSet == 18) pos = 240;
			
			else if(questionSet == 19) pos = 240;
			
			else if(questionSet == 20) pos = 240;
			
			else if(questionSet == 21) pos = 240;
			
			else if(questionSet == 22) pos = 240;
			
			else if(questionSet == 24) pos = 240;
			
			else if(questionSet == 25) pos = 240;
			
			else if(questionSet == 26) pos = 240;
			
			else if(questionSet == 27) pos = 240;
			
			
			else if(questionSet == 28) pos = 240;
		
			return pos;
		}
		
		// CHOICE 5
		public int setChoice5XPosition() {
			if(questionSet == 0) pos = 715;
			
			else if(questionSet == 1) pos = 715;
			
			else if(questionSet == 2) pos = 715;
			
			else if(questionSet == 3) pos = 715;
			
			else if(questionSet == 4) pos = 715;
			
			else if(questionSet == 6) pos = 590;
			
			else if(questionSet == 7) pos = 590;
			
			else if(questionSet == 8) pos = 715;
			
			else if(questionSet == 9) pos = 715;
			
			else if(questionSet == 10) pos = 590;
			
			else if(questionSet == 12) pos = 715;
			
			else if(questionSet == 13) pos = 715;
			
			else if(questionSet == 14) pos = 715;
			
			else if(questionSet == 15) pos = 715;
			
			else if(questionSet == 16) pos = 590;
			
			else if(questionSet == 18) pos = 590;
			
			else if(questionSet == 19) pos = 715;
			
			else if(questionSet == 20) pos = 715;
			
			else if(questionSet == 21) pos = 715;
			
			else if(questionSet == 22) pos = 590;
			
			else if(questionSet == 24) pos = 590;
			
			else if(questionSet == 25) pos = 715;
			
			else if(questionSet == 26) pos = 590;
			
			else if(questionSet == 27) pos = 715;
			
			else if(questionSet == 28) pos = 715;
			return pos;
		}
		
		public int setChoice5YPosition() {
			if(questionSet == 0) pos = 115;
			
			else if(questionSet == 1) pos = 115;
			
			else if(questionSet == 2) pos = 115;
			
			else if(questionSet == 3) pos = 115;
			
			else if(questionSet == 4) pos = 115;
			
			else if(questionSet == 6) pos = 115;
			
			else if(questionSet == 7) pos = 370;
			
			else if(questionSet == 8) pos = 115;
			
			else if(questionSet == 9) pos = 115;
			
			else if(questionSet == 10) pos = 370;
			
			else if(questionSet == 12) pos = 370;
			
			else if(questionSet == 13) pos = 370;
			
			else if(questionSet == 14) pos = 240;
			
			else if(questionSet == 15) pos = 115;
			
			else if(questionSet == 16) pos = 370;
			
			else if(questionSet == 18) pos = 370;
			
			else if(questionSet == 19) pos = 370;
			
			else if(questionSet == 20) pos = 370;
			
			else if(questionSet == 21) pos = 370;
			
			else if(questionSet == 22) pos = 370;
			
			else if(questionSet == 24) pos = 370;
			
			else if(questionSet == 25) pos = 370;
			
			else if(questionSet == 26) pos = 370;
			
			else if(questionSet == 27) pos = 370;
			
			else if(questionSet == 28) pos = 370;
			return pos;
		}
	
	// TEXTURES **************************************************************************
	public TiledTextureRegion question() {
		TiledTextureRegion questionRegion = null;
		if(questionSet == 0) questionRegion = resourcesManager.questionTriangleTexture;
		
		else if (questionSet == 1) questionRegion = resourcesManager.questionSquareTexture;
		
		else if (questionSet == 2) questionRegion = resourcesManager.questionAirplaneTexture;
		
		else if (questionSet == 3) questionRegion = resourcesManager.questionStarTexture;
		
		else if (questionSet == 4) questionRegion = resourcesManager.questionAvocadoTexture;
		
		else if (questionSet == 6) questionRegion = resourcesManager.questionCatTexture;
		
		else if (questionSet == 7) questionRegion = resourcesManager.questionCornTexture;
		
		else if (questionSet == 8) questionRegion = resourcesManager.questionBirdTexture;
		
		else if (questionSet == 9) questionRegion = resourcesManager.questionCircleTexture;
		
		else if (questionSet == 10) questionRegion = resourcesManager.questionDogTexture;
		
		else if (questionSet == 12) questionRegion = resourcesManager.questionCarrotTexture;
		
		else if (questionSet == 13) questionRegion = resourcesManager.questionDoughnutTexture;
		
		else if (questionSet == 14) questionRegion = resourcesManager.questionAppleTexture;
		
		else if (questionSet == 15) questionRegion = resourcesManager.questionStrawberryTexture;
		
		else if (questionSet == 16) questionRegion = resourcesManager.questionFlowerTexture;
		
		else if (questionSet == 18) questionRegion = resourcesManager.questionMangoTexture;
		
		else if (questionSet == 19) questionRegion = resourcesManager.questionBookTexture;
		
		else if (questionSet == 20) questionRegion = resourcesManager.questionHeartTexture;
		
		else if (questionSet == 21) questionRegion = resourcesManager.questionMushroomTexture;
		
		else if (questionSet == 22) questionRegion = resourcesManager.questionIcecreamTexture;
		
		else if (questionSet == 24) questionRegion = resourcesManager.questionGrapesTexture;
		
		else if (questionSet == 25) questionRegion = resourcesManager.questionPigTexture;
		
		else if (questionSet == 26) questionRegion = resourcesManager.questionPumpkinTexture;
		
		else if (questionSet == 27) questionRegion = resourcesManager.questionEggplantTexture;
		
		else if (questionSet == 28) questionRegion = resourcesManager.questionRabbitTexture;
		
		return questionRegion;
	}
	
	public ITextureRegion correctAnswerSprite() {
		if(questionSet == 0) r = resourcesManager.choiceTriangleTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 2) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 3) r = resourcesManager.choiceStarTexture;
		
		else if(questionSet == 4) r = resourcesManager.choiceAvocadoTexture;
		
		else if(questionSet == 6) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 7) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 8) r = resourcesManager.choiceBirdTexture;
		
		else if(questionSet == 9) r = resourcesManager.choiceCircleTexture;
		
		else if(questionSet == 10) r = resourcesManager.choiceDogTexture;
		
		else if(questionSet == 12) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 13) r = resourcesManager.choiceDoughnutTexture;
		
		else if(questionSet == 14) r = resourcesManager.choiceAppleTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceStrawberryTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceMangoTexture;
		
		else if(questionSet == 19) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 20) r = resourcesManager.choiceHeartTexture;
		
		else if(questionSet == 21) r = resourcesManager.choiceMushroomTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceIcecreamTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 25) r = resourcesManager.choicePigTexture;
		
		else if(questionSet == 26) r = resourcesManager.choicePumpkinTexture;
		
		else if(questionSet == 27) r = resourcesManager.choiceEggplantTexture;
		
		else if(questionSet == 28) r = resourcesManager.choiceRabbitTexture;
		return r;
	}
	
	public ITextureRegion Choice1() {
		if(questionSet == 0) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceFlowerTexture;
		else if(questionSet == 2) r = resourcesManager.choiceFlowerTexture;
		else if(questionSet == 3) r = resourcesManager.choiceCornTexture;
		else if(questionSet == 4) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 6) r = resourcesManager.choiceEggplantTexture;
		else if(questionSet == 7) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 8) r = resourcesManager.choiceCarrotTexture;
		else if(questionSet == 9) r = resourcesManager.choiceEggplantTexture;
		else if(questionSet == 10) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 12) r = resourcesManager.choiceAppleTexture;
		else if(questionSet == 13) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 14) r = resourcesManager.choicePumpkinTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 19) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 20) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 21) r = resourcesManager.choiceEggplantTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceMangoTexture;
		
		else if(questionSet == 25) r = resourcesManager.choiceTriangleTexture;
		
		else if(questionSet == 26) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 27) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 28) r = resourcesManager.choiceAvocadoTexture;
		
		return r;
	}
	
	public ITextureRegion Choice2() {
		if(questionSet == 0) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 1) r = resourcesManager.choicePumpkinTexture;
		else if(questionSet == 2) r = resourcesManager.choiceAppleTexture;
		else if(questionSet == 3) r = resourcesManager.choiceTriangleTexture;
		else if(questionSet == 4) r = resourcesManager.choiceDogTexture;
		else if(questionSet == 6) r = resourcesManager.choiceDoughnutTexture;
		else if(questionSet == 7) r = resourcesManager.choiceFlowerTexture;
		else if(questionSet == 8) r = resourcesManager.choiceDogTexture;
		else if(questionSet == 9) r = resourcesManager.choiceSquareTexture;
		else if(questionSet == 10) r = resourcesManager.choiceCircleTexture;
		else if(questionSet == 12) r = resourcesManager.choiceEggplantTexture;
		else if(questionSet == 13) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 14) r = resourcesManager.choiceDogTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceAppleTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 19) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 20) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 21) r = resourcesManager.choiceBirdTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceEggplantTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceCircleTexture;
		
		else if(questionSet == 25) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 26) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 27) r = resourcesManager.choiceDogTexture;
		
		else if(questionSet == 28) r = resourcesManager.choicePigTexture;
		return r;
	}
	
	public ITextureRegion Choice3() {
		if(questionSet == 0) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceBookTexture;
		else if(questionSet == 2) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 3) r = resourcesManager.choiceGrapesTexture;
		else if(questionSet == 4) r = resourcesManager.choiceHeartTexture;
		else if(questionSet == 6) r = resourcesManager.choiceBookTexture;
		else if(questionSet == 7) r = resourcesManager.choiceMushroomTexture;
		else if(questionSet == 8) r = resourcesManager.choiceGrapesTexture;
		else if(questionSet == 9) r = resourcesManager.choiceTriangleTexture;
		else if(questionSet == 10) r = resourcesManager.choicePigTexture;
		else if(questionSet == 12) r = resourcesManager.choiceGrapesTexture;
		else if(questionSet == 13) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 14) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceCircleTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceIcecreamTexture;
		
		else if(questionSet == 19) r = resourcesManager.choiceEggplantTexture;
		
		else if(questionSet == 20) r = resourcesManager.choiceAvocadoTexture;
		
		else if(questionSet == 21) r = resourcesManager.choicePigTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceDoughnutTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 25) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 26) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 27) r = resourcesManager.choiceAppleTexture;
		
		else if(questionSet == 28) r = resourcesManager.choiceBirdTexture;
		
		
		return r;
	}
	
	public ITextureRegion Choice4() {
		if(questionSet == 0) r= resourcesManager.choiceStarTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceHeartTexture;
		else if(questionSet == 2) r = resourcesManager.choicePumpkinTexture;
		else if(questionSet == 3) r = resourcesManager.choiceRabbitTexture;
		else if(questionSet == 4) r = resourcesManager.choiceIcecreamTexture;
		else if(questionSet == 6) r = resourcesManager.choiceMangoTexture;
		else if(questionSet == 7) r = resourcesManager.choiceGrapesTexture;
		else if(questionSet == 8) r = resourcesManager.choiceAirplane;
		else if(questionSet == 9) r = resourcesManager.choiceRabbitTexture;
		else if(questionSet == 10) r = resourcesManager.choiceRabbitTexture;
		else if(questionSet == 12) r = resourcesManager.choiceMangoTexture;
		else if(questionSet == 13) r = resourcesManager.choiceAvocadoTexture;
		
		else if(questionSet == 14) r = resourcesManager.choiceAvocadoTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceDogTexture;
		
		else if(questionSet == 19) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 20) r = resourcesManager.choiceTriangleTexture;
		
		else if(questionSet == 21) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceCarrotTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceMushroomTexture;
		
		else if(questionSet == 25) r = resourcesManager.choiceAirplane;
		
		else if(questionSet == 26) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 27) r = resourcesManager.choiceCircleTexture;
		
		else if(questionSet == 28) r = resourcesManager.choiceCatTexture;
		
		return r;
	}
	
	public ITextureRegion Choice5() {
		if(questionSet == 0) r = resourcesManager.choiceHeartTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceStarTexture;
		else if(questionSet == 2) r = resourcesManager.choiceBookTexture;
		else if(questionSet == 3) r = resourcesManager.choiceCarrotTexture;
		else if(questionSet == 4) r = resourcesManager.choicePigTexture;
		else if(questionSet == 6) r = resourcesManager.choiceMushroomTexture;
		else if(questionSet == 7) r = resourcesManager.choiceTriangleTexture;
		else if(questionSet == 8) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 9) r = resourcesManager.choiceCatTexture;
		else if(questionSet == 10) r = resourcesManager.choiceMangoTexture;
		else if(questionSet == 12) r = resourcesManager.choiceIcecreamTexture;
		else if(questionSet == 13) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 14) r = resourcesManager.choiceEggplantTexture;
		
		else if(questionSet == 15) r = resourcesManager.choiceMushroomTexture;
		
		else if(questionSet == 16) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 18) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 19) r = resourcesManager.choiceIcecreamTexture;
		
		else if(questionSet == 20) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 21) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 22) r = resourcesManager.choiceBookTexture;
		
		else if(questionSet == 24) r = resourcesManager.choiceCatTexture;
		
		else if(questionSet == 25) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 26) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 27) r = resourcesManager.choiceCornTexture;
		
		else if(questionSet == 28) r = resourcesManager.choiceAppleTexture;
			
		return r;
	}
	
	private ITextureRegion trivia() {
		ITextureRegion t = null;
		if(questionSet == 0) t = resourcesManager.triangleTriva;
		else if (questionSet == 1) t = resourcesManager.PigTriva;
		else if (questionSet == 2) t = resourcesManager.AirplaneTriva;
		else if (questionSet == 3) t = resourcesManager.StarTriva;
		else if (questionSet == 4) t = resourcesManager.avocadoTriva;
		
		else if (questionSet == 6) t = resourcesManager.catTriva;
		else if (questionSet == 7) t = resourcesManager.CornTriva;
		else if (questionSet == 8) t = resourcesManager.birdTriva;
		else if (questionSet == 9) t = resourcesManager.CircleTriva;
		else if (questionSet == 10) t = resourcesManager.DogTriva;
		
		else if (questionSet == 12) t = resourcesManager.carrotTriva;
		else if (questionSet == 13) t = resourcesManager.DoughnutTriva;
		else if (questionSet == 14) t = resourcesManager.appleTriva;
		else if (questionSet == 15) t = resourcesManager.strawberryTriva;
		else if (questionSet == 16) t = resourcesManager.flowerTriva;
		
		else if (questionSet == 18) t = resourcesManager.mangoTriva;
		else if (questionSet == 19) t = resourcesManager.bookTriva;
		else if (questionSet == 20) t = resourcesManager.heartTriva;
		else if (questionSet == 21) t = resourcesManager.mushroomTriva;
		else if (questionSet == 22) t = resourcesManager.IcecreamTriva;
		
		else if (questionSet == 24) t = resourcesManager.GrapesTriva;
		else if (questionSet == 25) t = resourcesManager.PigTriva;
		else if (questionSet == 26) t = resourcesManager.pumpkinTriva;
		else if (questionSet == 27) t = resourcesManager.rabbitTriva;
		else if (questionSet == 28) t = resourcesManager.rabbitTriva;
		return t;
	}
	
}
