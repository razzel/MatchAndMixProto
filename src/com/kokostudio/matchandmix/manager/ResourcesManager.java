package com.kokostudio.matchandmix.manager;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
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

import android.graphics.Color;

import com.kokostudio.matchandmix.GameActivity;


public class ResourcesManager {
	
	// =========================================================================================================================================
	// VARIABLES
	// =========================================================================================================================================
	private final static ResourcesManager INSTANCE = new ResourcesManager();
	
	public Engine engine;
	public GameActivity activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	// =========================================================================================================================================
	// TEXTURES & TEXTURE REGIONS VARIABLE DECLARATIONS
	// =========================================================================================================================================
	// BACKGROUND TEXTURE
	public BitmapTextureAtlas bgTextureAtlas;
	public ITextureRegion bgTextureRegion;
	
	public BuildableBitmapTextureAtlas mGameTextureAtlas;
	
	// HOW TO
	public TiledTextureRegion GTMLHTP;
	public TiledTextureRegion colorHTP;
	public TiledTextureRegion matchHTP;
	public TiledTextureRegion countHTP;
	public TiledTextureRegion addHTP;
	
	public TiledTextureRegion btnMatchHTP;
	public TiledTextureRegion btnCountHTP;
	public TiledTextureRegion btnSolveHTP;
	public TiledTextureRegion btnGTMLHTP;
	public TiledTextureRegion btnColorHTP;
	
	// CORRECT WRONG CONTINUE
	public ITextureRegion thatsCorrectTexture;
	public ITextureRegion thatsWrongTexture;
	public TiledTextureRegion continueTexture;
	
	// LIFE
	public ITextureRegion lifeTexture;
	public TiledTextureRegion lifeValueTexture;
	
	// WARNING MSG
	public ITextureRegion tryAgainWarningMsg;
	
	
	// ENTITIES
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
		public ITextureRegion leftTexture;
		public ITextureRegion rightTexture;
	
	// SPLASH TETURES ********************************************************************************************************
	public BitmapTextureAtlas SplashTextureAtlas;
	public ITextureRegion SplashTextureRegion;
	
	// LOADING TEXTURES
	public BuildableBitmapTextureAtlas loadingAtlas;
	public TiledTextureRegion loadingTexture;
	
	public BitmapTextureAtlas loadingBGAtlas;
	public ITextureRegion loadingBGTexture;
	
	// PLAY MENU TEXTURES *****************************************************************************************************
	public BuildableBitmapTextureAtlas playMenuTextureAtlas;
	public ITextureRegion playMenuBackgroundTexture;
	public ITextureRegion playTextTextureRegion;
	public TiledTextureRegion playTiledTextureRegion;
	
	// MAIN MENU TEXTURES *******************************************************************************************************
	public BuildableBitmapTextureAtlas mainMenuTextureAtlas;
	public ITextureRegion menuHeaderTextureRegion;
	public TiledTextureRegion gamesTiledTextureRegion;
	public TiledTextureRegion progressTiledTextureRegion;
	public TiledTextureRegion howtoTiledTextureRegion;
	public TiledTextureRegion aboutTiledTextureRegion;
	public TiledTextureRegion creditsTiledTextureRegion;
	public TiledTextureRegion optionTiledTextureRegion;
	public TiledTextureRegion exitTiledTextureRegion;
	
	// GAME MENU TEXTURES *********************************************************************************************************
	public BuildableBitmapTextureAtlas gameMenuTextureAtlas;
	public ITextureRegion gameHeaderTextureReion;
	public TiledTextureRegion guessTextureRegion;
	public TiledTextureRegion thatColorIsTextureRegion;
	public TiledTextureRegion solveITTextureRegion;
	public TiledTextureRegion matchItTextureRegion;
	public TiledTextureRegion countItTextureRegion;
	
	// CREDITS
	public ITextureRegion creditsHeaderTexture;
	public TiledTextureRegion creditsBodyTexture;
	
	
	// GUESS THE MISSING LETTER TEXTURES ******************************************************************************************
	public BitmapTextureAtlas GTMLAtlas;
	public ITextureRegion gtmlQuestionPad;
		// JAAYYYYYYPEEE DITO KA MAG DECLARE NG MGA VARIABLES PARA SA QUESTION IMAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// OPO MASTER WAG KA PONG GALIT SA AKIN HAHA
	public BuildableBitmapTextureAtlas gtmlQuestions;
	public TiledTextureRegion appleQuestionTexture;
	public TiledTextureRegion avocadoQuestionTexture;
	public TiledTextureRegion mangoQuestionTexture;
	public TiledTextureRegion orangeQuestionTexture;
	public TiledTextureRegion GrapesQuestionTexture;
	public TiledTextureRegion strawberryQuestionTexture;
	public TiledTextureRegion pumpkinQuestionTexture;
	public TiledTextureRegion zebreaQuestionTexture;
	public TiledTextureRegion DiamondQuestionTexture;
	public TiledTextureRegion CastleQuestionTexture;
	public TiledTextureRegion MouseQuestionTexture;
	public TiledTextureRegion BoatQuestionTexture;
	public TiledTextureRegion CupcakeQuestionTexture;
	public TiledTextureRegion PigQuestionTexture;
	public TiledTextureRegion turtleQuestionTexture;
	public TiledTextureRegion rabbitQuestionTexture;
	public TiledTextureRegion flagQuestionTexture;
	public TiledTextureRegion elephantQuestionTexture;
	public TiledTextureRegion monkeyQuestionTexture;
	public TiledTextureRegion pandaQuestionTexture;
	public TiledTextureRegion snakeQuestionTexture;
	public TiledTextureRegion MoonQuestionTexture;
	public TiledTextureRegion IglooQuestionTexture;
	public TiledTextureRegion GiraffeQuestionTexture;
	public TiledTextureRegion OwlQuestionTexture;
	
	// THAT COLOR IS ************************************************************************************************************
	public BuildableBitmapTextureAtlas thatColorIsBGTextureAtlas;
	public ITextureRegion thatColorIsBGTextureRegion;
	public ITextureRegion color_questionPlankTextureRegion;
	
	public BuildableBitmapTextureAtlas colorTextureAtlas;
	public TiledTextureRegion blackTextureRegion;
	public TiledTextureRegion blueTextureRegion;
	public TiledTextureRegion brownTextureRegion;
	public TiledTextureRegion grayTextureRegion;

	public TiledTextureRegion greenTextureRegion;
	public TiledTextureRegion orangeTextureRegion;
	public TiledTextureRegion pinkTextureRegion;
	public TiledTextureRegion purpleTextureRegion;

	public TiledTextureRegion redTextureRegion;
	public TiledTextureRegion violetTextureRegion;
	public TiledTextureRegion whiteTextureRegion;
	public TiledTextureRegion yellowTextureRegion;
	
	// MATCH IT **************************************************************************************************************
		public BuildableBitmapTextureAtlas matchItBGTextureAtlas;
		public ITextureRegion matchItBGTexture;
		public ITextureRegion matchItChoiceBox;
		public ITextureRegion match_questionPlank;
		
		// QUESTIONS
		public BuildableBitmapTextureAtlas questionAtlas;
		public TiledTextureRegion questionAirplaneTexture;
		public TiledTextureRegion questionAppleTexture;
		public TiledTextureRegion questionAvocadoTexture;
		public TiledTextureRegion questionBirdTexture;
		public TiledTextureRegion questionBookTexture;
		public TiledTextureRegion questionCarrotTexture;
		public TiledTextureRegion questionCatTexture;
		public TiledTextureRegion questionCircleTexture;
		public TiledTextureRegion questionCornTexture;
		public TiledTextureRegion questionDogTexture;
		public TiledTextureRegion questionDoughnutTexture;
		public TiledTextureRegion questionEggplantTexture;
		public TiledTextureRegion questionFlowerTexture;
		public TiledTextureRegion questionGrapesTexture;
		public TiledTextureRegion questionHeartTexture;
		public TiledTextureRegion questionIcecreamTexture;
		public TiledTextureRegion questionMangoTexture;
		public TiledTextureRegion questionMushroomTexture;
		public TiledTextureRegion questionPigTexture;
		public TiledTextureRegion questionPumpkinTexture;
		public TiledTextureRegion questionRabbitTexture;
		public TiledTextureRegion questionSquareTexture;
		public TiledTextureRegion questionStarTexture;
		public TiledTextureRegion questionStrawberryTexture;
		public TiledTextureRegion questionTriangleTexture;
			
		// CHOICES
		public BuildableBitmapTextureAtlas choiceAtlas;
		public ITextureRegion choiceAirplane;
		public ITextureRegion choiceAppleTexture;
		public ITextureRegion choiceAvocadoTexture;
		public ITextureRegion choiceBirdTexture;
		public ITextureRegion choiceBookTexture;
		public ITextureRegion choiceCarrotTexture;
		public ITextureRegion choiceCatTexture;
		public ITextureRegion choiceCircleTexture;
		public ITextureRegion choiceCornTexture;
		public ITextureRegion choiceDogTexture;
		public ITextureRegion choiceDoughnutTexture;
		public ITextureRegion choiceEggplantTexture;
		public ITextureRegion choiceFlowerTexture;
		public ITextureRegion choiceGrapesTexture;
		public ITextureRegion choiceHeartTexture;
		public ITextureRegion choiceIcecreamTexture;
		public ITextureRegion choiceMangoTexture;
		public ITextureRegion choiceMushroomTexture;
		public ITextureRegion choicePigTexture;
		public ITextureRegion choicePumpkinTexture;
		public ITextureRegion choiceRabbitTexture;
		public ITextureRegion choiceSquareTexture;
		public ITextureRegion choiceStarTexture;
		public ITextureRegion choiceStrawberryTexture;
		public ITextureRegion choiceTriangleTexture;
		
	
	// SOLVE IT ******************************************************************************************************
	public BuildableBitmapTextureAtlas solveItMenuAtlas;
	public TiledTextureRegion addTiledTexture;
	public TiledTextureRegion subTiledTexture;
	public TiledTextureRegion divTiledTexture;
	public TiledTextureRegion multiTiledTexture;
	
