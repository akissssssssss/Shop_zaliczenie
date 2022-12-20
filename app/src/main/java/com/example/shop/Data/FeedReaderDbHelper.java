package com.example.shop.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shop.db";

    static final String SQL_CREATE_TABLE1 =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_SURNAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_EMAIL + " TEXT UNIQUE," +
                    FeedReaderContract.FeedEntry.COLUMN_PHONE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_PASS + " TEXT)";

    static final String SQL_CREATE_TABLE2 =
            "CREATE TABLE " + FeedReaderContract.FeedEntry2.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry2._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry2.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry2.COLUMN_PRODUCTS_DESCRIPTION + " TEXT," +
                    FeedReaderContract.FeedEntry2.COLUMN_PRICE + " FLOAT,"+
                    FeedReaderContract.FeedEntry2.COLUMN_PHOTO + " TEXT," +
                    FeedReaderContract.FeedEntry2.COLUMN_CATEGORY+" TEXT)";

    static final String SQL_CREATE_TABLE3 =
            "CREATE TABLE " + FeedReaderContract.FeedEntry3.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry3._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry3.COLUMN_NAME + " INT," +
                    FeedReaderContract.FeedEntry3.COLUMN_ITEMS + " TEXT," +
                    FeedReaderContract.FeedEntry3.COLUMN_PRICE + " FLOAT,"+
                    FeedReaderContract.FeedEntry3.COLUMN_DATA+" DATETIME)";

    static final String SQL_CREATE_TABLE4 =
            "CREATE TABLE " + FeedReaderContract.FeedEntry4.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry4._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry4.COLUMN_CATEGORY_NAME+" TEXT)";

    static final String SQL_CREATE_TABLE5 =
            "CREATE TABLE " + FeedReaderContract.FeedEntry5.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry5._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry5.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry5.COLUMN_ITEM_ID + " INTEGER,"+
                    FeedReaderContract.FeedEntry5.COLUMN_QUANTITY+" INTEGER)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onDestroy(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE1);
        db.execSQL(SQL_CREATE_TABLE2);
        db.execSQL(SQL_CREATE_TABLE3);
        db.execSQL(SQL_CREATE_TABLE4);
        db.execSQL(SQL_CREATE_TABLE5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
