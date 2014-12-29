package com.kokostudio.matchandmix.base;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.kokostudio.matchandmix.manager.ResourcesManager;
import com.kokostudio.matchandmix.manager.SceneManager.SceneType;

import android.app.Activity;

public abstract class BaseScene extends Scene {
	
	//--------------------------------
	// VARIABLES
	//--------------------------------
	protected Engine engine;
	protected Activity activity;
	protected ResourcesManager resourcesManager;
	protected VertexBufferObjectManager vbom;
	protected Camera camera;
	
	//--------------------------------
	// CONSTRUCTOR
	//--------------------------------
	public BaseScene() {
		this.resourcesManager = ResourcesManager.getInstance();
		this.engine = resourcesManager.engine;
		this.activity = resourcesManager.activity;
		this.vbom = resourcesManager.vbom;
		this.camera = resourcesManager.camera;
		createScene();	
	}
	
	//--------------------------------
	// ABSTRACT METHODS
	//--------------------------------
	public abstract void createScene();
	
	public abstract void onBackKeyPressed();
	
	public abstract SceneType getSceneType();
	
	public abstract void onMenuKeyPressed();
	
	public abstract void disposeScene();
}
