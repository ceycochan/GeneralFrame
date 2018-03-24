package com.nshane.generalframe.db;

import android.provider.BaseColumns;

/**
 * Created by bryan on 2018-1-26.
 */

public class DBMetaData {

    /**
     * Remarks:
     * <p>
     * 1.standard SQL sentence: String sql = "CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name CHAR(10), nickname CHAR(10) )";
     * <p>
     * 2.a blank space needed to be inserted after "CREATE TABLE IF NOT EXISTS ",same as "DROP TABLE IF EXISTS "
     * <p>
     * 3.a "comma" needed to be inserted after "VARCHAR(50),"
     * <p>
     * 4.version at least is 1
     */

    public static final String DATABASE_NAME = "shane.db";
    public static final int DATABASE_VERSION = 1;  //at least version 1


    public static final class FriendTableMetaData {
        public static final String TABLE_NAME = "friend";

        public static final String USER_NAME = "name";
        public static final String USER_PASSWORD = "password";
        public static final String USER_GENDER = "gender";

//
        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + BaseColumns._ID
                + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + "VARCHAR(50),"
                + USER_PASSWORD + "VARCHAR(50),"
                + USER_GENDER + "VARCHAR(50)"
                + ");";

    }


    public static final class SearchHistoryMetaData {
        public static final String TABLE_NAME = "history";
        public static final String SERACH_HISTROY = "histroy";

        public static final String SQL_CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" + BaseColumns._ID
                + "integer primary key autoincrement,"
                + SERACH_HISTROY + "VARCHAR(50),"
                + ");";
    }

    public static final class UserBoughtLessons {
        public static final String TABLE_NAME = "lesson";

        public static final String LESSON_TITLE = "title";
        public static final String LESSON_AUTHOR = "author";
        public static final String LESSON_PRICE = "price";


        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + BaseColumns._ID
                + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LESSON_TITLE + "varchar(50),"
                + LESSON_AUTHOR + "varchar(50),"
                + LESSON_PRICE + "varchar(50)"
                + ")";
    }

}
