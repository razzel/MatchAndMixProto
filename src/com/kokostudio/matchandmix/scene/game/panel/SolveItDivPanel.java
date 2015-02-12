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

public class SolveItDivPanel extends BaseScene {
	
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
					
					SceneManager.getInstance().loadSolveItDivScene();
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
		if (questionSet == 0) r = resourcesManager.divQuestionImage1;
		else if (questionSet == 1) r = resourcesManager.divQuestionImage2;
		else if (questionSet == 2) r  = resourcesManager.divQuestionImage3;
		else if (questionSet == 3) r = resourcesManager.divQuestionImage4;
		else if (questionSet == 4) r = resourcesManager.divQuestionImage5;
		
		else if (questionSet == 6) r = resourcesManager.divQuestionImage6;
		else if (questionSet == 7) r = resourcesManager.divQuestionImage7;
		else if (questionSet == 8) r  = resourcesManager.divQuestionImage8;
		else if (questionSet == 9) r = resourcesManager.divQuestionImage9;
		else if (questionSet == 10) r = resourcesManager.divQuestionImage10;
		
		else if (questionSet == 12) r = resourcesManager.divQuestionImage11;
		else if (questionSet == 13) r = resourcesManager.divQuestionImage12;
		else if (questionSet == 14) r = resourcesManager.divQuestionImage13;
		else if (questionSet == 15) r = resourcesManager.divQuestionImage14;
		else if (questionSet == 16) r = resourcesManager.divQuestionImage15;
		
		else if (questionSet == 18) r = resourcesManager.divQuestionImage16;
		else if (questionSet == 19) r = resourcesManager.divQuestionImage17;
		else if (questionSet == 20) r = resourcesManager.divQuestionImage18;
		else if (questionSet == 21) r = resourcesManager.divQuestionImage19;
		else if (questionSet == 22) r = resourcesManager.divQuestionImage20;
		
		else if (questionSet == 24) r = resourcesManager.divQuestionImage21;
		else if (questionSet == 25) r = resourcesManager.divQuestionImage22;
		else if (questionSet == 26) r = resourcesManager.divQuestionImage23;
		else if (questionSet == 27) r = resourcesManager.divQuestionImage24;
		else if (questionSet == 28) r = resourcesManager.divQuestionImage25;

