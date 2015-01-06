package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class ProgressScene extends BaseScene {
	
	private TiledSprite back;
	private Sprite progressHeader;
	private Sprite solveItStat;
	
	private Text color_text;
	

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButtons();
		createProgress();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_PROGRESS;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		
	}
	
	// ============================================================================================
	// CLASS LOGIC
	// ============================================================================================
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		});
	}
	
	private void createButtons() {
		back = new TiledSprite(60, 45, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					back.setCurrentTileIndex(0);
					back.setScale(1.0f);
					// unload progress textures
					ResourcesManager.getInstance().unloadProgressResources();
					// set scene
					SceneManager.getInstance().loadMainMenuScene();
					break;
				}
				return true;
			}
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	private void createProgress() {
		// CREATE PROGRESS HEADER
		progressHeader = new Sprite(400, 430, resourcesManager.progressHeaderTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		attachChild(progressHeader);
		
		solveItStat = new Sprite(400, 240, resourcesManager.solveItStatTexture, vbom);
		attachChild(solveItStat);
	}
	
	// =============================================================================================
	// DATABASE SECTION
	// =============================================================================================
	
	private int getColorAnswred(String s) {
		return 0;
	}

}