		public BuildableBitmapTextureAtlas solveItQuestionImageAtlas;
			// FOR ADDITION
			public ITextureRegion addQuestionImage1;
			public ITextureRegion addQuestionImage2;
			public ITextureRegion addQuestionImage3;
			public ITextureRegion addQuestionImage4;
			public ITextureRegion addQuestionImage5;
			public ITextureRegion addQuestionImage6;
			public ITextureRegion addQuestionImage7;
			public ITextureRegion addQuestionImage8;
			public ITextureRegion addQuestionImage9;
			public ITextureRegion addQuestionImage10;
			public ITextureRegion addQuestionImage11;
			public ITextureRegion addQuestionImage12;
			public ITextureRegion addQuestionImage13;
			public ITextureRegion addQuestionImage14;
			public ITextureRegion addQuestionImage15;
			public ITextureRegion addQuestionImage16;
			public ITextureRegion addQuestionImage17;
			public ITextureRegion addQuestionImage18;
			public ITextureRegion addQuestionImage19;
			public ITextureRegion addQuestionImage20;
			public ITextureRegion addQuestionImage21;
			public ITextureRegion addQuestionImage22;
			public ITextureRegion addQuestionImage23;
			public ITextureRegion addQuestionImage24;
			public ITextureRegion addQuestionImage25;
			
			
			// FOR SUBTRACTION, USE THE SAME ATLAS --> solveItQuestionImageAtlas
			public ITextureRegion subQuestionImage1;
			public ITextureRegion subQuestionImage2;
			public ITextureRegion subQuestionImage3;
			public ITextureRegion subQuestionImage4;
			public ITextureRegion subQuestionImage5;
			public ITextureRegion subQuestionImage6;
			public ITextureRegion subQuestionImage7;
			public ITextureRegion subQuestionImage8;
			public ITextureRegion subQuestionImage9;
			public ITextureRegion subQuestionImage10;
			public ITextureRegion subQuestionImage11;
			public ITextureRegion subQuestionImage12;
			public ITextureRegion subQuestionImage13;
			public ITextureRegion subQuestionImage14;
			public ITextureRegion subQuestionImage15;
			public ITextureRegion subQuestionImage16;
			public ITextureRegion subQuestionImage17;
			public ITextureRegion subQuestionImage18;
			public ITextureRegion subQuestionImage19;
			public ITextureRegion subQuestionImage20;
			public ITextureRegion subQuestionImage21;
			public ITextureRegion subQuestionImage22;
			public ITextureRegion subQuestionImage23;
			public ITextureRegion subQuestionImage24;
			public ITextureRegion subQuestionImage25;
				
			// FOR MULTIPLICATION
			public ITextureRegion mulQuestionImage1;
			public ITextureRegion mulQuestionImage2;
			public ITextureRegion mulQuestionImage3;
			public ITextureRegion mulQuestionImage4;
			public ITextureRegion mulQuestionImage5;
			public ITextureRegion mulQuestionImage6;
			public ITextureRegion mulQuestionImage7;
			public ITextureRegion mulQuestionImage8;
			public ITextureRegion mulQuestionImage9;
			public ITextureRegion mulQuestionImage10;
			public ITextureRegion mulQuestionImage11;
			public ITextureRegion mulQuestionImage12;
			public ITextureRegion mulQuestionImage13;
			public ITextureRegion mulQuestionImage14;
			public ITextureRegion mulQuestionImage15;
			public ITextureRegion mulQuestionImage16;
			public ITextureRegion mulQuestionImage17;
			public ITextureRegion mulQuestionImage18;
			public ITextureRegion mulQuestionImage19;
			public ITextureRegion mulQuestionImage20;
			public ITextureRegion mulQuestionImage21;
			public ITextureRegion mulQuestionImage22;
			public ITextureRegion mulQuestionImage23;
			public ITextureRegion mulQuestionImage24;
			public ITextureRegion mulQuestionImage25;
			
			
			// FOR DIVISION
			public ITextureRegion divQuestionImage1;
			public ITextureRegion divQuestionImage2;
			public ITextureRegion divQuestionImage3;
			public ITextureRegion divQuestionImage4;
			public ITextureRegion divQuestionImage5;
			public ITextureRegion divQuestionImage6;
			public ITextureRegion divQuestionImage7;
			public ITextureRegion divQuestionImage8;
			public ITextureRegion divQuestionImage9;
			public ITextureRegion divQuestionImage10;
			public ITextureRegion divQuestionImage11;
			public ITextureRegion divQuestionImage12;
			public ITextureRegion divQuestionImage13;
			public ITextureRegion divQuestionImage14;
			public ITextureRegion divQuestionImage15;
			public ITextureRegion divQuestionImage16;
			public ITextureRegion divQuestionImage17;
			public ITextureRegion divQuestionImage18;
			public ITextureRegion divQuestionImage19;
			public ITextureRegion divQuestionImage20;
			public ITextureRegion divQuestionImage21;
			public ITextureRegion divQuestionImage22;
			public ITextureRegion divQuestionImage23;
			public ITextureRegion divQuestionImage24;
			public ITextureRegion divQuestionImage25;
			
			
		public BuildableBitmapTextureAtlas solveItQuestionTextAtlas;
			// FOR ADDTION
			public ITextureRegion addQuestionText1;
			public ITextureRegion addQuestionText2;
			public ITextureRegion addQuestionText3;
			public ITextureRegion addQuestionText4;
			public ITextureRegion addQuestionText5;
			public ITextureRegion addQuestionText6;
			public ITextureRegion addQuestionText7;
			public ITextureRegion addQuestionText8;
			public ITextureRegion addQuestionText9;
			public ITextureRegion addQuestionText10;
			public ITextureRegion addQuestionText11;
			public ITextureRegion addQuestionText12;
			public ITextureRegion addQuestionText13;
			public ITextureRegion addQuestionText14;
			public ITextureRegion addQuestionText15;
			public ITextureRegion addQuestionText16;
			public ITextureRegion addQuestionText17;
			public ITextureRegion addQuestionText18;
			public ITextureRegion addQuestionText19;
			public ITextureRegion addQuestionText20;
			public ITextureRegion addQuestionText21;
			public ITextureRegion addQuestionText22;
			public ITextureRegion addQuestionText23;
			public ITextureRegion addQuestionText24;
			public ITextureRegion addQuestionText25;		
			
			// FOR SUBTRACTION, USE THE SAME ATLAS --> solveItQuestionTextAtlas
			public ITextureRegion subQuestionText1;
			public ITextureRegion subQuestionText2;
			public ITextureRegion subQuestionText3;
			public ITextureRegion subQuestionText4;
			public ITextureRegion subQuestionText5;
			public ITextureRegion subQuestionText6;
			public ITextureRegion subQuestionText7;
			public ITextureRegion subQuestionText8;
			public ITextureRegion subQuestionText9;
			public ITextureRegion subQuestionText10;
			public ITextureRegion subQuestionText11;
			public ITextureRegion subQuestionText12;
			public ITextureRegion subQuestionText13;
			public ITextureRegion subQuestionText14;
			public ITextureRegion subQuestionText15;
			public ITextureRegion subQuestionText16;
			public ITextureRegion subQuestionText17;
			public ITextureRegion subQuestionText18;
			public ITextureRegion subQuestionText19;
			public ITextureRegion subQuestionText20;
			public ITextureRegion subQuestionText21;
			public ITextureRegion subQuestionText22;
			public ITextureRegion subQuestionText23;
			public ITextureRegion subQuestionText24;
			public ITextureRegion subQuestionText25;
						
			// FOR MULTIPLICATION
			public ITextureRegion mulQuestionText1;
			public ITextureRegion mulQuestionText2;
			public ITextureRegion mulQuestionText3;
			public ITextureRegion mulQuestionText4;
			public ITextureRegion mulQuestionText5;
			public ITextureRegion mulQuestionText6;
			public ITextureRegion mulQuestionText7;
			public ITextureRegion mulQuestionText8;
			public ITextureRegion mulQuestionText9;
			public ITextureRegion mulQuestionText10;
			public ITextureRegion mulQuestionText11;
			public ITextureRegion mulQuestionText12;
			public ITextureRegion mulQuestionText13;
			public ITextureRegion mulQuestionText14;
			public ITextureRegion mulQuestionText15;
			public ITextureRegion mulQuestionText16;
			public ITextureRegion mulQuestionText17;
			public ITextureRegion mulQuestionText18;
			public ITextureRegion mulQuestionText19;
			public ITextureRegion mulQuestionText20;
			public ITextureRegion mulQuestionText21;
			public ITextureRegion mulQuestionText22;
			public ITextureRegion mulQuestionText23;
			public ITextureRegion mulQuestionText24;
			public ITextureRegion mulQuestionText25;
			
			
			// FOR DIVISION
			public ITextureRegion divQuestionText1;
			public ITextureRegion divQuestionText2;
			public ITextureRegion divQuestionText3;
			public ITextureRegion divQuestionText4;
			public ITextureRegion divQuestionText5;
			public ITextureRegion divQuestionText6;
			public ITextureRegion divQuestionText7;
			public ITextureRegion divQuestionText8;
			public ITextureRegion divQuestionText9;
			public ITextureRegion divQuestionText10;
			public ITextureRegion divQuestionText11;
			public ITextureRegion divQuestionText12;
			public ITextureRegion divQuestionText13;
			public ITextureRegion divQuestionText14;
			public ITextureRegion divQuestionText15;
			public ITextureRegion divQuestionText16;
			public ITextureRegion divQuestionText17;
			public ITextureRegion divQuestionText18;
			public ITextureRegion divQuestionText19;
			public ITextureRegion divQuestionText20;
			public ITextureRegion divQuestionText21;
			public ITextureRegion divQuestionText22;
			public ITextureRegion divQuestionText23;
			public ITextureRegion divQuestionText24;
			public ITextureRegion divQuestionText25;
			
		// ANSWER IN THE SOLVE IT GAME, ALL CATEGORIES
		public BuildableBitmapTextureAtlas solveItAnswersAtlas;
		public ITextureRegion ansTexture0;
		public ITextureRegion ansTexture1;
		public ITextureRegion ansTexture2;
		public ITextureRegion ansTexture3;
		public ITextureRegion ansTexture4;
		public ITextureRegion ansTexture5;
		public ITextureRegion ansTexture6;
		public ITextureRegion ansTexture7;
		public ITextureRegion ansTexture8;
		public ITextureRegion ansTexture9;
		public ITextureRegion ansTexture10;
		public ITextureRegion ansTexture11;
		public ITextureRegion ansTexture12;
		public ITextureRegion ansTexture13;
		public ITextureRegion ansTexture14;
		public ITextureRegion ansTexture15;
		public ITextureRegion ansTexture16;
		public ITextureRegion ansTexture17;
		public ITextureRegion ansTexture18;
		public ITextureRegion ansTexture19;
		public ITextureRegion ansTexture20;
		public ITextureRegion equalsTexture;
		
	// OTHER IMAGES
		// SHAPES
		public BuildableBitmapTextureAtlas shapeAtlas;
		public ITextureRegion cirleTexture;
		public ITextureRegion diamondTexture;
		public ITextureRegion heartTexture;
		
		public ITextureRegion rectangleTexture;
		public ITextureRegion squareTexture;
		public ITextureRegion starTexture;
		
		public ITextureRegion triangleTexture;
		
		// FRUITS AND VEGETABLES
		public BuildableBitmapTextureAtlas fruitNvegsatlas;
		public ITextureRegion beanTexture; 
		public ITextureRegion orangesTexture;
		public ITextureRegion bananaTexture;
		public ITextureRegion eggplantTexture;
		public ITextureRegion papayaTexture;
		public ITextureRegion grapesTexture;
		public ITextureRegion garlicTexture;
		public ITextureRegion nutTexture;
		public ITextureRegion appleTexture;
		public ITextureRegion avocadoTexture;
		public ITextureRegion mangoTexture;
		public ITextureRegion strawberryTexture;
		public ITextureRegion pumpkinTexture;
		
		// ANIMALS
		public BuildableBitmapTextureAtlas AnimalsAtlas;
		public ITextureRegion snakeTexture;
		public ITextureRegion zebraTexture;
		public ITextureRegion mouseTexture;
		public ITextureRegion pigTexture;
		public ITextureRegion turtleTexture;
		public ITextureRegion rabbitTexture;	
		public ITextureRegion elephantTexture;
		public ITextureRegion monkeyTexture;
		public ITextureRegion pandaTexture;
		public ITextureRegion giraffeTexture;
		public ITextureRegion owlTexture;
		
