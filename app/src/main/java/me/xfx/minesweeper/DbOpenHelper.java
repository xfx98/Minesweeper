package me.xfx.minesweeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
    public DbOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table LocalPhb1 (_id integer primary key autoincrement," +
                "name varchar(40),time int)");
        db.execSQL("create table LocalPhb9 (_id integer primary key autoincrement," +
                "name varchar(40),time int)");
        db.execSQL("create table LocalPhb16 (_id integer primary key autoincrement," +
                "name varchar(40),time int)");
        db.execSQL("create table LocalPhb30 (_id integer primary key autoincrement," +
                "name varchar(40),time int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
