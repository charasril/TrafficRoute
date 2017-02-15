package com.example.win.trafficroute.db;

/**
 * Created by win on 15/2/2560.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "ITM801MAPRoute.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "SEARCH_USE_ROUTE_LIST";
    public static final String COL_ID = "_id";
    public static final String COL_USERNAME = "USER_NAME";
    public static final String COL_USE_DATE = "USE_DATETIME";
    public static final String COL_START = "USER_START";
    public static final String COL_START_LAT = "USER_LATITUDE_START";
    public static final String COL_START_LNG = "USER_LONGTITUDE_START";
    public static final String COL_END = "USER_END";
    public static final String COL_END_LAT = "USER_LATITUDE_END";
    public static final String COL_END_LNG = "USER_LONGTITUDE_END";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating table");

//        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
//                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COL_SCORE + " REAL, "
//                + COL_DIFFICULTY + " INTEGER" + ")";
//        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
