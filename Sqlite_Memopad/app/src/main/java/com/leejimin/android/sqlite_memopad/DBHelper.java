package com.leejimin.android.sqlite_memopad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leejimin.android.sqlite_memopad.com.leejimin.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

/**
 * Created by Jasmine on 2016-10-13.
 */
public class DBHelper extends SQLiteOpenHelper {


    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 2;


    //여기 예상되는 쿼리 먼저 만든다

    // C-insert R-select 1개 R-select전체 U-update D-delete 쿼리를 만들어준다
    public void dbInsert(Memo memo) { //메모 클래스 단위로 메모를 받는다


        //db를 연결하고
        SQLiteDatabase db = getWritableDatabase();

        //쿼리를 생성하고
        String query = "insert into memo(contents, ndate)"
                + " values('" + memo.contents + "','" + memo.ndate + "')";

        db.execSQL(query);

        //db를 사용후 닫는다
        db.close();
    }

    public Memo dbSelectOne(int no) { //select 는 table의 key를 기준으로 값을 받는다

        Memo memo = new Memo(); //리턴타입에 맞게 객체를 생성해주고

        //TODO처리

        return memo; //위에서 정의된 리턴변수를 넘겨준다
    }

    public ArrayList<Memo> dbSelectAll() {
        ArrayList<Memo> datas = new ArrayList<>();
        Cursor cursor = null;

        //1. 디비를 open 읽기모드로
        SQLiteDatabase db = getReadableDatabase();

        //2. select 쿼리 작성
        String query = "select * from memo";
        //3. 쿼리를 실행해서 커서에 담고
        cursor = db.rawQuery(query, null);

        //4. 커서에 담긴 데이터를 while문을 돌면서 꺼내고
        while (cursor.moveToNext()) {
            //5.1 한줄씩 cursor에서 꺼내와서 담아준다
            Memo data = new Memo();

            int idx = cursor.getColumnIndex("no");
            data.no = cursor.getInt(idx);

            idx = cursor.getColumnIndex("contents");
            data.contents = cursor.getString(idx);

            idx = cursor.getColumnIndex("ndate");
            data.ndate = cursor.getLong(idx);

            idx = cursor.getColumnIndex("image");
            data.image = cursor.getString(idx);

            //5.2data.add(메모데이터)
            datas.add(data);

        }
        cursor.close();
        db.close();
        return datas;

    }


    public void dbUpdatd(Memo memo) {


    }

    public void dbDelete(Memo memo) {
        //꺼낸후
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        //db version 1 - 최초등록 2016-10-13 11:30             (순서)primary key다음에 autoincrement써야한다
//        String scheme_01 = "create table memo(no integer primary key autoincrement not null" +
//                " ,contents text not null" +
//                " ,ndate integer not null)";  //테이블생성
//
//        db.execSQL(scheme_01);

        String scheme_02 = "create table memo(no integer primary key autoincrement not null" +
                " ,contents text not null" +
                " ,image text" +         //image 컬럼 추가
                " ,ndate integer not null)";  //테이블생성

        db.execSQL(scheme_02);

        for(Memo memo:backupDatas){
            String query = "insert into memo(contents, ndate)"
                    + " values('" + memo.contents + "','" + memo.ndate +"','" + memo.image+ "')";

            db.execSQL(query);

        }


    }



    ArrayList<Memo> backupDatas = new ArrayList<>();


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                                            // 이전 데이터버전 //현재버전(업그레이드되는)
        //이전 데이터베이스 버전에 따라 처리방식이 달라진다
        if(oldVersion == 1){

            //이전 데이터 가져오기
            String query = "select no,contents,ndate from memo order by no";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){

                Memo data = new Memo();
                data.no = cursor.getInt(0);
                data.contents = cursor.getString(1);
                data.ndate = cursor.getLong(2);

                backupDatas.add(data);

            }

        }

//        else if(oldVersion == 2){
//            // 컬럼이 4개
//        }



        //db version 2 - upgrade 2016-10-13 14:08
        String scheme_01_drop = "drop table memo";
        db.execSQL(scheme_01_drop);

        onCreate(db);



    }


    //현재 시간 가져오기 (숫자로 넣어놓고 꺼낼때 스트링으로 처리해줄것)
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

//
//    // 데이터베이스를 정의
//    public static SQLiteDatabase openDatabase(Context context, String dbFileName) {
//        return SQLiteDatabase.openDatabase(getFullpath(context, dbFileName), null, 1); // 0: 쓰기가능 1: read only
//    }
//    // 파일이름을 입력하면 내장 디렉토리에 있는 파일의 전체경로를 리턴해준다
//    public static String getFullpath(Context context,  String fileName){
//        // internal 디렉토리중 files 디렉토리의 경로를 가져온다
//        return context.getFilesDir().getAbsolutePath() + File.separator + fileName;
//    }


}
