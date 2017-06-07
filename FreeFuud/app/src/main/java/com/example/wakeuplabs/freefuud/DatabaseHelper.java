package com.example.wakeuplabs.freefuud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FF.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Events_data";
    public static final String COL_1 = "Events";
    public static final String COL_2 = "Food";
    public static final String COL_3 = "Location";
    public static final String COL_4 = "Time";
    private SQLiteDatabase sqLiteDatabase;
    Context context;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String event, String food,String location,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,event);
        contentValues.put(COL_2,food);
        contentValues.put(COL_3,location);
        contentValues.put(COL_4,time);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String event, String food,String location,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,event);
        contentValues.put(COL_2,food);
        contentValues.put(COL_3,location);
        contentValues.put(COL_4,time);
        db.update(TABLE_NAME, contentValues, "Event = ?",new String[] { event });
        return true;
    }

    public Cursor getInformation(SQLiteDatabase db){


        Cursor cursor;
        String[] projections = {COL_1, COL_2, COL_3, COL_4};
        cursor = db.query(TABLE_NAME,projections,null,null,null,null,null); //selection, selection args, group rows, filter by row group, sort order
        return cursor;

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void openDatabase(){
        String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
        if(sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase(){
        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }

    /***** PART 2
    public List<Product> getListProduct(){
        Product product = null;
        List<Product> productList = new ArrayList<>();
        openDatabase();
        Cursor curs = sqLiteDatabase.rawQuery("TEST GET LIST PRODUCT <-- DATABASE HALPER", null);
        curs.moveToFirst();
        while(!curs.isAfterLast()){
            product = new Product(curs.getString(0), curs.getString(1), curs.getString(2), curs.getString(3));
            productList.add(product);
            curs.moveToNext();

        }
        curs.close();
        closeDatabase();
        return productList

    }
     */
}
