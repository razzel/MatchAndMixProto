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
	
	
	// CREATE OPTION TABLE
	public static final String tOption = "option_table";
	public static final String option_id = "option_id";
	public static final String isOn = "isOn";
	public static final String CREATE_OPTION_TABLE = "CREATE TABLE IF NOT EXISTS " + tOption + " ("
														+ option_id + " INTEGER PRIMARY KEY, "
														+ isOn + " TEXT)";
	// THAT COLOR IS
	public static final String table_ThatColorIs = "Color";
	public static final String fThatColorIs_ID = "color_id";
	public static final String fColor_isAnswered = "isAnswered";
	public static final String CREATE_ThatColorIs_TABLE = "CREATE TABLE IF NOT EXISTS "+table_ThatColorIs+" (" 
													+ fThatColorIs_ID + " INTEGER PRIMARY KEY , "
													+ fColor_isAnswered+ " TEXT "
													+ ")";
	
	// GUESST THE MISSING LETTER
	public static final String table_GTML = "GTML";
	public static final String fGTML_ID = "gtml_id";
	public static final String fgtml_isAnswered = "isAnswered";
	public static final String CREATE_GTML_TABLE = "CREATE TABLE IF NOT EXISTS "+table_GTML+" ("
												+fGTML_ID+ " INTEGER PRIMARY KEY , "
												+fgtml_isAnswered+ " TEXT "
												+ ")";
	
	
	// COUNT
	/*
	public static final String table_color_count = "color_count";
	public static final String fcolor_count_id = "id";
	public static final String fcolor_answered = "answered";
	public static final String fcolor_remaining = "remaining";
	public static final String CREATE_COLOR_COUNT_TABLE = "CREATE TABLE IF NOT EXISTS " +table_color_count+ " ("
														+fcolor_count_id+ " INTEGER PRIMARY KEY, "
														+fcolor_answered+ " INTEGER , "
														+fcolor_remaining+ " INTEGER "
														+")"; */
	public myDatabase(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		ContentValues optionValues = new ContentValues();
		db.execSQL(CREATE_OPTION_TABLE);
			// VALUES FOR OPTIONS
			optionValues.put(option_id, 0);
			optionValues.put(isOn, "true");
				db.insert(tOption, null, optionValues);
			optionValues.put(option_id, 1);
			optionValues.put(isOn, "true");
				db.insert(tOption, null, optionValues);
		
		// THAT COLOR IS
		db.execSQL(CREATE_ThatColorIs_TABLE);
		insertThatColorIsValues(db);
		
		//db.execSQL(CREATE_COLOR_COUNT_TABLE);
		//ContentValues colorCountValues = new ContentValues();
		//	colorCountValues.put(fcolor_count_id, 0);
		//	colorCountValues.put(fcolor_answered, 0);
		//	colorCountValues.put(fcolor_remaining, 25);
		//		db.insert(table_color_count, null, colorCountValues);
		
		
		// GUESS THE MISSING LETTER
		db.execSQL(CREATE_GTML_TABLE);
		insertGTMLValues(db);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_ThatColorIs_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_OPTION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_GTML_TABLE);
		//db.execSQL("DROP TABLE IF EXISTS " + CREATE_COLOR_COUNT_TABLE);
		onCreate(db);
	}
	
	// THAT COLOR IS ******************************************************************************************************************
	public void insertThatColorIsValues(SQLiteDatabase db) {
		ContentValues colorValues = new ContentValues();	
			// VALUES FOR THAT COLOR IS
			colorValues.put(fThatColorIs_ID, 0);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 1);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
					
			colorValues.put(fThatColorIs_ID, 2);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 3);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
			
			colorValues.put(fThatColorIs_ID, 4);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 6);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);			
			
			colorValues.put(fThatColorIs_ID, 7);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);		
			colorValues.put(fThatColorIs_ID, 8);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);		
		
			colorValues.put(fThatColorIs_ID, 9);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
			
			colorValues.put(fThatColorIs_ID, 10);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 12);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);	
				
			colorValues.put(fThatColorIs_ID, 13);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);	
			
			colorValues.put(fThatColorIs_ID, 14);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 15);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
		
			colorValues.put(fThatColorIs_ID, 16);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 18);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 19);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 20);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 21);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
			
			colorValues.put(fThatColorIs_ID, 22);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
			
			colorValues.put(fThatColorIs_ID, 24);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 25);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);

			colorValues.put(fThatColorIs_ID, 26);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 27);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
				
			colorValues.put(fThatColorIs_ID, 28);
			colorValues.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, colorValues);
	}
	
	public void updateThatColorIs(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fColor_isAnswered, s);
		db.update(table_ThatColorIs, cv, fThatColorIs_ID+"=" +id, null);
	}
	
	public int colorGetAnswered() {
		return 0;
	}
	
	public int colorGetRemaining() {
		return 0;
	}
	
	public String colorIsAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fColor_isAnswered + " FROM " + table_ThatColorIs + " WHERE " + fThatColorIs_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fColor_isAnswered);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	// GUESS THE MISSING LETTER ***************************************************************************************************
	public void insertGTMLValues(SQLiteDatabase db) {
		ContentValues gtmlValues = new ContentValues();
		
		gtmlValues.put(fGTML_ID, 0);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,1);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);	
	
		gtmlValues.put(fGTML_ID,2);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);	
			
		gtmlValues.put(fGTML_ID,3);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,4);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,6);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,7);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,8);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,9);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
		
		gtmlValues.put(fGTML_ID,10);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,12);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
		
		gtmlValues.put(fGTML_ID,13);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,14);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,15);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,16);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);

		gtmlValues.put(fGTML_ID,18);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
	
		gtmlValues.put(fGTML_ID,19);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
		
		gtmlValues.put(fGTML_ID,20);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,21);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
	
		gtmlValues.put(fGTML_ID,22);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,24);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,25);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
	
		gtmlValues.put(fGTML_ID,26);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,27);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
			
		gtmlValues.put(fGTML_ID,28);
		gtmlValues.put(fgtml_isAnswered, "false");
			db.insert(table_GTML, null, gtmlValues);
	}
	
	public void updateGTML(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fgtml_isAnswered, s);
		db.update(table_GTML, cv, fGTML_ID+"=" +id, null);
	}
	
	public String gtmlIsAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fgtml_isAnswered + " FROM " + table_GTML + " WHERE " + fGTML_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fgtml_isAnswered);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	// OPTIONS ********************************************************************************************************
	public String isBGMOn() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + isOn + " FROM "+ tOption + " WHERE " + option_id + " = 0" , null);
		c.moveToFirst();
		int index = c.getColumnIndex(isOn);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	public String isSFXOn() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + isOn + " FROM "+ tOption + " WHERE " + option_id + " = 1" , null);
		c.moveToFirst();
		int index = c.getColumnIndex(isOn);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	public void updateBGM(String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(isOn, s);
		db.update(tOption, cv, option_id+" = 0", null);
	}
	
	public void updateSFX(String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(isOn, s);
		db.update(tOption, cv, option_id+" = 1", null);
	}
	
}
