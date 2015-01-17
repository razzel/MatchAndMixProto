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
	// GUESS THE MISSING LETTER
	public static final String table_ThatColorIs = "Color";
	public static final String fThatColorIs_ID = "color_id";
	public static final String fColor_isAnswered = "isAnswered";
	public static final String CREATE_ThatColorIs_TABLE = "CREATE TABLE IF NOT EXISTS "+table_ThatColorIs+" (" 
													+ fThatColorIs_ID + " INTEGER PRIMARY KEY , "
													+ fColor_isAnswered+ " TEXT "
													+ ")";
	
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
			
		
		ContentValues colorValues = new ContentValues();	
		db.execSQL(CREATE_ThatColorIs_TABLE);
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

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_ThatColorIs_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_OPTION_TABLE);
		onCreate(db);
	}
	
	// THAT COLOR IS
	public void updateThatColorIs(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fColor_isAnswered, s);
		db.update(table_ThatColorIs, cv, fThatColorIs_ID+"=" +id, null);
	}
	
	public int colorGetCount(String s) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		return 0;
	}
	
	public String isAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fColor_isAnswered + " FROM " + table_ThatColorIs + " WHERE " + fThatColorIs_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fColor_isAnswered);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	// OPTIONS
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
