package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GameMenuScene extends BaseScene {
	
	private TiledSprite guess, matchIt, solveIt, countIt, whatColor;
	private TiledSprite back, next, prev;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		//createGameHeader();
		createButtons();
		createGameSelection();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAMEMENU;
	}

	@Override
	public void disposeScene() {
		
	}
	
	// =======================================================================================
	// CLASS LOGIC
	// =======================================================================================
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		});
	}
	
	//private void createGameHeader() {}
	
	private void createButtons() {
		back = new TiledSprite(45,40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:	
					back.setCurrentTileIndex(0);
					back.setScale(1.0f);
					// unload the GameMenuTextures
					ResourcesManager.getInstance().unloadGameMenuTexture();
					// then reload the MAIN MENU Scene
					SceneManager.getInstance().loadMainMenuScene();		
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	private void createGameSelection() {
		// GUESS THE MISSING LETTER
		guess = new TiledSprite(400, 240, resourcesManager.guessTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					guess.setCurrentTileIndex(1);
					guess.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					guess.setCurrentTileIndex(0);
					guess.setScale(1.0f);
					// Load the GuessTheMissingLetter Scene
					SceneManager.getInstance().loadGTMLScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(guess);
		attachChild(guess);		
	}
	
}
