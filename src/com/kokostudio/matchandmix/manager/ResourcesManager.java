package com.kokostudio.matchandmix.manager;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.ClipData.Item;
import android.graphics.Typeface;
import android.view.textservice.TextInfo;

import com.kokostudio.matchandmix.GameActivity;


public class ResourcesManager {
	
	//-----------------------------
	// VARIABLES
	//-----------------------------
	private final static ResourcesManager INSTANCE = new ResourcesManager();
	
	public Engine engine;
	public GameActivity activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	//-----------------------------
	// TEXTURES & TEXTURE REGIONS
	//-----------------------------
	// BACKGROUND TEXTURE ****************************************
	public BitmapTextureAtlas bgTextureAtlas;
	public ITextureRegion bgTextureRegion;
	
	// ENTITIES *****************************************************
	// QFRAMES
	public BuildableBitmapTextureAtlas qFrameTextureAtlas;
	public ITextureRegion qHeaderTextureRegion;
	public TiledTextureRegion answeredTextureRegion;
	public TiledTextureRegion notAnsweredTextureRegion;
	
	// COMMON BUTTONS
	public BuildableBitmapTextureAtlas commonButtonsTextureAtlas;
	public TiledTextureRegion backTiledTextureRegion;
	public TiledTextureRegion nextTiledTextureRegion;
	public TiledTextureRegion prevTiledTextureRegion;
	
	// Splash Textures ********************************************************
	public BitmapTextureAtlas SplashTextureAtlas;
	public ITextureRegion SplashTextureRegion;
	
	// PLAY MENU TEXTURES **********************************************
	public BuildableBitmapTextureAtlas playMenuTextureAtlas;
	public ITextureRegion playMenuBackgroundTexture;
	public TiledTextureRegion playTiledTextureRegion;
	
	// MAIN MENU TEXTURES **********************************************
	public BuildableBitmapTextureAtlas mainMenuTextureAtlas;
	public ITextureRegion menuHeaderTextureRegion;
	public TiledTextureRegion gamesTiledTextureRegion;
	public TiledTextureRegion progressTiledTextureRegion;
	public TiledTextureRegion howtoTiledTextureRegion;
	public TiledTextureRegion aboutTiledTextureRegion;
	public TiledTextureRegion optionTiledTextureRegion;
	public TiledTextureRegion exitTiledTextureRegion;
	
	// GAME MENU TEXTURES ***************************************************************
	public BuildableBitmapTextureAtlas gameMenuTextureAtlas;
	public TiledTextureRegion guessTextureRegion;
	public TiledTextureRegion thatColorIsTextureRegion;
	public TiledTextureRegion solveITTextureRegion;
	public TiledTextureRegion matchItTextureRegion;
	public TiledTextureRegion countItTextureRegion;
	
	// GUESS THE MISSING LETTER TEXTURES *****************************************************
		// NOTE: THIS IS JUST A SAMPLE TEXTURES
	public BitmapTextureAtlas GTMLTextureAtlas;
	public ITextureRegion questionTextureRegion;
	public ITextureRegion choiceATExtureRegion;
	public ITextureRegion choiceBTextureRegion;
	public ITextureRegion choiceCTextureRegion;
	
	// THAT COLOR IS **************************************************************************
	public BuildableBitmapTextureAtlas thatColorIsBGTextureAtlas;
	public ITextureRegion thatColorIsBGTextureRegion;
	public ITextureRegion color_questionPlankTextureRegion;
	
	public BuildableBitmapTextureAtlas color1TextureAtlas;
	public TiledTextureRegion blackTextureRegion;
	public TiledTextureRegion blueTextureRegion;
	public TiledTextureRegion brownTextureRegion;
	public TiledTextureRegion grayTextureRegion;
	
	public BuildableBitmapTextureAtlas color2TextureAtlas;
	public TiledTextureRegion greenTextureRegion;
	public TiledTextureRegion orangeTextureRegion;
	public TiledTextureRegion pinkTextureRegion;
	public TiledTextureRegion purpleTextureRegion;
	
