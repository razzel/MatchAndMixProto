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

public class ThatColorIsPanel extends BaseScene {
	
	// variable in which what set of question will be retrieve
	private static int questionSet;
	
	// if the selected frame is already answered
	private boolean isAnswered;
	
	private ITextureRegion r;
	private int pos;
	
	// SPRITES
	private Sprite question;
	private Sprite c1, c2, c3, c4;
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
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub	
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_THATCOLORISPANEL;
	}

	@Override
	public void disposeScene() {
		
	}
	
	// -----------------------------------------------------------------------------
	// CLASS LOGIC
	// -----------------------------------------------------------------------------
	private void createBackground() {
		BG = new Sprite(400, 240, resourcesManager.thatColorIsBGTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		questionPlank = new Sprite(240, 70, resourcesManager.questionPlankTextureRegion, vbom);
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
					// unload that color is panel textures
					ResourcesManager.getInstance().unloadThatColorIsPanelTextures();
					// set the scene
					SceneManager.getInstance().loadThatColorIsScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	private void createQuestions() {
		question = new Sprite(250, 240, question(), vbom);
		attachChild(question);
	}
	
	private void createChoices() {
		c1 = new Sprite(650, setColor1Position(), color1(), vbom);
		c2 = new Sprite(650, setColor2Position(), color2(), vbom);
		c3 = new Sprite(650, setColor3Position(), color3(), vbom);
		c4 = new Sprite(650, setColor4Position(), color4(), vbom);
		correctSprite = new TiledSprite(650, correctSpritePosition(), correctAnswerSprite(), vbom);
		
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
	
	/* positions
	 * 400
	 * 320
	 * 240
	 * 160
	 * 80
	 */
	public int correctSpritePosition() {
		if(questionSet == 0) {
			pos = 160;
		}
		return pos;
	}
	public int setColor1Position() {
		if(questionSet == 0) {
			pos = 400;
		}
		return pos;
	}
	public int setColor2Position() {
		if(questionSet == 0) {
			pos = 320;
		}
		return pos;
	}
	public int setColor3Position() {
		if(questionSet == 0) {
			pos = 240;
		}
		return pos;
	}
	public int setColor4Position() {
		if(questionSet == 0) {
			pos = 80;
		}
		return pos;
	}
	
	// TEXTURES
	public ITextureRegion question() {
		ITextureRegion questionRegion = null;
		if(questionSet == 0) {
			questionRegion = resourcesManager.heartTexture;
		}
		return questionRegion;
	}
	
	public TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		if(questionSet == 0) {
			r = resourcesManager.redTextureRegion;
		}
		return r;
	}
	
	public ITextureRegion color1() {
		//ITextureRegion r = null;
		if(questionSet == 0) {
			r = resourcesManager.yellowTextureRegion;
		}
		return r;
	}
	
	public ITextureRegion color2() {
		if(questionSet == 0) {
			r = resourcesManager.blueTextureRegion;
		}
		return r;
	}
	
	public ITextureRegion color3() {
		if(questionSet == 0) {
			r = resourcesManager.pinkTextureRegion;
		}
		return r;
	}
	
	public ITextureRegion color4() {
		if(questionSet == 0) {
			r = resourcesManager.greenTextureRegion;
		}
		return r;
	}
	
}