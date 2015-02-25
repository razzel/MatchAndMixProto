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
	
	// MATCH IT
	public static final String table_MatchIt = "match";
	public static final String fMatch_ID = "match_id";
	public static final String fMatch_isAnswered = "isAnswered";
	public static final String CREATE_MATCHIT_TABLE = "CREATE TABLE IF NOT EXISTS " +table_MatchIt+ " ("
													+fMatch_ID+ " INTEGER PRIMARY KEY, "
													+fMatch_isAnswered+ " TEXT "
													+ ")";
	
	// COUNT IT
	public static final String table_CountIt = "count";
	public static final String fCount_ID = "count_id";
	public static final String fCount_isAnswered = "isAnswered";
	public static final String CREATE_COUNTIT_TABLE = "CREATE TABLE IF NOT EXISTS " +table_CountIt+ " ("
													+fCount_ID+ " INTEGER PRIMARY KEY, "
													+fCount_isAnswered+ " TEXT "
													+ ") ";
	
	// SOLVE IT 
		// ADD TABLE
		public static final String table_SolveItAdd = "Adding";
		public static final String fAdd_ID = "add_id";
		public static final String fAdd_isAnswered = "isAnswered";
		public static final String CREATE_SOLVEIT_ADD_TABLE = "CREATE TABLE IF NOT EXISTS " +table_SolveItAdd+ " ("
													+fAdd_ID+ " INTEGER PRIMARY KEY, "
													+fAdd_isAnswered+ " TEXT "
													+ ") ";
		// SUB TABLE
		public static final String table_SolveItSub = "Sub";
		public static final String fSub_ID = "Sub_id";
		public static final String fSub_isAnswered = "isAnswered";
		public static final String CREATE_SOLVEIT_SUB_TABLE = "CREATE TABLE IF NOT EXISTS " +table_SolveItSub+ " ("
															+fSub_ID+ " INTEGER PRIMARY KEY, "
															+fSub_isAnswered+ " TEXT "
															+ ") ";
		// MUL TABLE
		public static final String table_SolveItMul = "Mul";
		public static final String fMul_ID = "Mul_id";
		public static final String fMul_isAnswered = "isAnswered";
		public static final String CREATE_SOLVEIT_MUL_TABLE = "CREATE TABLE IF NOT EXISTS " +table_SolveItMul+ " ("
															+fMul_ID+ " INTEGER PRIMARY KEY, "
															+fMul_isAnswered+ " TEXT "
															+ ") ";
		// DIV TABLE
		public static final String table_SolveItDiv = "Div";
		public static final String fDiv_ID = "Div_id";
		public static final String fDiv_isAnswered = "isAnswered";
		public static final String CREATE_SOLVEIT_DIV_TABLE = "CREATE TABLE IF NOT EXISTS " +table_SolveItDiv+ " ("
															+fDiv_ID+ " INTEGER PRIMARY KEY, "
															+fDiv_isAnswered+ " TEXT "
															+ ") ";
		
		// IS FIRST TIME FOR GAMES
		public static final String table_isFirstTime = "first_time";
		public static final String fFirstTime_ID = "id";
		public static final String fIsFirstTime = "isFirstTime";
		public static final String CREATE_FIRSTTIME_TABLE = "CREATE TABLE IF NOT EXISTS " +table_isFirstTime+ " ("
															+fFirstTime_ID+ " INTEGER PRIMARY KEY, "
															+fIsFirstTime+ " TEXT " 
															+ ") ";
		
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
		
		// GUESS THE MISSING LETTER
		db.execSQL(CREATE_GTML_TABLE);
		insertGTMLValues(db);
		
		// MATCH IT
		db.execSQL(CREATE_MATCHIT_TABLE);
		insertMatchItValues(db);
		
		// COUNT IT
		db.execSQL(CREATE_COUNTIT_TABLE);
		insertCountValues(db);
		
		// SOLVE IT
			// ADD
			db.execSQL(CREATE_SOLVEIT_ADD_TABLE);
			insertSolveItAddValues(db);
			
			// SUB
			db.execSQL(CREATE_SOLVEIT_SUB_TABLE);
			insertSolveItSubValues(db);
			
			// MUL
			db.execSQL(CREATE_SOLVEIT_MUL_TABLE);
			insertSolveItMulValues(db);
			
			// DIV
			db.execSQL(CREATE_SOLVEIT_DIV_TABLE);
			insertSolveItDiv(db);
		
		// CREATE FIRSTTIME TABLE
		ContentValues isFirstTimeCV = new ContentValues();
		db.execSQL(CREATE_FIRSTTIME_TABLE);
		/* 0 - match it
		 * 1 - guess the missing letter
		 * 2 - that color is
		 * 3 - count it
		 * 4 - solveit add
		 * 5 - solveit sub
		 * 6 - solveit mul
		 * 7 - solveit div
		 */
		isFirstTimeCV.put(fFirstTime_ID, 0);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);
			
		isFirstTimeCV.put(fFirstTime_ID, 1);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 2);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 3);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 4);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 5);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 6);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);

		isFirstTimeCV.put(fFirstTime_ID, 7);
		isFirstTimeCV.put(fIsFirstTime, "true");
			db.insert(table_isFirstTime, null, isFirstTimeCV);
											
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_ThatColorIs_TABLE);
		db.execSQL("DROP T=ABLE IF EXISTS " + CREATE_OPTION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_GTML_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_MATCHIT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_SOLVEIT_ADD_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_SOLVEIT_SUB_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_SOLVEIT_MUL_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_SOLVEIT_DIV_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_FIRSTTIME_TABLE);
		onCreate(db);
	}
	
	public String checkIsFirstTime(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fIsFirstTime + " FROM " + table_isFirstTime + " WHERE " + fFirstTime_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fIsFirstTime);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	public void updateIsFirstTime(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fIsFirstTime, s);
		db.update(table_isFirstTime, cv, fFirstTime_ID+"=" +id, null);
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
				
				colorValues.put(fThatColorIs_ID, 5);
				colorValues.put(fColor_isAnswered, "true");	
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
				
				colorValues.put(fThatColorIs_ID, 11);
				colorValues.put(fColor_isAnswered, "true");	
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
				
				colorValues.put(fThatColorIs_ID, 17);
				colorValues.put(fColor_isAnswered, "true");	
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
				
				colorValues.put(fThatColorIs_ID, 23);
				colorValues.put(fColor_isAnswered, "true");	
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
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"true"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_ThatColorIs+ " WHERE " +fColor_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count-4;
	}
	
	public int colorGetRemaining() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"false"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_ThatColorIs+ " WHERE " +fColor_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count;
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
	private void insertGTMLValues(SQLiteDatabase db) {
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
			
			gtmlValues.put(fGTML_ID,5);
			gtmlValues.put(fgtml_isAnswered, "true");
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
			
			gtmlValues.put(fGTML_ID,11);
			gtmlValues.put(fgtml_isAnswered, "true");
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
			
			gtmlValues.put(fGTML_ID,17);
			gtmlValues.put(fgtml_isAnswered, "true");
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
			
			gtmlValues.put(fGTML_ID,23);
			gtmlValues.put(fgtml_isAnswered, "true");
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
	
	public int gtmlGetAnswered() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"true"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_GTML+ " WHERE " +fgtml_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count-4;
	}
	
	public int gtmlGetRemaining() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"false"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_GTML+ " WHERE " +fgtml_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count;
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
	
	// MATCH IT *************************************************************************************************************
	private void insertMatchItValues(SQLiteDatabase db) {
		ContentValues matchValues = new ContentValues();
		
		matchValues.put(fMatch_ID, 0);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 1);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
	
		matchValues.put(fMatch_ID, 2);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
			
		matchValues.put(fMatch_ID, 3);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 4);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
			
			matchValues.put(fMatch_ID, 5);
			matchValues.put(fMatch_isAnswered, "true");
				db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 6);
		matchValues.put(fMatch_isAnswered, "false");
			db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 7);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 8);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 9);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 10);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
				matchValues.put(fMatch_ID, 11);
				matchValues.put(fMatch_isAnswered, "true");
					db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 12);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 13);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 14);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 15);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
										
		matchValues.put(fMatch_ID, 16);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
											
				matchValues.put(fMatch_ID, 17);
				matchValues.put(fMatch_isAnswered, "true");
					db.insert(table_MatchIt, null, matchValues);
		matchValues.put(fMatch_ID, 18);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
														
		matchValues.put(fMatch_ID, 19);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
																
		matchValues.put(fMatch_ID, 20);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
																		
		matchValues.put(fMatch_ID, 21);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
																				
		matchValues.put(fMatch_ID, 22);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
				matchValues.put(fMatch_ID, 23);
				matchValues.put(fMatch_isAnswered, "true");
					db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 24);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 25);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 26);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
		
		matchValues.put(fMatch_ID, 27);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
				
		matchValues.put(fMatch_ID, 28);
		matchValues.put(fMatch_isAnswered, "false");
				db.insert(table_MatchIt, null, matchValues);
	}
	
	public void updateMatchIt(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fMatch_isAnswered, s);
		db.update(table_MatchIt, cv, fMatch_ID+" = " +id, null);
	}
	
	public int matchGetAnswered() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"true"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_MatchIt+ " WHERE " +fgtml_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count-4;
	}
	
	public int matchGetRemaining() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"false"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_MatchIt+ " WHERE " +fgtml_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count;
	}
	
	public String matchItIsAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fMatch_isAnswered + " FROM " + table_MatchIt + " WHERE " + fMatch_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fMatch_isAnswered);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	// COUNT IT ***********************************************************************************************************
	public void insertCountValues(SQLiteDatabase db) {
		ContentValues countValues = new ContentValues();
		
		countValues.put(fCount_ID, 0);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 1);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
	
		countValues.put(fCount_ID, 2);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
			
		countValues.put(fCount_ID, 3);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 4);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
			
			countValues.put(fCount_ID, 5);
			countValues.put(fMatch_isAnswered, "true");
				db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 6);
		countValues.put(fMatch_isAnswered, "false");
			db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 7);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 8);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 9);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 10);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
				countValues.put(fCount_ID, 11);
				countValues.put(fMatch_isAnswered, "true");
					db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 12);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 13);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 14);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 15);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
										
		countValues.put(fCount_ID, 16);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
											
				countValues.put(fCount_ID, 17);
				countValues.put(fMatch_isAnswered, "true");
					db.insert(table_CountIt, null, countValues);
		countValues.put(fCount_ID, 18);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
														
		countValues.put(fCount_ID, 19);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
																
		countValues.put(fCount_ID, 20);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
																		
		countValues.put(fCount_ID, 21);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
																				
		countValues.put(fCount_ID, 22);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
				countValues.put(fCount_ID, 23);
				countValues.put(fMatch_isAnswered, "true");
					db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 24);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 25);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 26);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
		
		countValues.put(fCount_ID, 27);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
				
		countValues.put(fCount_ID, 28);
		countValues.put(fMatch_isAnswered, "false");
				db.insert(table_CountIt, null, countValues);
		
	}
	
	public void updateCountIt(int id, String s) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fCount_isAnswered, s);
		db.update(table_CountIt, cv, fCount_ID+" = " +id, null);
	}
	
	public int countGetAnswered() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"true"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_CountIt+ " WHERE " +fCount_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count-4;
	}
	
	public int countGetRemaining() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] mySearch = new String[]{"false"};
		Cursor c = db.rawQuery("SELECT count(*) FROM " +table_CountIt+ " WHERE " +fColor_isAnswered+ " =?", mySearch);
		int count = 0;
		while(c.moveToNext()) {
			int countIndex = c.getColumnIndex("count(*)");
			count = c.getInt(countIndex);
		}
		c.close();
		db.close();
		return count;
	}
	
	public String countItIsAnswered(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + fCount_isAnswered + " FROM " + table_CountIt + " WHERE " + fCount_ID + " = " +id, null);
		c.moveToFirst();
		int index = c.getColumnIndex(fCount_isAnswered);
		String myReturn = c.getString(index);
		c.close();
		return myReturn;
	}
	
	// SOLVE IT *******************************************************************************************************
		// ADD
		public void insertSolveItAddValues(SQLiteDatabase db) {
			ContentValues addCV = new ContentValues();
			
			addCV.put(fAdd_ID, 0);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
			
			addCV.put(fAdd_ID, 1);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
			
			addCV.put(fAdd_ID, 2);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 3);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 4);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
				addCV.put(fAdd_ID, 5);
				addCV.put(fAdd_isAnswered, "true");
					db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 6);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 7);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 8);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 9);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 10);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
				addCV.put(fAdd_ID, 11);
				addCV.put(fAdd_isAnswered, "true");
					db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 12);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 13);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 14);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 15);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 16);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
				addCV.put(fAdd_ID, 17);
				addCV.put(fAdd_isAnswered, "true");
					db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 18);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 19);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 20);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 21);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 22);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
				addCV.put(fAdd_ID, 23);
				addCV.put(fAdd_isAnswered, "true");
					db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 24);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 25);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 26);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 27);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
	
			addCV.put(fAdd_ID, 28);
			addCV.put(fAdd_isAnswered, "false");
				db.insert(table_SolveItAdd, null, addCV);
			
		}
		
		public void updateSolveItAdd(int id, String s) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(fAdd_isAnswered, s);
			db.update(table_SolveItAdd, cv, fAdd_ID+" = " +id, null);
		}
		
		public int solveItAddGetAnswered() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"true"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItAdd+ " WHERE " +fAdd_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count-4;
		}
		
		public int solveItAddGetRemaining() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"false"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItAdd+ " WHERE " +fAdd_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count;
		}
		
		public String solveItAddIsAnswered(int id) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery("SELECT " + fAdd_isAnswered + " FROM " + table_SolveItAdd + " WHERE " + fAdd_ID + " = " +id, null);
			c.moveToFirst();
			int index = c.getColumnIndex(fAdd_isAnswered);
			String myReturn = c.getString(index);
			c.close();
			return myReturn;
		}
		
		// SUB
		public void insertSolveItSubValues(SQLiteDatabase db) {
			ContentValues subCV = new ContentValues();
			
			subCV.put(fSub_ID, 0);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
			
			subCV.put(fSub_ID, 1);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
			
			subCV.put(fSub_ID, 2);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 3);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 4);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
				subCV.put(fSub_ID, 5);
				subCV.put(fSub_isAnswered, "true");
					db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 6);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 7);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 8);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 9);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 10);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
				subCV.put(fSub_ID, 11);
				subCV.put(fSub_isAnswered, "true");
					db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 12);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 13);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 14);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 15);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 16);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
				subCV.put(fSub_ID, 17);
				subCV.put(fSub_isAnswered, "true");
					db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 18);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 19);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 20);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 21);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 22);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
				subCV.put(fSub_ID, 23);
				subCV.put(fSub_isAnswered, "true");
					db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 24);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 25);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 26);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 27);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
	
			subCV.put(fSub_ID, 28);
			subCV.put(fSub_isAnswered, "false");
				db.insert(table_SolveItSub, null, subCV);
		}
	
		public void updateSolveItSub(int id, String s) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(fSub_isAnswered, s);
			db.update(table_SolveItSub, cv, fSub_ID+" = " +id, null);
		}
		
		public int solveItSubGetAnswered() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"true"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItSub+ " WHERE " +fSub_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count-4;
		}
		
		public int solveItSubGetRemaining() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"false"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItSub+ " WHERE " +fSub_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count;
		}
		
		public String solveItSubIsAnswered(int id) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery("SELECT " + fSub_isAnswered + " FROM " + table_SolveItSub + " WHERE " + fSub_ID + " = " +id, null);
			c.moveToFirst();
			int index = c.getColumnIndex(fSub_isAnswered);
			String myReturn = c.getString(index);
			c.close();
			return myReturn;
		}
		
		// MUL
		public void insertSolveItMulValues(SQLiteDatabase db) {
			ContentValues mulCV = new ContentValues();
			
			mulCV.put(fMul_ID, 0);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
			
			mulCV.put(fMul_ID, 1);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
			
			mulCV.put(fMul_ID, 2);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 3);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 4);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
				mulCV.put(fMul_ID, 5);
				mulCV.put(fMul_isAnswered, "true");
					db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 6);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 7);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 8);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 9);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 10);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
				mulCV.put(fMul_ID, 11);
				mulCV.put(fMul_isAnswered, "true");
					db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 12);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 13);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 14);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 15);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 16);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
				mulCV.put(fMul_ID, 17);
				mulCV.put(fMul_isAnswered, "true");
					db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 18);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 19);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 20);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 21);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 22);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
				mulCV.put(fMul_ID, 23);
				mulCV.put(fMul_isAnswered, "true");
					db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 24);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 25);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 26);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 27);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
	
			mulCV.put(fMul_ID, 28);
			mulCV.put(fMul_isAnswered, "false");
				db.insert(table_SolveItMul, null, mulCV);
		}
		
		public void updateSolveItMul(int id, String s) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(fMul_isAnswered, s);
			db.update(table_SolveItMul, cv, fMul_ID+" = " +id, null);
		}
		
		public int solveItMulGetAnswered() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"true"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItMul+ " WHERE " +fMul_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count-4;
		}
		
		public int solveItMulGetRemaining() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"false"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItMul+ " WHERE " +fMul_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count;
		}
		
		public String solveItMulIsAnswered(int id) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery("SELECT " + fMul_isAnswered + " FROM " + table_SolveItMul + " WHERE " + fMul_ID + " = " +id, null);
			c.moveToFirst();
			int index = c.getColumnIndex(fMul_isAnswered);
			String myReturn = c.getString(index);
			c.close();
			return myReturn;
		}
		
		// DIV
		public void insertSolveItDiv(SQLiteDatabase db) {
			ContentValues divCV = new ContentValues();
			
			divCV.put(fDiv_ID, 0);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
			
			divCV.put(fDiv_ID, 1);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
			
			divCV.put(fDiv_ID, 2);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 3);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 4);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
				divCV.put(fDiv_ID, 5);
				divCV.put(fDiv_isAnswered, "true");
					db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 6);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 7);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 8);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 9);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 10);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
				divCV.put(fDiv_ID, 11);
				divCV.put(fDiv_isAnswered, "true");
					db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 12);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 13);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 14);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 15);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 16);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
				divCV.put(fDiv_ID, 17);
				divCV.put(fDiv_isAnswered, "true");
					db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 18);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 19);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 20);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 21);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 22);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
				divCV.put(fDiv_ID, 23);
				divCV.put(fDiv_isAnswered, "true");
					db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 24);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 25);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 26);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 27);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
	
			divCV.put(fDiv_ID, 28);
			divCV.put(fDiv_isAnswered, "false");
				db.insert(table_SolveItDiv, null, divCV);
		}
		
		public void updateSolveItDiv(int id, String s) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(fDiv_isAnswered, s);
			db.update(table_SolveItDiv, cv, fDiv_ID+" = " +id, null);
		}
		
		public int solveItDivGetAnswered() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"true"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItDiv+ " WHERE " +fDiv_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count-4;
		}
		
		public int solveItDivGetRemaining() {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] mySearch = new String[]{"false"};
			Cursor c = db.rawQuery("SELECT count(*) FROM " +table_SolveItDiv+ " WHERE " +fDiv_isAnswered+ " =?", mySearch);
			int count = 0;
			while(c.moveToNext()) {
				int countIndex = c.getColumnIndex("count(*)");
				count = c.getInt(countIndex);
			}
			c.close();
			db.close();
			return count;
		}
		
		public String solveItDivIsAnswered(int id) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery("SELECT " + fDiv_isAnswered + " FROM " + table_SolveItDiv + " WHERE " + fDiv_ID + " = " +id, null);
			c.moveToFirst();
			int index = c.getColumnIndex(fDiv_isAnswered);
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
