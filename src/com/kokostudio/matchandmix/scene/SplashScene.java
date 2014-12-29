package com.kokostudio.matchandmix.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;
import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SplashScene extends BaseScene {
	
	//private Sprite splash;

	@Override
	public void createScene() {
		setBackground(new Background(Color.YELLOW));
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
		//splash.detachSelf();
		//splash.dispose();
		this.detachSelf();
		this.dispose();
	}
	
}
