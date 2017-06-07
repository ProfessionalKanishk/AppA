package com.example.wakeuplabs.freefuud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Anjana on 6/7/2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FF.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Events_data";
    public static final String COL_1 = "Events";
    public static final String COL_2 = "Food";
    public static final String COL_3 = "Location";
    public static final String COL_4 = "Time";
    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + ", " + COL_2 + ", " + COL_3 + ", " + COL_4 + ")";


    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.e("DATABASE OPERATIONS","Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
            Log.e("DATABASE OPERATIONS","Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
