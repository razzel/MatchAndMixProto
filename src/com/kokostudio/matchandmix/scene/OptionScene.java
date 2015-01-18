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
	
	private Sprite sfxOn;
	private Sprite sfxOff;
	
	private Sprite bgmOn;
	private Sprite bgmOff;
	
	private Sprite optionBoard;
	
	myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();
		checkBGM();
		checkSFX();
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
					String result = isBGMOn();
					Log.d("DATABASE","results = " +result);
					
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
		
		optionBoard = new Sprite(440,210, resourcesManager.optionBoardTextureRegion, vbom);
		attachChild(optionBoard);
		
		// ON AND OFF FOR BGM
		// create on and off button	
		bgmOn = new Sprite(440, 233, resourcesManager.onTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					bgmOn.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					bgmOn.setScale(1.0f);
					toggleBGMButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(bgmOn);
		attachChild(bgmOn);
		
		bgmOff = new Sprite(440,  233, resourcesManager.offTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					bgmOff.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					bgmOff.setScale(1.0f);
					toggleBGMButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(bgmOff);
		attachChild(bgmOff);
		
		// ON AND OFF FOR SFX
		sfxOn = new Sprite(440, 167, resourcesManager.onTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					sfxOn.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					sfxOn.setScale(1.0f);
					toggleSFXButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(sfxOn);
		attachChild(sfxOn);
		
		sfxOff = new Sprite(440,  167, resourcesManager.offTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					sfxOff.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					sfxOff.setScale(1.0f);
					toggleSFXButton();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(sfxOff);
		attachChild(sfxOff);
		
	}
	

	
	// ========================================================================================================================
	// DATABASE SECTION 
	// ========================================================================================================================
	
	private String isBGMOn() {
		String s = db.isBGMOn();
		db.close();
		return s;
	}
	
	private void updateBGM(String s) {
		db.updateBGM(s);
		db.close();
	}
	
	private String isSFXOn() {
		String s = db.isSFXOn();
		db.close();
		return s;
	}
	
	private void updateSFX(String s) {
		db.updateSFX(s);
		db.close();
	}
	
	private void checkBGM() {
		String cmp = isBGMOn();
		if(cmp.compareTo("true") == 0) {
			bgmOn.setVisible(true);
			bgmOff.setVisible(false);		
		}
		else {
			bgmOn.setVisible(false);
			bgmOff.setVisible(true);
		}
	}
	
	private void checkSFX() {
		String cmp = isSFXOn();
		if(cmp.compareTo("true") == 0) {
			sfxOn.setVisible(true);
			sfxOff.setVisible(false);		
		}
		else {
			sfxOn.setVisible(false);
			sfxOff.setVisible(true);
		}
	}
	
	private void toggleBGMButton() {
		String cmp = isBGMOn();
		if(cmp.compareTo("true") == 0) {
			// if the sound is ON turn it OFF
			updateBGM("false");
			if(bgmOn.isVisible()) {
				bgmOn.setVisible(false);
				bgmOff.setVisible(true);
			}
			engine.getMusicManager().setMasterVolume(0.0f);	
		}
		else {
			// if the sound is OFF turn it ON
			updateBGM("true");
			if(bgmOff.isVisible()) {
				bgmOff.setVisible(false);
				bgmOn.setVisible(true);
			}
			engine.getMusicManager().setMasterVolume(0.70f);
		}
	}
	
	private void toggleSFXButton() {
		String cmp = isSFXOn();
		if(cmp.compareTo("true") == 0) {
			// if the sound is ON turn it OFF
			updateSFX("false");
			if(sfxOn.isVisible()) {
				sfxOn.setVisible(false);
				sfxOff.setVisible(true);
			}
			engine.getSoundManager().setMasterVolume(0.0f);	
		}
		else {
			// if the sound is OFF turn it ON
			updateSFX("true");
			if(sfxOff.isVisible()) {
				sfxOff.setVisible(false);
				sfxOn.setVisible(true);
			}
			engine.getSoundManager().setMasterVolume(1.0f);
		}
	}	
}
