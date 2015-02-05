package com.kokostudio.matchandmix.scene.game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.database.myDatabase;
import com.kokostudio.matchandmix.manager.SceneManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;
import com.kokostudio.matchandmix.scene.game.panel.ThatColorIsPanel;

public class ThatColorIs extends BaseScene {
	
	private Sprite qHeader;
	private TiledSprite back;
	public static TiledSprite[] qFrames;
	
	private int x, y, rowCounter;
	
	private myDatabase db;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		createBackground();
		createQuestionHeader();
		createButtons();
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
		return SceneType.SCENE_THATCOLORIS;
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// -------------------------------------------------------------------
	// CLASS LOGIC
	// ------------------------------------------------------------------
	
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
		//qHeader.registerEntityModifier(new ScaleModifier(0.5f, 0.1f, 1.0f));
		attachChild(qHeader);
		
		// CREATE QUESTION FRAMES
		qFrames = new TiledSprite[29];
		x = 190; y = 340; rowCounter = 0;
		for(int i = 0; i < qFrames.length; i++) {
			final int index = i;
			if(rowCounter < 5) {
				qFrames[i] = new TiledSprite(x, y, frameIsAnswered(index).compareTo("false")==0? resourcesManager.notAnsweredTextureRegion : resourcesManager.answeredTextureRegion, vbom) {
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
							// set the index variable to the THAT COLOR IS Panel's getQuestionIndex();
							ThatColorIsPanel.getQuestionIndex(index);
							// load the THAT COLOR IS Panel
							SceneManager.getInstance().loadThatColorIsPanelScene();	
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
					// unload THAT COLOR IS Textures
					//ResourcesManager.getInstance().unloadThatColorIsTextures();
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
	
	// -----------------------------------------------------
	// DATABASE SECTION
	// -----------------------------------------------------
	private String frameIsAnswered(int i) {
		String s = db.colorIsAnswered(i);
		db.close();
		return s;
	}
}
