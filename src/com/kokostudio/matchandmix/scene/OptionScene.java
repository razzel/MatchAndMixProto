package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.ease.EaseBounceOut;

import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

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
	
	private Sprite bg;
	
	private Sprite optionBoard;
	
	// RESET
	private TiledSprite reset; // button
	private TiledSprite yes;
	private TiledSprite no;
	private Sprite resetPanel;
	
	private CameraScene resetScene;
	
	myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();
		checkBGM();
		checkSFX();
		createResetPanel();
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
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// ----------------------------------------------------------------------------------------------------
	// CLASS LOGIC
	// ----------------------------------------------------------------------------------------------------
	
	private void createBackground() {
		bg = new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		};
		attachChild(bg);
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
		
		// OPTION BOARD
		optionBoard = new Sprite(440,210, resourcesManager.optionBoardTextureRegion, vbom);
		attachChild(optionBoard);
		
		// RESET BUTTON
		reset = new TiledSprite(380, 100, resourcesManager.resetTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					reset.setScale(0.9f);
					reset.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					reset.setScale(1.0f);
					reset.setCurrentTileIndex(0);
					OptionScene.this.setChildScene(resetScene, false, true, true);
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(reset);
		attachChild(reset);
		
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
	
	private void createResetPanel() {
		resetScene = new CameraScene(camera);
		
		
		resetPanel = new Sprite(400, 240, resourcesManager.resetPanelTextureRegion, vbom);
		yes = new TiledSprite(350, 190, resourcesManager.resetYesTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					yes.setScale(0.9f);
					yes.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					yes.setScale(1.0f);
					yes.setCurrentTileIndex(0);
					resetGames();
					break;
				}
				return true;
			}
		};
		
		no = new TiledSprite(450, 190, resourcesManager.resetNoTextureRegion, vbom) {
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
					OptionScene.this.clearChildScene();
					break;
				}
				return true;
			}
		};
		
		resetPanel.setZIndex(0);
		yes.setZIndex(1);
		no.setZIndex(1);
		
		resetScene.registerTouchArea(yes);
		resetScene.registerTouchArea(no);
		
		resetScene.attachChild(yes);
		resetScene.attachChild(no);
		resetScene.attachChild(resetPanel);
		resetScene.setBackgroundEnabled(false);
		
		resetScene.sortChildren();
	}
	
	// ========================================================================================================================
	// DATABASE SECTION 
	// ========================================================================================================================
	
	private void resetGames() {
		// RESET GUESS THE MISSING LETTER
		for(int i = 0; i < 29; i++) {
			db.updateGTML(i, "false");
		}
		db.updateGTML(5, "true");
		db.updateGTML(11, "true");
		db.updateGTML(17, "true");
		db.updateGTML(23, "true");
		
		
		// BACK TO MAIN MENU SCENE
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, "The Games has been successfully reset", Toast.LENGTH_SHORT).show();
				
				engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						unregisterUpdateHandler(pTimerHandler);
						SceneManager.getInstance().loadMainMenuScene();
					}
				}));
			}
		});
		
		
	}
	
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
