package com.kokostudio.matchandmix.manager;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.kokostudio.matchandmix.base.BaseScene;
import com.kokostudio.matchandmix.scene.AboutScene;
import com.kokostudio.matchandmix.scene.CreditScene;
import com.kokostudio.matchandmix.scene.LoadingScene;
import com.kokostudio.matchandmix.scene.MainMenuScene;
import com.kokostudio.matchandmix.scene.GameMenuScene;
import com.kokostudio.matchandmix.scene.OptionScene;
import com.kokostudio.matchandmix.scene.ProgressScene;
import com.kokostudio.matchandmix.scene.PlayMenuScene;
import com.kokostudio.matchandmix.scene.SplashScene;

import com.kokostudio.matchandmix.scene.game.GuessTheMissingLetter;
import com.kokostudio.matchandmix.scene.game.MatchIt;
import com.kokostudio.matchandmix.scene.game.SolveItAddition;
import com.kokostudio.matchandmix.scene.game.SolveItDivision;
import com.kokostudio.matchandmix.scene.game.SolveItMenu;
import com.kokostudio.matchandmix.scene.game.SolveItMultiplication;
import com.kokostudio.matchandmix.scene.game.SolveItSubtraction;
import com.kokostudio.matchandmix.scene.game.ThatColorIs;
import com.kokostudio.matchandmix.scene.game.countIt;
import com.kokostudio.matchandmix.scene.game.panel.CountItPanel;
import com.kokostudio.matchandmix.scene.game.panel.GuessTheMissingLetterPanel;
import com.kokostudio.matchandmix.scene.game.panel.MatchItPanel;
import com.kokostudio.matchandmix.scene.game.panel.ThatColorIsPanel;
import com.kokostudio.matchandmix.scene.game.panel.SolveItAddPanel;
import com.kokostudio.matchandmix.scene.game.panel.SolveItSubPanel;
import com.kokostudio.matchandmix.scene.game.panel.SolveItMulPanel;
import com.kokostudio.matchandmix.scene.game.panel.SolveItDivPanel;

public class SceneManager {
	
	//---------------------------------
	// SCENES
	//---------------------------------
	private BaseScene splashScene;
	private BaseScene playMenuScene;
	private BaseScene mainMenuScene;
	private BaseScene progressScene;
	private BaseScene optionScene;
	private BaseScene aboutScene;
	private BaseScene gameMenuScene;
	private BaseScene creditScene;
	
	private BaseScene loadingScene;
	
	// GAMES
	private BaseScene MatchItScene;
	private BaseScene GuessTheMissingLetterScene;
	private BaseScene CountItScene;
	private BaseScene SolveItMenuScene;
		private BaseScene SolveItAddScene;
		private BaseScene SolveItSubScene;
		private BaseScene SolveItMulScene;
		private BaseScene SolveItDivScene;
			private BaseScene SolveItAddPanel;
			private BaseScene SolveItSubPanel;
			private BaseScene SolveItMulPanel;
			private BaseScene SolveItDivPanel;
	private BaseScene ThatColorIsScene;
	// GAME PANELS
	private BaseScene MatchItPanelScene;
	private BaseScene GTMLPanelScene;
	private BaseScene CountItPanelScene;
	private BaseScene ThatColorIsPanelScene;
	
	//---------------------------------
	// VARIABLES
	//---------------------------------
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SCENE_SPLASH;
	private BaseScene currentScene;
	private Engine engine = ResourcesManager.getInstance().engine;
	
	public enum SceneType {
		SCENE_SPLASH,
		SCENE_PLAYMENU,
		SCENE_MAINMENU,
		SCENE_PROGRESS,
		SCENE_OPTION,
		SCENE_ABOUT,
		SCENE_LOADING,
		SCENE_NEXT,
		SCENE_GAMEMENU,
		SCENE_CREDITS,
		//GAME SCENES
		SCENE_MATCHIT,
		SCENE_GTML,
		SCENE_COUNTIT,
		SCENE_SOLVEITMENU,
		SCENE_SOLVEITADD,
		SCENE_SOLVEITSUB,
		SCENE_SOLVEITMUL,
		SCENE_SOLVEITDIV,
		SCENE_THATCOLORIS,
		
