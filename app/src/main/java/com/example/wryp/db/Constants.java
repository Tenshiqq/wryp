package com.example.wryp.db;

public class Constants {
    public static final String LOG_TAG = "TENSHI";

    public static final String DB_NAME = "wrypDB.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME_TAGS = "tags";
    public static final String TAG_ID = "tag_id";
    public static final String TAG_VALUE = "tag_value";
    public static final String CREATE_TABLE_TAGS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TAGS + " (" +
            TAG_ID + " INTEGER NOT NULL UNIQUE, " +
            TAG_VALUE + " TEXT NOT NULL UNIQUE, " +
            "PRIMARY KEY(" + TAG_ID + " AUTOINCREMENT));";
    public static final String DROP_TABLE_TAGS = "DROP TABLE IF EXISTS " + TABLE_NAME_TAGS + ";";

    public static final String TABLE_NAME_NOTES = "notes";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String TAG = "tag";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_TABLE_NOTES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_NOTES + " (" +
            ID + " INTEGER NOT NULL UNIQUE, \"" +
            DATE + "\" TEXT, " +
            TAG + " INTEGER, " +
            DESCRIPTION + " TEXT, " +
            "FOREIGN KEY(" + TAG + ") REFERENCES " + TABLE_NAME_TAGS + "(" + TAG_ID + "), " +
            "PRIMARY KEY(" + ID + " AUTOINCREMENT))";
    public static final String DROP_TABLE_NOTES = "DROP TABLE IF EXISTS " + TABLE_NAME_NOTES + ";";

    public static final String COUNT_OF_TAGS_REQUEST = "SELECT COUNT(*) FROM `" + TABLE_NAME_TAGS + "` WHERE " + TAG_VALUE + " == ";
    public static final String COUNT_OF_NOTES_REQUEST = "SELECT COUNT(*) FROM `" + TABLE_NAME_NOTES + "`";
    public static final String FIND_REQUEST = "SELECT * FROM " + TABLE_NAME_TAGS + " WHERE " + TAG_VALUE + " == ";
    public static final String GET_FROM_NOTES_REQUEST = "SELECT " + "`" +DATE + "`, " + TAG_VALUE + ", " + DESCRIPTION +
            " FROM " + TABLE_NAME_NOTES + " JOIN " + TABLE_NAME_TAGS + " ON " + TAG + " == " + TAG_ID + ";";
}
