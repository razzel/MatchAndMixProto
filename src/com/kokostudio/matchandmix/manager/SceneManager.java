package com.kokostudio.matchandmix.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.kokostudio.matchandmix.base.BaseScene;
//import com.kokostudio.matchandmix.scene.About;
import com.kokostudio.matchandmix.scene.MainMenuScene;
import com.kokostudio.matchandmix.scene.GameMenuScene;
import com.kokostudio.matchandmix.scene.OptionScene;
//import com.kokostudio.matchandmix.scene.GameMenuScene;
import com.kokostudio.matchandmix.scene.PlayMenuScene;
import com.kokostudio.matchandmix.scene.SplashScene;

import com.kokostudio.matchandmix.scene.game.GuessTheMissingLetter;
import com.kokostudio.matchandmix.scene.game.MatchIt;
import com.kokostudio.matchandmix.scene.game.SolveIT;
import com.kokostudio.matchandmix.scene.game.ThatColorIs;
import com.kokostudio.matchandmix.scene.game.countIt;
//import com.kokostudio.matchandmix.scene.game.MatchIt;
import com.kokostudio.matchandmix.scene.game.panel.GuessTheMissingLetterPanel;
import com.kokostudio.matchandmix.scene.game.panel.MatchItPanel;
import com.kokostudio.matchandmix.scene.game.panel.ThatColorIsPanel;

