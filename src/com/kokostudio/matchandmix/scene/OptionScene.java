package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import android.util.Log;
import android.widget.Toast;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class OptionScene extends BaseScene {
	
	private TiledSprite back;
	private Sprite onOff;
	//private Sprite off;
	private Sprite optionBoard;
	
	myDatabase db;

	@Override
	public void createScene() {
		db = new myDatabase(activity);
		createBackground();
		createButtons();
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
					back.setCurrentTileIndex(0);
					back.setScale(1.0f);
					// unload options textures
					//ResourcesManager.getInstance().unloadOptionTexture();
					// load the previous scene
					//SceneManager.getInstance().loadMainMenuScene();
					
					// TEST DB
					String result = db.isSoundOn();
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
		
		onOff = new Sprite(490, 210, db.isSoundOn()=="true"?resourcesManager.offTextureRegion : resourcesManager.onTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					onOff.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					onOff.setScale(1.0f);
					toggleButton();
					Log.d("database", db.isSoundOn());
					break;
				}
				return true;
			}	
		};
		registerTouchArea(onOff);
		attachChild(onOff);
		/*
		on = new Sprite(490, 210, resourcesManager.onTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					on.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
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
					off.setScale(1.0f);
					toggleButton();
					break;
				}
				return true;
			}	
		};
		attachChild(off); */
	}
	
	private void toggleButton() {
		if(db.isSoundOn()=="true") {
			// if the sound is ON turn it OFF
			db.updateSound("false");
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(activity, "Update Successful", Toast.LENGTH_SHORT).show();
				}
				
			});
		}
		else {
			// if the sound is OFF turn it ON
			db.updateSound("true");
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(activity, "Update Successful", Toast.LENGTH_SHORT).show();
				}
				
			});
		}
	}
	
}
