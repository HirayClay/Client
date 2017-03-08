package com.jiqu.data.persistence.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jiqu.data.BuildConfig;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {


    public OrmLiteHelper(Context context){
        super(context, BuildConfig.DB_NAME,null,BuildConfig.DB_VERSION);
    }
    public OrmLiteHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
