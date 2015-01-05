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

public class MainMenuScene extends BaseScene {
	
	private TiledSprite next, prev;
	private TiledSprite games, progress, howTo, about, options, exit;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createMenuHeader();
		createMenuSelection();
		createButtons();
		prev.setVisible(false);
		unregisterTouchArea(prev);
		about.setVisible(false);
		options.setVisible(false);
		exit.setVisible(false);
		
		unregisterTouchArea(options);
	}

	@Override
	public void onBackKeyPressed() {
		//
	}
	
	@Override
	public void onMenuKeyPressed() {
		
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MAINMENU;
	}

	@Override
	public void disposeScene() {
		// dispose and detach all of the TiledSprite declared in this class
	}
	
	// ===============================================================================================================================
	// CLASS LOGIC
	// ===============================================================================================================================
	
	public void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		});
	}
	
	public void createMenuHeader() {
		attachChild(new Sprite(400, 430, resourcesManager.menuHeaderTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		});
	}
	
	public void createButtons() {
		next = new TiledSprite(765, 200, resourcesManager.nextTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					next.setCurrentTileIndex(1);
					next.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					next.setCurrentTileIndex(0);
					next.setScale(1.0f);
					
					// Hide the 1st 3 entities
					next.setVisible(false);
					games.setVisible(false);
					progress.setVisible(false);
					howTo.setVisible(false);
					// then unregister their toucharea
					unregisterTouchArea(next);
					unregisterTouchArea(games);
					unregisterTouchArea(progress);
					unregisterTouchArea(howTo);
					
					// show the 2nd 3 entities
					prev.setVisible(true);
					about.setVisible(true);
					options.setVisible(true);
					exit.setVisible(true);
					// register again their toucharea
					registerTouchArea(prev);
					registerTouchArea(about);
					registerTouchArea(options);
					registerTouchArea(exit);
					
					break;
				}
				return true;
			}
		};
		prev = new TiledSprite(35, 200, resourcesManager.prevTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					prev.setCurrentTileIndex(1);
					prev.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					prev.setCurrentTileIndex(0);
					prev.setScale(1.0f);
					
					// show the 1st 3 entities
					next.setVisible(true);
					games.setVisible(true);
					progress.setVisible(true);
					howTo.setVisible(true);
					// register again their toucharea
					registerTouchArea(games);
					registerTouchArea(progress);
					registerTouchArea(howTo);
					
					// hide the 2nd 3 entities
					prev.setVisible(false);
					about.setVisible(false);
					options.setVisible(false);
					exit.setVisible(false);
					// unregister their toucharea
					unregisterTouchArea(prev);
					unregisterTouchArea(about);
					unregisterTouchArea(options);
					unregisterTouchArea(exit);
					break;
				}
				return true;
			}
		};
		registerTouchArea(next);
		registerTouchArea(prev);
		attachChild(next);
		attachChild(prev);	
	}
	
	public void createMenuSelection() {
		// 1ST MENU
		games = new TiledSprite(185, 200, resourcesManager.gamesTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					games.setCurrentTileIndex(1);
					games.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					// LOAD THE GAME MENU SCENE in the SceneManager
					SceneManager.getInstance().loadGameMenuScene();
					
					games.setCurrentTileIndex(0);
					games.setScale(1.0f);
					break;
				}
				return true;
			}	
		};
		
		progress = new TiledSprite(400, 200, resourcesManager.progressTiledTextureRegion, vbom);
		
		howTo = new TiledSprite(615, 200, resourcesManager.howtoTiledTextureRegion, vbom);
		
		
		registerTouchArea(games);
		attachChild(games);
		attachChild(progress);
		attachChild(howTo);
		
		// 2ND MENU
		about = new TiledSprite(185, 200, resourcesManager.aboutTiledTextureRegion, vbom);
		
		options = new TiledSprite(400, 200, resourcesManager.optionTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					options.setCurrentTileIndex(1);
					options.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					options.setCurrentTileIndex(0);
					options.setScale(1.0f);
					
					// set the scene to option
					SceneManager.getInstance().loadOptionScene();
					break;
				}
				return true;
			}		
		};
		
		exit = new TiledSprite(615, 200, resourcesManager.exitTiledTextureRegion, vbom);
		
		registerTouchArea(options);
		attachChild(about);
		attachChild(options);
		attachChild(exit);
		
	}

}
