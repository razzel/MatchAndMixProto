package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class ProgressScene extends BaseScene {
	
	private TiledSprite back;
	private Sprite progressHeader;
	private Sprite progressPanel;
	
	private myDatabase db;
	
	// TEXT
	private Text colorAnswered;
	private Text colorRemaining;
	
	private Text matchAnswered;
	private Text matchRemaining;
	
	private Text solveAnswered;
	private Text solveRemaining;
	
	private Text countAnswered;
	private Text countRemaining;
	
	private Text guessAnswered;
	private Text guessRemaining;
	
	

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createButtons();
		createProgress();
		createText();
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
		
		progressPanel = new Sprite(410,195, resourcesManager.progressPanelTexture, vbom);
		attachChild(progressPanel);
		
	}
	
	private void createText() {
		colorAnswered = new Text(400, 240, resourcesManager.aklatanFont, "test" , vbom);
		attachChild(colorAnswered);
	}
	
	// =============================================================================================
	// DATABASE SECTION
	// =============================================================================================
	
	
	// THAT COLOR IS
	private int getColorAnswred() {
		int asd = 0;
		return 0;
	}
	
	private int getColorRemaining() {
		return 0;
	}

}
