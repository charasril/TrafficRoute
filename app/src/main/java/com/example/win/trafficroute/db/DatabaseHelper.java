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

    public static final String TABLE_ROUTE_NAME = "SEARCH_USE_ROUTE_LIST";
    public static final String COL_ROUTE_ID = "_id";
    public static final String COL_USERNAME = "USER_NAME";
    public static final String COL_USE_DATE = "USE_DATETIME";
    public static final String COL_START_WAYPOINT = "USER_START_WAYPOINT";
    public static final String COL_START_LAT = "USER_LATITUDE_START";
    public static final String COL_START_LNG = "USER_LONGTITUDE_START";
    public static final String COL_END_WAYPOIN = "USER_END_WAYPOINT";
    public static final String COL_END_LAT = "USER_LATITUDE_END";
    public static final String COL_END_LNG = "USER_LONGTITUDE_END";

    public static final String TABLE_USER_NAME = "TABLE_USER_INFORMATION";
    public static final String COL_USER_ID = "_id";
    public static final String COL_USER_NAME = "USER_NAME";
    public static final String COL_USER_PASSWORD= "USER_PASSWORD";
    public static final String COL_USER_FNAME = "USER_FNAME";
    public static final String COL_USER_LNAME = "USER_LNAME";
    public static final String COL_USER_EMAIL = "USER_EMAIL";
    public static final String COL_USER_ACTIEVE_DATE = "USER_ACTIVE_DATE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating table==>"+TABLE_ROUTE_NAME);

  String SQL_CREATE_TABLE_ROUTE = "CREATE TABLE " + TABLE_ROUTE_NAME + "("
                + COL_ROUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USERNAME + " TEXT, "
                + COL_USE_DATE + " TEXT, "
                + COL_START_WAYPOINT + " TEXT, "
                + COL_START_LAT + " TEXT, "
                + COL_START_LNG + " TEXT, "
                + COL_END_WAYPOIN + " TEXT, "
                + COL_END_LAT + " TEXT, "
                + COL_END_LNG + " TEXT" + ")";
        db.execSQL(SQL_CREATE_TABLE_ROUTE);

         String SQL_CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER_NAME + "("
                + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_NAME + " TEXT, "
                + COL_USER_PASSWORD + " TEXT, "
                + COL_USER_FNAME + " TEXT, "
                + COL_USER_LNAME + " TEXT, "
                + COL_USER_EMAIL + " TEXT, "
                + COL_USER_ACTIEVE_DATE + " DATE" + ")";
        db.execSQL(SQL_CREATE_TABLE_USER);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
