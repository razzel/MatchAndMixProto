package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.color.Color;

import android.database.sqlite.SQLiteDatabase;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GuessTheMissingLetterPanel extends BaseScene {
	
	// variable in which what set of question will the DB will retrieve
	private static int questionSet;
	
	// this will determine if the questionSet is already answered
	private boolean answered = false;
	
	// Sprites
	private TiledSprite back;
	
	// TEXT
	private Text test;
	
	// DATABASE
	private myDatabase myDB;
	
	// QUESTIONS AND CHOICES
	private Sprite question;
	private Sprite a, b, c, d;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButtons();	
	}

	@Override
	public void onBackKeyPressed() {
		
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
		setBackground(new Background(Color.BLUE));
		
		// create TEXT
		test = new Text(100, 100, resourcesManager.font, "frame number: "+questionSet,vbom);
		attachChild(test);
		
		question = new Sprite(400, 240, getQuestionRegion(), vbom);
		attachChild(question);
		
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					// unload the PANEL'S TEXUTRES / RESOURCES
					
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
		
	}
	// ======================================================================================
	// DATABASE SECTION
	// ======================================================================================
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}
	
	public static boolean isAnswered(int i) {
		
		
		return false;
	}
	
	private ITextureRegion getQuestionRegion() {
		ITextureRegion questionRegion = null;
		switch(questionSet) {
		case 0:
			questionRegion = resourcesManager.questionTextureRegion;
			break;
		case 1:
			questionRegion = resourcesManager.choiceATExtureRegion;
			break;
		case 2:
			questionRegion = resourcesManager.choiceBTextureRegion;
			break;
			
		}
		return questionRegion;
	}
	
}