		// OTHERS
		public BuildableBitmapTextureAtlas OthersAtlas;
		public ITextureRegion balloonTexture;
		public ITextureRegion candyTexture;
		public ITextureRegion boneTexture;
		public ITextureRegion stoneTexture;
		public ITextureRegion ribbonTexture;
		public ITextureRegion iglooTexture;
		public ITextureRegion ballTexture;
		public ITextureRegion cheeseTexture;
		public ITextureRegion flowerTexture;
		public ITextureRegion swordTexture;
		public ITextureRegion bearTexture;
		public ITextureRegion cookieTexture;
		public ITextureRegion hatTexture;
		public ITextureRegion castleTexture;
		public ITextureRegion BoatTexture;
		public ITextureRegion CupcakeTexture;
		public ITextureRegion flagTexture;
		public ITextureRegion MoonTexture;
				
		// LETTERS
		public BuildableBitmapTextureAtlas lettersAtlas;
		public TiledTextureRegion aTexture;
		public TiledTextureRegion bTexture;
		public TiledTextureRegion cTexture;
		public TiledTextureRegion dTexture;
		public TiledTextureRegion eTexture;
		public TiledTextureRegion fTexture;
		public TiledTextureRegion gTexture;
		public TiledTextureRegion hTexture;
		public TiledTextureRegion iTexture;
		public TiledTextureRegion jTexture;
		public TiledTextureRegion kTexture;
		public TiledTextureRegion lTexture;
		public TiledTextureRegion mTexture;
		public TiledTextureRegion nTexture;
		public TiledTextureRegion oTexture;
		public TiledTextureRegion pTexture;
		public TiledTextureRegion qTexture;
		public TiledTextureRegion rTexture;
		public TiledTextureRegion sTexture;
		public TiledTextureRegion tTexture;
		public TiledTextureRegion uTexture;
		public TiledTextureRegion vTexture;
		public TiledTextureRegion wTexture;
		public TiledTextureRegion xTexture;
		public TiledTextureRegion yTexture;
		public TiledTextureRegion zTexture;
		
		// NUMBERS
		public BuildableBitmapTextureAtlas numberAtlas;
		public TiledTextureRegion texture0;
		public TiledTextureRegion texture1;
		public TiledTextureRegion texture2;
		public TiledTextureRegion texture3;
		public TiledTextureRegion texture4;
		public TiledTextureRegion texture5;
		public TiledTextureRegion texture6;
		public TiledTextureRegion texture7;
		public TiledTextureRegion texture8;
		public TiledTextureRegion texture9;
		public TiledTextureRegion texture10;
		public TiledTextureRegion texture11;
		public TiledTextureRegion texture12;
		public TiledTextureRegion texture13;
		public TiledTextureRegion texture14;
		public TiledTextureRegion texture15;
		public TiledTextureRegion texture16;
		public TiledTextureRegion texture17;
		public TiledTextureRegion texture18;
		public TiledTextureRegion texture19;
		public TiledTextureRegion texture20;
			
	// COUNT IT *************************************************************************************************************
	public BuildableBitmapTextureAtlas countItAtlas;
	public ITextureRegion countItClueBox;
	public ITextureRegion countItQuestionPad;
		// CLUES FOR COUNT IT
		public BuildableBitmapTextureAtlas countItClueAtlas;
		public ITextureRegion countItBGTexture;
		public ITextureRegion countItClueTriangleTexture;
		public ITextureRegion countItClueSquareTexture;
		public ITextureRegion countItClueCirceTexture;
		public ITextureRegion countItClueBugTexture;
		public ITextureRegion countItClueButterflyTexture;
		public ITextureRegion countItClueCaterpillarTexture;
		public ITextureRegion countItClueElephantTexture;
		public ITextureRegion countItClueFanTexture;
		public ITextureRegion countItClueFrogTexture;
		public ITextureRegion countItClueHorseTexture;
		public ITextureRegion countItClueKiteTexture;
		public ITextureRegion countItClueLionTexture;
		public ITextureRegion countItClueLollipopTexture;
		public ITextureRegion countItClueRacconTexture;
		public ITextureRegion countItClueTurtleTexture;
		public ITextureRegion countItClueIglooTexture;
		public ITextureRegion countItClueIcecreamTexture;
		public ITextureRegion countItClueKoalaTexture;
		public ITextureRegion countItClueEggTexture;
		public ITextureRegion countItCluePencilTexture;
		public ITextureRegion countItClueBeesTexture;
		public ITextureRegion countItClueRectangleTexture;
		public ITextureRegion countItClueDiamondTexture;
		public ITextureRegion countItClueMoonTexture;
		public ITextureRegion countItClueAppleTexure;
		public ITextureRegion countItClueBananaTexture;
		public ITextureRegion countItClueCarTexure;
		public ITextureRegion countItClueMilkTexture;
		public ITextureRegion countItClueCarabaoTexture;
		public ITextureRegion countItClueSnowmanTexture;
		public ITextureRegion countItClueRibonTexture;
		public ITextureRegion countItClueDolphinTexture;
		public ITextureRegion countItClueCakesTexture;
		public ITextureRegion countItClueStarTexture;
		public ITextureRegion countItClueCandyTexture;
		public ITextureRegion countItClueMangoTexture;
		public ITextureRegion countItClueStrawberryTexture;
		public ITextureRegion countItClueCookieTexture;
		public ITextureRegion countItClueAirplaneTexture;
		public ITextureRegion countItClueBeeTexture;
		public ITextureRegion countItClueCowTexture;
		public ITextureRegion countItClueWhaleTexture;
		public ITextureRegion countItClueBreadTexture;
		public ITextureRegion countItClueCakeTexture;
		public ITextureRegion coutItClueBirdTexture;
		public ITextureRegion countITClueRectangle;
		
		// QUESTION FOR COUNT IT
		public BuildableBitmapTextureAtlas countItQuestionAtlas;
		public ITextureRegion countItQuestion1;
		public ITextureRegion countItQuestion2;
		public ITextureRegion countItQuestion3;
		public ITextureRegion countItQuestion4;
		public ITextureRegion countItQuestion5;
		public ITextureRegion countItQuestion6;
		public ITextureRegion countItQuestion7;
		public ITextureRegion countItQuestion8;
		public ITextureRegion countItQuestion9;
		public ITextureRegion countItQuestion10;
		public ITextureRegion countItQuestion11;
		public ITextureRegion countItQuestion12;
		public ITextureRegion countItQuestion13;
		public ITextureRegion countItQuestion14;
		public ITextureRegion countItQuestion15;
		public ITextureRegion countItQuestion16;
		public ITextureRegion countItQuestion17;
		public ITextureRegion countItQuestion18;
		public ITextureRegion countItQuestion19;
		public ITextureRegion countItQuestion20;
		public ITextureRegion countItQuestion21;
		public ITextureRegion countItQuestion22;
		public ITextureRegion countItQuestion23;
		public ITextureRegion countItQuestion24;
		public ITextureRegion countItQuestion25;
			
		// OBJECTS FOR COUNT IT
		public BuildableBitmapTextureAtlas countItObjectAtlas;
		public ITextureRegion countItObjectTriangle;
		public ITextureRegion countItObjectRectangle;
		public ITextureRegion countItObjectSquare;
		public ITextureRegion countItObjectDiamond;
		public ITextureRegion countItObjectCircle;
		public ITextureRegion countItObjectLollipop;
		public ITextureRegion countItObjectCandy;
		public ITextureRegion countItObjectStar;
		public ITextureRegion countItObjectBugs;
		public ITextureRegion countItObjectButterfly;
		public ITextureRegion countItObjectApple;
		public ITextureRegion countItObjectBanana;
		public ITextureRegion countItObjectTurtle;
		public ITextureRegion countItObjectFrog;
		public ITextureRegion countItObjectCookie;
		public ITextureRegion countItObjectIcecream;
		public ITextureRegion countItObjectHat;
		public ITextureRegion countItObjectFan;
		public ITextureRegion countItObjectCar;
		public ITextureRegion countItObjectRaccoon;
		public ITextureRegion countItObjectKoala;
		public ITextureRegion countItObjectCatterpillar;
		public ITextureRegion countItObjectBee;
		public ITextureRegion countItObjectEgg;
		public ITextureRegion countItObjectChicken;
		public ITextureRegion countItObjectKite;
		public ITextureRegion countItObjectCow;
		public ITextureRegion countItObjectMilk;
		public ITextureRegion countItObjectHorse;
		public ITextureRegion countItObjectCarabao;
		public ITextureRegion countItObjectIgloo;
		public ITextureRegion countItObjectSnowman;
		public ITextureRegion countItObjectRibbon;
		public ITextureRegion countItObjectPencil;
		public ITextureRegion countItObjectLion;
		public ITextureRegion countItObjectElephant;
		public ITextureRegion countItObjectDolphin;
		public ITextureRegion countItObjectWhale;
		public ITextureRegion countItObjectBread;
		public ITextureRegion countItObjectCake;
		public ITextureRegion countItObjectMoon;
		public ITextureRegion countItObjectStrawberry;
		public ITextureRegion countItObjectMango;
		public ITextureRegion countItObjectAirplane;
		public ITextureRegion countItObjectBird;
	// OPTION TEXTURES *********************************************************************************************************
	public BuildableBitmapTextureAtlas optionTextureAtlas;
	public ITextureRegion optionBoardTextureRegion;	
	public ITextureRegion onTextureRegion;
	public ITextureRegion offTextureRegion;
	
		public BuildableBitmapTextureAtlas resetAtlas;
		public TiledTextureRegion resetTextureRegion;
		public TiledTextureRegion resetYesTextureRegion;
		public TiledTextureRegion resetNoTextureRegion;
		public ITextureRegion resetPanelTextureRegion;
		
		public ITextureRegion exitPanelTexture;
		public TiledTextureRegion tapItTexture;
		
	
	// PROGRESS TEXTURES ***************************************************************************************************************
	public BuildableBitmapTextureAtlas progressTextureAtlas;
	public ITextureRegion progressHeaderTexture;
	public ITextureRegion progressPanelTexture;
	public ITextureRegion progressLegendTexture;
	public ITextureRegion progressExcellent;
	public ITextureRegion progressVGood;
	public ITextureRegion progressGood;
	public ITextureRegion progressFair;
	
	// ABOUT PANELS ********************************************************************************************************
	public BitmapTextureAtlas AboutSceneTextureAtlas;
	public ITextureRegion aboutpanelTextureRegion;
	
	// TRIVIAS
	public BuildableBitmapTextureAtlas triviaAtlas;
	public ITextureRegion triviaPanel;
	public TiledTextureRegion triviaOK;
	public ITextureRegion appleTriva;
	public ITextureRegion avocadoTriva;
	public ITextureRegion mangoTriva;
	public ITextureRegion orangeTriva;
	public ITextureRegion GrapesTriva;
	public ITextureRegion strawberryTriva;
	public ITextureRegion pumpkinTriva;
	public ITextureRegion zebreaTriva;
	public ITextureRegion DiamondTriva;
	public ITextureRegion CastleTriva;
	public ITextureRegion MouseTriva;
	public ITextureRegion BoatTriva;
	public ITextureRegion CupcakeTriva;
	public ITextureRegion PigTriva;
	public ITextureRegion turtleTriva;
	public ITextureRegion rabbitTriva;
	public ITextureRegion flagTriva;
	public ITextureRegion elephantTriva;
	public ITextureRegion monkeyTriva;
	public ITextureRegion pandaTriva;
	public ITextureRegion snakeTriva;
	public ITextureRegion MoonTriva;
	public ITextureRegion IglooTriva;
	public ITextureRegion GiraffeTriva;
	public ITextureRegion OwlTriva;
	
