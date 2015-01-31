package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GameMenuScene extends BaseScene {
	
	private TiledSprite guess, matchIt, solveIt, countIt, whatColor, backToMenu;
	private TiledSprite next, prev;
	private Sprite gameHeader;
	

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createGameHeader();
		createButtons();
		createGameSelection();
		prev.setVisible(false);
		unregisterTouchArea(prev);
		matchIt.setVisible(false);
		countIt.setVisible(false);
		backToMenu.setVisible(false);
		unregisterTouchArea(matchIt);
		unregisterTouchArea(countIt);
		unregisterTouchArea(backToMenu);
	}

	@Override
	public void onBackKeyPressed() {
		// unload the GameMenuTextures
		//ResourcesManager.getInstance().unloadGameMenuTexture();
		// then reload the MAIN MENU Scene
		
	}

	@Override
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAMEMENU;
	}
	

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
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
	
	private void createGameHeader() {
		gameHeader = new Sprite(400, 430, resourcesManager.gameHeaderTextureReion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		}; 
		attachChild(gameHeader);
		//gameHeader.registerEntityModifier(new MoveModifier(.5f, 1600, 430, 400, 430));
	}
	
	private void createButtons() {
		int y = 200;
		next = new TiledSprite(765, y, resourcesManager.nextTiledTextureRegion, vbom) {
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
					guess.setVisible(false);
					solveIt.setVisible(false);
					whatColor.setVisible(false);
					// then unregister their toucharea
					unregisterTouchArea(next);
					unregisterTouchArea(guess);
					unregisterTouchArea(solveIt);
					unregisterTouchArea(whatColor);
					
					// show the 2nd 3 entities
					prev.setVisible(true);
					countIt.setVisible(true);
					matchIt.setVisible(true);
					backToMenu.setVisible(true);
					//exit.setVisible(true);
					// register again their toucharea
					registerTouchArea(prev);
					registerTouchArea(countIt);
					registerTouchArea(matchIt);
					registerTouchArea(backToMenu);
					//registerTouchArea(exit);
					
					break;
				}
				return true;
			}
		};
		prev = new TiledSprite(35, y, resourcesManager.prevTiledTextureRegion, vbom) {
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
					guess.setVisible(true);
					solveIt.setVisible(true);
					whatColor.setVisible(true);
					// register again their toucharea
					registerTouchArea(next);
					registerTouchArea(guess);
					registerTouchArea(solveIt);
					registerTouchArea(whatColor);
					
					// hide the 2nd 3 entities
					prev.setVisible(false);
					matchIt.setVisible(false);
					countIt.setVisible(false);
					backToMenu.setVisible(false);
					//exit.setVisible(false);
					// unregister their toucharea
					unregisterTouchArea(prev);
					unregisterTouchArea(matchIt);
					unregisterTouchArea(countIt);
					unregisterTouchArea(backToMenu);
					//unregisterTouchArea(exit);
					break;
				}
				return true;
			}
		};
		registerTouchArea(next);
		registerTouchArea(prev);
		attachChild(next);
		attachChild(prev);	
		/*
		back = new TiledSprite(60,40, resourcesManager.backTiledTextureRegion, vbom) {
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
		attachChild(back); */
	}
	
	private void createGameSelection() {
		int y = 200;
		// GUESS THE MISSING LETTER
		guess = new TiledSprite(185, y, resourcesManager.guessTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					guess.setCurrentTileIndex(1);
					guess.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					guess.setCurrentTileIndex(0);
					guess.setScale(1.0f);
					
					disposeScene();
					// Load the GuessTheMissingLetter Scene
					SceneManager.getInstance().loadGTMLScene();
					break;
				}
				return true;
			}
			
		};
		
		// THAT COLOR IS
		whatColor = new TiledSprite(400, y, resourcesManager.thatColorIsTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					whatColor.setScale(0.9f);
					whatColor.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					whatColor.setScale(1.0f);
					whatColor.setCurrentTileIndex(0);
					disposeScene();
					// Switch the scene
					SceneManager.getInstance().loadThatColorIsScene();
					break;
				}
				return true;
			}
			
		};
		
		// SOLVE IT
		solveIt = new TiledSprite(615, y, resourcesManager.solveITTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					solveIt.setScale(0.9f);
					solveIt.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					solveIt.setScale(1.0f);
					solveIt.setCurrentTileIndex(0);
					//disposeScene();
					// Switch the scene
					SceneManager.getInstance().loadSolveItMenuScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(guess);
		registerTouchArea(whatColor);
		registerTouchArea(solveIt);
		attachChild(whatColor);
		attachChild(solveIt);
		attachChild(guess);
		
		// 2nd GAME MENU
		// MATCH IT
		matchIt = new TiledSprite(185, y, resourcesManager.matchItTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					matchIt.setCurrentTileIndex(1);
					guess.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					matchIt.setScale(1.0f);
					matchIt.setCurrentTileIndex(0);
					disposeScene();
					// Load the GuessTheMissingLetter Scene
					SceneManager.getInstance().loadMatchItScene();
					break;
				}
				return true;
			}		
		};
		
		
		// THAT COLOR IS
		countIt = new TiledSprite(400, y, resourcesManager.countItTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					countIt.setScale(0.9f);
					countIt.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					countIt.setScale(1.0f);
					countIt.setCurrentTileIndex(0);
					disposeScene();
					// Switch the scene
					SceneManager.getInstance().loadCountItScene();
					break;
				}
				return true;
			}
			
		};
		backToMenu = new TiledSprite(615, y, resourcesManager.backToMainTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					backToMenu.setScale(0.9f);
					backToMenu.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					backToMenu.setScale(1.0f);
					backToMenu.setCurrentTileIndex(0);
					resourcesManager.click.play();
					SceneManager.getInstance().loadMainMenuScene();
					break;
				}
				return true;
			}
			
		};
		
		registerTouchArea(backToMenu);
		registerTouchArea(matchIt);
		registerTouchArea(countIt);
		attachChild(backToMenu);
		attachChild(matchIt);
		attachChild(countIt);
	}

}
