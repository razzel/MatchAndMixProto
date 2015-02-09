package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.ease.EaseBounceOut;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SolveItAddPanel extends BaseScene {
	
	public static int questionSet;
	
	private ITextureRegion r;
	private int pos;
	
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

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButtons();
		createEquation();
		createChoices();
		
		answerSprite.setVisible(false);
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
		bg = new Sprite(400,  240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		
		attachChild(bg);
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
					
					SceneManager.getInstance().loadSolveItAddScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
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
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					correctAnswerSprite.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					correctAnswerSprite.setScale(1.0f);
					resourcesManager.correct.play();
					lock();
					playAnimation();
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
		
		c2 = new Sprite(setChoice2Position(), spriteY, choice2(), vbom) {
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
		
		c3 = new Sprite(setChoice3Position(), spriteY, choice3(), vbom) {
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
		registerTouchArea(c3);
		attachChild(c3);
		
	}

	// =========================================================================================
	// DATABASE
	// =========================================================================================
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
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
	
	// TEXTURES
	private ITextureRegion questionImage() {
		r = null;
		if(questionSet == 0) r = resourcesManager.addQuestionImage1;
		else if(questionSet == 1) r = resourcesManager.addQuestionImage2;
		else if(questionSet == 2) r = resourcesManager.addQuestionImage3;
		else if(questionSet == 3) r = resourcesManager.addQuestionImage4;
		else if(questionSet == 4) r = resourcesManager.addQuestionImage5;
		
		return r;
	}
	
	private ITextureRegion questionText() {
		
		if(questionSet == 0) r = resourcesManager.addQuestionText1;
		else if(questionSet == 1) r = resourcesManager.addQuestionText2;
		else if(questionSet == 2) r = resourcesManager.addQuestionText3;
		else if(questionSet == 3) r = resourcesManager.addQuestionText4;
		else if(questionSet == 4) r = resourcesManager.addQuestionText5;
		
		return r;
	}
	
	private ITextureRegion answerSprite() {
		if(questionSet == 0) r = resourcesManager.ansTexture2;
		else if(questionSet == 1) r = resourcesManager.ansTexture3;
		else if(questionSet == 2) r = resourcesManager.ansTexture4;
		else if(questionSet == 3) r = resourcesManager.ansTexture5;
		else if(questionSet == 4) r = resourcesManager.ansTexture6;
		
		return r;
	}
	
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion tr = null;
		if(questionSet == 0) tr = resourcesManager.texture2;
		else if(questionSet == 1) tr = resourcesManager.texture3;
		else if(questionSet == 2) tr = resourcesManager.texture4;
		else if(questionSet == 3) tr = resourcesManager.texture5;
		else if(questionSet == 4) tr = resourcesManager.texture6;
		
		return tr;
	}
	
	private ITextureRegion choice1() {
		if(questionSet == 0) r = resourcesManager.texture1;
		else if(questionSet == 1) r = resourcesManager.texture4;
		else if(questionSet == 2) r = resourcesManager.texture5;
		else if(questionSet == 3) r = resourcesManager.texture6;
		else if(questionSet == 4) r = resourcesManager.texture2;
		
		return r;
	}
	
	private ITextureRegion choice2() {
		if(questionSet == 0) r = resourcesManager.texture3;
		else if(questionSet == 1) r = resourcesManager.texture5;
		else if(questionSet == 2) r = resourcesManager.texture6;
		else if(questionSet == 3) r = resourcesManager.texture7;
		else if(questionSet == 4) r = resourcesManager.texture5;
		
		return r;
	}
	
	private ITextureRegion choice3() {
		if(questionSet == 0) r = resourcesManager.texture4;
		else if(questionSet == 1) r = resourcesManager.texture6;
		else if(questionSet == 2) r = resourcesManager.texture7;
		else if(questionSet == 3) r = resourcesManager.texture1;
		else if(questionSet == 4) r = resourcesManager.texture3;
		
		return r;
	}
	
	/*
	 * POSITIONS
	 * 250, 350, 450, 550
	 */
	
	private int setCorrectSpritePosition() {
		if(questionSet == 0) pos = 350;
		else if(questionSet == 1) pos = 250;
		else if(questionSet == 2) pos = 350;
		else if(questionSet == 3) pos = 550;
		else if(questionSet == 4) pos = 450;
		
		return pos;
	}
	
	private int setChoice1Position() {
		if(questionSet == 0) pos = 250;
		else if(questionSet == 1) pos = 350;
		else if(questionSet == 2) pos = 250;
		else if(questionSet == 3) pos = 250;
		else if(questionSet == 4) pos = 250;
		
		return pos;
	}
	
	private int setChoice2Position() {
		if(questionSet == 0) pos = 450;
		else if(questionSet == 1) pos = 450;
		else if(questionSet == 2) pos = 450;
		else if(questionSet == 3) pos = 350;
		else if(questionSet == 4) pos = 350;
		
		return pos;
	}
	
	private int setChoice3Position() {
		if(questionSet == 0) pos = 550;
		else if(questionSet == 1) pos = 550;
		else if(questionSet == 2) pos = 550;
		else if(questionSet == 3) pos = 450;
		else if(questionSet == 4) pos = 550;
		
		return pos;
	}
}