	public BuildableBitmapTextureAtlas color3TextureAtlas;
	public TiledTextureRegion redTextureRegion;
	public TiledTextureRegion violetTextureRegion;
	public TiledTextureRegion whiteTextureRegion;
	public TiledTextureRegion yellowTextureRegion;
	
	// MATCH IT ********************************************************************************************
	public BuildableBitmapTextureAtlas questionFruits1Atlas;
	public ITextureRegion questionAppleTexture;
	public ITextureRegion questionAvocadoTexture;
	
	public BuildableBitmapTextureAtlas choiceFruits1Atlas;
	public ITextureRegion choiceAppleTexture;
	public ITextureRegion choiceAvocadoTexture;
	public ITextureRegion choiceBeanTexture;
	public ITextureRegion choiceBellPepperTexture;
	public ITextureRegion choiceCabbageTexture;
	public ITextureRegion choiceCactusTexture;
	
	public BuildableBitmapTextureAtlas matchItBGTextureAtlas;
	public ITextureRegion matchItBGTexture;
	public ITextureRegion matchItChoiceBox;
	public ITextureRegion match_questionPlank;
	
	// OTHER IMAGES  **********************************************************************
		// SHAPES
		public BuildableBitmapTextureAtlas shape1Atlas;
		public ITextureRegion cirleTexture;
		public ITextureRegion diamondTexture;
		public ITextureRegion heartTexture;
		
		public BuildableBitmapTextureAtlas shape2Atlas;
		public ITextureRegion rectangleTexture;
		public ITextureRegion squareTexture;
		public ITextureRegion starTexture;
		
		public BuildableBitmapTextureAtlas shape3Atlas;
		public ITextureRegion triangleTexture;
		
	// OPTION TEXTURES****************************************************************************
	public BuildableBitmapTextureAtlas optionTextureAtlas;
	public ITextureRegion optionBoardTextureRegion;
	public ITextureRegion onTextureRegion;
	public ITextureRegion offTextureRegion;
	
	// ABOUT PANELS ***********************************************
	public BuildableBitmapTextureAtlas AboutSceneTextureAtlas;
	public ITextureRegion aboutpanelTextureRegion;
	
	// ---------------------------------------------------------------------------
	// SFX
	// ---------------------------------------------------------------------------
	// BACKGROUND MUSIC
	public Music bgm;
	
	// ---------------------------------------------------------------------------
	// FONTS
	// ---------------------------------------------------------------------------
	public Font font;
	
