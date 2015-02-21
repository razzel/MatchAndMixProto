package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class AboutScene extends BaseScene {
	
	private Sprite BG;
	private TiledSprite back;
	private Sprite about;

	@Override
	public void createScene() {
		createBackground();
		createAbout();
		createButtons();
		
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_ABOUT;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		System.gc();
		this.dispose();
		this.detachSelf();
	}
	
	// =================================================================
	// CLASS LOGIC
	// =================================================================
	
	private void createBackground() {
		BG = new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		attachChild(BG);
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					
					disposeScene();
					// unload options textures
					ResourcesManager.getInstance().unloadAboutResources();
					// load the previous scene
					SceneManager.getInstance().loadMainMenuScene();
					
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	private void createAbout() {
		about = new Sprite(430, 240, resourcesManager.aboutpanelTextureRegion, vbom);
		attachChild(about);
	}

}
