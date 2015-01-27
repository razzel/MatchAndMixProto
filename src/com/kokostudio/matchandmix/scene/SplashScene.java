package com.kokostudio.matchandmix.scene;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;
import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SplashScene extends BaseScene {
	
	private Sprite splash;

	@Override
	public void createScene() {
		setBackground(new Background(0,0,0));
		splash = new Sprite(400, 240, resourcesManager.SplashTextureRegion, vbom);
		attachChild(splash);
		
		engine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
				resourcesManager.loadGameResources();	
			}
		}));
		
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {	
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene() {
		System.gc();
		splash.detachSelf();
		splash.dispose();
		this.detachSelf();
		this.dispose();
	}
	
}
