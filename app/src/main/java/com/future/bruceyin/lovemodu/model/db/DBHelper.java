package com.future.bruceyin.lovemodu.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.future.bruceyin.lovemodu.model.dao.ContactTable;
import com.future.bruceyin.lovemodu.model.dao.InviteTable;

/**
 * Created by admin on 2017/5/11.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建联系人的表
        db.execSQL(ContactTable.CREATE_TAB);
        //创建邀请信息的表
        db.execSQL(InviteTable.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
