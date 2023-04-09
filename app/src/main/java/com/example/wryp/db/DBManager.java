package com.example.wryp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        Log.d(Constants.LOG_TAG, "getDb: return db");
        return db;
    }

    public DBManager(Context context, String DBName) {
        this.context = context;
        dbHelper = new DBHelper(context, DBName);
        Log.d(Constants.LOG_TAG, "DBManager: create");
    }

    public void openDB() {
        db = dbHelper.getWritableDatabase();
        Log.d(Constants.LOG_TAG, "openDB: open");
    }

    public void insertTagToDB(String tag) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.TAG_VALUE, tag);
        db.insert(Constants.TABLE_NAME_TAGS, null, cv);
        Log.d(Constants.LOG_TAG, "insertTagToDB: yes");
    }

    public void insertToDB(String date, int tag, String description) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Constants.TAG_VALUE, tag);
//        db.insert(Constants.TABLE_NAME_TAGS, null, contentValues);

        DBManager dbManager = new DBManager(context, Constants.TABLE_NAME_TAGS);
        dbManager.openDB();

        ContentValues cv = new ContentValues();
        cv.put("`" + Constants.DATE + "`", date);
        cv.put("`" +Constants.TAG + "`", tag);
        cv.put("`" + Constants.DESCRIPTION + "`", description);
        Log.d(Constants.LOG_TAG, "insertToDB: " + date + " " + tag  + " " +  description);
        Log.d(Constants.LOG_TAG, "insertToDB: " + dbManager.getFromDB());
        Log.d(Constants.LOG_TAG, "insertToDB: " + cv);
        db.execSQL("INSERT INTO `notes`(`date`, `tag`, `description`) VALUES (\"" + date + "\", " + tag + ", \"" + description + "\")");
//        db.insert(Constants.TABLE_NAME_NOTES, null, cv);
        Log.d(Constants.LOG_TAG, "insertToDB: Insert successfully");
        dbManager.closeDB();
    }

    public List<String> getFromDB() {
        Log.d(Constants.LOG_TAG, "getFromDB: zapustilis");
        List<String> tempList = new ArrayList<>();
        if (noteTableIsEmpty()) {
            Cursor cursor = db.rawQuery(Constants.GET_FROM_NOTES_REQUEST, null);

            while (cursor.moveToNext()) {
                String tempString = "";
                for (int i = 0; i <= 2; i++) {
                    tempString += cursor.getString(i);
                }
                tempList.add(tempString);
            }

            cursor.close();
            Log.d(Constants.LOG_TAG, "getFromDB: zabrali");
            return tempList;
        }
        else {
            Log.d(Constants.LOG_TAG, "getFromDB: list vernuli");
            return tempList;
        }
    }

    public int countOfTag(String tag) {
        DBManager dbManager = new DBManager(context, Constants.TABLE_NAME_TAGS);
        dbManager.openDB();

        Cursor cursor = dbManager.getDb().rawQuery(Constants.COUNT_OF_TAGS_REQUEST + "\"" + tag + "\";", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        dbManager.closeDB();
        return count;
    }

    public int convertTag(String s) {
        DBManager dbManager = new DBManager(context, Constants.TABLE_NAME_TAGS);
        dbManager.openDB();

        if (dbManager.countOfTag(s) > 0) {
            Cursor cursor = dbManager.getDb().rawQuery(Constants.FIND_REQUEST + "\"" + s + "\"", null);
            cursor.moveToFirst();
            int intTag = cursor.getInt(0);
            cursor.close();
            dbManager.closeDB();
            return intTag;
        }
        else {
            dbManager.insertTagToDB(s);

            Cursor cursor = dbManager.getDb().rawQuery(Constants.FIND_REQUEST + "\"" + s + "\"", null);
            cursor.moveToFirst();
            int intTag = cursor.getInt(0);
            cursor.close();
            dbManager.closeDB();
            return intTag;
        }
    }

    public boolean noteTableIsEmpty() {
        DBManager dbManager = new DBManager(context, Constants.TABLE_NAME_NOTES);
        dbManager.openDB();

        Cursor cursor = dbManager.getDb().rawQuery(Constants.COUNT_OF_NOTES_REQUEST, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0) {
            cursor.close();
            dbManager.closeDB();
            return true;
        }
        else {
            cursor.close();
            dbManager.closeDB();
            return false;
        }
    }

    public void closeDB() {
        dbHelper.close();
    }

    public void dropTable() {
        db.delete(Constants.TABLE_NAME_TAGS, null, null);
    }
}
