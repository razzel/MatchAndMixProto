package com.kokostudio.matchandmix.scene.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SolveItMenu extends BaseScene {
	
	private Sprite qHeader;
	private TiledSprite back;
	private TiledSprite add,divide,sub,multi;
	
	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createGameSelection();
		createButtons();
		

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
		return SceneType.SCENE_SOLVEITMENU;
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
		
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
					// set scene
					SceneManager.getInstance().loadGameMenuScene();
					break;
				}
				return true;
			}
		};
		registerTouchArea(back);
		attachChild(back);		
	}
	private void createGameSelection() {
		
		// ADD
		add = new TiledSprite(400, 420, resourcesManager.addTiledTexture, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
			
		};
		add.registerEntityModifier(new MoveModifier(0.5f, -200, 420, 400, 420));
		//registerTouchArea(add);
		attachChild(add);
		
		// SUB
		sub = new TiledSprite(400, 300, resourcesManager.subTiledTexture, vbom);
		sub.registerEntityModifier(new MoveModifier(0.5f, 1000, 300, 400, 300));
		attachChild(sub);
		
		// MULTIPLY
		multi = new TiledSprite(400, 180, resourcesManager.multiTiledTexture, vbom);
		multi.registerEntityModifier(new MoveModifier(0.5f, -200, 180, 400, 180));
		attachChild(multi);
		
		// DIVIDE
		divide = new TiledSprite(400,  60, resourcesManager.divTiledTexture, vbom);
		divide.registerEntityModifier(new MoveModifier(0.5f, 1000, 60, 400, 60));
		attachChild(divide);

	
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