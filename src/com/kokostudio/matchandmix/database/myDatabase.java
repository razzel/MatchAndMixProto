package com.kokostudio.matchandmix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabase extends SQLiteOpenHelper {
	
	// DB Name and Version
	private static final String dbName = "myDB";
	private static final int dbVersion = 1;
	
	
	// OPTION
	public static final String tOption = "option_table";
	public static final String option_id = "option_id";
	public static final String isOn = "isOn";
	public static final String CREATE_OPTION_TABLE = "CREATE TABLE IF NOT EXISTS " + tOption + " ("
														+ option_id + " INTEGER PRIMARY KEY, "
														+ isOn + " TEXT)";
	
	// GUESS THE MISSING LETTER
	public static final String tGTML = "GTML";
	public static final String fGTML_ID = "_id";
	public static final String isAnswered = "isAnswered";
	public static final String CREATE_GTML_TABLE = "CREATE TABLE IF NOT EXISTS "+tGTML+" (" 
													+ fGTML_ID + " INTEGER PRIMARY KEY , "
													+ isAnswered + " TEXT, "
													+ ")";
	
	public myDatabase(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create database table
		db.execSQL(CREATE_OPTION_TABLE);
		
		// Prepopulate some values in the table
		ContentValues cv = new ContentValues();
		cv.put(option_id, 0);
		cv.put(isOn, "true");
			db.insert(tOption, null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+tGTML);
		onCreate(db);
	}
	
	public String isSoundOn() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + isOn + " FROM "+ tOption + " WHERE " + option_id + " = 0" , null);
		c.moveToFirst();
		int index = c.getColumnIndex(isOn);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	public void updateSound(String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(isOn, s);
		db.update(tOption, cv, option_id+" = 0", null);
	}
	
}
