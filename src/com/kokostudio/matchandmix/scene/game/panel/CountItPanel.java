package com.kokostudio.matchandmix.scene.game.panel;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.util.GLState;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

public class CountItPanel extends BaseScene {

	public static int questionSet;
	
	private TiledSprite back;
	private Sprite clueBox;
	private Sprite clue1;
	private Sprite clue2;
	
	private Sprite[] object1;
	private Sprite[] object2;
	
	private Sprite question;
	private Sprite plank;
	private TiledSprite correctSprite;
	private Sprite c1, c2, c3, c4;

	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		createBackground();
		createQuestions();
		createButtons();
		createChoices();
		createObjects();
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_COUNTITPANEL;
	}

	@Override
	public void onMenuKeyPressed() {
		
	}

	@Override
	public void disposeScene() {
		
	}
	
	// ---------------------------------------------------------
	// ENTITIES
	// ---------------------------------------------------------
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.countItBGTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		});
	}
	
	private void createButtons() {
		back = new TiledSprite(45, 430, resourcesManager.backTiledTextureRegion, vbom);
		attachChild(back);
	}
	
	private void createQuestions() { // ALSO CLUE BOX AND CLUES WILL BE CREATED HERE
		// clue box
		clueBox = new Sprite(680, 320, resourcesManager.countItClueBox, vbom);
		attachChild(clueBox);
		
		// clues
		clue1 = new Sprite(680, 370, resourcesManager.countItClueTriangleTexture, vbom);
		attachChild(clue1);
		
		clue2 = new Sprite(680, 270, resourcesManager.countItClueSquareTexture, vbom);
		attachChild(clue2);
		
		// plank
		plank = new Sprite(400, 130, resourcesManager.countItQuestionPad, vbom);
		attachChild(plank);
		
		// questions
		question = new Sprite(400, 130, resourcesManager.countItQuestion1, vbom);
		attachChild(question);
	}
	
	private void createChoices() {
		
		correctSprite = new TiledSprite(200, 50, resourcesManager.texture1, vbom);
		attachChild(correctSprite);
		
		c1 = new Sprite(300, 50, resourcesManager.texture2, vbom);
		attachChild(c1);
		
		c2 = new Sprite(400, 50, resourcesManager.texture3, vbom);
		attachChild(c2);
		
		c3= new Sprite(500, 50, resourcesManager.texture4, vbom);
		attachChild(c3);
		
		c4 = new Sprite(600, 50, resourcesManager.texture5, vbom);
		attachChild(c4);
		
	}
	
	private void createObjects() {
		// ENDED IN TESTING THE COUNT OBJECTS
	}
	
	// --------------------------------------------------------
	// DATABASE
	// --------------------------------------------------------
	
	public static void getQuestionIndex(int i) {
		questionSet = i;
	}

}
