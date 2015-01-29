package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.modifier.IModifier;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class PlayMenuScene extends BaseScene {
	
	private TiledSprite play;
	
	private myDatabase db;
	
	@Override
	public void createScene() {
		db = new myDatabase(activity);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createButton();
		checkSFX();
		checkBGM();
	}

	@Override
	public void onBackKeyPressed() {
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
		this.detachSelf();
		this.dispose();	
	}
	
	// =================================================================================
	// CLASS LOGIC
	// =================================================================================
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.playMenuBackgroundTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
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
					play.setCurrentTileIndex(0);
					play.setScale(1.0f);
					disposeScene();
					break;
				}
				return true;
			}	
		};
		registerTouchArea(play);
		attachChild(play);
	}	
	
	private void checkBGM() {
		if(db.isBGMOn().compareTo("true") == 0) {
			engine.getMusicManager().setMasterVolume(0.70f);
		} else {
			engine.getMusicManager().setMasterVolume(0.0f);
		}
		resourcesManager.bgm.play();
		resourcesManager.bgm.setVolume(1.0f);
	}
	private void checkSFX() {
		if(db.isSFXOn().compareTo("true") == 0) {
			engine.getSoundManager().setMasterVolume(1.0f);
		} else {
			engine.getSoundManager().setMasterVolume(0.0f);
		}
	}
}
