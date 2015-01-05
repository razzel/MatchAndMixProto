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
		ContentValues cv = new ContentValues();
		
		db.execSQL(CREATE_OPTION_TABLE);
			// VALUES FOR OPTIONS
			cv.put(option_id, 0);
			cv.put(isOn, "true");
				db.insert(tOption, null, cv);
				
		db.execSQL(CREATE_ThatColorIs_TABLE);
			// VALUES FOR THAT COLOR IS
			cv.put(fThatColorIs_ID, 0);
			cv.put(fColor_isAnswered, "false");	
				db.insert(table_ThatColorIs, null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_ThatColorIs_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_OPTION_TABLE);
		onCreate(db);
	}
	
	// THAT COLOR IS
	public void insertThatColorIsValues() {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = this.getWritableDatabase();
		// VALUES FOR THATCOLORIS
		cv.put(fThatColorIs_ID, 0);
		cv.put(fColor_isAnswered, "false");	
			db.insert(table_ThatColorIs, null, cv);
			db.close();
	}
	
	public void updateThatColorIs(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fColor_isAnswered, s);
		db.update(table_ThatColorIs, cv, fThatColorIs_ID+"=" +id, null);
	}
	
	public String isAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		//String[] mySearch = new String[]{String.valueOf(id)};
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
