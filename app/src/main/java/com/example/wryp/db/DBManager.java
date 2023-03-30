package com.example.wryp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context, String DBName) {
        this.context = context;
        dbHelper = new DBHelper(context, DBName);
    }

    public void openDB() {
        db = dbHelper.getWritableDatabase();
    }

    public void insertToDB(String date, String tag, String description) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Constants.TAG_VALUE, tag);
//        db.insert(Constants.TABLE_NAME_TAGS, null, contentValues);

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATE, date);
        cv.put(Constants.TAG, tag);
        cv.put(Constants.DESCRIPTION, description);
        db.insert(Constants.CREATE_TABLE_NOTES, null, cv);
    }

    public List<String> getFromDB() {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME_TAGS, null, null, null,
                null, null, null);

        while (cursor.moveToNext()) {
            String s = cursor.getString(cursor.getColumnIndex(Constants.TAG_VALUE));
            tempList.add(s);
        }

        cursor.close();
        return tempList;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public void dropTable() {
        db.delete(Constants.TABLE_NAME_TAGS, null, null);
    }
}
