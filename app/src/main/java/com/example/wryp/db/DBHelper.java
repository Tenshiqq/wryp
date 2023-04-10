package com.example.wryp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_TAGS);
        Log.d(Constants.LOG_TAG, "onCreate: TAGS CREATED");
        db.execSQL(Constants.CREATE_TABLE_NOTES);
        Log.d(Constants.LOG_TAG, "onCreate: NOTES CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TABLE_NOTES);
        db.execSQL(Constants.DROP_TABLE_TAGS);
        onCreate(db);
    }
}
