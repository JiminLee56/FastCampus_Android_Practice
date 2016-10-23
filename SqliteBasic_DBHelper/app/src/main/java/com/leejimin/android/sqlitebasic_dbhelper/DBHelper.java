package com.leejimin.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jasmine on 2016-10-12.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 3;



    @Override
    public void onCreate(SQLiteDatabase db) {

        //assets에 있는 파일을 복사해서 디스크로 옮긴다

        String query = "delete from bbs4";
        db.execSQL(query);

        for (int i = 0; i < 100; i++) {
            query = "insert into bbs4(title) values('everything "+i+"')";
            db.execSQL(query);
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //변경사항이 있으면 디스크에 있는 db에 덮어쓰기 한다
        String query = "delete from bbs4";
        db.execSQL(query);

        for (int i = 0; i < 100; i++) {
            query = "insert into bbs4(title) values('everything "+i+"')";
            db.execSQL(query);
        }



    }




    private static DBHelper dbHerper = null;
    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static SQLiteDatabase openDatabase(Context context){
        if(dbHerper == null)
            dbHerper = new DBHelper(context);

        return dbHerper.getWritableDatabase();
    }




}



