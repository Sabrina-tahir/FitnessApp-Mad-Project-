package com.example.fitnessapp;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteQueryBuilder;
//
//import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//
//public class FitnessDb extends SQLiteAssetHelper {
//    private static final String DATABASE_NAME = "Fitness.db";
//    private static final int DATABASE_VERSION = 1;
//    public FitnessDb(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//    public int getModes(){
//        SQLiteDatabase db = getWritableDatabase();
//        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
//
//
//        String[] sqlSelect = {"Mode"};
//        String sqlTable = "Settings";
//
//        query.setTables(sqlTable);
//        Cursor c = query.query(db,sqlSelect,null,null,null,null,null);
//        c.moveToFirst();
//        return c.getInt(c.getColumnIndex("Mode"));
//
//    }
//    public void SaveMode(int Value){
//        SQLiteDatabase db = getWritableDatabase();
//        String updatequery = "UPDATE Settings SET Mode=" +Value;
//        db.execSQL(updatequery);
//    }
//}
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class FitnessDb extends SQLiteOpenHelper {
    private static final String TAG = "FitnessDb";

    private static final String TABLE_NAME = "AppSettings";
    private static final String COL1 = "Mode";


    public FitnessDb(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " TEXT" + ")";
        db.execSQL(createTable);
        setvalues(db);

    }

    public void setvalues(SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, 1);
        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_NAME));
        onCreate(db);
    }
    public int getModes() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);


            data.moveToFirst();

            return data.getInt(data.getColumnIndex("Mode"));


    }


    public void SaveMode(int Value){
        SQLiteDatabase db = getWritableDatabase();
        String updatequery = "UPDATE AppSettings SET Mode=" +Value;
        db.execSQL(updatequery);
    }





}//class end
