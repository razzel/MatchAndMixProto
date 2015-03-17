package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class LoadingScene extends BaseScene {
	
	private Sprite BG;
	private AnimatedSprite loading;
	
	private Thread t;

	@Override
	public void createScene() {
		BG = new Sprite(400, 240, resourcesManager.loadingBGTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		};
		attachChild(BG);
		
		loading = new AnimatedSprite(400, 240, resourcesManager.loadingTexture, vbom);
		loading.animate(400);
		attachChild(loading);
		
		engine.registerUpdateHandler(new TimerHandler(5f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				unregisterUpdateHandler(pTimerHandler);
				//ResourcesManager.getInstance().loadGameResources();
				SceneManager.getInstance().createPlayMenuScene();
				disposeScene();
			}
		}));
		
		/*
		t = new Thread() {
			public void run() {
				try {
					sleep(5000);
					SceneManager.getInstance().createPlayMenuScene();
					disposeScene();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		*/
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOADING;
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

}
