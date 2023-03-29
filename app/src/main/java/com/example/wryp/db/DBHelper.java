//package com.example.wryp.db;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class DBHelper extends SQLiteOpenHelper {
//    public DBHelper(@Nullable Context context) {
//        super(context, Constants.TABLE_NAME, null, Constants.DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(Constants.TABLE_STRUCTURE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(Constants.DROP_TABLE);
//        onCreate(db);
//    }
//}
