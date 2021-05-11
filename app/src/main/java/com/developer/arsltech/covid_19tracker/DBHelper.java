package com.developer.arsltech.covid_19tracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata15.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, date TEXT, phone TEXT, address TEXT, out TEXT, come TEXT, healths TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata(String name, String date, String phone, String address, String out, String come, String healths){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", date);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("out", out);
        contentValues.put("come", come);
        contentValues.put("healths", healths);
        long result = DB.insert("Userdetails", null, contentValues);
        return result != -1;
    }

    public boolean updateuserdata(String name, String date, String phone, String address, String out, String come, String healths) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("out", out);
        contentValues.put("come", come);
        contentValues.put("healths", healths);
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }
    }
    public Boolean deletedata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails","name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}