//import com.kokostudio.matchandmix.scene.game.panel.MatchItPanel;

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
	
	private BaseScene loadingScene;
	
	// GAMES
	private BaseScene MatchItScene;
	private BaseScene GuessTheMissingLetterScene;
	private BaseScene CountItScene;
	private BaseScene SolveItScene;
	private BaseScene ThatColorIsScene;
	// GAME PANELS
	private BaseScene MatchItPanelScene;
	private BaseScene GTMLPanelScene;
	private BaseScene CountItPanelScene;
	private BaseScene SolveItPanelScene;
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
		//GAME SCENES
		SCENE_MATCHIT,
		SCENE_GTML,
		SCENE_COUNTIT,
		SCENE_SOLVEIT,
		SCENE_THATCOLORIS,
		
		//GAME PANEL SCENES
		SCENE_MATCHITPANEL,
		SCENE_GTMLPANEL,
		SCENE_COUNTITPANEL,
		SCENE_SOLVEITPANEL,
		SCENE_THATCOLORISPANEL
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
	
	
	// PLAY MENU SCENE ======================================================================================================
	public void createPlayMenuScene() {
		ResourcesManager.getInstance().loadPlayMenuResources();
		playMenuScene = new PlayMenuScene();
		setScene(playMenuScene);
		//disposeSplashScene();
	}
	
	public void loadPlayMenuScene() {
		// dispose the entities in the play menu scene
		ResourcesManager.getInstance().unloadBackground();
		// load the play menu texture
		ResourcesManager.getInstance().loadPlayMenuTextures();
		setScene(playMenuScene);
	}
	
	// MAIN MENU SCENE ========================================================================================================
	public void loadMainMenuScene() {
		// unload the play menu texture
		ResourcesManager.getInstance().unloadPlayMenuTextures();
		// load the main menu texture
		ResourcesManager.getInstance().loadMainMenuResources();
		mainMenuScene = new MainMenuScene();
		setScene(mainMenuScene);
	}
	
	// GAME MENU SCENE =========================================================================================================
	public void loadGameMenuScene() {
		// unload the MAIN MENU texture
		ResourcesManager.getInstance().unloadMainMenuTextures();	
		// load the GAME MENU texture
		ResourcesManager.getInstance().loadGameMenuResources();
		gameMenuScene = new GameMenuScene();
		setScene(gameMenuScene);
	}
	
	// OPTION SCENE ============================================================================================================
	public void loadOptionScene() {
		// unload main menu scene resources
		ResourcesManager.getInstance().unloadMainMenuTextures();
		// load the option scene resources
		ResourcesManager.getInstance().loadOptionResources();
		optionScene = new OptionScene();
		setScene(optionScene);
	}
	
	// ABOUT ================================================================================================================
	public void loadaboutScene() {
		// unload the play menu texture
		ResourcesManager.getInstance().unloadMainMenuTextures();	
		// load the main menu texture
		//ResourcesManager.getInstance().loadAboutPanelResources();
		//aboutScene = new About();
		setScene(aboutScene);
	}
	
	
	// GAMES SCENES MANAGEMENT
	
	// GUESS THE MISSING LETTER SCENE ===============================================================================================
	public void loadGTMLScene() {
		// unload the GAME MENU textures
		ResourcesManager.getInstance().unloadGameMenuTexture();
		// load the guess the missing letter resources
		ResourcesManager.getInstance().loadGTMLResources();
		GuessTheMissingLetterScene = new GuessTheMissingLetter();
		setScene(GuessTheMissingLetterScene);
		
	}
	
		// LOAD GTML  PANEL SCENE
	public void loadGTMLPanelScene() {
		// unload the GTML it textures
		ResourcesManager.getInstance().unloadGTMLTextures();
		// load the GTML PANEL SCENE RESOURCES
		ResourcesManager.getInstance().loadGTMLPanelResources();
		GTMLPanelScene = new GuessTheMissingLetterPanel();
		setScene(GTMLPanelScene);	
	}
	
	// MAtch IT SCENE ==============================================================================================================
	public void loadMatchItScene() {
		// unload the main menu textures
		ResourcesManager.getInstance().unloadGameMenuTexture();
		// load the guess the missing letter resources
		ResourcesManager.getInstance().loadMatchItResources();
		MatchItScene = new MatchIt();
		setScene(MatchItScene);		
	}
	
		// LOAD MATCH IT PANEL SCENE
		public void loadMatchItPanelScene() {
			// unload the match it textures
			ResourcesManager.getInstance().unloadMatchItResources();
			// load the MATCH IT PANEL SCENE RESOURCES
			ResourcesManager.getInstance().loadMatchItPanelResources();
			MatchItPanelScene = new MatchItPanel();
			setScene(MatchItPanelScene);	
		}
	
	// THAT COLOR IS SCENE =================================================================================================
	public void loadThatColorIsScene() {
		// unload the game menu textures
		ResourcesManager.getInstance().unloadGameMenuTexture();
		// load that THAT COLOR IS SCENE RESOURCES
		ResourcesManager.getInstance().loadThatColorIsResources();
		// set the scene
		ThatColorIsScene = new ThatColorIs();
		setScene(ThatColorIsScene);
	}
	
		// LOAD THAT COLOR IS PANEL SCENE
	public void loadThatColorIsPanelScene() {
		// unload the textures of the THATCOLORIS textures
		ResourcesManager.getInstance().unloadThatColorIsTextures();
		// load THAT COLOR IS SCENE Resources
		ResourcesManager.getInstance().loadThatColorIsPanelResources();
		
		ThatColorIsPanelScene = new ThatColorIsPanel();
		setScene(ThatColorIsPanelScene);
		
	}
	
	//SOLVE IT ====================================================================================================================
	public void loadSolveItScene() {
		// unload the game menu textures
		ResourcesManager.getInstance().unloadGameMenuTexture();
		// load that THAT COLOR IS SCENE RESOURCES
		ResourcesManager.getInstance().loadThatColorIsResources();
		// set the scene
		SolveItScene = new SolveIT();
		setScene(SolveItScene);
	}
	//COUNT IT ==================================================================================================
	public void loadCountItScene() {
		//unload the game textures
		ResourcesManager.getInstance().unloadGameMenuTexture();
		//Load that Count it Scene Resources
		ResourcesManager.getInstance().loadCountItResources();
		// set the scene
		CountItScene = new countIt();
		setScene(CountItScene);
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
