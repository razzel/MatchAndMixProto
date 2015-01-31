package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ClickDetector;
import org.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import android.opengl.GLES20;
import android.util.Log;

import com.kokostudio.matchandmix.GameActivity;
import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IScrollDetectorListener, IOnSceneTouchListener, IClickDetectorListener, IOnMenuItemClickListener {
	
	private TiledSprite next, prev;
	private TiledSprite games, progress, howTo, about, options, exit;
	
	private MenuScene scene;
	private final int MENU_YES = 0;
	private final int MENU_NO = 1;
	
	private Sprite menuLeft;
	private Sprite menuRight;
	private Sprite menuHeader;

	public SurfaceScrollDetector scrollDetector;
	public ClickDetector clickDetector;
	
	public float minX = 0;
	public float maxX = 0;
	public float currentX = 0;
	
	private TiledSprite[] menuSelectionTiledSprite;
	
	@Override
	public void createScene() {
		
		//this.scrollDetector = new SurfaceScrollDetector(this);
		//this.clickDetector = new ClickDetector(this);
		
		this.sortChildren();
		//this.setOnSceneTouchListener(this);
		//this.setTouchAreaBindingOnActionMoveEnabled(true);
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		
		//createMenuScene();
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
		/*
		createMenuHeader();
		createMenuBoxes();
		createParallaxBackground();*/
		
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
		menuHeader = new Sprite(400, 430, resourcesManager.menuHeaderTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		//menuHeader.registerEntityModifier(new MoveModifier(.5f, -800, 430, 400, 430));
		attachChild(menuHeader);
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
					disposeScene();
					// LOAD THE GAME MENU SCENE in the SceneManager
					SceneManager.getInstance().loadGameMenuScene();
					games.setCurrentTileIndex(0);
					games.setScale(1.0f);
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
					progress.setCurrentTileIndex(0);
					progress.setScale(1.0f);
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
					options.setCurrentTileIndex(0);
					options.setScale(1.0f);
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
					exit.setCurrentTileIndex(0);
					exit.setScale(1.0f);
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
		/*
		this.clickDetector.onTouchEvent(pSceneTouchEvent);
		this.scrollDetector.onTouchEvent(pSceneTouchEvent);
		
		this.sortChildren();
		this.setOnSceneTouchListener(this);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		
		Log.d("currentX", "currentX = " +currentX);
		Log.d("currentX", "minX = " +minX);
		Log.d("currentX", "maxX = " +maxX);
		*/
		return true;
		
	}
	
	@Override
	public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		maxX = 1030;
		if(camera.getXMin()<=15)
         	menuLeft.setVisible(false);
         else
         	menuLeft.setVisible(true);
    	 
    	 if(camera.getXMin()>maxX-15)
             menuRight.setVisible(false);
         else
        	 menuRight.setVisible(true);
    	 
         //Return if ends are reached
     	 if ( ((currentX - pDistanceX) < minX)) {
     		return;
     		
     	 } else if( ((currentX - pDistanceX) > maxX)) {
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

	@Override
	public void onClick(ClickDetector pClickDetector, int pPointerID, float pSceneX, float pSceneY) {
		
	}
	
	private void createMenuBoxes() {
		int spriteX = 150;
		int spriteY = 200;
		
		menuSelectionTiledSprite = new TiledSprite[6];
		TiledTextureRegion[] menuSelectionTexture = {
				resourcesManager.gamesTiledTextureRegion, 
				resourcesManager.progressTiledTextureRegion,
				resourcesManager.howtoTiledTextureRegion, 
				resourcesManager.aboutTiledTextureRegion, 
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
						menuSelectionTiledSprite[index].setScale(0.9f);
						menuSelectionTiledSprite[index].setCurrentTileIndex(1);
						break;
					case TouchEvent.ACTION_UP:
						menuSelectionTiledSprite[index].setScale(1.0f);
						menuSelectionTiledSprite[index].setCurrentTileIndex(0);
						
						Log.d("position", "menuLeft " + (camera.getCenterX() - GameActivity.CAMERA_WIDTH/2 + 45));
						Log.d("position", "menuRight " + (camera.getCenterX()+ GameActivity.CAMERA_WIDTH/2 - 45));
						Log.d("GAME WIDTH", "test "+GameActivity.CAMERA_WIDTH);
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
				}
			});
			spriteX += 300;
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


	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		
	}
	
	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		
	}
	
	private void createMenuScene() {
		this.scene = new MenuScene(this.camera);
		
		final SpriteMenuItem yes = new SpriteMenuItem(MENU_YES, resourcesManager.nextTiledTextureRegion, vbom);
		yes.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.scene.addMenuItem(yes);
		
		final SpriteMenuItem no = new SpriteMenuItem(MENU_NO, resourcesManager.nextTiledTextureRegion, vbom);
		yes.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.scene.addMenuItem(no);
		
		scene.buildAnimations();
		
		setBackgroundEnabled(false);
		
		scene.setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case MENU_YES:
			System.exit(0);
			return true;
		case MENU_NO:
			scene.reset();
			return true;
		default:
			return false;
		}
	}

}
