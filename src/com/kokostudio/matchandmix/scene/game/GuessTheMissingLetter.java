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
import com.kokostudio.matchandmix.scene.game.panel.GuessTheMissingLetterPanel;

public class GuessTheMissingLetter extends BaseScene {
	
	private Sprite qHeader;
	private TiledSprite[] qFrames;
	private TiledSprite back;
	
	private int x, y, rowCounter;
	
	private myDatabase db;
	
	private AnimatedSprite tap; // <-- this is supposed to be an AnimatedSprite. Just for testing only
	
	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		checkIsFirstTime();
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
		return SceneType.SCENE_GTML;
	}

	@Override
	public void disposeScene() {
		this.dispose();
		this.detachSelf();
		System.gc();
	}
	
	// =====================================================================================================
	// CLASS LOGIC
	// =====================================================================================================
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.bgTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}	
		});
	}
	
	// In this method, we will also create the question frames
	private void createQuestionHeader() {
		// Create the Question Header Sprite
		qHeader = new Sprite(400, 430, resourcesManager.qHeaderTextureRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
		};
		attachChild(qHeader);
		
		// Create the 5x5 array of question frames
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
							qFrames[index].setCurrentTileIndex(1);
							qFrames[index].setScale(0.9f);
							break;
						case TouchEvent.ACTION_UP:
							resourcesManager.click.play();
							
							// set the index variable to the GTMLPanel's getQuestionIndex()
							GuessTheMissingLetterPanel.getQuestionIndex(index);
							// load the GTML Panel
							SceneManager.getInstance().loadGTMLPanelScene();
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
				y -=75;
				x = 190;
				rowCounter = 0;
			}		
		}
		
	}
	
	private void createButtons() {
		// create the Back Button
		back = new TiledSprite(45, 40, resourcesManager.backTiledTextureRegion, vbom) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					back.setCurrentTileIndex(1);
					back.setScale(0.9f);
					break;
				case TouchEvent.ACTION_UP:
					resourcesManager.click.play();
					disposeScene();
					// unload the GTML textures / resources
					//ResourcesManager.getInstance().unloadGTMLTextures();
					// then reload the Game Menu SCENE
					SceneManager.getInstance().loadGameMenuScene();
					break;
				}
				return true;
			}
		};
		registerTouchArea(back);
		attachChild(back);
	}
	
	//-------------------------------------------------
	// DATABASE SECTION
	// ------------------------------------------------
	private String frameIsAnswered(int i) {
		String s = db.gtmlIsAnswered(i);
		db.close();
		return s;
	}
	
	private void checkIsFirstTime() {
		if(db.checkIsFirstTime(1).compareTo("true") == 0) {
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
