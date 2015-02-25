package com.kokostudio.matchandmix.scene.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;
import com.kokostudio.matchandmix.scene.game.panel.SolveItMulPanel;

public class SolveItMultiplication extends BaseScene {
	
	private Sprite qHeader;
	private TiledSprite[] qFrames;
	private TiledSprite back;
	private AnimatedSprite tap;
	
	private int x, y, rowCounter;
	
	private myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		checkIsFirstTime();
		createBackground();
		createButtons();
		createQuestionHeader();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SOLVEITSUB;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		
	}
	
	// =========================================================================
	// CLASS LOGIC
	// =========================================================================
	
	private void createBackground() {
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
				qFrames[i] = new TiledSprite(x, y, frameIsAnswered(index).compareTo("false")==0 ? resourcesManager.notAnsweredTextureRegion : resourcesManager.answeredTextureRegion, vbom) {
					@Override
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
						switch(pSceneTouchEvent.getAction()) {
						case TouchEvent.ACTION_DOWN:
							qFrames[index].setScale(0.9f);
							qFrames[index].setCurrentTileIndex(1);
							break;
						case TouchEvent.ACTION_UP:
							resourcesManager.click.play();
							
							// get the index of the pressed frame
							SolveItMulPanel.getQuestionIndex(index);
							// SET SCENE TO COUNT IT PANEL
							SceneManager.getInstance().loadSolveItMulPanelScene();
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
						qFrames[index].registerEntityModifier(new ScaleModifier(0.5f, 0.1f, 1.0f));
						qFrames[index].setZIndex(0);
						sortChildren();
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
					disposeScene();
					// SET SCENE
					SceneManager.getInstance().loadSolveItMenuScene();
					break;
				}
				return true;
			}
			
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	// =========================================================
	// DATABASE 
	// =========================================================
	private String frameIsAnswered(int id) {
		String s = db.solveItMulIsAnswered(id);
		db.close();
		return s;
	}
	
	private void checkIsFirstTime() {
		if(db.checkIsFirstTime(6).compareTo("true") == 0) {
			tap = new AnimatedSprite(340, 340, resourcesManager.tapItTexture, vbom);
			tap.animate(500);
			tap.setZIndex(1);
			attachChild(tap);
			for(int i = 1; i < 29; i++) {
				final int index = i;
				engine.runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						unregisterTouchArea(qFrames[index]);	
					}
				});			
			}
		}
	}
}

