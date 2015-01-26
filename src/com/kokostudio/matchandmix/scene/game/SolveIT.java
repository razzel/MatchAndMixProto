package com.kokostudio.matchandmix.scene.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SolveIT extends BaseScene {
	private Sprite qHeader;
	private TiledSprite back;
	private TiledSprite add,divide,sub,multi;
	
	
	private int x, y, rowCounter;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createGameSelection();
		

	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onMenuKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SOLVEIT;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	private void createBackground(){
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		});
		
	}
	private void createGameSelection() {
	
		// ADD
		add = new TiledSprite(200, 200, resourcesManager.addTiledTexture, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					add.setCurrentTileIndex(1);
					add.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					add.setCurrentTileIndex(0);
					add.setScale(1.0f);
					// Load the GuessTheMissingLetter Scene
					//SceneManager.getInstance().loadGTMLScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(add);
		attachChild(add);
	

	
	// divide
		/*
	divide = new TiledSprite(400, 400, resourcesManager.divTiledTexture, vbom) {
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()) {
			case TouchEvent.ACTION_DOWN:
				divide.setCurrentTileIndex(1);
				divide.setScale(0.9f);
				break;
			case TouchEvent.ACTION_UP:
				resourcesManager.click.play();
				divide.setCurrentTileIndex(0);
				divide.setScale(1.0f);
				// Load the GuessTheMissingLetter Scene
				//SceneManager.getInstance().loadGTMLScene();
				break;
			}
			return true;
		}
		
	};
	registerTouchArea(divide);
	attachChild(divide); */

	}	
}