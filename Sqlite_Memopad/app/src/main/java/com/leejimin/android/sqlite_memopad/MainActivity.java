package com.leejimin.android.sqlite_memopad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leejimin.android.sqlite_memopad.com.leejimin.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Log.i("DBHelper", "version="+dbHelper.getReadableDatabase().getVersion());

//        Memo memo = new Memo();
//        memo.contents = "메모내용";
//        memo.ndate = dbHelper.getTimeStamp();
//        dbHelper.dbInsert(memo);

        ArrayList<Memo> datas = dbHelper.dbSelectAll();
        for(Memo data:datas){
            Log.i("Memp", "no========"+data.no);
            Log.i("Memp", "contents========="+data.contents);
            Log.i("Memp", "ndate========="+data.ndate);
            Log.i("Memp", "image========"+data.image);
        }


    }
}