		//GAME PANEL SCENES
		SCENE_MATCHITPANEL,
		SCENE_GTMLPANEL,
		SCENE_COUNTITPANEL,
		SCENE_THATCOLORISPANEL,
		
		SCENE_SOLVEITADDPANEL,
		SCENE_SOLVEITSUBPANEL,
		SCENE_SOLVEITMULPANEL,
		SCENE_SOLVEITDIVPANEL
	}
	
	//----------------------------------
	// CLASS LOGIC
	//----------------------------------
	
	// SPLASH SCENE ==================================================================================================
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
		ResourcesManager.getInstance().loadSplashScene();
		splashScene = new SplashScene();
		currentScene = splashScene;
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
	}
	
	public void disposeSplashScene() {
		ResourcesManager.getInstance().unloadSplashScene();
		splashScene.disposeScene();
		splashScene = null;
	}
	
	// LOADING SCENE
	public void createLoadingScene() {
		disposeSplashScene();
		ResourcesManager.getInstance().loadLoadingScene();
		loadingScene = new LoadingScene();
		setScene(loadingScene);
	}
	
	// PLAY MENU SCENE ======================================================================================================
	public void createPlayMenuScene() {
		//ResourcesManager.getInstance().loadPlayMenuResources();
		playMenuScene = new PlayMenuScene();
		setScene(playMenuScene);
	}
	
	public void loadPlayMenuScene() {
		// dispose the entities in the play menu scene
		//ResourcesManager.getInstance().unloadBackground();
		// load the play menu texture
		ResourcesManager.getInstance().loadPlayMenuTextures();
		setScene(playMenuScene);
	}
	
	// MAIN MENU SCENE ========================================================================================================
	public void loadMainMenuScene() {
		// unload the play menu texture
		ResourcesManager.getInstance().unloadPlayMenuTextures();
		// load the main menu texture
		//ResourcesManager.getInstance().loadMainMenuResources();
		mainMenuScene = new MainMenuScene();
		setScene(mainMenuScene);
	}
	
	// GAME MENU SCENE =========================================================================================================
	public void loadGameMenuScene() {
		gameMenuScene = new GameMenuScene();
		setScene(gameMenuScene);
	}
	
	// OPTION SCENE ============================================================================================================
	public void loadOptionScene() {
		// load the option scene resources
		ResourcesManager.getInstance().loadOptionResources();
		optionScene = new OptionScene();
		setScene(optionScene);
	}
	
	public void loadProgressScene() {
		// load progress texture
		ResourcesManager.getInstance().loadProgressResources();
		// set scene
		progressScene = new ProgressScene();
		setScene(progressScene);
	}
	
	// ABOUT ================================================================================================================
	public void loadaboutScene() {
		// unload the play menu texture
		//ResourcesManager.getInstance().unloadMainMenuTextures();	
		// load the main menu texture
		ResourcesManager.getInstance().loadAboutResources();
		aboutScene = new AboutScene();
		setScene(aboutScene);
	}
	
	// CREDITS
	public void loadCreditScene() {
		ResourcesManager.getInstance().loadCredits();
		creditScene = new CreditScene();
		setScene(creditScene);
	}
	
	
	// GAMES SCENES MANAGEMENT
	
	// GUESS THE MISSING LETTER SCENE ===============================================================================================
	public void loadGTMLScene() {
		GuessTheMissingLetterScene = new GuessTheMissingLetter();
		setScene(GuessTheMissingLetterScene);
		
	}
	
		// LOAD GTML  PANEL SCENE
		public void loadGTMLPanelScene() {
			GTMLPanelScene = new GuessTheMissingLetterPanel();
			setScene(GTMLPanelScene);	
		}
	
	// MAtch IT SCENE ==============================================================================================================
	public void loadMatchItScene() {
		MatchItScene = new MatchIt();
		setScene(MatchItScene);		
	}
	
		// LOAD MATCH IT PANEL SCENE
		public void loadMatchItPanelScene() {
			MatchItPanelScene = new MatchItPanel();
			setScene(MatchItPanelScene);	
		}
	
	// THAT COLOR IS SCENE =================================================================================================
	public void loadThatColorIsScene() {
		// set the scene
		ThatColorIsScene = new ThatColorIs();
		setScene(ThatColorIsScene);
	}
	
		// LOAD THAT COLOR IS PANEL SCENE
	public void loadThatColorIsPanelScene() {
		// unload the textures of the THATCOLORIS textures
		//ResourcesManager.getInstance().unloadThatColorIsTextures();
		// load THAT COLOR IS SCENE Resources
		//ResourcesManager.getInstance().loadThatColorIsPanelResources();
		
		ThatColorIsPanelScene = new ThatColorIsPanel();
		setScene(ThatColorIsPanelScene);
		
	}
	
	//SOLVE IT ====================================================================================================================
	
	public void loadSolveItMenuScene() {
		// set the scene
		SolveItMenuScene = new SolveItMenu();
		setScene(SolveItMenuScene);
	}
	
		public void loadSolveItAddScene() {
			SolveItAddScene = new SolveItAddition();
			setScene(SolveItAddScene);
		}
		
			public void loadSolveItAddPanelScene() {
				SolveItAddPanel = new SolveItAddPanel();
				setScene(SolveItAddPanel);
			}
		
		public void loadSolveItSubScene() {
			SolveItSubScene = new SolveItSubtraction();
			setScene(SolveItSubScene);
		}
			public void loadSolveItSubPanelScene() {
				SolveItSubPanel = new SolveItSubPanel();
				setScene(SolveItSubPanel);
			}
		
		public void loadSolveItMulScene() {
			SolveItMulScene = new SolveItMultiplication();
			setScene(SolveItMulScene);
		}
		
			public void loadSolveItMulPanelScene() {
				SolveItMulPanel = new SolveItMulPanel();
				setScene(SolveItMulPanel);
			}
		
		public void loadSolveItDivScene() {
			SolveItDivScene = new SolveItDivision();
			setScene(SolveItDivScene);
		}
		
			public void loadSolveItDivPanelScene() {
				SolveItDivPanel = new SolveItDivPanel();
				setScene(SolveItDivPanel);
			}
	
	//COUNT IT ==================================================================================================
	public void loadCountItScene() {
		// set the scene
		CountItScene = new countIt();
		setScene(CountItScene);
	}
	
	public void loadCountItPanelScene() {
		CountItPanelScene = new CountItPanel();
		setScene(CountItPanelScene);
	}
	
	
	//----------------------------------
	// GETTERS AND SETTERS
	//----------------------------------
	public static SceneManager getInstance() {
		return INSTANCE;
	}
	
	public SceneType getCurrentSceneType() {
		return currentSceneType;
	}
	
	public BaseScene getCurrentScene() {
		return currentScene;
	}	
	
	public void setScene(BaseScene scene) {
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}
	
	public void setSceneType(SceneType sceneType) {
		switch(sceneType) {
		case SCENE_SPLASH:
			setScene(splashScene);
			break;
		case SCENE_LOADING:
			setScene(loadingScene);
			break;
		case SCENE_PLAYMENU:
			setScene(playMenuScene);
			break;
		case SCENE_MAINMENU:
			setScene(mainMenuScene);
			break;
		case SCENE_PROGRESS:
			setScene(progressScene);
			break;
		case SCENE_OPTION:
			setScene(optionScene);
			break;
		// GAMES AND PANELS
		case SCENE_MATCHIT:
			setScene(MatchItScene);
			break;
		case SCENE_MATCHITPANEL:
			setScene(MatchItPanelScene);
			break;		
		case SCENE_GTMLPANEL:
			setScene(GTMLPanelScene);
			break;
		case SCENE_COUNTITPANEL:
			setScene(CountItPanelScene);
			break;
		case SCENE_COUNTIT:
			setScene(CountItScene);
			break;
		case SCENE_GAMEMENU:
			setScene(gameMenuScene);
			break;
		case SCENE_ABOUT:
			setScene(aboutScene);
			break;
		//
		default:
			break;
		}
	}
	
}
