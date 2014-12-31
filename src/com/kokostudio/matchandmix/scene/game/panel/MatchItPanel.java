package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
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
	
	private boolean isAnswered;
	
	private ITextureRegion r;
	private int pos;
	
	// SPRITES
	private Sprite question;
	private Sprite c1, c2, c3, c4, c5;
	private Sprite choiceBox;
	private TiledSprite correctSprite;
	
	private Sprite BG;
	private Sprite questionPlank;
	private TiledSprite back;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButtons();
		createChoices();
		createQuestions();
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
		question = new Sprite(250, 250, resourcesManager.questionAppleTexture, vbom);
		attachChild(question);
	}
	
	private void createChoices() {
		// CHOICE BOX
		choiceBox = new Sprite(650, 240, resourcesManager.matchItChoiceBox, vbom);
		attachChild(choiceBox);
		
		// CHOICES
		c1 = new Sprite(715, 115, resourcesManager.choiceAppleTexture, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				c1.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					c1.setScale(3.5f);
					break;
				case TouchEvent.ACTION_UP:
					c1.setScale(1.0f);
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(c1);
		attachChild(c1);
	}
	
	// ========================================================================================
	// DATABASE SECTION 
	// ========================================================================================
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	// POSITIONS 
		/*
		 * (590,375) | (715,370)
		 * (590,240) | (715,240)
		 * (590,115) | (715,115)
		 * */
	public int correctSpritePosition() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	public int setChoice1Position() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	public int setChoice2Position() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	public int setChoice3Position() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	public int setChoice4Position() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	
	public int setChoice5Position() {
		if(questionSet == 0) {
			
		}
		return pos;
	}
	
	// TEXTURES **************************************************
	public ITextureRegion question() {
		ITextureRegion questionRegion = null;
		if(questionSet == 0) {
			
		}
		return questionRegion;
	}
	
	public TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		if(questionSet == 0) {
			
		}
		return r;
	}
	
	public ITextureRegion Choice1() {
		if(questionSet == 0) {
			
		}
		return r;
	}
	
	public ITextureRegion Choice2() {
		if(questionSet == 0) {
			
		}
		return r;
	}
	
	public ITextureRegion Choice3() {
		if(questionSet == 0) {
			
		}
		return r;
	}
	
	public ITextureRegion Choice4() {
		if(questionSet == 0) {
			
		}
		return r;
	}
	
	public ITextureRegion Choice5() {
		if(questionSet == 0) {
			
		}
		return r;
	}
}
