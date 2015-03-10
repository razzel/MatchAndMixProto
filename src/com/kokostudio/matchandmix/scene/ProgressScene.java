package com.kokostudio.matchandmix.scene;

import java.text.DecimalFormat;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
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
	private Sprite progressLegend;
	private Sprite remarkStar;
	private Sprite remarkVGood;
	private Sprite remarkGood;
	private Sprite remarkFair;
	
	private myDatabase db;
	
	private DecimalFormat df;
	
	// TEXT
	private Text colorAnswered;
	private Text colorRemaining;
	private Rectangle colorGraph;
	
	private Text matchAnswered;
	private Text matchRemaining;
	
	private Text solveAnswered;
	private Text solveRemaining;
	
	private Text countAnswered;
	private Text countRemaining;
	
	private Text guessAnswered;
	private Text guessRemaining;
	
	private double grade;
	
	private Text t;
	
	float totalRate;
	float totalTry;
	float totalGrade;
	
	@Override
	public void createScene() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		db = new myDatabase(activity);
		df = new DecimalFormat("0.00");
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
		this.dispose();
		this.detachSelf();
		System.gc();
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
					
					disposeScene();
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
		
		// 470
		progressPanel = new Sprite(470,195, resourcesManager.progressPanelTexture, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				pGLState.enableDither();
				super.preDraw(pGLState, pCamera);
			}
			
		};
		attachChild(progressPanel);
		
		progressLegend = new Sprite(100, 230, resourcesManager.progressLegendTexture, vbom);
		attachChild(progressLegend);
		
	}
	
	private void createText() {
		int textX = 520;
		
		// MATCH IT COUNT
		matchAnswered = new Text(textX, 358, resourcesManager.aklatanFont, " " + (db.matchGetAnswered()*100)/25+" %", vbom);
		attachChild(matchAnswered);
		grade = db.getRate(0) / db.getTry(0);
		evaluate(0);
		//matchRemaining = new Text(textX, 330, resourcesManager.aklatanFont, " " + grade, vbom);
		//attachChild(matchRemaining);
		
		// GTML COUNT
		guessAnswered = new Text(textX, 283, resourcesManager.aklatanFont, " " + (db.gtmlGetAnswered()*100)/25+" %", vbom);
		attachChild(guessAnswered);
		grade = db.getRate(1) / db.getTry(1);
		evaluate(1);
		//guessRemaining = new Text(textX, 255, resourcesManager.aklatanFont, " " + db.gtmlGetRemaining(), vbom);
		//attachChild(guessRemaining);
		
		// COUNT IT COUNT
		countAnswered = new Text(textX, 210, resourcesManager.aklatanFont, " "+ (db.countGetAnswered()*100)/25+" %", vbom);
		attachChild(countAnswered);
		grade = db.getRate(3) / db.getTry(3);
		evaluate(3);
		//countRemaining = new Text(textX, 182, resourcesManager.aklatanFont, " " + db.countGetRemaining(), vbom);
		//attachChild(countRemaining);
		
		// SOLVE IT COUNT
		int totalAns = db.solveItAddGetAnswered() + db.solveItSubGetAnswered() + db.solveItMulGetAnswered() + db.solveItDivGetAnswered();
		solveAnswered = new Text(textX, 135, resourcesManager.aklatanFont, " " + totalAns+" %", vbom);
		totalRate = db.getRate(4) + db.getRate(5) + db.getRate(6) + db.getRate(7);
		totalTry = db.getTry(4) + db.getTry(5) + db.getTry(6) + db.getTry(7);
		totalGrade = totalRate / totalTry;
		evaluate(4);
		attachChild(solveAnswered);
				
		// THAT COLOR IS COUNT
		//colorGraph = new Rectangle(560, 68, 20, 20, vbom);
		//colorGraph.setColor(0,0,1);
		//attachChild(colorGraph);
		colorAnswered = new Text(textX, 68, resourcesManager.aklatanFont, " " + (db.colorGetAnswered()*100)/25+" %", vbom);
		attachChild(colorAnswered);
		grade = db.getRate(2) / db.getTry(2);
		evaluate(2);
		//colorRemaining = new Text(textX, 40, resourcesManager.aklatanFont, " " + db.colorGetRemaining(), vbom);
		//attachChild(colorRemaining);
	}
	
	private void evaluate(int id) {
		
		/* 0 - match it
		 * 1 - guess the missing letter
		 * 2 - that color is
		 * 3 - count it
		 * 4 - solveit add
		 * 5 - solveit sub
		 * 6 - solveit mul
		 * 7 - solveit div
		 */
		
		switch(id) {
		case 0:
			if(db.matchGetAnswered()==25) {
				if(roundOffTo2DecimalPlace(grade) >= 0.00 & roundOffTo2DecimalPlace(grade) <= 0.34) {
					// draw excellent remark
					remarkStar = new Sprite(510, 330, resourcesManager.progressExcellent, vbom);
					attachChild(remarkStar);
				} else if (roundOffTo2DecimalPlace(grade) > 0.34 & roundOffTo2DecimalPlace(grade) <= 0.67) {
					remarkVGood = new Sprite(510, 330, resourcesManager.progressVGood, vbom);
					attachChild(remarkVGood);
				} else if (roundOffTo2DecimalPlace(grade) > 0.67 & roundOffTo2DecimalPlace(grade) <= 1.00) {
					remarkGood = new Sprite(510, 330, resourcesManager.progressGood, vbom);
					attachChild(remarkGood);
				} else {
					remarkFair = new Sprite(510, 330, resourcesManager.progressFair, vbom);
					attachChild(remarkFair);
				}
			} else {
				t = new Text(560, 330, resourcesManager.aklatanFont, "UNRATED", vbom);
				attachChild(t);
			}
 			break;
 			
		case 1:
			if(db.gtmlGetAnswered()==25) {
				if(roundOffTo2DecimalPlace(grade) >= 0.00 & roundOffTo2DecimalPlace(grade) <= 0.34) {
					// draw excellent remark
					remarkStar = new Sprite(510, 255, resourcesManager.progressExcellent, vbom);
					attachChild(remarkStar);
				} else if (roundOffTo2DecimalPlace(grade) > 0.34 & roundOffTo2DecimalPlace(grade) <= 0.67) {
					remarkVGood = new Sprite(510, 255, resourcesManager.progressVGood, vbom);
					attachChild(remarkVGood);
				} else if (roundOffTo2DecimalPlace(grade) > 0.67 & roundOffTo2DecimalPlace(grade) <= 1.00) {
					remarkGood = new Sprite(510, 255, resourcesManager.progressGood, vbom);
					attachChild(remarkGood);
				} else {
					remarkFair = new Sprite(510, 255, resourcesManager.progressFair, vbom);
					attachChild(remarkFair);
				}
			} else {
				t = new Text(560, 255, resourcesManager.aklatanFont, "UNRATED", vbom);
				attachChild(t);
			}
			break;
			
		case 2:
			if(db.colorGetAnswered()==25) {
				if(roundOffTo2DecimalPlace(grade) >= 0.00 & roundOffTo2DecimalPlace(grade) <= 0.34) {
					// draw excellent remark
					remarkStar = new Sprite(510, 40, resourcesManager.progressExcellent, vbom);
					attachChild(remarkStar);
				} else if (roundOffTo2DecimalPlace(grade) > 0.34 & roundOffTo2DecimalPlace(grade) <= 0.67) {
					remarkVGood = new Sprite(510, 40, resourcesManager.progressVGood, vbom);
					attachChild(remarkVGood);
				} else if (roundOffTo2DecimalPlace(grade) > 0.67 & roundOffTo2DecimalPlace(grade) <= 1.00) {
					remarkGood = new Sprite(510, 40, resourcesManager.progressGood, vbom);
					attachChild(remarkGood);
				} else {
					remarkFair = new Sprite(510, 40, resourcesManager.progressFair, vbom);
					attachChild(remarkFair);
				}
			} else {
				t = new Text(560, 40, resourcesManager.aklatanFont, "UNRATED", vbom);
				attachChild(t);
			}
			break;
			
		case 3:
			if(db.countGetAnswered()==25) {
				if(roundOffTo2DecimalPlace(grade) >= 0.00 & roundOffTo2DecimalPlace(grade) <= 0.34) {
					// draw excellent remark
					remarkStar = new Sprite(510, 182, resourcesManager.progressExcellent, vbom);
					attachChild(remarkStar);
				} else if (roundOffTo2DecimalPlace(grade) > 0.34 & roundOffTo2DecimalPlace(grade) <= 0.67) {
					remarkVGood = new Sprite(510, 182, resourcesManager.progressVGood, vbom);
					attachChild(remarkVGood);
				} else if (roundOffTo2DecimalPlace(grade) > 0.67 & roundOffTo2DecimalPlace(grade) <= 1.00) {
					remarkGood = new Sprite(510, 182, resourcesManager.progressGood, vbom);
					attachChild(remarkGood);
				} else {
					remarkFair = new Sprite(510, 182, resourcesManager.progressFair, vbom);
					attachChild(remarkFair);
				}
			} else {
				t = new Text(560, 182, resourcesManager.aklatanFont, "UNRATED", vbom);
				attachChild(t);
			}
			break;
			
		case 4:
			int totalAns = db.solveItAddGetAnswered() + db.solveItSubGetAnswered() + db.solveItMulGetAnswered() + db.solveItDivGetAnswered();
			if(totalAns == 100) {
				if(roundOffTo2DecimalPlace(totalGrade) >= 0.00 & roundOffTo2DecimalPlace(totalGrade) <= 0.34) {
					// draw excellent remark
					remarkStar = new Sprite(510, 113, resourcesManager.progressExcellent, vbom);
					attachChild(remarkStar);
				} else if (roundOffTo2DecimalPlace(totalGrade) > 0.34 & roundOffTo2DecimalPlace(totalGrade) <= 0.67) {
					remarkVGood = new Sprite(510, 113, resourcesManager.progressVGood, vbom);
					attachChild(remarkVGood);
				} else if (roundOffTo2DecimalPlace(totalGrade) > 0.67 & roundOffTo2DecimalPlace(totalGrade) <= 1.00) {
					remarkGood = new Sprite(510, 113, resourcesManager.progressGood, vbom);
					attachChild(remarkGood);
				} else {
					remarkFair = new Sprite(510, 113, resourcesManager.progressFair, vbom);
					attachChild(remarkFair);
				}
			} else {
				t = new Text(560, 113, resourcesManager.aklatanFont, "UNRATED", vbom);
				attachChild(t);
			}
			break;
		}
	}

	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		return super.onSceneTouchEvent(pSceneTouchEvent);
	}
	
	private double roundOffTo2DecimalPlace(double d) {
		return (double)Math.round(d * 100) / 100;
	}
	
	// =============================================================================================
	// DATABASE SECTION
	// =============================================================================================
	

	
}