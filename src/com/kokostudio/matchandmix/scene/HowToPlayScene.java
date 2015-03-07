package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class HowToPlayScene extends BaseScene {
	
	private Sprite htpHeader;
	
	private TiledSprite htpMatch;
	private TiledSprite htpGTML;
	private TiledSprite htpColor;
	private TiledSprite htpCount;
	private TiledSprite htpSolve;
	
	private TiledSprite back;
	
	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButtons();
		createHTPSelection();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_HOWTOPLAY;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// -------------------------------------------------------------------
	// ENTITIES
	// -------------------------------------------------------------------
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		});
			
		htpHeader = new Sprite(400, 430, resourcesManager.htpHeader, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		attachChild(htpHeader);
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadMainMenuScene();
					break;
				}
				return true;
			}
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	private void createHTPSelection() {
		htpMatch = new TiledSprite(400, 350, resourcesManager.btnMatchHTP, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					htpMatch.setScale(0.9f);
					htpMatch.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHtpMatch();
					break;
				}
				return true;
			}		
		};
		registerTouchArea(htpMatch);
		attachChild(htpMatch);
		
		htpGTML = new TiledSprite(400, 270, resourcesManager.btnGTMLHTP, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					htpGTML.setScale(0.9f);
					htpGTML.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHtpGTML();
					break;
				}
				return true;
			}
		};
		registerTouchArea(htpGTML);
		attachChild(htpGTML);
		
		htpCount = new TiledSprite(400, 190, resourcesManager.btnCountHTP, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					htpCount.setScale(0.9f);
					htpCount.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHtpCount();
					break;
				}
				return true;
			}
		};
		registerTouchArea(htpCount);
		attachChild(htpCount);
		
		htpSolve = new TiledSprite(400, 110, resourcesManager.btnSolveHTP, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					htpSolve.setScale(0.9f);
					htpSolve.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHtpSolve();
					break;
				}
				return true;
			}
		};
		registerTouchArea(htpSolve);
		attachChild(htpSolve);
		
		htpColor = new TiledSprite(400, 30, resourcesManager.btnColorHTP, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					htpColor.setScale(0.9f);
					htpColor.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHtpColor();
					break;
				}
				return true;
			}
		};
		registerTouchArea(htpColor);
		attachChild(htpColor);
	}
	
	// ---------------------------------------------------------------------
	// METHODS AND FUNCTIONS
	// ---------------------------------------------------------------------
}
