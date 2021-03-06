package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.ease.EaseElasticOut;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class PlayMenuScene extends BaseScene {
	
	private TiledSprite play;
	
	private myDatabase db;
	
	private Sprite header;
	
	private Sprite bg;
	
	@Override
	public void createScene() {
		
		db = new myDatabase(activity);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		
		this.registerUpdateHandler(new TimerHandler(1, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
				createPlayHeader();
				createButton();
				
			}
		}));
		createBackground();
		
		checkSFX();
		checkBGM();
		/*
		try {
			db = new myDatabase(activity);
			this.setTouchAreaBindingOnActionDownEnabled(true);
			
			this.registerUpdateHandler(new TimerHandler(1, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					unregisterUpdateHandler(pTimerHandler);
					createPlayHeader();
					createButton();
					
				}
			}));
			createBackground();
			
			checkSFX();
			checkBGM();
		} catch (NullPointerException e) {
			Debug.e(e);
		} */
	}

	@Override
	public void onBackKeyPressed() {
		activity.finish();
		System.exit(0);
	}
	
	@Override
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_PLAYMENU;
	}

	@Override
	public void disposeScene() {
		System.gc();
		play.detachSelf();
		play.dispose();
		play = null;
		
		header.detachSelf();
		header.dispose();
		header = null;
		
		bg.detachSelf();
		bg.dispose();
		bg = null;
		
		this.detachSelf();
		this.dispose();	
	}
	
	// =================================================================================
	// CLASS LOGIC
	// =================================================================================
	private void createBackground() {
		bg = new Sprite(400, 240, resourcesManager.playMenuBackgroundTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);			
			}
		};
		attachChild(bg);
	}
	
	private void createPlayHeader() {
		header = new Sprite(410, 330, resourcesManager.playTextTextureRegion, vbom);
		header.registerEntityModifier(new MoveModifier(3, 410, 600, 410, 330, EaseElasticOut.getInstance()));
		
		attachChild(header);
	}
	
	private void createButton() {
		play = new TiledSprite(400, 180, resourcesManager.playTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					play.setCurrentTileIndex(1);
					play.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadMainMenuScene();
					disposeScene();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(play);
		play.registerEntityModifier(new MoveModifier(3, 400, -100, 400, 180, EaseElasticOut.getInstance()));
		attachChild(play);
	}	
	
	private void checkBGM() {
		if(db.isBGMOn().compareTo("true") == 0) {
			engine.getMusicManager().setMasterVolume(0.15f);
		} else {
			engine.getMusicManager().setMasterVolume(0.0f);
		}
		resourcesManager.bgm.play();
		resourcesManager.bgm.setVolume(1.0f);
	}
	private void checkSFX() {
		if(db.isSFXOn().compareTo("true") == 0) {
			engine.getSoundManager().setMasterVolume(1.6f);
		} else {
			engine.getSoundManager().setMasterVolume(0.0f);
		}
	}
}
