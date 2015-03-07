package com.kokostudio.matchandmix.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class HtpSolveScene extends BaseScene {
	
	private TiledSprite back;
	private Sprite htpHeader;
	
	private TiledSprite htp;
	
	private TiledSprite next;
	private TiledSprite prev;
	
	private int currentTile;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		currentTile = 0;
		
		createBackground();
		createButtons();
		createHTP();
		
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_HTPMATCH;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		
	}
	
	// -----------------------------------------------------------
	// ENTITIES
	// ----------------------------------------------------------
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}		
		});
			
		htpHeader = new Sprite(400, 430, resourcesManager.htpHeader, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		};
		attachChild(htpHeader);
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					SceneManager.getInstance().loadHowToPlayScene();
					break;
				}
				return true;
			}
		};
		registerTouchArea(back);
		attachChild(back);
		
		next = new TiledSprite(660, 200, resourcesManager.nextTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					currentTile++;
					htp.setCurrentTileIndex(currentTile);
					
					if(currentTile == 1) {
						//next.setVisible(false);
						//htpScene.unregisterTouchArea(next);
						
						prev.setVisible(true);
						registerTouchArea(prev);
						
						//OK.setVisible(true);
						//htpScene.registerTouchArea(OK);
					} else if (currentTile == 2) {
						next.setVisible(false);
						unregisterTouchArea(next);
						
					}
					resourcesManager.click.play();
					next.setScale(1.0f);
					next.setCurrentTileIndex(0);
				} else if (pSceneTouchEvent.isActionDown()) {
					next.setScale(0.9f);
					next.setCurrentTileIndex(1);
				} 
				return true;
			}
			
		};
		registerTouchArea(next);
		attachChild(next);
		
		prev = new TiledSprite(120, 200, resourcesManager.prevTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					currentTile--;
					htp.setCurrentTileIndex(currentTile);
					
					if(currentTile == 0) {
						next.setVisible(true);
						registerTouchArea(next);
						
						prev.setVisible(false);
						unregisterTouchArea(prev);
					}
					
					resourcesManager.click.play();
					prev.setScale(1.0f);
					prev.setCurrentTileIndex(0);
				} else if (pSceneTouchEvent.isActionDown()) {
					prev.setScale(0.9f);
					prev.setCurrentTileIndex(1);
				}
				return true;
			}
		};
		prev.setVisible(false);
		attachChild(prev);
		
	}
	
	private void createHTP() {
		htp = new TiledSprite(400, 200, resourcesManager.addHTP, vbom);
		attachChild(htp);
	}

}
