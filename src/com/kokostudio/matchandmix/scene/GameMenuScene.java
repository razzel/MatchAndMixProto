package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ClickDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.GameActivity;
import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class GameMenuScene extends BaseScene implements IScrollDetectorListener, IOnSceneTouchListener {
	
	private TiledSprite guess, matchIt, solveIt, countIt, whatColor, backToMenu;
	private TiledSprite next, prev;
	private Sprite gameHeader;
	
	private Sprite menuLeft;
	private Sprite menuRight; 
	
	public SurfaceScrollDetector scrollDetector;
	public ClickDetector clickDetector;
	
	public float minX = 0;
	public float maxX = 0;
	public float currentX = 0;
	
	private TiledSprite gameMenuSelection[];
	
	private float mDownXCurrent;
	private float mDownYCurrent;
	private float mDownXLast;
	private float mDownYLast;
	
	private TiledSprite back;

	@Override
	public void createScene() {
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createGameHeader();
		/*
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
		*/
		
		this.scrollDetector = new SurfaceScrollDetector(this);
		this.setOnSceneTouchListener(this);
		
		resourcesManager.selectagame.play();
		
		createMenuBoxes();
		createParallaxBackground();
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
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
		
		back = new TiledSprite(45, 40,resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					disposeScene();
					resourcesManager.selectagame.stop();
					camera.setCenter(GameActivity.CAMERA_WIDTH / 2, GameActivity.CAMERA_HEIGHT / 2);
					SceneManager.getInstance().loadMainMenuScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
		
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
					
					disposeScene();
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
					
					disposeScene();
					// Switch the scene
					SceneManager.getInstance().loadCountItScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(matchIt);
		registerTouchArea(countIt);
		attachChild(matchIt);
		attachChild(countIt);
	}
	
	private void createMenuBoxes() {
		int spriteX = 150;
		int spriteY = 210;
		
		gameMenuSelection = new TiledSprite[5];
		TiledTextureRegion[] gameMenuSelectionTexture = {
			resourcesManager.matchItTextureRegion,
			resourcesManager.guessTextureRegion,
			resourcesManager.countItTextureRegion,
			resourcesManager.solveITTextureRegion,
			resourcesManager.thatColorIsTextureRegion		
		};
		
		for(int ctr = 0; ctr < gameMenuSelectionTexture.length; ctr++) {
			final int index = ctr;
			
			gameMenuSelection[ctr] = new TiledSprite(spriteX, spriteY, gameMenuSelectionTexture[ctr], vbom) {
				
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(0.9f);
						this.setCurrentTileIndex(1);	
						mDownXCurrent = pSceneTouchEvent.getX();
						mDownYCurrent = pSceneTouchEvent.getY();
						mDownXLast = mDownXCurrent;
						mDownYLast = mDownYCurrent;
						break;
					case TouchEvent.ACTION_UP:	
						if(mDownXLast == mDownXCurrent & mDownYCurrent == mDownYLast)
							setTheScene(index);
						break;
					case TouchEvent.ACTION_MOVE:
						mDownXLast = pSceneTouchEvent.getX();
						mDownYLast = pSceneTouchEvent.getY();
						break;
					}
						
					return false;
				}	
			};
			engine.runOnUpdateThread(new Runnable() {
				@Override
				public void run() {
					attachChild(gameMenuSelection[index]);
					registerTouchArea(gameMenuSelection[index]);
					gameMenuSelection[index].setZIndex(0);
					sortChildren();
					setTouchAreaBindingOnActionDownEnabled(true);
					setTouchAreaBindingOnActionMoveEnabled(true);
				}
			});
			spriteX += 280;
		}
		
		maxX = spriteX - GameActivity.CAMERA_WIDTH;
		
		// LEFT AND RIGHT OF MENU				
		menuLeft = new Sprite(45, 200, resourcesManager.leftTexture, vbom);			
		attachChild(menuLeft);
		menuLeft.setZIndex(1);
		menuRight = new Sprite(755, 200, resourcesManager.rightTexture, vbom);			
		attachChild(menuRight);	
		menuRight.setZIndex(1);
	}
	
	private void createParallaxBackground() {
		ParallaxBackground bg = new ParallaxBackground(0,0,0);
		bg.attachParallaxEntity(new ParallaxEntity(0, new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom)));
		setBackground(bg);
	}
	
	private void setTheScene(int i) {
		/*
		 * Match it
		 * guess the missing letter
		 * count it
		 * solve it
		 * that color is
		 */
		
		switch(i) {
		case 0:
			resourcesManager.selectagame.stop();
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadMatchItScene();
			break;
		case 1:
			resourcesManager.selectagame.stop();
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadGTMLScene();
			break;
		case 2:
			resourcesManager.selectagame.stop();
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadCountItScene();
			break;
		case 3:
			resourcesManager.selectagame.stop();
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadSolveItMenuScene();
			break;
		case 4:
			resourcesManager.selectagame.stop();
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadThatColorIsScene();
			break;
		}
	}
	
	private void resetCamera() {
		camera.setCenter(GameActivity.CAMERA_WIDTH / 2, GameActivity.CAMERA_HEIGHT / 2);
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		this.scrollDetector.onTouchEvent(pSceneTouchEvent);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.sortChildren();
		
		Log.d("currentX", "currentX = " +currentX);
		Log.d("currentX", "minX = " +minX);
		Log.d("currentX", "maxX = " +maxX);
		
		return true;
	}
	
	@Override
	public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		//maxX = 1030;
		if (camera.getXMin() <= 15)
			menuLeft.setVisible(false);
		else
			menuLeft.setVisible(true);

		if (camera.getXMin() > 690 - 15)
			menuRight.setVisible(false);
		else
			menuRight.setVisible(true);

		// Return if ends are reached
		if (((currentX - pDistanceX) < minX)) {
			return;

		} else if (((currentX - pDistanceX) > 690)) {
			return;
		}

		// Center camera to the current point
		this.camera.offsetCenter(-pDistanceX, 0);
		currentX -= pDistanceX;

		menuLeft.setPosition(this.camera.getCenterX()
				- GameActivity.CAMERA_WIDTH / 2 + 45, 200);
		menuRight.setPosition(this.camera.getCenterX()
				+ GameActivity.CAMERA_WIDTH / 2 - 45, 200);
		gameHeader.setPosition(this.camera.getCenterX()
				- GameActivity.CAMERA_WIDTH / 2 + 400, 430);
		
		back.setPosition(this.camera.getCenterX() - GameActivity.CAMERA_WIDTH / 2 + 45, 40);

		// Because Camera can have negativ X values, so set to 0
		if (this.camera.getXMin() < 0) {
			this.camera.offsetCenter(0, 0);
			currentX = 0;
		}
	}

	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		for(int i = 0; i < gameMenuSelection.length; i++) {
			final int index = i;
			gameMenuSelection[index].setScale(1.0f);
			gameMenuSelection[index].setCurrentTileIndex(0);
		}
		
	}
	

	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}


}
