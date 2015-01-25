package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class MatchItPanel extends BaseScene {
	
	// POSITIONS 
	/*
	 * (590,375) | (715,370)
	 * (590,240) | (715,240)
	 * (590,115) | (715,115)
	 * */
	
	private static int questionSet;
	
	private ITextureRegion r;
	private int pos;
	
	// SPRITES
	private TiledSprite question;
	private Sprite c1, c2, c3, c4, c5;
	private Sprite choiceBox;
	private Sprite correctSprite;
	
	private Sprite BG;
	private Sprite questionPlank;
	private TiledSprite back;
	
	private myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();
		createChoices();
		createQuestions();
		checkStatus();
		sortChildren();
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
					back.setCurrentTileIndex(0);
					back.setScale(1f);
					resourcesManager.click.play();
					// unload MATCH IT panel textures
					ResourcesManager.getInstance().unloadMatchItPanelTextures();
					// SET THE SCENE TO MATCH IT
					SceneManager.getInstance().loadMatchItScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);				
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
					checkPosition(c1, pTouchAreaLocalX, pTouchAreaLocalY, setChoice1XPosition(), setChoice1YPosition());
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
					checkPosition(c2, pTouchAreaLocalX, pTouchAreaLocalY, setChoice2XPosition(), setChoice2YPosition());
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
					checkPosition(c3, pTouchAreaLocalX, pTouchAreaLocalX, setChoice3XPosition(), setChoice3YPosition());
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
					checkPosition(c4, pTouchAreaLocalX, pTouchAreaLocalY, setChoice4XPosition(), setChoice4YPosition());
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
					checkPosition(c5, pTouchAreaLocalX, pTouchAreaLocalY, setChoice5XPosition(), setChoice5YPosition());
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
			
			return pos;
		}
		public int correctSpriteYPosition() {	
			if(questionSet == 0) pos = 240;
			
			else if(questionSet == 1) pos = 370;
				
			return pos;
		}
		
		// CHOICE 1
		public int setChoice1XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 590;
			
			return pos;
		}	
		public int setChoice1YPosition() {
			if(questionSet == 0) pos = 370;
			
			else if(questionSet == 1) pos = 370;
			
			return pos;
		}
		
		// CHOICE 2
		public int setChoice2XPosition() {
			if(questionSet == 0) pos = 715;
			
			else if(questionSet == 1) pos = 590;
			
			return pos;
		}
		public int setChoice2YPosition() {
			if(questionSet == 0) pos = 370;
			
			else if(questionSet == 1) pos = 240;
			
			return pos;
		}
		
		// CHOICE 3
		public int setChoice3XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 715;
			
			return pos;
		}
		public int setChoice3YPosition() {
			if(questionSet == 0) pos = 240;
			
			else if(questionSet == 1) pos = 240;
			
			return pos;
		}
		
		// CHOICE 4
		public int setChoice4XPosition() {
			if(questionSet == 0) pos = 590;
			
			else if(questionSet == 1) pos = 590;
			
			return pos;
		}
		public int setChoice4YPosition() {
			if(questionSet == 0) pos = 115;
			
			else if(questionSet == 1) pos = 115;
					
			return pos;
		}
		
		// CHOICE 5
		public int setChoice5XPosition() {
			if(questionSet == 0) pos = 715;
			
			else if(questionSet == 1) pos = 715;
			
			return pos;
		}
		
		public int setChoice5YPosition() {
			if(questionSet == 0) pos = 115;
			
			else if(questionSet == 1) pos = 115;
			
			return pos;
		}
	
	// TEXTURES **************************************************************************
	public TiledTextureRegion question() {
		TiledTextureRegion questionRegion = null;
		if(questionSet == 0) questionRegion = resourcesManager.questionTriangleTexture;
		else if (questionSet == 1) questionRegion = resourcesManager.questionSquareTexture;
		return questionRegion;
	}
	
	public ITextureRegion correctAnswerSprite() {
		if(questionSet == 0) r = resourcesManager.choiceTriangleTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceSquareTexture;
		
		return r;
	}
	
	public ITextureRegion Choice1() {
		if(questionSet == 0) r = resourcesManager.choiceGrapesTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceFlowerTexture;
		
		return r;
	}
	
	public ITextureRegion Choice2() {
		if(questionSet == 0) r = resourcesManager.choiceFlowerTexture;
		
		else if(questionSet == 1) r = resourcesManager.choicePumpkinTexture;
		
		return r;
	}
	
	public ITextureRegion Choice3() {
		if(questionSet == 0) r = resourcesManager.choiceSquareTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceBookTexture;
		
		return r;
	}
	
	public ITextureRegion Choice4() {
		if(questionSet == 0) r= resourcesManager.choiceStarTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceHeartTexture;
		
		return r;
	}
	
	public ITextureRegion Choice5() {
		if(questionSet == 0) r = resourcesManager.choiceHeartTexture;
		
		else if(questionSet == 1) r = resourcesManager.choiceStarTexture;
		
		return r;
	}
	
	// BACK TO POSITION
	private void checkPosition(Sprite sprite, float touchX, float touchY, int posX, int posY) {
		if(touchX < 260 && touchX  > 240 && touchY < 260 && touchY > 240) {
			sprite.detachSelf();
			lock();
			resourcesManager.correct.play();
			update(questionSet, "true");
			
		} else { 
			// Back to Original Position
			sprite.setPosition(posX, posY);
			resourcesManager.wrong.play();
		}
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
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
		unregisterTouchArea(c5);
	}
}