	public ITextureRegion birdTriva;
	public ITextureRegion CornTriva;
	public ITextureRegion catTriva;
	public ITextureRegion StarTriva;
	public ITextureRegion AirplaneTriva;
	public ITextureRegion CircleTriva;
	public ITextureRegion DogTriva;
	public ITextureRegion carrotTriva;
	public ITextureRegion DoughnutTriva;
	public ITextureRegion flowerTriva;
	public ITextureRegion bookTriva;
	public ITextureRegion heartTriva;
	public ITextureRegion mushroomTriva;
	public ITextureRegion IcecreamTriva;
	public ITextureRegion eggplantTriva;
	public ITextureRegion triangleTriva;
	
	// ==========================================================================================================================================
	// END OF TEXTURES & TEXTURE REGIONS VARIABLE DECLARATIONS
	// ===========================================================================================================================================
	
	// ==========================================================================================================================================
	// SFX & MFX VARIABLE DECLARATION
	// ===========================================================================================================================================
	// BACKGROUND MUSIC
	public Music bgm;
	public Sound click;
	public Sound correct;
	public Sound wrong;
	public Sound sorry;
	public Sound selectagame;;
	
	public void loadBGM() {
		MusicFactory.setAssetBasePath("sfx/");
		try {
			this.bgm = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "bgm.mp3");
			this.bgm.setLooping(true);
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	public void loadClickSound() {
		SoundFactory.setAssetBasePath("sfx/");
		try {
			this.click = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Click.ogg");
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	public void loadCorrectWrongSound() {
		SoundFactory.setAssetBasePath("sfx/");
		try {
			this.correct = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "correct.mp3");
			this.wrong = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "wrong.mp3");
			this.sorry = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "SorryTryAgain.mp3");
			this.selectagame = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Selectagame.mp3");
			
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	
	// FRUITS SOUNDS !!!!!!!!!!!!!!!!!!!!!
	public Sound apple;
	public Sound avocado;
	public Sound mango;
	public Sound orange;
	public Sound grapes;
	public Sound strawberry;
	public Sound pumpkin;
	
	public void loadFruitsSound() {
		SoundFactory.setAssetBasePath("sfx/fruits/");
		try {
			this.apple = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "APPLE.mp3");
			this.avocado = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "AVOCADO.mp3");
			this.mango = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "MANGO.mp3");
			this.orange = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "ORANGE.mp3");
			this.grapes = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "GRAPES.mp3");
			this.strawberry = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "STRAWBERRY.mp3");
			this.pumpkin = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "PUMPKIN.mp3");
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	
	//ANIMALS SOUNDS !!!!!!!!!!!!!!!!!!!!!!!!
	public Sound Zebra;
	public Sound Mouse;
	public Sound Pig;
	public Sound Turtle;
	public Sound Rabbit;
	public Sound Elephant;
	public Sound Monkey;
	public Sound Panda;
	public Sound Snake;
	public Sound Giraffe;
	public Sound Owl;
	
	public void LoadAnimalsSound(){
		SoundFactory.setAssetBasePath("sfx/animals/");
		try {
			this.Zebra = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "ZEBRA.mp3");
			this.Mouse = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "MOUSE.mp3");
			this.Pig = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "PIG.mp3");
			this.Turtle = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "TURTLE.mp3");
			this.Rabbit = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "RABBIT.mp3");
			this.Elephant = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "ELEPHANT.mp3");
			this.Monkey = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "MONKEY.mp3");
			this.Panda = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "PANDA.mp3");
			this.Snake = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "SNAKE.mp3");
			this.Giraffe = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "GIRRAFE.mp3");
			//this.Owl = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "OWL.mp3");
			
		} catch(final IOException e) {
			Debug.e(e);                     
		}
	}
	public void LoadAnimalsSound2(){
		SoundFactory.setAssetBasePath("sfx/animals/");
		try {
			
			this.Owl = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "OWL.mp3");
			
		} catch(final IOException e) {
			Debug.e(e);                     
		}
	}
	
	// OTHER SOUNDS !!!!!!!!!!!!!!!!!!!!
	public Sound Diamond;
	public Sound Castle;
	public Sound Boat;
	public Sound Cupcake;
	public Sound flag;
	public Sound Moon;
	public Sound Igloo;
	public void LoadOtherSound(){
		SoundFactory.setAssetBasePath("sfx/others/");
		try {
			this.Diamond = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "DIAMOND.mp3");
			this.Castle = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "CASTLE.mp3");
			this.Boat = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "BOAT.mp3");
			this.Cupcake = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "CUPCAKE.mp3");
			this.flag = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FLAG.mp3");
			this.Moon = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "MOON.mp3");
			this.Igloo = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "IGLOO.mp3");
			
		} catch(final IOException e) {
			Debug.e(e);                     
		}
	}
	
	// NUMBER SOUNDS !!!!!!!!!!!!!!!!!!!!!!!!!!
	public Sound number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13, number14;
	public Sound number15, number16, number17, number18, number19, number20;
	public void loadNumberSound() {
		SoundFactory.setAssetBasePath("sfx/numbers/");
		try {
			this.number1 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "1.mp3");
			this.number2 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "2.mp3");
			this.number3 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "3.mp3");
			this.number4 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "4.mp3");
			this.number5 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "5.mp3");
			this.number6 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "6.mp3");
			this.number7 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "7.mp3");
			this.number8 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "8.mp3");
			this.number9 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "9.mp3");
			this.number10 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "10.mp3");
			this.number11 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "11.mp3");
			this.number12 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "12.mp3");
			this.number13 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "13.mp3");
			this.number14 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "14.mp3");
			this.number15 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "15.mp3");
			this.number16 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "16.mp3");
			this.number17 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "17.mp3");
			this.number18 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "18.mp3");
			this.number19 = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "19.mp3");
			this.number20= SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "20.mp3");
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	
	// ==========================================================================================================================================
	// END OF SFX & MFX VARIABLE DECLARATION
	// ===========================================================================================================================================
	
	// ==========================================================================================================================================
	// FONTS
	// ==========================================================================================================================================
	public Font font;
	public Font soosFont;
	public Font aklatanFont;
	
	public void loadAklatanFont() {
		FontFactory.setAssetBasePath("fonts/");
		final ITexture soosFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		aklatanFont = FontFactory.createFromAsset(activity.getFontManager(), soosFontTexture, activity.getAssets(), "aklatan.ttf", 28, true, Color.rgb(46, 33, 14));
		aklatanFont.load();
	}
	
	public void unloadAklatanFont() {
		aklatanFont.unload();
	}
	
	// ===============================================================================================================================
	// LOADING GAME RESOURCES
	// ===============================================================================================================================
	
	// ------------------------------------------------------------------
	// GENERAL BACKGROUND AND ENTITIES
	//
	// *This section will be loading entities that are common in the game
	//  or can be seen anywhere in the scene / use repeatedly in the game
	// ------------------------------------------------------------------
		// BACKGROUND
		private void createGeneralBackground() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			bgTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			bgTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgTextureAtlas, activity, "background.png", 10, 10);
			bgTextureAtlas.load();
		}
		
		public void unloadBackground() {
			bgTextureAtlas.unload();
		}
		
		// QUESTION FRAMES
		private void createQuestionFrames() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			qFrameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 256, TextureOptions.BILINEAR);
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
		private void unloadQuestionFrame() {
			qFrameTextureAtlas.unload();
		}
		
		// COMMON BUTTONS
		private void createCommonButtons() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			commonButtonsTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 720, 720, TextureOptions.BILINEAR);
			backTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "back_btn.png", 2, 1);
			nextTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "next_btn.png", 2, 1);
			prevTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "prev_btn.png", 2, 1);
			leftTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(commonButtonsTextureAtlas, activity, "menu_left.png");
			rightTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(commonButtonsTextureAtlas, activity, "menu_right.png");
			tapItTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(commonButtonsTextureAtlas, activity, "tap.png", 2,1);
			try {
				this.commonButtonsTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.commonButtonsTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			
			creatCommonButtons2();
			howToPlayGTML();
			howToPlayThatColorIs();
			howToPlayMatchIt();
			howToPlayCountIt();
			howToPlaySolveItAdd();
		}
		private void creatCommonButtons2() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 680, 256, TextureOptions.BILINEAR);
			thatsCorrectTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "correct.png");
			thatsWrongTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "wrong.png");
			lifeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "life.png");
			lifeValueTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "l.png", 4, 1);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}	
		}
		
		private void howToPlayGTML() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 360, TextureOptions.BILINEAR);
			GTMLHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_gml.png", 2, 1);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void howToPlayThatColorIs() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 720, TextureOptions.BILINEAR);
			colorHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_tci.png", 2, 2);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}		
		}
		
		private void howToPlayMatchIt() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 720, TextureOptions.BILINEAR);
			matchHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_mi.png", 2, 2);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void howToPlayCountIt() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 720, TextureOptions.BILINEAR);
			countHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_ci.png", 2, 2);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void howToPlaySolveItAdd() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 720, TextureOptions.BILINEAR);
			addHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_si.png", 2, 2);
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadWarningMsg() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 800, 480, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			tryAgainWarningMsg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "try.png");
			try {
				this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mGameTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
	
		// SHAPES
		private void loadShapes() {
			loadShape1();
			loadShape2();
			loadShape3();
		}
			private void loadShape1() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
				shapeAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				cirleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_circle.png");
				diamondTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_diamond.png");
				heartTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_heart.png");
				try {
					this.shapeAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.shapeAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadShape2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
				shapeAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				rectangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_rectangle.png");
				squareTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_square.png");
				starTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_star.png");
				try {
					this.shapeAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.shapeAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadShape3() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shapes/");
				shapeAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				triangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas, activity, "a_triangle.png");
				try {
					this.shapeAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.shapeAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void unloadShapes() {
			shapeAtlas.unload();
		}
		
		// FRUITSNVEGS
		private void loadfruitsNvegs(){
			loadfruitsNvegs1();	
			loadFruitsNvegs2();
		}
			private void loadfruitsNvegs1(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/fruitsNvegs/");
				fruitNvegsatlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				beanTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_bean.png");
				orangesTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_orange.png");
				bananaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_banana.png");
				eggplantTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_eggplant.png");
				papayaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_papaya.png");
				grapesTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_grapes.png");
				garlicTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_garlic.png");
				nutTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_nut.png");
				try {
					this.fruitNvegsatlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.fruitNvegsatlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadFruitsNvegs2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/fruitsNvegs/");
				fruitNvegsatlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				appleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_apple.png");
				avocadoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_avocado.png");
				mangoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_mango.png");
				strawberryTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_strawberry.png");
				pumpkinTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(fruitNvegsatlas, activity, "a_pumpkin.png");
				try {
					this.fruitNvegsatlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.fruitNvegsatlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
		private void unloadfruitNvegs(){
			fruitNvegsatlas.unload();
		}
		
		//ANIMALS 
		private void loadAnimals(){
			loadAnimals1();
			loadAnimals2();
		}
			private void loadAnimals1(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animals/");
				AnimalsAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				zebraTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_zebra.png");
				bearTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_bear.png");
				mouseTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_mouse.png");
				pigTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_pig.png");
				turtleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_turtle.png");
				rabbitTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_rabbit.png");
				elephantTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas,activity , "a_elephant.png");
				monkeyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_monkey.png");
				pandaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_panda.png");
				try {
					this.AnimalsAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.AnimalsAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			
			private void loadAnimals2(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animals/");
				AnimalsAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				//pandaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_panda.png");
				
				snakeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_snake.png");
				giraffeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_giraffe.png");
				owlTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AnimalsAtlas, activity, "a_owl.png");
				try {
					this.AnimalsAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.AnimalsAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			private void unloadAnimals(){
			AnimalsAtlas.unload();
		}
		
		// OTHER ENTITIES
		private void loadOthers(){
			loadOthers1();
			loadOthers2();
			loadOthers3();
		}
			private void loadOthers1(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
				OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				balloonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_balloon.png");
				candyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_candy.png");
				castleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_castle.png");
				try {
					this.OthersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.OthersAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadOthers2(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
				OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				boneTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_bone.png");
				stoneTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_stone.png");
				ribbonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_ribbon.png");
				BoatTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_boat.png");
				CupcakeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_cupcake.png");
				flagTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_flag.png");
				MoonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_moon.png");
				try {
					this.OthersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.OthersAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadOthers3(){
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
				OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				iglooTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_igloo.png");
				ballTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_ball.png");
				cheeseTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_cheese.png");
				flowerTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_flower.png");
				swordTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_sword.png");
				cookieTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_cookie.png");
				hatTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_hat.png");
				try {
					this.OthersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.OthersAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
		private void unloadOthers(){
			OthersAtlas.unload();
		}
		
		// LETTERS
		private void loadLetters() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/letters/");
			lettersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			aTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "A.png", 2, 1);
			bTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "B.png", 2, 1);
			cTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "C.png", 2, 1);
			dTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "D.png", 2, 1);
			eTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "E.png", 2, 1);
			fTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "F.png", 2, 1);
			gTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "G.png", 2, 1);
			hTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "H.png", 2, 1);
			iTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "I.png", 2, 1);
			jTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "J.png", 2, 1);
			kTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "K.png", 2, 1);
			lTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "L.png", 2, 1);
			mTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "M.png", 2, 1);
			nTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "N.png", 2, 1);
			oTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "O.png", 2, 1);
			pTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "P.png", 2, 1);
			qTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "Q.png", 2, 1);
			rTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "R.png", 2, 1);
			sTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "S.png", 2, 1);
			tTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "T.png", 2, 1);
			uTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "U.png", 2, 1);
			vTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "V.png", 2, 1);
			wTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "W.png", 2, 1);
			xTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "X.png", 2, 1);
			yTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "Y.png", 2, 1);
			zTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(lettersAtlas, activity, "Z.png", 2, 1);
			try {
				this.lettersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
				this.lettersAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}	
		}
		
		private void unloadLetters() {
			lettersAtlas.unload();
		}
		
		// NUMBERS 
		private void loadNumbers() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/numbers/");
			numberAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			texture0 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "0.png", 2, 1);
			texture1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "1.png", 2, 1);
			texture2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "2.png", 2, 1);
			texture3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "3.png", 2, 1);
			texture4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "4.png", 2, 1);
			texture5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "5.png", 2, 1);
			texture6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "6.png", 2, 1);
			texture7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "7.png", 2, 1);
			texture8 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "8.png", 2, 1);
			texture9 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "9.png", 2, 1);
			texture10 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "10.png", 2, 1);
			texture11 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "11.png", 2, 1);
			texture12 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "12.png", 2, 1);
			texture13 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "13.png", 2, 1);
			texture14 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "14.png", 2, 1);
			texture15 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "15.png", 2, 1);
			texture16 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "16.png", 2, 1);
			texture17 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "17.png", 2, 1);
			texture18 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "18.png", 2, 1);
			texture19 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "19.png", 2, 1);
			texture20 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numberAtlas, activity, "20.png", 2, 1);
			
			try {
				this.numberAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
				this.numberAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
	// -------------------------------------------------------
	// END OF LOADING GENERAL BACKGROUND AND ENTITIES
	// -------------------------------------------------------
	
	// --------------------------------------------------------------
	// SCENE ENTITIES
	//
	// *This section will be loading entities that are only to use in
	//  the scene itself
	// --------------------------------------------------------------
		
	public void loadGameResources() {
		// Games Resources
		loadMatchItResources();
		loadMatchItPanelResources();
		loadGTMLResources();
		loadGTMLPanelResources();
		loadCountItResources();
		loadCountItPanelResources();
		loadSolveItResources();
		loadThatColorIsResources();
		loadThatColorIsPanelResources();
		
		loadNumbers();
		// Menu Resources
		loadPlayMenuResources();
		loadMainMenuResources();
		loadGameMenuResources();
		//loadProgressResources();
		loadOptionResources();
		
		loadWarningMsg();
		
		createGeneralBackground();
		createQuestionFrames();
		createCommonButtons();
		loadTrivias();
		loadTrivias2();
		loadTrivias3();
		loadTrivias4();
		loadTrivias5();
		loadTrivias6();
		loadTrivias7();

		
		// sounds
		loadClickSound();
		loadCorrectWrongSound();
		
		// fonts
		loadAklatanFont();
	}
		
	
	// SPLASH SCENE  *************************************************************************************************************************
	public void loadSplashScene() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		SplashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 474, 270, TextureOptions.BILINEAR);
		SplashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(SplashTextureAtlas, activity, "team_logo.png", 0, 0);
		SplashTextureAtlas.load();
	}
	
	public void unloadSplashScene() {
		SplashTextureAtlas.unload();
		SplashTextureRegion = null;
	}
	
	// **************************************************************************
	// LOADING SCENE
	// **************************************************************************
	public void loadLoadingScene() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animations/");
		loadingAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 450, 850, TextureOptions.BILINEAR);
		loadingTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(loadingAtlas, activity, "loading.png", 1, 10);
		try {
			loadingAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
			loadingAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		loadLoadingBG();
	}
	
	private void loadLoadingBG() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		loadingBGAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		loadingBGTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadingBGAtlas, activity, "loading_bg.png", 0,0);
		loadingBGAtlas.load();
	}
	
	// **********************************************************************
	// PLAY MENU SCENE
	// ***********************************************************************
	public void loadPlayMenuResources() {
		loadPlayMenuGraphics();
		loadPlayMenuAudio();
	}
	
		private void loadPlayMenuGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_play/");
			playMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			playTextTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playMenuTextureAtlas, activity, "mNm.png");
			playMenuBackgroundTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playMenuTextureAtlas, activity, "ms_bg.png");
			playTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(playMenuTextureAtlas, activity, "play_btn.png", 2,1);
			try {
				this.playMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.playMenuTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadPlayMenuAudio() {
			loadBGM();
			//loadClickSound();
		}	
		
		public void loadPlayMenuTextures() {
			playMenuTextureAtlas.load();
		}
		
	// UNLOAD
	public void unloadPlayMenuTextures() {
		playMenuTextureAtlas.unload();
	}
		
	// ************************************************************************
	// MAIN MENU SCENE 
	// *************************************************************************
	public void loadMainMenuResources() {
		//createGeneralBackground();
		//createCommonButtons();
		
		loadMainMenuGraphics();
		loadMainMenuAudio();
	}
		
		private void loadMainMenuGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_main/");
			mainMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			menuHeaderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mainMenuTextureAtlas, activity, "menu_header.png");
			gamesTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "games_btn.png", 2, 1);
			progressTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "prog_btn.png", 2, 1);
			howtoTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "htp_btn.png", 2, 1);
			aboutTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "about_btn.png", 2, 1);
			optionTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "option_btn.png", 2, 1);
			exitTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "quit_btn.png", 2, 1);
			
			//divTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "add_btn.png", 2, 1);
			
			try {
				this.mainMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mainMenuTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			mainMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 430, 294, TextureOptions.BILINEAR);
			creditsTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "credits_btn.png", 2, 1);
			try {
				this.mainMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.mainMenuTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			
		}
		
		private void loadMainMenuAudio() {
			//loadClickSound();
		}
	// UNLOAD
	public void unloadMainMenuTextures() {
		//unloadBackground();
		mainMenuTextureAtlas.unload();
	}
	
	// ********************************************************************
	// GAME MENU SCENE
	// ********************************************************************
	public void loadGameMenuResources() {
		//createGeneralBackground();
		createCommonButtons();
		loadGameMenuGraphics();
		loadGameMenuAudio();
		loadExitConfirmationGraphics();
	}
	
		private void loadGameMenuGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu_games/");
			gameMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			gameHeaderTextureReion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameMenuTextureAtlas, activity, "games_header.png");
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
		
		private void loadExitConfirmationGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/option/");
			resetAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
			exitPanelTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(resetAtlas, activity, "quit_panel.png");
			try {
				this.resetAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.resetAtlas.load();
			} catch( final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadGameMenuAudio() {
			//loadClickSound();
		}
		
	// UNLOAD
	public void unloadGameMenuTexture() {
		//unloadBackground();
		gameMenuTextureAtlas.unload();
	}
	
	// ************************************************************************************************
	// PROGRESS SCENE
	// ************************************************************************************************
	public void loadProgressResources() {
		loadProgressGraphics();
		loadProgressFonts();
	}
	
		private void loadProgressGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/progress/");
			progressTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			progressHeaderTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "progress_header.png");
			progressPanelTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_panel.png");
			try {
				this.progressTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.progressTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			
			progressTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 256, 512, TextureOptions.BILINEAR);
			progressLegendTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_legends.png");
			progressExcellent = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_excellent.png");
			progressVGood = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_vgood.png");
			progressGood = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_good.png");
			progressFair = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_fair.png");
			try {
				this.progressTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.progressTextureAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		private void loadProgressFonts() {
			loadAklatanFont();
		}
	
	public void unloadProgressResources() {
		unloadAklatanFont();
		progressTextureAtlas.unload();
	}
	
	// ***************************************************************************
	// OPTION SCENE
	// ***************************************************************************
	public void loadOptionResources() {
		//createGeneralBackground();
		//createCommonButtons();
		loadOptionGraphics();
		loadResetPanelGraphics();
		loadOptionAudio();
	}
	
		private void loadOptionGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/option/");
			optionTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 768, 768, TextureOptions.BILINEAR); // 768x768
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
		
		private void loadResetPanelGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/option/");
			resetAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			resetPanelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(resetAtlas, activity, "reset_panel.png");
			resetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(resetAtlas, activity, "reset_btn.png", 2,1);
			resetYesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(resetAtlas, activity, "yes_btn.png", 2, 1);
			resetNoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(resetAtlas, activity, "no_btn.png", 2, 1);
			try {
				this.resetAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.resetAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadOptionAudio() {
			
		}
	
	public void unloadOptionTexture() {
		optionTextureAtlas.unload();
	}
	
	// *************************************************************
	// ABOUT SCENE
	// *************************************************************
	public void loadAboutResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		AboutSceneTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 464, TextureOptions.BILINEAR);
		aboutpanelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(AboutSceneTextureAtlas, activity, "about_panel.png", 0,0);
		AboutSceneTextureAtlas.load();
	}
	
	public void unloadAboutResources() {
		AboutSceneTextureAtlas.unload();
	}
	// *************************************************************
	// CREDITS
	// *************************************************************
	public void loadCredits() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/credits/");
		mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 720, TextureOptions.BILINEAR);
		creditsBodyTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "credits.png", 2, 2);
		try {
			mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			mGameTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 800, 100, TextureOptions.BILINEAR);
		creditsHeaderTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "credits_header.png");
		
		try {
			mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			mGameTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	// ***************************************************************
	// HOW TO PLAY
	// ***************************************************************
	
	public void loadHowToPlayResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/how_to_play/");
		mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 630, 370, TextureOptions.BILINEAR);
		btnMatchHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_miBtn.png", 2, 1);
		btnColorHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_tciBtn.png", 2, 1);
		btnCountHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_ciBtn.png", 2, 1);
		btnGTMLHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_gmlBtn.png", 2, 1);
		btnSolveHTP = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "htp_siBtn.png", 2, 1);
		try {
			mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			mGameTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	// *************************************************************
	// TRIVIAS
	// *************************************************************
	private void loadTrivias() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		triviaPanel = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "wood.png");
		//triviaOK = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(triviaAtlas, activity, "ok_btn.png", 2, 1);
		appleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "apple.png");
		avocadoTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "avocado.png");
		mangoTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "mango.png");		
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadContBtn();
		
	}
	private void loadContBtn() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 720, 80, TextureOptions.BILINEAR);
		triviaOK =  BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(triviaAtlas, activity, "cont_btn.png", 2, 1);
		
		try {
			this.triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.triviaAtlas.load();
		} catch( final TextureAtlasBuilderException e ) {
			Debug.e(e);
		}
	}
	
	private void loadTrivias2() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		CupcakeTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "pig.png");//Cake
		PigTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "pig.png");
		turtleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "pig.png");//Turtle
		rabbitTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "rabbit.png");//Rabbit
		
		
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	
	private void loadTrivias3() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		zebreaTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "zebra.png");//zebra
		DiamondTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "diamond.png");//Diamond
		CastleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "castle.png");
		MouseTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "mouse.png");
		
		
		
		
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	
	private void loadTrivias4() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		GrapesTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "grapes.png");
		strawberryTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "strawberry.png");
		pumpkinTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "pumpkin.png");
	
		
		
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	
	private void loadTrivias5() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		snakeTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "snake.png");
		IglooTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "igloo.png");
		GiraffeTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "giraffe.png");//Girraffe
		OwlTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "owl.png");
		
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	
	private void loadTrivias6() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		flagTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "flag.png");
		elephantTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "elephant.png");//Elephant
		monkeyTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "monkey.png");//monkey
		BoatTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "boat.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	private void loadTrivias7() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		pandaTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "panda.png");
		AirplaneTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "airplane.png");
		StarTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "star.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias8(); 
	}
	private void loadTrivias8() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		catTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "cat.png");
		CornTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "corn.png");
		birdTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "birds.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias9();
	}
	
	private void loadTrivias9() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		CircleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "circle.png");//Circle
		CornTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "corn.png");
		DogTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "dogs.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias10();
	}
	
	private void loadTrivias10() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		carrotTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "carrot.png");
		DoughnutTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "doughnut.png");
		appleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "apple.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias11();
	}
	
	private void loadTrivias11() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		flowerTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "flowers.png");
		bookTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "books.png");
		heartTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "heart.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias12();
	}
	
	private void loadTrivias12() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		mushroomTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "mushroom.png");
		IcecreamTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "icecream.png");
		rabbitTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "rabbit.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		loadTrivias13();
	}
	
	private void loadTrivias13() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trivia/");
		triviaAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		orangeTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "orange.png");//Orange
		MoonTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "moon.png");//Moon
		rabbitTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "rabbit.png");
		triangleTriva = BitmapTextureAtlasTextureRegionFactory.createFromAsset(triviaAtlas, activity, "triangle.png");
		try {
			triviaAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			triviaAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	// ============================================================================================================================
	// GAMES RESOURCES LOADING AREA
	// ============================================================================================================================
	
	// ************************************************************
	// MATCH IT
	// ************************************************************
	public void loadMatchItResources() {
		//loadMatchItGraphics();
		loadMatchItAudio();
	}
	
		private void loadMatchItGraphics() {
			createQuestionFrames();
			createGeneralBackground();
		}
	
		private void loadMatchItAudio() {
			//loadClickSound();
		}
	
	public void unloadMatchItResources() {
		unloadQuestionFrame();
		unloadBackground();
		unloadQuestionFrame();
	}

		// MATCH IT PANEL
		public void loadMatchItPanelResources() {
			//createGeneralBackground();
			loadMatchItPanelGraphics();
			loadMatchItPanelAudio();
			//loadfruitsNvegs();
		}
		
		private void loadMatchItPanelGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/");
			matchItBGTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			matchItBGTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "mi_bg.png");
			match_questionPlank = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "question_plank.png");
			matchItChoiceBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(matchItBGTextureAtlas, activity, "mi_choiceBox.png");
			try {
				this.matchItBGTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.matchItBGTextureAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			loadMatchItQuestions();
			loadChoices();
		}
		
		private void loadMatchItQuestions() {
			loadMatchItQuestions1();
			loadMatchItQuestions2();
			loadMatchItQuestions3();
			loadMatchItQuestions4();
			loadMatchItQuestions5();
			loadMatchItQuestions6();
			loadMatchItQuestions7();
			loadMatchItQuestions8();
			loadMatchItQuestions9();
		}
		
			private void loadMatchItQuestions1() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionAirplaneTexture= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_airplane.png", 2,1);
				questionAppleTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_apple.png", 2,1);
				questionAvocadoTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_avocado.png", 2,1);
						
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionBirdTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_bird.png", 2,1);
				questionBookTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_book.png", 2,1);
				questionCarrotTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_carrot.png", 2,1);	
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			
			private void loadMatchItQuestions3() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionCatTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_cat.png", 2,1);
				questionCircleTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_circle.png", 2,1);
				questionCornTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_corn.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions4() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionDogTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_dog.png", 2,1);
				questionDoughnutTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_doughnut.png", 2,1);
				questionEggplantTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_eggplant.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions5() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionFlowerTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_flower.png", 2,1);
				questionGrapesTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_grapes.png", 2,1);
				questionHeartTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_heart.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions6() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionIcecreamTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_icecream.png", 2,1);
				questionMangoTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_mango.png", 2,1);
				questionMushroomTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_mushroom.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions7() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionPigTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_pig.png", 2,1);
				questionPumpkinTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_pumpkin.png", 2,1);
				questionRabbitTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_rabbit.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions8() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				questionSquareTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_square.png", 2,1);
				questionStarTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_star.png", 2,1);
				questionStrawberryTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_strawberry.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMatchItQuestions9() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
				questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 800, 512, TextureOptions.BILINEAR);
				questionTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_triangle.png", 2,1);
				try {
					this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.questionAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
		
		private void loadChoices() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/choices/");
			choiceAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			choiceAirplane = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_airplane.png");
			choiceAppleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_apple.png");
			choiceAvocadoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_avocado.png");
			choiceBirdTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_bird.png");
			choiceBookTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_book.png");
			choiceCatTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_cat.png");
			choiceCircleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_circle.png");
			choiceCornTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_corn.png");
			choiceDogTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_dog.png");
			choiceDoughnutTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_doughnut.png");
			choiceEggplantTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_eggplant.png");
			choiceFlowerTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_flower.png");
			choiceGrapesTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_grapes.png");
			choiceHeartTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_heart.png");
			choiceIcecreamTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_icecream.png");
			choiceMangoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_mango.png");
			choiceMushroomTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_mushroom.png");
			choicePigTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_pig.png");
			choicePumpkinTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_pumpkin.png");
			choiceRabbitTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_rabbit.png");
			choiceSquareTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_square.png");
			choiceStarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_star.png");
			choiceStrawberryTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_strawberry.png");
			choiceTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_triangle.png");
			choiceCarrotTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(choiceAtlas, activity, "c_carrot.png");
			try {
				this.choiceAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.choiceAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadMatchItPanelAudio() {
			//loadClickSound();
			
		}
		
		public void unloadMatchItPanelTextures() {
			unloadBackground();
			questionAtlas.unload();
			choiceAtlas.unload();
		}
		
	// ************************************************************
	// GUESS THE MISSING LETTER
	// ************************************************************
	public void loadGTMLResources() {
		//loadGTMLGraphics();
		loadGTMLAudio();
	}
	
		private void loadGTMLGraphics() {
			createGeneralBackground();
			createQuestionFrames();
		}
		
		private void loadGTMLAudio() {
			//loadClickSound();
		}
	
	public void unloadGTMLTextures() {
		unloadBackground();
	}
		
		// GTML PANEL RESOURCES
		public void loadGTMLPanelResources() {
			loadGTMLPanelGrahics();
			loadGTMLPanelAudio();
		}
			
			private void loadGTMLPanelGrahics() {
				loadGTMLQuestions();
				loadLetters();
				loadfruitsNvegs();
				loadOthers();
				loadAnimals();
				loadShapes();
				
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_GTML/");
				GTMLAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 128);
				gtmlQuestionPad = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLAtlas, activity, "plank.png", 0,0);
				GTMLAtlas.load();
			}
		
			private void loadGTMLQuestions() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_GTML/questions/");
				gtmlQuestions = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				appleQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_apple.png", 2, 1);
				avocadoQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_avocado.png", 2, 1);
				mangoQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_mango.png", 2, 1);
				orangeQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_orange.png", 2, 1);
				GrapesQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_grapes.png", 2, 1);
				strawberryQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_strawberry.png", 2, 1);
				pumpkinQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_pumpkin.png", 2, 1);
				zebreaQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_zebra.png", 2, 1);
				DiamondQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_diamond.png", 2, 1);
				CastleQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_castle.png", 2, 1);
				MouseQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_mouse.png", 2, 1);
				BoatQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_boat.png", 2, 1);
				CupcakeQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_cupcake.png", 2, 1);
				PigQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_pig.png", 2, 1);
				turtleQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_turtle.png", 2, 1);
				rabbitQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_rabbit.png", 2, 1);
				flagQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_flag.png", 2, 1);
				elephantQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_elephant.png", 2, 1);
				monkeyQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_monkey.png", 2, 1);
				pandaQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_panda.png", 2, 1);
				snakeQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_snake.png", 2, 1);
				MoonQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_moon.png", 2, 1);
				IglooQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_igloo.png", 2, 1);
				GiraffeQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_giraffe.png", 2, 1);
				OwlQuestionTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gtmlQuestions, activity, "gm_owl.png", 2, 1);
				try {
					this.gtmlQuestions.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.gtmlQuestions.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
		
		public void unloadQuestions() {
			gtmlQuestions.unload();
		}
		
		private void loadGTMLPanelAudio() {
			//loadClickSound();
			
			loadFruitsSound();
			LoadAnimalsSound();
			LoadAnimalsSound2();
			LoadOtherSound();
		}
	
	public void unloadGTMLPanelTextures() {
		unloadBackground();
		unloadQuestionFrame();
		unloadLetters();
		unloadfruitNvegs();
	}
	
	// ****************************************************
	// COUNT IT
	// ****************************************************
	public void loadCountItResources() {
		//loadCountItGraphics();
		loadCountItAudio();
	}
	
		private void loadCountItGraphics() {
			createGeneralBackground();
			createQuestionFrames();
		}
		
		private void loadCountItAudio() {
			//loadClickSound();
		}
	
	public void unloadCountItResources() {
		unloadBackground();
		unloadQuestionFrame();
	}
		// COUNT IT PANEL
		public void loadCountItPanelResources() {
			//createCommonButtons();
			loadCountItPanelGraphics();
			loadCountItPanelAudio();
		}
		
			private void loadCountItPanelGraphics() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/");
				countItAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				countItBGTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "ci_bg.png");
				countItClueBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "clue_box.png");
				countItQuestionPad = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "plank.png");
				try {
					this.countItAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.countItAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
				//loadNumbers();
				loadCountItQuestions();
				loadCountItOjects();
				loadClues();
			}
		
			private void loadCountItQuestions() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/questions/");
				countItQuestionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				countItQuestion1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_q1.png");
				countItQuestion2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_q2.png");
				countItQuestion3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p3.png");
				countItQuestion4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p4.png");
				countItQuestion5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p5.png");
				countItQuestion6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p6.png");
				countItQuestion7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p7.png");
				countItQuestion8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p8.png");
				countItQuestion9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p9.png");
				countItQuestion10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p10.png");
				countItQuestion11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p11.png");
				countItQuestion12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p12.png");
				countItQuestion13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p13.png");
				countItQuestion14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p14.png");
				countItQuestion15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p15.png");
				countItQuestion16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_16.png");
				countItQuestion17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p17.png");
				countItQuestion18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p18.png");
				countItQuestion19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p19.png");
				countItQuestion20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p20.png");
				countItQuestion21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p21.png");
				countItQuestion22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p22.png");
				countItQuestion23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p23.png");
				countItQuestion24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p24.png");
				countItQuestion25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_p25.png");
				
				try {
					this.countItQuestionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.countItQuestionAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
	
			}
			
			private void loadClues() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/clues/");
				countItClueAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				countItClueTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_triangle.png");
				countItClueSquareTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_square.png");
				countItClueCirceTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_circle.png");
				countItClueAirplaneTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_airplane.png");
				countItClueAppleTexure = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_apple.png");
				countItClueBananaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_banana.png");
				countItClueBeeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_bee.png");
				countItClueBreadTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_bread.png");
				countItClueBugTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_bug.png");
				countItClueButterflyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_butterfly.png");
				countItClueCakeTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_cake.png");
				countItClueCandyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_candy.png");
				countItClueCarabaoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_carabao.png");
				countItClueCarTexure = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_car.png");
				countItClueCaterpillarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_caterpillar.png");
				countItClueCookieTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_cookie.png");
				countItClueDiamondTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_diamond.png");
				countItClueEggTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_egg.png");
				countItClueElephantTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_elephant.png");
				countItClueFanTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_fan.png");
				countItClueFrogTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_frog.png");
				countItClueHorseTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_hat.png");
				countItClueIcecreamTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_icecream.png");
				countItClueKiteTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_kite.png");
				countItClueKoalaTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_koala.png");
				countItClueLionTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_lollipop.png");
				countItClueMangoTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_mango.png");
				countItClueMilkTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_milk.png");
				countItClueMoonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_moon.png");
				countItCluePencilTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_pencil.png");
				countItClueRacconTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_raccoon.png");
				countItClueRectangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_rectangle.png");
				countItClueRibonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_ribbon.png");
				countItClueSnowmanTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_snowman.png");
				countItClueStarTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_star.png");
				countItClueStrawberryTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_strawberry.png");
				countItClueTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_triangle.png");
				countItClueTurtleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_turtle.png");
				coutItClueBirdTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_bird.png");
				countItClueRectangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_rectangle.png");
				countItClueLollipopTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_lollipop.png");
				countItClueIglooTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_igloo.png");
				countItClueDolphinTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_crab.png");
				try {
					this.countItClueAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.countItClueAtlas.load();
					
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadCountItOjects() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/objects_small/");
				countItObjectAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				countItObjectTriangle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_triangle.png");
				countItObjectSquare = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_square.png");
				countItObjectCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_circle.png");
				countItObjectBanana = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_banana.png");
				countItObjectApple = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_apple.png");
				countItObjectBee = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_bee.png");
				countItObjectBread = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_bread.png");
				countItObjectBugs = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_bug.png");
				countItObjectButterfly = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_butterfly.png");
				countItObjectCake = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_cake.png");
				countItObjectCandy = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_candy.png");
				countItObjectCar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_car.png");
				countItObjectCarabao = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_carabao.png");
				countItObjectCatterpillar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_caterpillar.png");
				countItObjectChicken = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_chicken.png");
				countItObjectCookie = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_cookie.png");
				//countItObjectCow = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_cow.png");
				countItObjectDolphin = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_dolphin.png");
				countItObjectEgg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_egg.png");
				countItObjectElephant = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_elephant.png");
				countItObjectFan = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_fan.png");
				countItObjectFrog = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_frog.png");
				countItObjectHat = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_hat.png");
				countItObjectHorse = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_horse.png");
				countItObjectIcecream = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_icecream.png");
				countItObjectIgloo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_igloo.png");
				countItObjectKoala = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_koala.png");
				countItObjectKite = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_kite.png");
				countItObjectLion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_lion.png");
				countItObjectLollipop = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_lollipop.png");
				countItObjectMilk = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_milk.png");
				countItObjectPencil = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_pencil.png");
				countItObjectRaccoon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_raccoon.png");
				countItObjectRibbon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_ribbon.png");
				countItObjectSnowman= BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_snowman.png");
				countItObjectSquare = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_square.png");
				countItObjectStar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_star.png");
				countItObjectTriangle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_triangle.png");
				countItObjectTurtle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_turtle.png");
				countItObjectRectangle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_rectangle.png");
				countItObjectDiamond = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_diamond.png");
				countItObjectMoon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_moon.png");
				countItObjectStrawberry = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_strawberry.png");
				countItObjectMango = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_mango.png");
				countItObjectAirplane = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_airplane.png");
				countItObjectBird = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_bird.png");
				try {
					this.countItObjectAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.countItObjectAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadCountItPanelAudio() {
				//loadClickSound();
				//loadCorrectWrongSound();
				loadNumberSound();
			}
			
		public void unloadCountItPanelResources() {
			countItAtlas.unload();
			countItQuestionAtlas.unload();
			countItClueAtlas.unload();
		}
		
	// *******************************************************************
	// SOLVE IT
	// *******************************************************************
	public void loadSolveItResources() {
		loadSolveItGraphics();
		loadSolveItAudio();
		loadSolveItAnswers();
		
		// addition
		loadSolveItAddResources();
		// subtraction
		loadSolveItSubResources();
		// multiplication
		loadSolveItMulResources();
		// division
		loadSolveItDivResources();
		
	}
	
		private void loadSolveItGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/");
			solveItMenuAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			addTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveItMenuAtlas, activity, "add_btn.png", 1, 2);
			subTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveItMenuAtlas, activity, "sub_btn.png", 1, 2);
			multiTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveItMenuAtlas, activity, "mul_btn.png", 1, 2);
			divTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveItMenuAtlas, activity, "div_btn.png", 1, 2);
			try {
				this.solveItMenuAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.solveItMenuAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		private void loadSolveItAudio() {
			
		}
		
		private void loadSolveItAnswers() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/answers/");
			solveItAnswersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
			ansTexture0 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans0.png");
			ansTexture1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans1.png");
			ansTexture2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans2.png");
			ansTexture3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans3.png");
			ansTexture4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans4.png");
			ansTexture5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans5.png");
			ansTexture6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans6.png");
			ansTexture7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans7.png");
			ansTexture8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans8.png");
			ansTexture9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans9.png");
			ansTexture10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans10.png");
			ansTexture11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans11.png");
			ansTexture12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans12.png");
			ansTexture13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans13.png");
			ansTexture14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans14.png");
			ansTexture15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans15.png");
			ansTexture16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans16.png");
			ansTexture17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans17.png");
			ansTexture18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans18.png");
			ansTexture19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans19.png");
			ansTexture20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "ans20.png");
			equalsTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItAnswersAtlas, activity, "question_mark.png");		
			try {
				this.solveItAnswersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.solveItAnswersAtlas.load();
			} catch (final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
	public void loadSolveItAddResources() {
		loadSolveItAddGraphics();
	}
		private void loadSolveItAddGraphics() {
			loadAddQuestionImage();
			loadAddQuestionImage2();
			loadAddQuestionText();
			loadAddQuestionText2();
		}
		
			private void loadAddQuestionImage() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/add/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				addQuestionImage1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q1.png");
				addQuestionImage2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q2.png");
				addQuestionImage3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q3.png");
				addQuestionImage4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q4.png");
				addQuestionImage5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q5.png");
				addQuestionImage6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q6.png");
				addQuestionImage7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q7.png");
				addQuestionImage8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q8.png");
				addQuestionImage9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q9.png");
				addQuestionImage10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q10.png");
				addQuestionImage11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q11.png");
				addQuestionImage12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q12.png");
				addQuestionImage13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q13.png");
				addQuestionImage14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q14.png");
				addQuestionImage15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q15.png");
				addQuestionImage16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q16.png");
				addQuestionImage17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q17.png");
				addQuestionImage18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q18.png");
				addQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q19.png");
				
				
				try {
					solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					solveItQuestionImageAtlas.load();
				} catch (final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadAddQuestionImage2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/add/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				addQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q19.png");
				addQuestionImage20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q20.png");
				addQuestionImage21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q21.png");
				addQuestionImage22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q22.png");
				addQuestionImage23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q23.png");
				addQuestionImage24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q24.png");
				addQuestionImage25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sia_q25.png");
				
				try {
					solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					solveItQuestionImageAtlas.load();
				} catch (final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadAddQuestionText2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/add/question_text/");
				solveItQuestionTextAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				addQuestionText21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p21.png");
				addQuestionText22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p22.png");
				addQuestionText23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p23.png");
				addQuestionText24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p24.png");
				addQuestionText25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p25.png");
			
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			private void loadAddQuestionText() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/add/question_text/");
				solveItQuestionTextAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				addQuestionText1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p1.png");
				addQuestionText2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p2.png");
				addQuestionText3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p3.png");
				addQuestionText4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p4.png");
				addQuestionText5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p5.png");
				addQuestionText6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p6.png");
				addQuestionText7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p7.png");
				addQuestionText8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p8.png");
				addQuestionText9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p9.png");
				addQuestionText10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p10.png");
				addQuestionText11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p11.png");
				addQuestionText12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p12.png");
				addQuestionText13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p13.png");
				addQuestionText14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p14.png");
				addQuestionText15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p15.png");
				addQuestionText16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p16.png");
				addQuestionText17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p17.png");
				addQuestionText18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p18.png");
				addQuestionText19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p19.png");
				addQuestionText20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sia_p20.png");
				
			
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
		private void loadSolveItAddAudio() {
			
		}
		
	public void loadSolveItSubResources() {
		loadSolveItSubGraphics();
		loadSolveItSubAudio();
	}
	
		private void loadSolveItSubGraphics() {
			loadSubQuestionImage();
			loadSubQuestionImage2();
			loadSubQuestionText();
			loadSubQuestionText2();
		}
		
			private void loadSubQuestionImage() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/subtract/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				subQuestionImage1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q1.png");
				subQuestionImage2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q2.png");
				subQuestionImage3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q3.png");
				subQuestionImage4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q4.png");
				subQuestionImage5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q5.png");
				subQuestionImage6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q6.png");
				subQuestionImage7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q7.png");
				subQuestionImage8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q8.png");
				subQuestionImage9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q9.png");
				subQuestionImage10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q10.png");
				subQuestionImage11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q11.png");
				subQuestionImage12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q12.png");
				subQuestionImage13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q13.png");
				subQuestionImage14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q14.png");
				subQuestionImage15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q15.png");
				subQuestionImage16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q16.png");
				subQuestionImage17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q17.png");
				subQuestionImage18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q18.png");
				subQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q19.png");
				
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			private void loadSubQuestionImage2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/subtract/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				subQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q19.png");
				subQuestionImage20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q20.png");
				subQuestionImage21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q21.png");
				subQuestionImage22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q22.png");
				subQuestionImage23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q23.png");
				subQuestionImage24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q24.png");
				subQuestionImage25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sis_q25.png");
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
				
			}
			
			
			
			private void loadSubQuestionText() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/subtract/question_text/");
				solveItQuestionTextAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				subQuestionText1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p1.png");
				subQuestionText2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p2.png");
				subQuestionText3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p3.png");
				subQuestionText4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p4.png");
				subQuestionText5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p5.png");
				subQuestionText6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p6.png");
				subQuestionText7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p7.png");
				subQuestionText8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p8.png");
				subQuestionText9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p9.png");
				subQuestionText10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p10.png");
				subQuestionText11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p11.png");
				subQuestionText12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p12.png");
				subQuestionText13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p13.png");
				subQuestionText14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p14.png");
				subQuestionText15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p15.png");
				
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					
				}
				
			}
			private void loadSubQuestionText2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/subtract/question_text/");
				solveItQuestionTextAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				subQuestionText16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p16.png");
				subQuestionText17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p17.png");
				subQuestionText18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p18.png");
				subQuestionText19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p19.png");
				subQuestionText20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p20.png");
				subQuestionText21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p21.png");
				subQuestionText22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p22.png");
				subQuestionText23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p23.png");
				subQuestionText24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p24.png");
				subQuestionText25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sis_p25.png");
				
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					
				}
				
			}
		private void loadSolveItSubAudio() {
			
		}
		
	public void loadSolveItMulResources() {
		loadSolveItMulGraphics();
		loadSolveItMulAudio();
	}
		private void loadSolveItMulGraphics() {
			loadMulQuestionImage();
			loadMulQuestionImage2();
			loadMulQuestionText();
		}
		
			private void loadMulQuestionImage() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/multiply/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				mulQuestionImage1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q1.png");
				mulQuestionImage2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q2.png");
				mulQuestionImage3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q3.png");
				mulQuestionImage4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q4.png");
				mulQuestionImage5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q5.png");
				mulQuestionImage6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q6.png");
				mulQuestionImage7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q7.png");
				mulQuestionImage8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q8.png");
				mulQuestionImage9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q9.png");
				mulQuestionImage10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q10.png");
				mulQuestionImage11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q11.png");
				mulQuestionImage12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q12.png");
				mulQuestionImage13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q13.png");
				mulQuestionImage14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q14.png");
				mulQuestionImage15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q15.png");
			
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadMulQuestionImage2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/multiply/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				mulQuestionImage16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q16.png");
				mulQuestionImage17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q17.png");
				mulQuestionImage18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q18.png");
				mulQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q19.png");
				mulQuestionImage20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q20.png");
				mulQuestionImage21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q21.png");
				mulQuestionImage22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q22.png");
				mulQuestionImage23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q23.png");
				mulQuestionImage24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q24.png");
				mulQuestionImage25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "sim_q25.png");
			
				
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadMulQuestionText() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/multiply/question_text/");
				solveItQuestionTextAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				mulQuestionText1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p1.png");
				mulQuestionText2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p2.png");
				mulQuestionText3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p3.png");
				mulQuestionText4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p4.png");
				mulQuestionText5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p5.png");
				mulQuestionText6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p6.png");
				mulQuestionText7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p7.png");
				mulQuestionText8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p8.png");
				mulQuestionText9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p9.png");
				mulQuestionText10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p10.png");
				mulQuestionText11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p11.png");
				mulQuestionText12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p12.png");
				mulQuestionText13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p13.png");
				mulQuestionText14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p14.png");
				mulQuestionText15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p15.png");
				mulQuestionText16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p16.png");
				mulQuestionText17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p17.png");
				mulQuestionText18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p18.png");
				mulQuestionText19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p19.png");
				mulQuestionText20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p20.png");
				mulQuestionText21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p21.png");
				mulQuestionText22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p22.png");
				mulQuestionText23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p23.png");
				mulQuestionText24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p24.png");
				mulQuestionText25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "sim_p25.png");
				
				
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
		
		private void loadSolveItMulAudio() {
			
		}
		
	public void loadSolveItDivResources() {
		loadSolveItDivGraphics();
		loadSolveItDivAudio();
	}
		private void loadSolveItDivGraphics() {
			loadDivQuestionImage();
			loadDivQuestionImage2();
			loadDivQuestionText();
		}
			private void loadDivQuestionImage() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/divide/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				divQuestionImage1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q1.png");
				divQuestionImage2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q2.png");
				divQuestionImage3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q3.png");
				divQuestionImage4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q4.png");
				divQuestionImage5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q5.png");
				divQuestionImage6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q6.png");
				divQuestionImage7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q7.png");
				divQuestionImage8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q8.png");	
				divQuestionImage9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q9.png");
				divQuestionImage10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q10.png");				
				divQuestionImage11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q11.png");				
				divQuestionImage12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q12.png");
				divQuestionImage13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q13.png");
				divQuestionImage14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q14.png");
				divQuestionImage15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q15.png");
				/*divQuestionImage16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q16.png");
				divQuestionImage17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q17.png");
				divQuestionImage18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q18.png");
				divQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q19.png");
				divQuestionImage20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q20.png");
				divQuestionImage21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q21.png");
				divQuestionImage22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q22.png");
				divQuestionImage23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q23.png");
				divQuestionImage24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q24.png");		
				divQuestionImage25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q25.png");
				*/
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadDivQuestionImage2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/divide/question_image/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				
				divQuestionImage16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q16.png");
				divQuestionImage17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q17.png");
				divQuestionImage18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q18.png");
				divQuestionImage19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q19.png");
				divQuestionImage20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q20.png");
				divQuestionImage21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q21.png");
				divQuestionImage22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q22.png");
				divQuestionImage23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q23.png");
				divQuestionImage24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q24.png");		
				divQuestionImage25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionImageAtlas, activity, "siq_q25.png");
				
				try {
					this.solveItQuestionImageAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionImageAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadDivQuestionText() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/divide/question_text/");
				solveItQuestionImageAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				divQuestionText1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p1.png");
				divQuestionText2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p2.png");
				divQuestionText3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p3.png");
				divQuestionText4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p4.png");
				divQuestionText5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p5.png");
				divQuestionText6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p6.png");
				divQuestionText7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p7.png");
				divQuestionText8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p8.png");
				divQuestionText9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p9.png");
				divQuestionText10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p10.png");
				divQuestionText11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p11.png");
				divQuestionText12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p12.png");
				divQuestionText13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p13.png");
				divQuestionText14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p14.png");
				divQuestionText15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p15.png");
				divQuestionText16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p16.png");
				divQuestionText17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p17.png");
				divQuestionText18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p18.png");
				divQuestionText19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p19.png");
				divQuestionText20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p20.png");
				divQuestionText21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p21.png");
				divQuestionText22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p22.png");
				divQuestionText23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p23.png");
				divQuestionText24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p24.png");
				divQuestionText25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(solveItQuestionTextAtlas, activity, "siq_p25.png");
				
			
				try {
					this.solveItQuestionTextAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.solveItQuestionTextAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
		
		private void loadSolveItDivAudio() {
			
		}
		
	// ****************************************************************
	// THAT COLOR IS
	// ****************************************************************
	public void loadThatColorIsResources() {
		//loadThatColorIsGraphics();
		loadThatColorIsAudio();
	}
	
		private void loadThatColorIsGraphics() {
			createGeneralBackground();
			createQuestionFrames();
		}
		
		private void loadThatColorIsAudio() {
			//loadClickSound();
		}
		
	public void unloadThatColorIsTextures() {
		unloadBackground();
		unloadQuestionFrame();
	}
	
		// THAT COLOR IS PANEL
		public void loadThatColorIsPanelResources() {
			//loadClickSound();
			//loadCorrectWrongSound();
			loadShapes();
			loadThatColorIsPanelGraphics();
			loadColor();
			loadfruitsNvegs();
			loadAnimals();
			loadOthers();
		}
		
			private void loadThatColorIsPanelGraphics() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
				thatColorIsBGTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				thatColorIsBGTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(thatColorIsBGTextureAtlas, activity, "tci_bg.png");
				color_questionPlankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(thatColorIsBGTextureAtlas, activity, "question_plank.png");
				try {
					this.thatColorIsBGTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.thatColorIsBGTextureAtlas.load();		
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadColor() {
				loadColor1();
				loadColor2();
				loadColor3();
			}
			
			private void loadColor1() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
				colorTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				blackTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "black_btn.png", 2, 1);
				blueTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "blue_btn.png", 2, 1);
				brownTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "brown_btn.png", 2, 1);
				grayTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "gray_btn.png", 2, 1);
				
				try {
					this.colorTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.colorTextureAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			
			private void loadColor2() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
				colorTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				greenTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "green_btn.png", 2, 1);
				orangeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "orange_btn.png", 2, 1);
				pinkTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "pink_btn.png", 2, 1);
				purpleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "purple_btn.png", 2, 1);
				try {
					this.colorTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.colorTextureAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
			private void loadColor3() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_color/");
				colorTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
				redTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "red_btn.png", 2, 1);
				violetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "violet_btn.png", 2, 1);
				whiteTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "white_btn.png", 2, 1);
				yellowTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(colorTextureAtlas, activity, "yellow_btn.png", 2, 1);
				try {
					this.colorTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
					this.colorTextureAtlas.load();
				} catch(final TextureAtlasBuilderException e) {
					Debug.e(e);
				}
			}
		
		public void unloadThatColorIsPanelTextures() {
			unloadShapes();
			unloadfruitNvegs();
			unloadAnimals();
			unloadOthers();
			thatColorIsBGTextureAtlas.unload();
			colorTextureAtlas.unload();
		}
	
	
	// ====================================================================================
	// SETTERS & GETTERS
	// ====================================================================================
	
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
