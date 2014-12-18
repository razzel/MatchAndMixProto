package com.kokostudio.matchandmix.database;

import org.andengine.opengl.texture.region.ITextureRegion;

import com.kokostudio.matchandmix.manager.ResourcesManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabase extends SQLiteOpenHelper {
	
	// DB Name and Version
	private static final String dbName = "myDB";
	private static final int dbVersion = 1;
	
	ResourcesManager resourcesManger;
	
	// GUESS THE MISSING LETTER
	public static final String tGTML = "GTML";
	public static final String fGTML_ID = "_id";
	public static final String isAnswered = "isAnswered";
	
	public myDatabase(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create database table
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tGTML+" (" 
				+ fGTML_ID + " INTEGER PRIMARY KEY , "
				+ isAnswered + " TEXT, "
				+ ")");
		// Prepopulate some values in the table
		ContentValues cv = new ContentValues();
			cv.put(fGTML_ID, 0);
			cv.put(isAnswered, "false");
				db.insert(tGTML, null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+tGTML);
		onCreate(db);
	}
	
}
