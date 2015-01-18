package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.color.Color;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GuessTheMissingLetterPanel extends BaseScene {
	
	// variable in which what set of question will the DB will retrieve
	private static int questionSet;
	
	// this will determine if the questionSet is already answered
	private boolean answered;
	
	// Sprites
	private TiledSprite back;
	
	// TEXT
	private Text test;
	
	// DATABASE
	private myDatabase myDB;
	
	private Sprite BG;
	
	private ITextureRegion r;
	private int pos;
	
	// QUESTIONS AND CHOICES
	private Sprite questionImage;
	private TiledSprite question;
	private Sprite plank;
	private Sprite c1, c2, c3, c4;
	private TiledSprite correctSprite;
	
	private myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();	
		createQuestion();
		createChoices();
		checkStatus();
		questionImage.setScale(0.8f);
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
		return SceneType.SCENE_GTMLPANEL;
	}

	@Override
	public void disposeScene() {
		
	}
	
	// ======================================================================================
	// CLASS LOGIC
	// ======================================================================================
	
	private void createBackground() {
		attachChild(new Sprite(400, 240,resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		});
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
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					// unload the PANEL'S TEXUTRES / RESOURCES
					resourcesManager.unloadGTMLPanelTextures();
					// then set the SCENE to GuessTheMissingLetter
					SceneManager.getInstance().loadGTMLScene();
					break;
				}
				return true;
			}		
		};
		registerTouchArea(back);
		attachChild(back);
		
	}
	
	public void createQuestion() {
		// create the plank
		plank = new Sprite(400, 130, resourcesManager.gtmlQuestionPad, vbom);
		attachChild(plank);
		
		// create question image
		questionImage = new Sprite(400, 290, questionImage(), vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					questionImage.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					questionImageSound().play();
					questionImage.setScale(0.8f);
				}
				return true;
			}
			
		};
		registerTouchArea(questionImage);
		attachChild(questionImage);
		
		// create the question (WORD with "?" mark)
		question = new TiledSprite(400, 130, question(), vbom);
		attachChild(question);
	}
	
	public void createChoices() {
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
					questionImageSound().play();
					update(questionSet, "true");
					lock();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(correctSprite);
		attachChild(correctSprite);
		
		c1 = new Sprite(letter1Position(),  50, letter1Sprite(), vbom) {
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
		
		c2 = new Sprite(letter2Position(),  50, letter2Sprite(), vbom) {
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
		
		c3 = new Sprite(letter3Position(),  50, letter3Sprite(), vbom) {
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
		
		c4 = new Sprite(letter4Position(),  50, letter4Sprite(), vbom) {
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
		registerTouchArea(c4);
		attachChild(c4);
		
	}
	
	private void lock() {
		correctSprite.setCurrentTileIndex(1);
		question.setCurrentTileIndex(1);
		unregisterTouchArea(correctSprite);
		unregisterTouchArea(c1);
		unregisterTouchArea(c2);
		unregisterTouchArea(c3);
		unregisterTouchArea(c4);
	}
	
	// ======================================================================================
	// DATABASE SECTION
	// ======================================================================================
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	public void update(int id, String s) {
		db.updateGTML(id, s);
		db.close();
	}
	
	public String isAnswered(int id) {
		String s = db.gtmlIsAnswered(id);
		db.close();
		return s;
	}
	
	public void checkStatus() {
		String cmp = db.gtmlIsAnswered(questionSet);
		if(cmp.compareTo("true") == 0) {
			lock();
		}
		else return;
	}
	
	// SOUND
	private Sound questionImageSound() {
		Sound s = null;
		
		if(questionSet == 0) s = resourcesManager.apple;
		
		else if(questionSet == 1) s = resourcesManager.avocado;
		
		return s;
	}
	
	// TEXTURE REGIONS
	private ITextureRegion questionImage() {
		
		if(questionSet == 0) r = resourcesManager.appleTexture;
		
		else if (questionSet == 1) r = resourcesManager.avocadoTexture;
		
		return r;
	}
	
	private TiledTextureRegion question() {
		TiledTextureRegion r = null;
		
		if(questionSet == 0) r = resourcesManager.appleQuestionTexture;
		
		else if (questionSet == 1) r = resourcesManager.avocadoQuestionTexture;
		
		return r;
	}
	
	// choices
	private TiledTextureRegion correctAnswerSprite() {
		TiledTextureRegion r = null;
		
		if(questionSet == 0) r = resourcesManager.aTexture;
		
		else if (questionSet == 1) r = resourcesManager.dTexture;
		
		return r;
	}
	
	private ITextureRegion letter1Sprite() {
		
		if(questionSet == 0) r = resourcesManager.hTexture;
		
		else if (questionSet == 1) r = resourcesManager.gTexture;
		
		return r;
	}
	
	private ITextureRegion letter2Sprite() {
		
		if(questionSet == 0) r = resourcesManager.oTexture;
		
		else if(questionSet == 1) r = resourcesManager.hTexture;
		
		return r;
	}
	
	private ITextureRegion letter3Sprite() {
		
		if(questionSet == 0) r = resourcesManager.eTexture;
		
		else if (questionSet == 1) r = resourcesManager.zTexture;
		
		return r;
	}
	
	private ITextureRegion letter4Sprite() {
		
		if(questionSet == 0) r = resourcesManager.iTexture;
		
		else if (questionSet == 1) r = resourcesManager.tTexture; 
		
		return r;
	}
	
	// POSISTIONS
	/*
	 *  FROM LEFT TO RIGHT OF THE SCREEN YOU ARE LOOKING
	 *  200, 300, 400, 500, 600
	 * 
	 */
	private int correctSpritePosition() {
		
		if(questionSet == 0) pos = 400;
		
		else if (questionSet == 1) pos = 300;
		
		return pos;
	}
	
	private int letter1Position() {
		
		if(questionSet == 0) pos = 200;
		
		else if (questionSet == 1) pos = 200;
		
		return pos;
	}
	
	private int letter2Position() {
		
		if(questionSet == 0) pos = 300;
		
		else if (questionSet == 1) pos = 400;
				
		return pos;
	}
	
	private int letter3Position() {
		
		if(questionSet == 0) pos = 500;
		
		else if (questionSet == 1) pos = 500;
		
		return pos;
	}
	
	private int letter4Position() {
		
		if(questionSet == 0) pos = 600;
		
		else if (questionSet == 1) pos = 600;
		
		return pos;
	}
	
}
