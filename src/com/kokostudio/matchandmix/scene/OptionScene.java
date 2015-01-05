package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class OptionScene extends BaseScene {
	
	private TiledSprite back;
	private Sprite on;
	private Sprite off;
	private Sprite optionBoard;
	
	myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();
		checkSound();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public void onMenuKeyPressed() {
		
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_OPTION;
	}
	
	@Override
	public void disposeScene() {
		
	}
	
	// ----------------------------------------------------------------------------------------------------
	// CLASS LOGIC
	// ----------------------------------------------------------------------------------------------------
	
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
					back.setCurrentTileIndex(0);
					back.setScale(1.0f);
					// unload options textures
					ResourcesManager.getInstance().unloadOptionTexture();
					// load the previous scene
					SceneManager.getInstance().loadMainMenuScene();
					
					// TEST DB
					String result = isSoundOn();
					Log.d("DATABASE","results = " +result);
					
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
		
		optionBoard = new Sprite(400,240, resourcesManager.optionBoardTextureRegion, vbom);
		attachChild(optionBoard);
		
		// create on and off button	
		on = new Sprite(490, 210, resourcesManager.onTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					on.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					on.setScale(1.0f);
					toggleButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(on);
		attachChild(on);
		
		off = new Sprite(490,  210, resourcesManager.offTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					off.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					off.setScale(1.0f);
					toggleButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(off);
		attachChild(off);
	}
	

	
	// ========================================================================================================================
	// DATABASE SECTION 
	// ========================================================================================================================
	
	private String isSoundOn() {
		String s = db.isSoundOn();
		db.close();
		return s;
	}
	
	private void updateSound(String s) {
		db.updateSound(s);
		db.close();
	}
	
	private void checkSound() {
		String cmp = isSoundOn();
		if(cmp.compareTo("true") == 0) {
			on.setVisible(true);
			off.setVisible(false);		
		}
		else {
			on.setVisible(false);
			off.setVisible(true);
		}
	}
	
	private void toggleButton() {
		String cmp = isSoundOn();
		if(cmp.compareTo("true") == 0) {
			// if the sound is ON turn it OFF
			updateSound("false");
			if(on.isVisible()) {
				on.setVisible(false);
				off.setVisible(true);
			}
			engine.getSoundManager().setMasterVolume(0.0f);
			engine.getMusicManager().setMasterVolume(0.0f);			
		}
		else {
			// if the sound is OFF turn it ON
			updateSound("true");
			if(off.isVisible()) {
				off.setVisible(false);
				on.setVisible(true);
			}
			engine.getSoundManager().setMasterVolume(1.0f);
			engine.getMusicManager().setMasterVolume(1.0f);	
		}
	}
	
}
