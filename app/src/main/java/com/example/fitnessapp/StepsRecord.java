package com.example.fitnessapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class StepsRecord extends SQLiteOpenHelper {

    private static final String TAG = "StepsRecord";
    private static final String TABLE_NAME = "StepCounterRecord";
    private static final String COL1="Date";
    private static final String COL2 = "Goal";
    private static final String COL3= "stepsTaken";



    public StepsRecord(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("+ COL1 + " TEXT,"
                + COL2 + " TEXT," + COL3 + " TEXT"+ ")";
        db.execSQL(createTable);
        setvalues(db);
    }
    public void setvalues(SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, "Date");
        contentValues.put(COL2, "Target Steps");
        contentValues.put(COL3, "Steps Taken");
        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public boolean addData(float goal, int steps) {
         SQLiteDatabase db = this.getWritableDatabase();
        String gooals = String.valueOf(goal);
        String stepstaken = String.valueOf(steps);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, date());
        contentValues.put(COL2, gooals);
        contentValues.put(COL3, stepstaken);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private String date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }


}