		return r;
	}

	private ITextureRegion questionText() {

		if (questionSet == 0)
			r = resourcesManager.divQuestionText1;
		else if (questionSet == 1)
			r = resourcesManager.divQuestionText2;
		else if (questionSet == 2)
			r = resourcesManager.divQuestionText3;
		else if (questionSet == 3)
			r = resourcesManager.divQuestionText4;
		else if (questionSet == 4)
			r = resourcesManager.divQuestionText5;

		else if (questionSet == 6) r = resourcesManager.divQuestionText6;
		else if (questionSet == 7) r = resourcesManager.divQuestionText7;
		else if (questionSet == 8) r = resourcesManager.divQuestionText8;
		else if (questionSet == 9) r = resourcesManager.divQuestionText9;
		else if (questionSet == 10) r = resourcesManager.divQuestionText10;
		
		else if (questionSet == 12) r = resourcesManager.divQuestionText11;
		else if (questionSet == 13) r = resourcesManager.divQuestionText12;
		else if (questionSet == 14) r = resourcesManager.divQuestionText13;
		else if (questionSet == 15) r = resourcesManager.divQuestionText14;
		else if (questionSet == 16) r = resourcesManager.divQuestionText15;
		
		else if (questionSet == 18) r = resourcesManager.divQuestionText16;
		else if (questionSet == 19) r = resourcesManager.divQuestionText17;
		else if (questionSet == 20) r = resourcesManager.divQuestionText18;
		else if (questionSet == 21) r = resourcesManager.divQuestionText19;
		else if (questionSet == 22) r = resourcesManager.divQuestionText20;
		
		else if (questionSet == 24) r = resourcesManager.divQuestionText21;
		else if (questionSet == 25) r = resourcesManager.divQuestionText22;
		else if (questionSet == 26) r = resourcesManager.divQuestionText23;
		else if (questionSet == 27) r = resourcesManager.divQuestionText24;
		else if (questionSet == 28) r = resourcesManager.divQuestionText25;
		return r;
	}

	private ITextureRegion answerSprite() {
		if (questionSet == 0) r = resourcesManager.ansTexture1;
		else if (questionSet == 1) r = resourcesManager.ansTexture2;
		else if (questionSet == 2) r = resourcesManager.ansTexture3;
		else if (questionSet == 3) r = resourcesManager.ansTexture4;
		else if (questionSet == 4) r = resourcesManager.ansTexture5;
		
		else if (questionSet == 6) r = resourcesManager.ansTexture3;
		else if (questionSet == 7) r = resourcesManager.ansTexture2;
		else if (questionSet == 8) r = resourcesManager.ansTexture1;
		else if (questionSet == 9) r = resourcesManager.ansTexture2;
		else if (questionSet == 10) r = resourcesManager.ansTexture4;
		
		else if (questionSet == 12) r = resourcesManager.ansTexture3;
		else if (questionSet == 13) r = resourcesManager.ansTexture5;
		else if (questionSet == 14) r = resourcesManager.ansTexture8;
		else if (questionSet == 15) r = resourcesManager.ansTexture6;
		else if (questionSet == 16) r = resourcesManager.ansTexture1;
		
		else if (questionSet == 18) r = resourcesManager.ansTexture1;
		else if (questionSet == 19) r = resourcesManager.ansTexture9;
		else if (questionSet == 20) r = resourcesManager.ansTexture2;
		else if (questionSet == 21) r = resourcesManager.ansTexture1;
		else if (questionSet == 22) r = resourcesManager.ansTexture10;
		
		else if (questionSet == 24) r = resourcesManager.ansTexture2;
		else if (questionSet == 25) r = resourcesManager.ansTexture1;
		else if (questionSet == 26) r = resourcesManager.ansTexture7;
		else if (questionSet == 27) r = resourcesManager.ansTexture1;
		else if (questionSet == 28) r = resourcesManager.ansTexture6;
		
		return r;
	}

	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion tr = null;
		if (questionSet == 0) tr = resourcesManager.texture1;
		else if (questionSet == 1) tr = resourcesManager.texture2;
		else if (questionSet == 2) tr = resourcesManager.texture3;
		else if (questionSet == 3) tr = resourcesManager.texture4;
		else if (questionSet == 4) tr = resourcesManager.texture5;
		
		else if (questionSet == 6) tr = resourcesManager.texture3;
		else if (questionSet == 7) tr = resourcesManager.texture2;
		else if (questionSet == 8) tr = resourcesManager.texture1;
		else if (questionSet == 9) tr = resourcesManager.texture2;
		else if (questionSet == 10) tr = resourcesManager.texture4;
		
		else if (questionSet == 12) tr = resourcesManager.texture3;
		else if (questionSet == 13) tr = resourcesManager.texture5;
		else if (questionSet == 14) tr = resourcesManager.texture8;
		else if (questionSet == 15) tr = resourcesManager.texture6;
		else if (questionSet == 16) tr = resourcesManager.texture1;
		
		else if (questionSet == 18) tr = resourcesManager.texture1;
		else if (questionSet == 19) tr = resourcesManager.texture9;
		else if (questionSet == 20) tr = resourcesManager.texture2;
		else if (questionSet == 21) tr = resourcesManager.texture1;
		else if (questionSet == 22) tr = resourcesManager.texture10;
		
		else if (questionSet == 24) tr = resourcesManager.texture2;
		else if (questionSet == 25) tr = resourcesManager.texture1;
		else if (questionSet == 26) tr = resourcesManager.texture7;
		else if (questionSet == 27) tr = resourcesManager.texture1;
		else if (questionSet == 28) tr = resourcesManager.texture6;
		return tr;
	}

	private ITextureRegion choice1() {
		if (questionSet == 0)
			r = resourcesManager.texture5;
		else if (questionSet == 1)
			r = resourcesManager.texture2;
		else if (questionSet == 2)
			r = resourcesManager.texture1;
		else if (questionSet == 3)
			r = resourcesManager.texture5;
		else if (questionSet == 4)
			r = resourcesManager.texture6;
		
		else if (questionSet == 6) r = resourcesManager.texture6;
		else if (questionSet == 7) r = resourcesManager.texture5;
		else if (questionSet == 8) r = resourcesManager.texture2;
		else if (questionSet == 9) r = resourcesManager.texture3;
		else if (questionSet == 10) r = resourcesManager.texture1;
		
		else if (questionSet == 12) r = resourcesManager.texture6;
		else if (questionSet == 13) r = resourcesManager.texture9;
		else if (questionSet == 14) r = resourcesManager.texture2;
		else if (questionSet == 15) r = resourcesManager.texture3;
		else if (questionSet == 16) r = resourcesManager.texture5;
		
		else if (questionSet == 18) r = resourcesManager.texture10;
		else if (questionSet == 19) r = resourcesManager.texture3;
		else if (questionSet == 20) r = resourcesManager.texture8;
		else if (questionSet == 21) r = resourcesManager.texture2;
		else if (questionSet == 22) r = resourcesManager.texture8;
		
		else if (questionSet == 24) r = resourcesManager.texture5;
		else if (questionSet == 25) r = resourcesManager.texture3;
		else if (questionSet == 26) r = resourcesManager.texture1;
		else if (questionSet == 27) r = resourcesManager.texture6;
		else if (questionSet == 28) r = resourcesManager.texture7;
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
			r = resourcesManager.texture1;
		
		else if (questionSet == 6) r = resourcesManager.texture4;
		else if (questionSet == 7) r = resourcesManager.texture4;
		else if (questionSet == 8) r = resourcesManager.texture5;
		else if (questionSet == 9) r = resourcesManager.texture1;
		else if (questionSet == 10) r = resourcesManager.texture5;
		
		else if (questionSet == 12) r = resourcesManager.texture5;
		else if (questionSet == 13) r = resourcesManager.texture1;
		else if (questionSet == 14) r = resourcesManager.texture6;
		else if (questionSet == 15) r = resourcesManager.texture2;
		else if (questionSet == 16) r = resourcesManager.texture3;
		
		else if (questionSet == 18) r = resourcesManager.texture5;
		else if (questionSet == 19) r = resourcesManager.texture6;
		else if (questionSet == 20) r = resourcesManager.texture4;
		else if (questionSet == 21) r = resourcesManager.texture4;
		else if (questionSet == 22) r = resourcesManager.texture7;
		
		else if (questionSet == 24) r = resourcesManager.texture10;
		else if (questionSet == 25) r = resourcesManager.texture6;
		else if (questionSet == 26) r = resourcesManager.texture8;
		else if (questionSet == 27) r = resourcesManager.texture3;
		else if (questionSet == 28) r = resourcesManager.texture2;
		
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
		
		else if (questionSet == 6) r = resourcesManager.texture8;
		else if (questionSet == 7) r = resourcesManager.texture8;
		else if (questionSet == 8) r = resourcesManager.texture7;
		else if (questionSet == 9) r = resourcesManager.texture6;
		else if (questionSet == 10) r = resourcesManager.texture2;
		
		else if (questionSet == 12) r = resourcesManager.texture9;
		else if (questionSet == 13) r = resourcesManager.texture4;
		else if (questionSet == 14) r = resourcesManager.texture1;
		else if (questionSet == 15) r = resourcesManager.texture1;
		else if (questionSet == 16) r = resourcesManager.texture2;
		
		else if (questionSet == 18) r = resourcesManager.texture2;
		else if (questionSet == 19) r = resourcesManager.texture7;
		else if (questionSet == 20) r = resourcesManager.texture6;
		else if (questionSet == 21) r = resourcesManager.texture6;
		else if (questionSet == 22) r = resourcesManager.texture9;
		
		else if (questionSet == 24) r = resourcesManager.texture1;
		else if (questionSet == 25) r = resourcesManager.texture9;
		else if (questionSet == 26) r = resourcesManager.texture2;
		else if (questionSet == 27) r = resourcesManager.texture2;
		else if (questionSet == 28) r = resourcesManager.texture5;

		return r;
	}

	/*
	 * POSITIONS 250, 350, 450, 550
	 */

	private int setCorrectSpritePosition() {
		if (questionSet == 0)
			pos = 350;
		else if (questionSet == 1)
			pos = 250;
		else if (questionSet == 2)
			pos = 350;
		else if (questionSet == 3)
			pos = 550;
		else if (questionSet == 4)
			pos = 450;
		
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
		if (questionSet == 0)
			pos = 250;
		else if (questionSet == 1)
			pos = 350;
		else if (questionSet == 2)
			pos = 250;
		else if (questionSet == 3)
			pos = 250;
		else if (questionSet == 4)
			pos = 250;
		
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
		if (questionSet == 0)
			pos = 550;
		else if (questionSet == 1)
			pos = 550;
		else if (questionSet == 2)
			pos = 550;
		else if (questionSet == 3)
			pos = 450;
		else if (questionSet == 4)
			pos = 550;
		
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
