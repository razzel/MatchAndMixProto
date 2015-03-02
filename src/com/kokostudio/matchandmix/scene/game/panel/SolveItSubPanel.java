package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.ease.EaseBounceOut;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SolveItSubPanel extends BaseScene {
	
	private ITextureRegion r;
	private int pos;
	private int lives;
	private int x;
	private float totalLives;
	private float liveSpent;
	private float rate;
	
	public static int questionSet;

	private Sprite bg;
	private Sprite questionImage;
	private Sprite questionText;
	private Sprite equals;
	private Sprite answerSprite;
	private TiledSprite back;
	private Sprite plank;

	// CHOICES
	private TiledSprite correctAnswerSprite;
	private Sprite c1, c2, c3;
	
	private myDatabase db;
	
	private CameraScene tryScene;
	private Sprite tryMsg;
	
	private TiledSprite OK;
	
	private Sprite thatsWrong;
	private Sprite thatsCorrect;
	
	private Sprite sLife;
	private TiledSprite sLifeValue;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		totalLives = 3;
		lives  = 3;
		liveSpent = 0;
		createBackground();
		createButtons();
		createEquation();
		createChoices();
		checkStatus();
		createTryAgainScene();
		
		thatsCorrect.setAlpha(0f);
		thatsWrong.setAlpha(0f);
	}

	@Override
	public void onBackKeyPressed() {

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SOLVEITADDPANEL;
	}

	@Override
	public void onMenuKeyPressed() {

	}

	@Override
	public void disposeScene() {

	}

	// ====================================================================
	// CLASS LOGIC
	// ====================================================================

	private void createBackground() {
		bg = new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};

		attachChild(bg);
	}

	private void createButtons() {
		back = new TiledSprite(45, 430, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					disposeScene();
					resourcesManager.click.play();

					SceneManager.getInstance().loadSolveItSubScene();
					break;
				}
				return true;
			}

		};
		registerTouchArea(back);
		attachChild(back);
		
		//
		thatsCorrect = new Sprite(410, 445, resourcesManager.thatsCorrectTexture, vbom);
		attachChild(thatsCorrect);
		thatsWrong = new Sprite(410, 445, resourcesManager.thatsWrongTexture, vbom);
		attachChild(thatsWrong);
		// LIFE SPRITE AND TEXT
		sLife = new Sprite(690, 445, resourcesManager.lifeTexture, vbom);
		attachChild(sLife);
		sLifeValue = new TiledSprite(750, 450, resourcesManager.lifeValueTexture, vbom);
		sLifeValue.setCurrentTileIndex(lives);
		attachChild(sLifeValue);
	}

	private void createEquation() {
		plank = new Sprite(400, 130, resourcesManager.gtmlQuestionPad, vbom);
		attachChild(plank);

		questionImage = new Sprite(300, 300, questionImage(), vbom);
		attachChild(questionImage);

		questionText = new Sprite(400, 130, questionText(), vbom);
		attachChild(questionText);

		equals = new Sprite(600, 300, resourcesManager.equalsTexture, vbom);
		attachChild(equals);

		answerSprite = new Sprite(600, 300, answerSprite(), vbom);
		attachChild(answerSprite);
	}

	private void createChoices() {
		int spriteY = 50;
		correctAnswerSprite = new TiledSprite(setCorrectSpritePosition(), spriteY, correctAnswerSprite(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					correctAnswerSprite.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					correctAnswerSprite.setScale(1.0f);
					resourcesManager.correct.play();
					update(questionSet, "true");
					updateIsFirstTime();
					db.updateRate(5, computeRate());
					db.updateTry(5, 1);
					lock();
					playAnimation();
					nextQuestion();
					thatsCorrect.setAlpha(1.0f);
					break;
				}
				return true;
			}
		};
		registerTouchArea(correctAnswerSprite);
		attachChild(correctAnswerSprite);

		c1 = new Sprite(setChoice1Position(), spriteY, choice1(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c1.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					c1.setScale(1.0f);
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
		registerTouchArea(c1);
		attachChild(c1);

		c2 = new Sprite(setChoice2Position(), spriteY, choice2(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
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

		c3 = new Sprite(setChoice3Position(), spriteY, choice3(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
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
		registerTouchArea(c3);
		attachChild(c3);

	}

	// =========================================================================================
	// DATABASE
	// =========================================================================================

	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	private void nextQuestion() {
		this.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
					x = 0;
					if(db.solveItSubGetAnswered()==25) {
						// if the questions are finished go back to the question frams
						SceneManager.getInstance().loadSolveItSubScene();
					} else {
						if(questionSet == 28 || questionSet+x>=28) {
							while(db.solveItSubIsAnswered(0+x).compareTo("true")==0) {
								x++;
							}
							getQuestionIndex(0+x);
						} else {
							while(db.solveItSubIsAnswered(questionSet+x).compareTo("true")==0) {
								x++;
							}
							getQuestionIndex(questionSet+x);
						}
						SceneManager.getInstance().loadSolveItSubPanelScene();	
					}				
				}
		}));
		
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
					SolveItSubPanel.this.clearChildScene();
					SceneManager.getInstance().loadSolveItSubScene();
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

	private void lock() {
		answerSprite.setVisible(true);
		detachChild(equals);
		correctAnswerSprite.setCurrentTileIndex(1);
		unregisterTouchArea(correctAnswerSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
	}

	private void playAnimation() {
		answerSprite.registerEntityModifier(new MoveModifier(0.5f, 600, 528, 600, 300, EaseBounceOut.getInstance()));
	}
	
	private void checkLives() {
		if(lives == 0) {
			db.updateTry(5, 1);
			db.updateRate(5, computeRate());
			resourcesManager.sorry.play();
			SolveItSubPanel.this.setChildScene(tryScene, false, true, true);
		}
	}
	
	private float computeRate() {
		rate = (liveSpent / totalLives);
		return rate;
	}
	
	private void checkStatus() {
		String cmp = db.solveItSubIsAnswered(questionSet);
		if(cmp.compareTo("true")==0)
			lock();
		else answerSprite.setVisible(false);
	}
	
	private void update(int id, String s) {
		db.updateSolveItSub(id, s);
		db.close();
	}
	
	private void updateIsFirstTime() {
		db.updateIsFirstTime(5, "false");
		db.close();
	}

	// TEXTURES
	private ITextureRegion questionImage() {
		r = null;
		if(questionSet == 0) r = resourcesManager.subQuestionImage1;
		else if (questionSet == 1) r = resourcesManager.subQuestionImage2;
		else if (questionSet == 2) r = resourcesManager.subQuestionImage3;
		else if (questionSet == 3) r = resourcesManager.subQuestionImage4;
		else if (questionSet == 4) r = resourcesManager.subQuestionImage5;
		
		else if (questionSet == 6) r = resourcesManager.subQuestionImage6;
		else if (questionSet == 7) r = resourcesManager.subQuestionImage7;
		else if (questionSet == 8) r = resourcesManager.subQuestionImage8;
		else if (questionSet == 9) r = resourcesManager.subQuestionImage9;
		else if (questionSet == 10) r = resourcesManager.subQuestionImage10;
		
		else if (questionSet == 12) r = resourcesManager.subQuestionImage11;
		else if (questionSet == 13) r = resourcesManager.subQuestionImage12;
		else if (questionSet == 14) r = resourcesManager.subQuestionImage13;
		else if (questionSet == 15) r = resourcesManager.subQuestionImage14;
		else if (questionSet == 16) r = resourcesManager.subQuestionImage15;
		
		else if (questionSet == 18) r = resourcesManager.subQuestionImage16;
		else if (questionSet == 19) r = resourcesManager.subQuestionImage17;
		else if (questionSet == 20) r = resourcesManager.subQuestionImage18;
		else if (questionSet == 21) r = resourcesManager.subQuestionImage19;
		else if (questionSet == 22) r = resourcesManager.subQuestionImage20;
		
		else if (questionSet == 24) r = resourcesManager.subQuestionImage21;
		else if (questionSet == 25) r = resourcesManager.subQuestionImage22;
		else if (questionSet == 26) r = resourcesManager.subQuestionImage23;
		else if (questionSet == 27) r = resourcesManager.subQuestionImage24;
		else if (questionSet == 28) r = resourcesManager.subQuestionImage25;
		
		

		return r;
	}

	private ITextureRegion questionText() {

		if (questionSet == 0)
			r = resourcesManager.subQuestionText1;
		else if (questionSet == 1)
			r = resourcesManager.subQuestionText2;
		else if (questionSet == 2)
			r = resourcesManager.subQuestionText3;
		else if (questionSet == 3)
			r = resourcesManager.subQuestionText4;
		else if (questionSet == 4) r = resourcesManager.subQuestionText5;
		
		else if (questionSet == 6) r = resourcesManager.subQuestionText6;
		else if (questionSet == 7) r = resourcesManager.subQuestionText7;
		else if (questionSet == 8) r = resourcesManager.subQuestionText8;
		else if (questionSet == 9) r = resourcesManager.subQuestionText9;
		else if (questionSet == 10) r = resourcesManager.subQuestionText10;
		
		else if (questionSet == 12) r = resourcesManager.subQuestionText11;
		else if (questionSet == 13) r = resourcesManager.subQuestionText12;
		else if (questionSet == 14) r = resourcesManager.subQuestionText13;
		else if (questionSet == 15) r = resourcesManager.subQuestionText14;
		else if (questionSet == 16) r = resourcesManager.subQuestionText15;
		
		else if (questionSet == 18) r = resourcesManager.subQuestionText16;
		else if (questionSet == 19) r = resourcesManager.subQuestionText17;
		else if (questionSet == 20) r = resourcesManager.subQuestionText18;
		else if (questionSet == 21) r = resourcesManager.subQuestionText19;
		else if (questionSet == 22) r = resourcesManager.subQuestionText20;
		
		else if (questionSet == 24) r = resourcesManager.subQuestionText21;
		else if (questionSet == 25) r = resourcesManager.subQuestionText22;
		else if (questionSet == 26) r = resourcesManager.subQuestionText23;
		else if (questionSet == 27) r = resourcesManager.subQuestionText24;
		else if (questionSet == 28) r = resourcesManager.subQuestionText25;

		return r;
	}

	private ITextureRegion answerSprite() {
		if (questionSet == 0)
			r = resourcesManager.ansTexture1;
		else if (questionSet == 1)
			r = resourcesManager.ansTexture2;
		else if (questionSet == 2)
			r = resourcesManager.ansTexture3;
		else if (questionSet == 3)
			r = resourcesManager.ansTexture4;
		else if (questionSet == 4)
			r = resourcesManager.ansTexture6;
		
		else if (questionSet == 6) r = resourcesManager.ansTexture1;
		else if (questionSet == 7) r = resourcesManager.ansTexture5;
		else if (questionSet == 8) r = resourcesManager.ansTexture2;
		else if (questionSet == 9) r = resourcesManager.ansTexture2;
		else if (questionSet == 10) r = resourcesManager.ansTexture5;
		
		else if (questionSet == 12) r = resourcesManager.ansTexture7;
		else if (questionSet == 13) r = resourcesManager.ansTexture6;
		else if (questionSet == 14) r = resourcesManager.ansTexture4;
		else if (questionSet == 15) r = resourcesManager.ansTexture6;
		else if (questionSet == 16) r = resourcesManager.ansTexture0;
		
		else if (questionSet == 18) r = resourcesManager.ansTexture10;
		else if (questionSet == 19) r = resourcesManager.ansTexture9;
		else if (questionSet == 20) r = resourcesManager.ansTexture13;
		else if (questionSet == 21) r = resourcesManager.ansTexture16;
		else if (questionSet == 22) r = resourcesManager.ansTexture13;
		
		else if (questionSet == 24) r = resourcesManager.ansTexture12;
		else if (questionSet == 25) r = resourcesManager.ansTexture13;
		else if (questionSet == 26) r = resourcesManager.ansTexture14;
		else if (questionSet == 27) r = resourcesManager.ansTexture9;
		else if (questionSet == 28) r = resourcesManager.ansTexture11;
		
		

		return r;
	}

	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion tr = null;
		if (questionSet == 0)
			tr = resourcesManager.texture1;
		else if (questionSet == 1)
			tr = resourcesManager.texture2;
		else if (questionSet == 2)
			tr = resourcesManager.texture3;
		else if (questionSet == 3)
			tr = resourcesManager.texture4;
		else if (questionSet == 4)
			tr = resourcesManager.texture6;

		else if (questionSet == 6) tr = resourcesManager.texture1;
		else if (questionSet == 7) tr = resourcesManager.texture5;
		else if (questionSet == 8) tr = resourcesManager.texture2;
		else if (questionSet == 9) tr = resourcesManager.texture2;
		else if (questionSet == 10) tr = resourcesManager.texture5;
		
		else if (questionSet == 12) tr = resourcesManager.texture7;
		else if (questionSet == 13) tr = resourcesManager.texture6;
		else if (questionSet == 14) tr = resourcesManager.texture4;
		else if (questionSet == 15) tr = resourcesManager.texture6;
		else if (questionSet == 16) tr = resourcesManager.texture0;
		
		else if (questionSet == 18) tr = resourcesManager.texture10;
		else if (questionSet == 19) tr = resourcesManager.texture9;
		else if (questionSet == 20) tr = resourcesManager.texture13;
		else if (questionSet == 21) tr = resourcesManager.texture16;
		else if (questionSet == 22) tr = resourcesManager.texture13;
		
		else if (questionSet == 24) tr = resourcesManager.texture12;
		else if (questionSet == 25) tr = resourcesManager.texture13;
		else if (questionSet == 26) tr = resourcesManager.texture14;
		else if (questionSet == 27) tr = resourcesManager.texture9;
		else if (questionSet == 28) tr = resourcesManager.texture11;
		return tr;
	}

	private ITextureRegion choice1() {
		if (questionSet == 0)
			r = resourcesManager.texture2;
		else if (questionSet == 1)
			r = resourcesManager.texture4;
		else if (questionSet == 2)
			r = resourcesManager.texture5;
		else if (questionSet == 3)
			r = resourcesManager.texture6;
		else if (questionSet == 4)
			r = resourcesManager.texture2;
		
		else if (questionSet == 6) r = resourcesManager.texture0;
		else if (questionSet == 7) r = resourcesManager.texture6;
		else if (questionSet == 8) r = resourcesManager.texture3;
		else if (questionSet == 9) r = resourcesManager.texture8;
		else if (questionSet == 10) r = resourcesManager.texture0;
		
		else if (questionSet == 12) r = resourcesManager.texture14;
		else if (questionSet == 13) r = resourcesManager.texture0;
		else if (questionSet == 14) r = resourcesManager.texture8;
		else if (questionSet == 15) r = resourcesManager.texture2;
		else if (questionSet == 16) r = resourcesManager.texture5;
		
		else if (questionSet == 18) r = resourcesManager.texture5;
		else if (questionSet == 19) r = resourcesManager.texture10;
		else if (questionSet == 20) r = resourcesManager.texture7;
		else if (questionSet == 21) r = resourcesManager.texture14;
		else if (questionSet == 22) r = resourcesManager.texture12;
		
		else if (questionSet == 24) r = resourcesManager.texture15;
		else if (questionSet == 25) r = resourcesManager.texture1;
		else if (questionSet == 26) r = resourcesManager.texture8;
		else if (questionSet == 27) r = resourcesManager.texture12;
		else if (questionSet == 28) r = resourcesManager.texture16;
		return r;
	}

	private ITextureRegion choice2() {
		if (questionSet == 0)
			r = resourcesManager.texture3;
		else if (questionSet == 1)
			r = resourcesManager.texture5;
		else if (questionSet == 2)
			r = resourcesManager.texture6;
		else if (questionSet == 3)
			r = resourcesManager.texture7;
		else if (questionSet == 4)
			r = resourcesManager.texture5;
		
		else if (questionSet == 6) r = resourcesManager.texture9;
		else if (questionSet == 7) r = resourcesManager.texture1;
		else if (questionSet == 8) r = resourcesManager.texture5;
		else if (questionSet == 9) r = resourcesManager.texture9;
		else if (questionSet == 10) r = resourcesManager.texture9;
		
		else if (questionSet == 12) r = resourcesManager.texture5;
		else if (questionSet == 13) r = resourcesManager.texture8;
		else if (questionSet == 14) r = resourcesManager.texture16;
		else if (questionSet == 15) r = resourcesManager.texture3;
		else if (questionSet == 16) r = resourcesManager.texture4;
		
		else if (questionSet == 18) r = resourcesManager.texture4;
		else if (questionSet == 19) r = resourcesManager.texture8;
		else if (questionSet == 20) r = resourcesManager.texture9;
		else if (questionSet == 21) r = resourcesManager.texture15;
		else if (questionSet == 22) r = resourcesManager.texture8;
		
		else if (questionSet == 24) r = resourcesManager.texture3;
		else if (questionSet == 25) r = resourcesManager.texture3;
		else if (questionSet == 26) r = resourcesManager.texture5;
		else if (questionSet == 27) r = resourcesManager.texture15;
		else if (questionSet == 28) r = resourcesManager.texture5;
		return r;
	}

	private ITextureRegion choice3() {
		if (questionSet == 0)
			r = resourcesManager.texture4;
		else if (questionSet == 1)
			r = resourcesManager.texture6;
		else if (questionSet == 2)
			r = resourcesManager.texture7;
		else if (questionSet == 3)
			r = resourcesManager.texture1;
		else if (questionSet == 4)
			r = resourcesManager.texture3;
		else if (questionSet == 6) r = resourcesManager.texture5;
		else if (questionSet == 7) r = resourcesManager.texture0;
		else if (questionSet == 8) r = resourcesManager.texture6;
		else if (questionSet == 9) r = resourcesManager.texture6;
		else if (questionSet == 10) r = resourcesManager.texture12;

		else if (questionSet == 12) r = resourcesManager.texture12;
		else if (questionSet == 13) r = resourcesManager.texture7;
		else if (questionSet == 14) r = resourcesManager.texture5;
		else if (questionSet == 15) r = resourcesManager.texture1;
		else if (questionSet == 16) r = resourcesManager.texture7;
		
		else if (questionSet == 18) r = resourcesManager.texture7;
		else if (questionSet == 19) r = resourcesManager.texture7;
		else if (questionSet == 20) r = resourcesManager.texture5;
		else if (questionSet == 21) r = resourcesManager.texture12;
		else if (questionSet == 22) r = resourcesManager.texture3;
		
		else if (questionSet == 24) r = resourcesManager.texture2;
		else if (questionSet == 25) r = resourcesManager.texture0;
		else if (questionSet == 26) r = resourcesManager.texture2;
		else if (questionSet == 27) r = resourcesManager.texture2;
		else if (questionSet == 28) r = resourcesManager.texture1;
		return r;
	}

	/*
	 * POSITIONS 250, 350, 450, 550
	 */

	private int setCorrectSpritePosition() {
		if (questionSet == 0) pos = 350;
		else if (questionSet == 1) pos = 250;
		else if (questionSet == 2) pos = 350;
		else if (questionSet == 3) pos = 550;
		else if (questionSet == 4) pos = 450;
		
		else if (questionSet == 6) pos = 350;
		else if (questionSet == 7) pos = 250;
		else if (questionSet == 8) pos = 350;
		else if (questionSet == 9) pos = 550;
		else if (questionSet == 10) pos = 450;
		
		else if (questionSet == 12) pos = 350;
		else if (questionSet == 13) pos = 250;
		else if (questionSet == 14) pos = 350;
		else if (questionSet == 15) pos = 550;
		else if (questionSet == 16) pos = 450;

		else if (questionSet == 18) pos = 350;
		else if (questionSet == 19) pos = 250;
		else if (questionSet == 20) pos = 350;
		else if (questionSet == 21) pos = 550;
		else if (questionSet == 22) pos = 450;

		else if (questionSet == 24) pos = 350;
		else if (questionSet == 25) pos = 250;
		else if (questionSet == 26) pos = 350;
		else if (questionSet == 27) pos = 550;
		else if (questionSet == 28) pos = 450;



		return pos; 
	}

	private int setChoice1Position() {
		if (questionSet == 0) pos = 250;
		else if (questionSet == 1) pos = 350;
		else if (questionSet == 2) pos = 250;
		else if (questionSet == 3) pos = 250;
		else if (questionSet == 4) pos = 250;
		
		else if (questionSet == 6) pos = 250;
		else if (questionSet == 7) pos = 350;
		else if (questionSet == 8) pos = 250;
		else if (questionSet == 9) pos = 250;
		else if (questionSet == 10) pos = 250;
		
		else if (questionSet == 12) pos = 250;
		else if (questionSet == 13) pos = 350;
		else if (questionSet == 14) pos = 250;
		else if (questionSet == 15) pos = 250;
		else if (questionSet == 16) pos = 250;
		
		else if (questionSet == 18) pos = 250;
		else if (questionSet == 19) pos = 350;
		else if (questionSet == 20) pos = 250;
		else if (questionSet == 21) pos = 250;
		else if (questionSet == 22) pos = 250;
		
		else if (questionSet == 24) pos = 250;
		else if (questionSet == 25) pos = 350;
		else if (questionSet == 26) pos = 250;
		else if (questionSet == 27) pos = 250;
		else if (questionSet == 28) pos = 250;
		return pos;
	}

	private int setChoice2Position() {
		if (questionSet == 0) pos = 450;
		else if (questionSet == 1) pos = 450;
		else if (questionSet == 2) pos = 450;
		else if (questionSet == 3) pos = 350;
		else if (questionSet == 4) pos = 350;
		
		else if (questionSet == 6) pos = 450;
		else if (questionSet == 7) pos = 450;
		else if (questionSet == 8) pos = 450;
		else if (questionSet == 9) pos = 350;
		else if (questionSet == 10) pos = 350;
		
		else if (questionSet == 12) pos = 450;
		else if (questionSet == 13) pos = 450;
		else if (questionSet == 14) pos = 450;
		else if (questionSet == 15) pos = 350;
		else if (questionSet == 16) pos = 350;
		
		else if (questionSet == 18) pos = 450;
		else if (questionSet == 19) pos = 450;
		else if (questionSet == 20) pos = 450;
		else if (questionSet == 21) pos = 350;
		else if (questionSet == 22) pos = 350;
		
		else if (questionSet == 24) pos = 450;
		else if (questionSet == 25) pos = 450;
		else if (questionSet == 26) pos = 450;
		else if (questionSet == 27) pos = 350;
		else if (questionSet == 28) pos = 350;
		
		return pos;
	}

	private int setChoice3Position() {
		if (questionSet == 0) pos = 550;
		else if (questionSet == 1) pos = 550;
		else if (questionSet == 2) pos = 550;
		else if (questionSet == 3) pos = 450;
		else if (questionSet == 4) pos = 550;
		
		else if (questionSet == 6) pos = 550;
		else if (questionSet == 7) pos = 550;
		else if (questionSet == 8) pos = 550;
		else if (questionSet == 9) pos = 450;
		else if (questionSet == 10) pos = 550;
		
		else if (questionSet == 12) pos = 550;
		else if (questionSet == 13) pos = 550;
		else if (questionSet == 14) pos = 550;
		else if (questionSet == 15) pos = 450;
		else if (questionSet == 16) pos = 550;
		
		else if (questionSet == 18) pos = 550;
		else if (questionSet == 19) pos = 550;
		else if (questionSet == 20) pos = 550;
		else if (questionSet == 21) pos = 450;
		else if (questionSet == 22) pos = 550;
		
		else if (questionSet == 24) pos = 550;
		else if (questionSet == 25) pos = 550;
		else if (questionSet == 26) pos = 550;
		else if (questionSet == 27) pos = 450;
		else if (questionSet == 28) pos = 550;

		return pos;
	}

}
