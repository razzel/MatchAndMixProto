package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GuessTheMissingLetterPanel extends BaseScene {
	
	// variable in which what set of question will the DB will retrieve
	private int questionSet;
	
	// this will determine if the questionSet is already answered
	private boolean answered = false;
	
	// Sprites
	private TiledSprite back;

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
		setBackground(new Background(1.0f *questionSet, 0.8f *questionSet, 0.9f *questionSet));
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
	
	// ======================================================================================
	// DATABASE SECTION
	// ======================================================================================
	
	
	
	// SETTERS & GETTERS
	public void setQuestions(int i) {
		questionSet = i;
	}
	
	public void getQuestions() {
		
	}
	
}
