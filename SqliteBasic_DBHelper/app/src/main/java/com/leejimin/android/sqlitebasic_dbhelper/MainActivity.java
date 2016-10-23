package com.leejimin.android.sqlitebasic_dbhelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLiteDatabase db = DBHelper.openDatabase(this);

        Log.i("Database", "path="+db);



        Cursor cursor = db.rawQuery("select title from bbs4", null);

        while (cursor.moveToNext()){
            Log.i("Data", "title="+cursor.getString(0));

        }
        cursor.close();

        db.close();


    }

}
