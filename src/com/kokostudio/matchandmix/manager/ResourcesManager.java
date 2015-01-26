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
import org.andengine.opengl.font.IFont;
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
	public ITextureRegion gameHeaderTextureReion;
	public TiledTextureRegion guessTextureRegion;
	public TiledTextureRegion thatColorIsTextureRegion;
	public TiledTextureRegion solveITTextureRegion;
	public TiledTextureRegion matchItTextureRegion;
	public TiledTextureRegion countItTextureRegion;
	
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
		// questions
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
		
		// choices
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
	
	public BuildableBitmapTextureAtlas matchItBGTextureAtlas;
	public ITextureRegion matchItBGTexture;
	public ITextureRegion matchItChoiceBox;
	public ITextureRegion match_questionPlank;
	// SOLVE IT ===========================================================================
	public BuildableBitmapTextureAtlas solveitmenuAtlas;
	public ITextureRegion SolveitmenutextureRegion;
	public TiledTextureRegion addTiledTexture;
	public TiledTextureRegion subTiledTexture;
	public TiledTextureRegion divTiledTexture;
	public TiledTextureRegion multiTiledTexture;
	
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
		
		// fruitsNvegs
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
		
		// animals
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
		
		//Others
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
		
		// QUESTION FOR COUNT IT
		public BuildableBitmapTextureAtlas countItQuestionAtlas;
		public ITextureRegion countItQuestion1;
		
		// OBJECTS FOR COUNT IT
		public BuildableBitmapTextureAtlas countItObjectAtlas;
		public ITextureRegion countItObjectTriangle;
		public ITextureRegion countItObjectSquare;
		
	// OPTION TEXTURES ***************************************************************************
	public BuildableBitmapTextureAtlas optionTextureAtlas;
	public ITextureRegion optionBoardTextureRegion;
	public ITextureRegion onTextureRegion;
	public ITextureRegion offTextureRegion;
	
	// PROGRESS TEXTURES *************************************************************************
	public BuildableBitmapTextureAtlas progressTextureAtlas;
	public ITextureRegion progressHeaderTexture;
	public ITextureRegion progressPanelTexture;
	
	// ABOUT PANELS ***********************************************
	public BuildableBitmapTextureAtlas AboutSceneTextureAtlas;
	public ITextureRegion aboutpanelTextureRegion;
	
	// ---------------------------------------------------------------------------
	// SFX
	// ---------------------------------------------------------------------------
	// BACKGROUND MUSIC
	public Music bgm;
	public Sound click;
	public Sound correct;
	public Sound wrong;
	
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
		} catch(final IOException e) {
			Debug.e(e);
		}
	}
	
	// FRUITS !!!!!!!!!!!!!!!!!!!!!
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
	
	//ANIMALS!!!!!!!!!!!!!!!!!!!!!!!!
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
	
	//OTHERS===================================
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
	
	// NUMBERS ================================
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
	
	
	// ---------------------------------------------------------------------------
	// FONTS
	// ---------------------------------------------------------------------------
	public Font font;
	public Font soosFont;
	public Font aklatanFont;
	
	public void loadAklatanFont() {
		FontFactory.setAssetBasePath("fonts/");
		final ITexture soosFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256);
		aklatanFont = FontFactory.createFromAsset(activity.getFontManager(), soosFontTexture, activity.getAssets(), "aklatan.ttf", 30, true, Color.rgb(0, 0, 0));
		aklatanFont.load();
	}
	
	//-----------------------------
	// CLASS LOGIC
	//-----------------------------
	
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
		commonButtonsTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 512, 300);
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
	
	// SHAPES ===================================================================
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
	//fruitsNVegs =====================================================================================
	public void loadfruitsNvegs(){
		loadfruitsNvegs1();	
		loadFruitsNvegs2();
	}
	public void loadfruitsNvegs1(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/fruitsNvegs/");
		fruitNvegsatlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	
	public void loadFruitsNvegs2() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/fruitsNvegs/");
		fruitNvegsatlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	public void unloadfruitNvegs(){
		fruitNvegsatlas.unload();
	}
	
	//ANIMALS========================================================================================================================
	public void loadAnimals(){
		loadAnimals1();
		loadAnimals2();
	}
	public void loadAnimals1(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animals/");
		AnimalsAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	
	public void loadAnimals2(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animals/");
		AnimalsAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	public void unloadAnimals(){
		AnimalsAtlas.unload();
	}
	//OTHERS =========================================================================================================================
	public void loadOthers(){
		loadOthers1();
		loadOthers2();
		loadOthers3();
	}
		public void loadOthers1(){
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
			OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			balloonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_balloon.png");
			candyTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_candy.png");
			try {
				this.OthersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.OthersAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		public void loadOthers2(){
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
			OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		public void loadOthers3(){
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/others/");
			OthersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			iglooTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_igloo.png");
			ballTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_ball.png");
			cheeseTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_cheese.png");
			flowerTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_flower.png");
			swordTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_sword.png");
			cookieTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_cookie.png");
			hatTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_hat.png");
			castleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(OthersAtlas, activity, "a_castle.png");
			try {
				this.OthersAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.OthersAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		public void unloadOthers(){
			OthersAtlas.unload();
		}
	
	public void loadLetters() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/letters/");
		lettersAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	
	public void unloadLetters() {
		lettersAtlas.unload();
	}
	
	public void loadNumbers() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/numbers/");
		numberAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
	// SPLASH SCENE  *************************************************************************************************************************
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
		loadClickSound();
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
		//divTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mainMenuTextureAtlas, activity, "add_btn.png", 2, 1);
		
		try {
			this.mainMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.mainMenuTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadMainMenuAudio() {
		loadClickSound();
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
	
	public void loadGameMenuAudio() {
		loadClickSound();
	}
	// UNLOAD
	public void unloadGameMenuTexture() {
		unloadBackground();
		gameMenuTextureAtlas.unload();
	}
	
	// PROGRESS SCENE =======================================================================================================================
	public void loadProgressResources() {
		createGeneralBackground();
		createCommonButtons();
		loadProgressGraphics();
		loadProgressFonts();
	}
	
	public void loadProgressGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/progress/");
		progressTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		progressHeaderTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "progress_header.png");
		progressPanelTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(progressTextureAtlas, activity, "prog_panel.png");
		try {
			this.progressTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.progressTextureAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	
	public void loadProgressFonts() {
		loadAklatanFont();
	}
	
	public void unloadProgressResources() {
		unloadBackground();
		unloadCommonButtons();
		progressTextureAtlas.unload();
	}
	
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
		loadClickSound();
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
		loadClickSound();
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
			//loadfruitsNvegs();
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
			loadMatchItQuestions1();
			loadMatchItQuestions2();
			loadMatchItQuestions3();
			loadMatchItQuestions4();
			loadMatchItQuestions5();
			loadMatchItQuestions6();
			loadMatchItQuestions7();
			loadMatchItQuestions8();
			loadMatchItQuestions9();
			loadChoices1();
		}
		
		public void loadMatchItQuestions1() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions2() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions3() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions4() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions5() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			questionFlowerTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_flower.png", 2,1);
			questionGrapesTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_heart.png", 2,1);
			questionHeartTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_heart.png", 2,1);
			try {
				this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.questionAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadMatchItQuestions6() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions7() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions8() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItQuestions9() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/questions/");
			questionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 800, 512);
			questionTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(questionAtlas, activity, "g_triangle.png", 2,1);
			try {
				this.questionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.questionAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadChoices1() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_matchit/choices/");
			choiceAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadMatchItPanelAudio() {
			loadClickSound();
			loadCorrectWrongSound();
		}
		
		public void unloadMatchItPanelTextures() {
			unloadBackground();
			unloadQuestionFrame();
			questionAtlas.unload();
			choiceAtlas.unload();
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
		loadClickSound();
	}
		// GTML PANEL
		public void loadGTMLPanelResources() {
			createGeneralBackground();
			createCommonButtons();
			loadGTMLPanelGrahics();
			loadGTMLPanelAudio();
		}
			
		public void loadGTMLPanelGrahics() {
			loadLetters();
			loadfruitsNvegs();
			loadGTMLQuestions();
			loadAnimals1();
			loadAnimals2();
			loadShape1();
			loadOthers2();
			loadOthers3();
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_GTML/");
			GTMLAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 128);
			gtmlQuestionPad = BitmapTextureAtlasTextureRegionFactory.createFromAsset(GTMLAtlas, activity, "plank.png", 0,0);
			GTMLAtlas.load();
		}
		
		public void loadGTMLQuestions() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_GTML/questions/");
			gtmlQuestions = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
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
		
		public void loadGTMLPanelAudio() {
			loadClickSound();
			loadCorrectWrongSound();
			loadFruitsSound();
			LoadAnimalsSound();
			LoadAnimalsSound2();
			LoadOtherSound();
		}

	// UNLOAD
	public void unloadGTMLTextures() {
		unloadBackground();
		unloadCommonButtons();
	}
	
	public void unloadGTMLPanelTextures() {
		unloadBackground();
		unloadQuestionFrame();
		unloadLetters();
		unloadfruitNvegs();
	}
	
	// COUNT IT !!!! =======================================================================================================================
	public void loadCountItResources() {
		createGeneralBackground();
		createQuestionFrames();
		loadCountItGraphics();
		loadCountItAudio();
	}
	
	public void loadCountItGraphics() {
		
	}
	
	public void loadCountItAudio() {
		loadClickSound();
	}
	
	public void unloadCountItResources() {
		unloadBackground();
		unloadQuestionFrame();
	}
		// COUNT IT PANEL
		public void loadCountItPanelResources() {
			createCommonButtons();
			loadCountItPanelGraphics();
			loadCountItPanelAudio();
		}
		
		public void loadCountItPanelGraphics() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/");
			countItAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			countItBGTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "ci_bg.png");
			countItClueBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "clue_box.png");
			countItQuestionPad = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItAtlas, activity, "plank.png");
			try {
				this.countItAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.countItAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
			
			loadNumbers();
			loadCountItQuestions();
			loadCountItOjects();
			loadClues();
		}
		
		public void loadCountItQuestions() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/questions/");
			countItQuestionAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			countItQuestion1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItQuestionAtlas, activity, "ci_q1.png");
			try {
				this.countItQuestionAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.countItQuestionAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}

		}
		
		public void loadClues() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/clues/");
			countItClueAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			countItClueTriangleTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_triangle.png");
			countItClueSquareTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItClueAtlas, activity, "cc_square.png");
			
			try {
				this.countItClueAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.countItClueAtlas.load();
				
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void loadCountItOjects() {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_countit/objects_small/");
			countItObjectAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
			countItObjectTriangle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_triangle.png");
			countItObjectSquare = BitmapTextureAtlasTextureRegionFactory.createFromAsset(countItObjectAtlas, activity, "s_sqaure.png");
			try {
				this.countItObjectAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
				this.countItObjectAtlas.load();
			} catch(final TextureAtlasBuilderException e) {
				Debug.e(e);
			}
		}
		
		public void unloadCountItPanelResources() {
			unloadCommonButtons();
			countItAtlas.unload();
			countItQuestionAtlas.unload();
			countItClueAtlas.unload();
		}
		
		public void loadCountItPanelAudio() {
			loadClickSound();
			loadCorrectWrongSound();
			loadNumberSound();
		}
	
	// SOLVE IT !!!! =======================================================================================================================
	public void loadSolveItResources() {
		createGeneralBackground();
		loadSolveItGraphics();
		loadSolveItAudio();
	}
	
	public void loadSolveItGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game_solveit/");
		solveitmenuAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
		//addTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveitmenuAtlas, activity, "add_btn.png", 2, 1);
		//divTiledTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(solveitmenuAtlas, activity, "div_btn.png", 2, 1);
		try {
			this.solveitmenuAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.solveitmenuAtlas.load();
		} catch(final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}
	public void unloadSolveItGraphics() {
		solveitmenuAtlas.unload();
	}
	
	public void loadSolveItAudio() {
		loadClickSound();
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
		loadClickSound();
	}
	
	public void unloadThatColorIsTextures() {
		unloadBackground();
		unloadQuestionFrame();
	}
	
		// THAT COLOR IS PANEL
		public void loadThatColorIsPanelResources() {
			loadClickSound();
			loadCorrectWrongSound();
			loadShapes();
			loadThatColorIsPanelGraphics();
			loadColor1();
			loadColor2();
			loadColor3();
			loadfruitsNvegs();
			loadAnimals();
			loadOthers();
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
			violetTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(color3TextureAtlas, activity, "violet_btn.png", 2, 1);
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