	//-----------------------------
	// CLASS LOGIC
	//-----------------------------
	public void loadBGM() {
		MusicFactory.setAssetBasePath("sfx/");
		try {
			this.bgm = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "bgm.mp3");
			this.bgm.setLooping(true);
		}
		catch(final IOException e) {
			Debug.e(e);
		}
		
	}
	
	// CREATE GENERAL BACKGROUND AND ENTITIES ==========================================================================================
		// BACKGROUND
	public void createGeneralBackground() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		bgTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		bgTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgTextureAtlas, activity, "background.png", 10, 10);
		bgTextureAtlas.load();
	}
	public void unloadBackground() {
		bgTextureAtlas.unload();
	}
		// QUESTION FRAMES
	public void createQuestionFrames() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		qFrameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 256);
		qHeaderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(qFrameTextureAtlas, activity, "question_header.png");
		answeredTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(qFrameTextureAtlas, activity, "ans.png", 2,1);	
		notAnsweredTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(qFrameTextureAtlas, activity, "not_ans.png", 2,1);
		try {
			this.qFrameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.qFrameTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	public void unloadQuestionFrame() {
		qFrameTextureAtlas.unload();
	}
	
		// COMMON BUTTONS
	public void createCommonButtons() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		commonButtonsTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 300, 300);
		backTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "back_btn.png", 2, 1);
		nextTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "next_btn.png", 2, 1);
		prevTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "prev_btn.png", 2, 1);
		try {
			this.commonButtonsTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.commonButtonsTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	public void unloadCommonButtons() {
		commonButtonsTextureAtlas.unload();
	}
	public void loadShapes() {
		loadShape1();
		loadShape2();
		loadShape3();
	}
	public void loadShape1() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
		shape1Atlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		cirleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape1Atlas, activity, "a_circle.png");
		diamondTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape1Atlas, activity, "a_diamond.png");
		heartTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape1Atlas, activity, "a_heart.png");
		try {
			this.shape1Atlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.shape1Atlas.load();
			
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	public void loadShape2() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
		shape2Atlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		rectangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape2Atlas, activity, "a_rectangle.png");
		squareTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape2Atlas, activity, "a_square.png");
		starTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape2Atlas, activity, "a_star.png");
		try {
			this.shape2Atlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.shape2Atlas.load();
			
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadShape3() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
		shape3Atlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		triangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shape3Atlas, activity, "a_triangle.png");
		try {
			this.shape3Atlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.shape3Atlas.load();
			
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void unloadShapes() {
		shape1Atlas.unload();
		shape2Atlas.unload();
		shape3Atlas.unload();
	}
	
	// SPLASH SCENE ==================================================================================================================
	public void loadSplashScene() {
		//BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//SplashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		//SplashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playMenuTextureAtlas, activity, "loading.png");	
	}
	
	public void unloadSplashScene() {
		SplashTextureAtlas.unload();
		SplashTextureRegion = null;
	}
	
	// PLAY MENU SCENE ========================================================================================================================
	public void loadPlayMenuResources() {
		loadPlayMenuGraphics();
		loadPlayMenuAudio();
	}
	
	public void loadPlayMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_play/");
		playMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		playMenuBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playMenuTextureAtlas, activity, "playmenu_background.png");
		playTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(playMenuTextureAtlas, activity, "play_btn.png", 2,1);
		try {
			this.playMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.playMenuTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	public void loadPlayMenuAudio() {
		loadBGM();
	}	
	
	public void loadPlayMenuTextures() {
		playMenuTextureAtlas.load();
	}
	// UNLOAD
	public void unloadPlayMenuTextures() {
		playMenuTextureAtlas.unload();
	}
	
	
	// MAIN MENU SCENE ======================================================================================================================
	public void loadMainMenuResources() {
		createGeneralBackground();
		createCommonButtons();
		loadMainMenuGraphics();
		loadMainMenuAudio();
	}
	
	public void loadMainMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_main/");
		mainMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		menuHeaderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "menu_header.png");
		gamesTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "games_btn.png", 2, 1);
		progressTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "prog_btn.png", 2, 1);
		howtoTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "htp_btn.png", 2, 1);
		aboutTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "about_btn.png", 2, 1);
		optionTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "option_btn.png", 2, 1);
		exitTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "quit_btn.png", 2, 1);
		try {
			this.mainMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.mainMenuTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadMainMenuAudio() {
		
	}
	// UNLOAD
	public void unloadMainMenuTextures() {
		unloadBackground();
		mainMenuTextureAtlas.unload();
	}
	
	// GAME MENU SCENE ======================================================================================================================
	public void loadGameMenuResources() {
		createGeneralBackground();
		createCommonButtons();
		loadGameMenuGraphics();
		loadGameMenuAudio();
	}
	
	public void loadGameMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_games/");
		gameMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		guessTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameMenuTextureAtlas, activity, "gml_btn.png", 2, 1);
		thatColorIsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameMenuTextureAtlas, activity, "tci_btn.png", 2, 1);
		solveITTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameMenuTextureAtlas, activity, "si_btn.png", 2, 1);
		matchItTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameMenuTextureAtlas, activity, "mi_btn.png", 2, 1);
		countItTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameMenuTextureAtlas, activity, "ci_btn.png", 2, 1);
		try {
			this.gameMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
			this.gameMenuTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadGameMenuAudio() {
		
	}
	// UNLOAD
	public void unloadGameMenuTexture() {
		unloadBackground();
		gameMenuTextureAtlas.unload();
	}
	
	// PROGRESS SCENE =======================================================================================================================
	
	// OPTION SCENE =========================================================================================================================
	
	public void loadOptionResources() {
		createGeneralBackground();
		createCommonButtons();
		loadOptionGraphics();
		loadOptionAudio();
	}
	
	public void loadOptionGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/option/");
		optionTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 768, 768);
		optionBoardTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionTextureAtlas, activity, "option_board.png");
		onTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionTextureAtlas, activity, "on_btn.png");
		offTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionTextureAtlas, activity, "off_btn.png");	
		try {
			this.optionTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.optionTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadOptionAudio() {
		
	}
	
	public void unloadOptionTexture() {
		this.optionTextureAtlas.unload();
		unloadBackground();
		unloadCommonButtons();
	}
	
	// ABOUT SCENE ==========================================================================================================================
	
	//------------------------------------------------------------------------
	// GAMES LOADING TEXTURE AREA
	//------------------------------------------------------------------------
	
	// MATCH IT !!!! ============================================================================================================
	public void loadMatchItResources() {
		createQuestionFrames();
		createGeneralBackground();
		loadMatchItGraphics();
		loadMatchItAudio();
	}
	
	public void loadMatchItGraphics() {
		
	}
	
	public void loadMatchItAudio() {
		
	}
	
	public void unloadMatchItResources() {
		unloadQuestionFrame();
		unloadBackground();
		unloadQuestionFrame();
	}

		// MATCH IT PANEL
		public void loadMatchItPanelResources() {
			createGeneralBackground();
			createQuestionFrames();
			loadMatchItPanelGraphics();
			loadMatchItPanelAudio();
		}
		
		public void loadMatchItPanelGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/");
			matchItBGTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			matchItBGTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "mi_bg.png");
			match_questionPlank = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "question_plank.png");
			matchItChoiceBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "mi_choiceBox.png");
			try {
				this.matchItBGTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.matchItBGTextureAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			loadQuestionFruits1();
			loadChoiceFruits1();
			
		}
		
		public void loadQuestionFruits1() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/question_fruits/");
			questionFruits1Atlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			questionAppleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(questionFruits1Atlas, activity, "g_apple.png");
			questionAvocadoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(questionFruits1Atlas, activity, "g_avocado.png");
			try {
				this.questionFruits1Atlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.questionFruits1Atlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadChoiceFruits1() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/question_fruits/choices_fruits/");
			choiceFruits1Atlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			choiceAppleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_apple.png");
			choiceAvocadoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_avocado.png");
			choiceBeanTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_bean.png");
			choiceBellPepperTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_bellpepper.png");
			choiceCabbageTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_cabbage.png");
			choiceCactusTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceFruits1Atlas, activity, "c_cactus.png");
			try {
				this.choiceFruits1Atlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.choiceFruits1Atlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadMatchItPanelAudio() {
			
		}
		
		public void unloadMatchItPanelTextures() {
			unloadBackground();
			unloadQuestionFrame();
			questionFruits1Atlas.unload();
			choiceFruits1Atlas.unload();
		}

	// GUESS THE MISSING LETTER !!!! =========================================================================================================
	public void loadGTMLResources() {
		createGeneralBackground();
		createQuestionFrames();
		loadGTMLGraphics();
		loadGTMLAudio();
	}
	
	public void loadGTMLGraphics() {
		
	}
	
	public void loadGTMLAudio() {
		
	}
		// GTML PANEL
		public void loadGTMLPanelResources() {
			createGeneralBackground();
			createCommonButtons();
			loadGTMLPanelGrahics();
			loadGTMLFonts();
			loadGTMLPanelAudio();
		}
			
		public void loadGTMLPanelGrahics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_GTML/");
			GTMLTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			questionTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLTextureAtlas, activity, "q.png", 300,300);
			choiceATExtureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLTextureAtlas, activity, "a.png", 0,0);
			choiceBTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLTextureAtlas, activity, "b.png", 100,100);
			choiceCTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLTextureAtlas, activity, "c.png", 200,200);
			GTMLTextureAtlas.load();
		}
		
		public void loadGTMLFonts() {
			font = FontFactory.create(activity.getFontManager(), activity.getTextureManager(), 256, 256, 
					Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
			font.load();
		}
		
		public void loadGTMLPanelAudio() {
		
		}

	// UNLOAD
	public void unloadGTMLTextures() {
		unloadBackground();
		unloadCommonButtons();
	}
	
	public void unloadGTMLPanelTextures() {
		unloadBackground();
		unloadQuestionFrame();
	}
	
	// COUNT IT !!!! =======================================================================================================================
	public void loadCountItResources() {
		loadCountItGraphics();
		loadCountItAudio();
	}
	
	public void loadCountItGraphics() {
		
	}
	
	public void loadCountItAudio() {
		
	}
	
	
	// SOLVE IT !!!! =======================================================================================================================
	public void loadSolveItResources() {
		loadSolveItGraphics();
		loadSolveItAudio();
	}
	
	public void loadSolveItGraphics() {
		
	}
	
	public void loadSolveItAudio() {
		
	}
	
	// THAT COLOR IS ???? ===================================================================================================================
	public void loadThatColorIsResources() {
		createGeneralBackground();
		createQuestionFrames();
		loadThatColorIsGraphics();
		loadThatColorIsAudio();
	}
	
	public void loadThatColorIsGraphics() {
		
	}
	
	public void loadThatColorIsAudio() {
		
	}
	
	public void unloadThatColorIsTextures() {
		unloadBackground();
		unloadQuestionFrame();
	}
	
		// THAT COLOR IS PANEL
		public void loadThatColorIsPanelResources() {
			loadShapes();
			loadThatColorIsPanelGraphics();
			loadColor1();
			loadColor2();
			loadColor3();
		}
		
		public void loadThatColorIsPanelGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
			thatColorIsBGTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			thatColorIsBGTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(thatColorIsBGTextureAtlas, activity, "tci_bg.png");
			color_questionPlankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(thatColorIsBGTextureAtlas, activity, "question_plank.png");
			try {
				this.thatColorIsBGTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.thatColorIsBGTextureAtlas.load();		
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadColor1() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
			color1TextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			blackTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color1TextureAtlas, activity, "black_btn.png", 2, 1);
			blueTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color1TextureAtlas, activity, "blue_btn.png", 2, 1);
			brownTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color1TextureAtlas, activity, "brown_btn.png", 2, 1);
			grayTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color1TextureAtlas, activity, "gray_btn.png", 2, 1);
			
			try {
				this.color1TextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.color1TextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadColor2() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
			color2TextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			greenTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color2TextureAtlas, activity, "green_btn.png", 2, 1);
			orangeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color2TextureAtlas, activity, "orange_btn.png", 2, 1);
			pinkTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color2TextureAtlas, activity, "pink_btn.png", 2, 1);
			purpleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color2TextureAtlas, activity, "purple_btn.png", 2, 1);
			try {
				this.color2TextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.color2TextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		public void loadColor3() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
			color3TextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			redTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color3TextureAtlas, activity, "red_btn.png", 2, 1);
			violetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color3TextureAtlas, activity, "red_btn.png", 2, 1);
			whiteTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color3TextureAtlas, activity, "white_btn.png", 2, 1);
			yellowTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color3TextureAtlas, activity, "yellow_btn.png", 2, 1);
			try {
				this.color3TextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.color3TextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void unloadThatColorIsPanelTextures() {
			unloadShapes();
			thatColorIsBGTextureAtlas.unload();
			color1TextureAtlas.unload();
			color2TextureAtlas.unload();
			color3TextureAtlas.unload();
		}
	
	
	//----------------------------------------------------------
	// SETTERS & GETTERS
	//----------------------------------------------------------
	
	public static void prepareManager(Engine eng, GameActivity act, Camera cam, VertexBufferObjectManager vbom) { // should be static
		getInstance().engine = eng;
		getInstance().activity = act;
		getInstance().camera = cam;
		getInstance().vbom = vbom;
	}
	
	public static ResourcesManager getInstance() {
		return INSTANCE;
	}
	
}
