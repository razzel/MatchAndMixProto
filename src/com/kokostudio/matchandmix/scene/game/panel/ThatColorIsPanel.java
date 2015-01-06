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
	
	// DATABASE
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
		questionPlank = new Sprite(240, 70, resourcesManager.color_questionPlankTextureRegion, vbom);
		attachChild(BG);
		attachChild(questionPlank);
	}
	
	private void createButtons() {
		back = new TiledSprite(60, 430, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
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
		question = new Sprite(250, 240, question(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					question.setScale(1.1f);
					break;
				case TouchEvent.ACTION_UP:
					question.setScale(1.0f);
					// PLAY THE SOUND OF THE QUESTION
					Log.d("database", isAnswred(questionSet));
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(question);
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
					lock();
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
	
	public void update(int id, String s) {
		db.updateThatColorIs(id, s);
		db.close();
	}
	
	public String isAnswred(int id) {
		String s = db.isAnswered(id);
		db.close();
		return s;
	}
	
	public void checkStatus() {
		String cmp = db.isAnswered(questionSet);
		if(cmp.compareTo("true") == 0) {
			lock();
		}
		else return;
	}
	
	public void lock() {
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
		unregisterTouchArea(correctSprite);
		correctSprite.setCurrentTileIndex(1);
	}
	
	/* positions
	 * 400 pos1
	 * 320 pos2
	 * 240 pos3
	 * 160 pos4
	 * 80 pos5
	 */
	
	public int correctSpritePosition() {
		if(questionSet == 0) pos = 160;
		
		else if (questionSet == 1) pos = 320;
		
		else if (questionSet == 2) pos = 240;
			
		return pos;
	}
	public int setColor1Position() {
		if(questionSet == 0) pos = 400;
		
		else if (questionSet == 1) pos = 400;
		
		else if (questionSet == 2) pos = 400;
		
		return pos;
	}
	public int setColor2Position() {
		if(questionSet == 0) pos = 320;
		
		else if (questionSet == 1) pos = 240;
		
		else if (questionSet == 2) pos = 320;
		
		return pos;
	}
	public int setColor3Position() {
		if(questionSet == 0) pos = 240;
		
		else if (questionSet == 1) pos = 160;
		
		else if (questionSet == 2) pos = 160;
		
		return pos;
	}
	public int setColor4Position() {
		if(questionSet == 0) pos = 80;
		
		else if (questionSet == 1) pos = 80;
		
		else if (questionSet == 2) pos = 80;
		
		return pos;
	}
	
	// TEXTURES
	public ITextureRegion question() {
		ITextureRegion questionRegion = null;
		if(questionSet == 0) questionRegion = resourcesManager.heartTexture;
		
		else if (questionSet == 1) questionRegion = resourcesManager.starTexture;
		
		else if (questionSet == 2) questionRegion = resourcesManager.squareTexture;
		
		return questionRegion;
	}
	
	public TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		if(questionSet == 0) r = resourcesManager.redTextureRegion;
		else if (questionSet == 1) r = resourcesManager.yellowTextureRegion;
		else if (questionSet == 2) r = resourcesManager.blueTextureRegion;
		
		return r;
	}
	
	public ITextureRegion color1() {
		if(questionSet == 0) r = resourcesManager.yellowTextureRegion;
		
		else if (questionSet == 1)  r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.violetTextureRegion;
		
		return r;
	}
	
	public ITextureRegion color2() {
		if(questionSet == 0) r = resourcesManager.blueTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.pinkTextureRegion;
		
		return r;
	}
	
	public ITextureRegion color3() {
		if(questionSet == 0) r = resourcesManager.pinkTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.orangeTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.redTextureRegion;
		
		return r;
	}
	
	public ITextureRegion color4() {
		if(questionSet == 0) r = resourcesManager.greenTextureRegion;
		
		else if (questionSet == 1) r = resourcesManager.redTextureRegion;
		
		else if (questionSet == 2) r = resourcesManager.greenTextureRegion;
		
		return r;
	}
	
}