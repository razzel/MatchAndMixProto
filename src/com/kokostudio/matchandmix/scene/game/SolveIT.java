package com.kokostudio.matchandmix.scene.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class SolveIT extends BaseScene {
	private Sprite qHeader;
	private TiledSprite back;
	private TiledSprite[] qFrames;
	
	private int x, y, rowCounter;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createQuestionHeader();
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
	
	private void createQuestionHeader() {
		qHeader = new Sprite(400, 430, resourcesManager.qHeaderTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		attachChild(qHeader);
		
		// CREATE QUESTION FRAMES
		qFrames = new TiledSprite[29];
		x = 190; y = 340; rowCounter = 0;
		for(int i = 0; i < qFrames.length; i++) {
			final int index = i;
			if(rowCounter < 5) {
				qFrames[i] = new TiledSprite(x, y, resourcesManager.notAnsweredTextureRegion, vbom) {
					@Override
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
						switch(pSceneTouchEvent.getAction()) {
						case TouchEvent.ACTION_DOWN:
							qFrames[index].setScale(0.9f);
							qFrames[index].setCurrentTileIndex(1);
							break;
						case TouchEvent.ACTION_UP:
							resourcesManager.click.play();
							qFrames[index].setScale(1.0f);
							qFrames[index].setCurrentTileIndex(0);
							break;
						}
						return true;
					}
					
				};
				
				engine.runOnUpdateThread(new Runnable() {

					@Override
					public void run() {
						registerTouchArea(qFrames[index]);
						attachChild(qFrames[index]);
					}
					
				});
				x += 110;
				rowCounter++;
			}
			else if(rowCounter == 5) {
				y -= 75;
				x = 190;
				rowCounter = 0;
			}
		}
	}
	
	
	
	private void createButtons() {
		back = new TiledSprite(45, 40,resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setScale(0.9f);
					back.setCurrentTileIndex(1);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					back.setScale(1.0f);
					back.setCurrentTileIndex(0);
					// unload THAT COLOR IS Textures
					ResourcesManager.getInstance().unloadThatColorIsTextures();
					// SET SCENE
					SceneManager.getInstance().loadGameMenuScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
	}
}
