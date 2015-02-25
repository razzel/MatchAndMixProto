package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.CameraScene;
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

public class MainMenuScene extends BaseScene implements IScrollDetectorListener, IOnSceneTouchListener {
	
	private TiledSprite next, prev;
	private TiledSprite games, progress, howTo, about, options, exit;
	
	private Sprite menuLeft;
	private Sprite menuRight;
	private Sprite menuHeader;
	
	private CameraScene exitConfirmationScene;
	private Sprite exitPanel;
	private TiledSprite yes;
	private TiledSprite no;

	public SurfaceScrollDetector scrollDetector;
	public ClickDetector clickDetector;
	
	public float minX = 0;
	public float maxX = 0;
	public float currentX = 0;
	
	private TiledSprite[] menuSelectionTiledSprite;
	
	private float mDownXCurrent;
	private float mDownYCurrent;
	private float mDownXLast;
	private float mDownYLast;
	
	@Override
	public void createScene() {
		this.scrollDetector = new SurfaceScrollDetector(this);
		this.setOnSceneTouchListener(this);
		
		
		createMenuHeader();
		createMenuBoxes();
		createParallaxBackground();
		createExitPanel();
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		/* //UNCOMMENT THIS SECTION TO REMOVE THE SCROLL IN THE GAME
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
		*/
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
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// ===============================================================================================================================
	// CLASS LOGIC
	// ===============================================================================================================================
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		});
	}
	
	private void createMenuHeader() {
		menuHeader = new Sprite(400, 430, resourcesManager.menuHeaderTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		attachChild(menuHeader);
	}
	
	private void createButtons() {
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
					registerTouchArea(next); //<-- dagdag mo ito
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
	
	private void createMenuSelection() {
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
					disposeScene();
					
					// LOAD THE GAME MENU SCENE in the SceneManager
					SceneManager.getInstance().loadGameMenuScene();
					break;
				}
				return true;
			}	
		};
		
		progress = new TiledSprite(400, 200, resourcesManager.progressTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					progress.setCurrentTileIndex(1);
					progress.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					
					disposeScene();
					// set scene
					SceneManager.getInstance().loadProgressScene();
					break;
				}
				return true;
			}
			
		};
		
		howTo = new TiledSprite(615, 200, resourcesManager.howtoTiledTextureRegion, vbom);
		
		
		registerTouchArea(games);
		registerTouchArea(progress);
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
					
					disposeScene();
					
					// set the scene to option
					SceneManager.getInstance().loadOptionScene();
					break;
				}
				return true;
			}		
		};
		
		exit = new TiledSprite(615, 200, resourcesManager.exitTiledTextureRegion, vbom){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					exit.setCurrentTileIndex(1);
					exit.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();

					System.exit(0);
					break;
				}
				return true;
			}		
		};
		
		registerTouchArea(options);
		attachChild(about);
		attachChild(options);
		attachChild(exit);
		
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		//this.clickDetector.onTouchEvent(pSceneTouchEvent);
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
		if(camera.getXMin()<=15)
         	menuLeft.setVisible(false);
         else
         	menuLeft.setVisible(true);
    	 
    	 if(camera.getXMin()>1200-15)
             menuRight.setVisible(false);
         else
        	 menuRight.setVisible(true);
    	 
         //Return if ends are reached
     	 if ( ((currentX - pDistanceX) < minX)) {
     		return;
     		
     	 } else if( ((currentX - pDistanceX) > 1200)) {
     		return;
     	 }
     		 
    	 
        //Center camera to the current point
        this.camera.offsetCenter(-pDistanceX, 0);
        currentX -= pDistanceX;

        menuLeft.setPosition(this.camera.getCenterX()- GameActivity.CAMERA_WIDTH/2 + 45 ,200);
        menuRight.setPosition(this.camera.getCenterX()+ GameActivity.CAMERA_WIDTH/2 - 45, 200);
        menuHeader.setPosition(this.camera.getCenterX() - GameActivity.CAMERA_WIDTH/2 +400, 430);
       
        //Because Camera can have negativ X values, so set to 0
    	if(this.camera.getXMin()<0){
    		this.camera.offsetCenter(0,0);
    		currentX = 0;
    	}
    	
	}
	
	private void createMenuBoxes() {
		int spriteX = 150;
		int spriteY = 200;
		
		menuSelectionTiledSprite = new TiledSprite[7];
		TiledTextureRegion[] menuSelectionTexture = {
				resourcesManager.gamesTiledTextureRegion, 
				resourcesManager.progressTiledTextureRegion,
				resourcesManager.howtoTiledTextureRegion, 
				resourcesManager.aboutTiledTextureRegion,
				resourcesManager.creditsTiledTextureRegion,
				resourcesManager.optionTiledTextureRegion, 
				resourcesManager.exitTiledTextureRegion
				};
		for(int ctr = 0; ctr < menuSelectionTexture.length; ctr++) {
			final int index = ctr;
			
			menuSelectionTiledSprite[ctr] = new TiledSprite(spriteX, spriteY, menuSelectionTexture[ctr], vbom) {
				
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
					attachChild(menuSelectionTiledSprite[index]);
					registerTouchArea(menuSelectionTiledSprite[index]);
					menuSelectionTiledSprite[index].setZIndex(0);
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
		menuLeft.setVisible(false);
	}
	
	private void createParallaxBackground() {
		ParallaxBackground bg = new ParallaxBackground(0,0,0);
		bg.attachParallaxEntity(new ParallaxEntity(0, new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom)));
		setBackground(bg);
	}
	
	private void createExitPanel() {
		exitConfirmationScene = new CameraScene(camera);
		
		exitPanel = new Sprite(400, 240, resourcesManager.exitPanelTexture, vbom);		
		yes = new TiledSprite(300, 170, resourcesManager.resetYesTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					yes.setScale(0.9f);
					yes.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					System.exit(0);
					break;
				}
				return true;
			}
		};
		
		no = new TiledSprite(500, 170, resourcesManager.resetNoTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					no.setScale(0.9f);
					no.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					no.setScale(1.0f);
					no.setCurrentTileIndex(0);
					MainMenuScene.this.clearChildScene();
					break;
				}
				return true;
			}
		};
		
		exitPanel.setZIndex(0);
		yes.setZIndex(1);
		no.setZIndex(1);
		
		exitConfirmationScene.registerTouchArea(yes);
		exitConfirmationScene.registerTouchArea(no);
		
		exitConfirmationScene.attachChild(exitPanel);
		exitConfirmationScene.attachChild(yes);
		exitConfirmationScene.attachChild(no);
		exitConfirmationScene.setBackgroundEnabled(false);
		
		exitConfirmationScene.sortChildren();
	}
	
	private void setTheScene(int i) {
		/*
		 * game
		 * progress
		 * how to
		 * about
		 * credits
		 * option
		 * exit
		 */
		switch(i) {
		case 0:
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadGameMenuScene();
			break;
		case 1:
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadProgressScene();
			break;
		case 2:
			resourcesManager.click.play();
			//resetCamera();
			// HOW TO PLAY
			break;
		case 3:
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadaboutScene();
			break;
		case 4:
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadCreditScene();
			break;
		case 5:
			resourcesManager.click.play();
			resetCamera();
			SceneManager.getInstance().loadOptionScene();
			break;
		case 6:
			resourcesManager.click.play();
			MainMenuScene.this.setChildScene(exitConfirmationScene, false, true, true );
			break;
		}
	}
	
	private void resetCamera() {
		camera.setCenter(GameActivity.CAMERA_WIDTH / 2, GameActivity.CAMERA_HEIGHT / 2);
	}
	
	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
	}
	
	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		
		for(int i = 0; i < menuSelectionTiledSprite.length; i++) {
			final int index = i;
			menuSelectionTiledSprite[index].setScale(1.0f);
			menuSelectionTiledSprite[index].setCurrentTileIndex(0);
		}
		
	}

}
