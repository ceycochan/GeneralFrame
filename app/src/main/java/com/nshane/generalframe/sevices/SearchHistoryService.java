package com.nshane.generalframe.sevices;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nshane.generalframe.db.DBMetaData;
import com.nshane.generalframe.db.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 2018-1-27.
 */

public class SearchHistoryService {
    private DBOpenHelper mHelper;

    /**
     * getWritableDatabase取得的实例不是仅仅具有写的功能，而是同时具有读和写的功能同样的
     * <p>
     * getReadableDatabase取得的实例也是具对数据库进行读和写的功能。
     * <p>
     * 两者的区别在于
     * getWritableDatabase取得的实例是以读写的方式打开数据库，如果打开的数据库磁盘满了，此时只能读不能写，此时调用了getWritableDatabase的实例，那么将会发生错误（异常）
     * getReadableDatabase取得的实例是先调用getWritableDatabase以读写的方式打开数据库，如果数据库的磁盘满了，此时返回打开失败，继而用getReadableDatabase的实例以只读的方式去打开数据库
     *
     * @param context
     */

    public SearchHistoryService(Context context) {
        mHelper = DBOpenHelper.getInstance(context);


    }

    /**
     * DESC / Descending
     * ASC  / Ascending
     *
     * @return
     */

    /**
     * public Cursor query(String table, String[] columns, String selection,
     * String[] selectionArgs, String groupBy, String having,
     * String orderBy) {
     *
     *
     *
     * table：指定查询的表名，对应：from table_name
     * columns：指定查询的列名，对应select column1, column2
     * selection：指定where 的约束条件，对应：where column = value
     * selectionArgs：为where 中的占位符提供具体的值
     * groupBy：指定需要group by 的列，对应：group by column
     * having：value 对group by 后的结果进一步约束，对应：having column = value
     * orderBy：指定查询结果的排序方式，对应：order by column1, column2
     * <p>
     * }
     */
    public List<String> queryAllHistory() {
        List<String> historyList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DBMetaData.SearchHistoryMetaData.TABLE_NAME,
                null, null, null, null, null, "id desc");

        while (cursor.moveToNext()) {
            String item = cursor.getString(cursor.getColumnIndex(DBMetaData.SearchHistoryMetaData.SERACH_HISTROY));
            historyList.add(item);
        }

        cursor.close();
        db.close();
        return historyList;

    }


//    public boolean isHistory(String history) {
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor  = db.query(DBMetaData.SearchHistoryMetaData.TABLE_NAME,null,
//        ,)
//    }

}
