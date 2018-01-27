package com.nshane.generalframe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nshane.generalframe.utils.LogUtil;

/**
 * Created by bryan on 2018-1-26.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static DBOpenHelper mInstance;

    public DBOpenHelper(Context context) {
        super(context, DBMetaData.DATABASE_NAME, null, DBMetaData.DATABASE_VERSION);
    }

    public synchronized static DBOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBOpenHelper(context);
        }
        return mInstance;
    }

    /**
     * invoked when db created @ 1st time
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBMetaData.SearchHistoryMetaData.SQL_CREATE_TABLE);
        db.execSQL(DBMetaData.FriendTableMetaData.SQL_CREATE_TABLE);
        LogUtil.d("cg", "db---onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBMetaData.FriendTableMetaData.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBMetaData.SearchHistoryMetaData.TABLE_NAME);
    }


}
