package com.future.bruceyin.lovemodu.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.future.bruceyin.lovemodu.model.dao.UserAccountTable;

/**
 * Created by Bruce Yin on 2017/5/7.
 */
public class UserAccountDB extends SQLiteOpenHelper {
    public UserAccountDB(Context context) {
        super(context, "account.db", null, 1);
    }

    //数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserAccountTable.CREATE_TAB);
    }

    //数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
