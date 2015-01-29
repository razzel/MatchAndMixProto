package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
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

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createQuestions();
		createButtons();
		createChoices();
		createCorrectObjects();
		createOtherObjects();
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
					back.setScale(1.0f);
					back.setCurrentTileIndex(0);
					disposeScene();
					resourcesManager.click.play();
					SceneManager.getInstance().loadCountItScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
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
					lock();
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
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c4);;
		attachChild(c4);
		
	}
	
	private void createCorrectObjects() {
		int[] posX = {172,410,454,123,180,308};
		int[] posY = {219,318,217,208,265,205};
		
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
						object1[index].setScale(1.5f);
						playNextSound();
						unregisterTouchArea(object1[index]);
						object1[index].registerUpdateHandler(new TimerHandler(4f, new ITimerCallback() {				
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								numCount = 0;
								for(int ctr = 0; ctr<object1.length; ctr++) {
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
	
	private void lock() {
		correctSprite.setCurrentTileIndex(1);
		unregisterTouchArea(correctSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
	}
	
	private void playNextSound() {
		Sound[] numbers = 
			{ resourcesManager.number1, resourcesManager.number2, resourcesManager.number3, resourcesManager.number4, resourcesManager.number5 };
		numbers[numCount].play();
		numCount++;
	}
	
	private int setHowManyCorrectObject() {
		if(questionSet == 0) cnt = 3;
		
		else if(questionSet == 1) cnt = 4;
		
		return cnt;
	}
	
	private int setHowManyOtherObject() { 
		if(questionSet == 0) cnt = 2;
		
		else if(questionSet == 1) cnt = 5;
		return cnt;
	}
	
	// TEXTURES
	private ITextureRegion question() {
		if(questionSet == 0) r = resourcesManager.countItQuestion1;
		
		else if (questionSet == 1) r = resourcesManager.countItQuestion2;
			
		return r;
	}
	
	private ITextureRegion correctClue() {
		if(questionSet == 0) r = resourcesManager.countItClueTriangleTexture;
		
		else if (questionSet == 1) r = resourcesManager.countItClueCirceTexture;
		
		return r;
	}
	
	private ITextureRegion otherClue() {
		if(questionSet == 0) r = resourcesManager.countItClueSquareTexture;
		
		else if(questionSet == 1) r = resourcesManager.countItClueSquareTexture;
		
		return r;
	}
	
	private ITextureRegion correctObject() {
		if(questionSet == 0) r = resourcesManager.countItObjectTriangle;
		
		else if(questionSet == 1) r = resourcesManager.countItObjectCircle;
		
		return r;
	}
	
	private ITextureRegion otherObject() {
		if(questionSet == 0) r = resourcesManager.countItObjectSquare;
		
		else  if(questionSet == 1) r = resourcesManager.countItObjectSquare;
		
		return r;
	}
	
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion tr = null;
		if(questionSet == 0) tr = resourcesManager.texture1;
		
		else if (questionSet == 1) tr = resourcesManager.texture4;
		
		return tr;
	}
	
	private ITextureRegion choice1() {
		if(questionSet == 0) r = resourcesManager.texture4;
		
		else if(questionSet == 1) r = resourcesManager.texture5;
		
		return r;
	}
	
	private ITextureRegion choice2() {
		if(questionSet == 0) r = resourcesManager.texture3;
		
		else if(questionSet == 1) r = resourcesManager.texture2;
		
		return r;
	}
	
	private ITextureRegion choice3() {
		if(questionSet == 0) r = resourcesManager.texture2;
		
		else if(questionSet == 1) r = resourcesManager.texture3;
		
		return r;
	}
	
	private ITextureRegion choice4() {
		if(questionSet == 0) r = resourcesManager.texture6;
		
		else if(questionSet == 1) r = resourcesManager.texture6;
		
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
		
		return pos;
	}
	
	private int setChoice1Position() {
		if(questionSet == 0) pos = 300;
		
		else if(questionSet == 1) pos = 200;
		
		return pos;
	}
	
	private int setChoice2Position() {
		if(questionSet == 0) pos = 400;
		
		else if(questionSet == 1) pos = 300;
		
		return pos;
	}
	
	private int setChoice3Position() {
		if(questionSet == 0) pos = 500;
		
		else if(questionSet == 1) pos = 500;
		
		return pos;
	}
	
	private int setChoice4Position() {
		if(questionSet == 0) pos = 600;
		
		else if(questionSet == 1) pos = 600;
		
		return pos;
	}
}
