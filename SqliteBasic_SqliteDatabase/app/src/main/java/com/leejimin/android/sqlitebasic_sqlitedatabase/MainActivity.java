package com.leejimin.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button openDatabase;
    Button btnInsert;
    Button btnSelect;
    Button btnUpdate;
    Button btnDelete;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



        openDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = SQLiteDatabase.openDatabase(getFullpath("sqlite.db"),null,0 );
            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null){
                    //쿼리를 실행해준다.selecct 문을 제외한 모든 쿼리에 사용

                    db.execSQL("insert into bbs2(no,user_name,title) values(1,'홍길동','글제목')");

                    //쿼리를 실행후 결과값을 커서로 리턴해준다. select문 사용
                    //db.rawQuery("",null);

                }
            }
        });


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null ){
                    Cursor cursor = db.rawQuery("select * from bbs2", null);
                    while (cursor.moveToNext()){
                        int idx = cursor.getColumnIndex("no");
                        String no = cursor.getString(idx);

                        idx = cursor.getColumnIndex("user_name");
                        String name = cursor.getString(idx);

                        idx = cursor.getColumnIndex("title");
                        String title = cursor.getString(idx);


                        String ndate = cursor.getString(idx);

                        String temp = result.getText().toString();

                        result.setText(temp + "\n no=" + no + ", name=" + name + ", title=" + title+", date="+ndate);
                    }
                }
            }
        });


    }








    private void init(){
        //파일이 있으면 덮어쓰지 않는다
        File file = new File(getFullpath("sqlite.db"));
        if(!file.exists())
            assetToDisk("sqlite.db");


        result = (TextView)findViewById(R.id.textView);
        openDatabase = (Button)findViewById(R.id.btnOpen);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

    }




    public String getFullpath(String fileName ){
        return getFilesDir().getAbsolutePath() + File.separator + fileName;   //internal 디렉토리 경로를 써줘야함

    }




    public void assetToDisk(String fileName){

        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try { // 외부에서 작성된 sqlite db파일 사용하기
            //1. asset에 담아둔 파일을 internal 혹은 external공간으로 복사하기 위해 읽어온다
            // 가져오려면 assetmanager로 가져옴
            AssetManager manager = getAssets();
            //assets에 있는 파일 검사
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);

            //2. 저장할 위치에 파일이 없으면 생성한다
            String targetFile = getFullpath(fileName);

            File file = new File(targetFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            //3. outputstream을 생성해서 파일 내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            //버퍼의 사이즈를 지정해주는 코드
            //읽어올 데이터를 담아줄 변수
            int read = -1; //(다읽으면 -1이 리턴)
            //한번에 읽을 버퍼의 크기를 지정 - 1024단위로 읽어옴
            byte buffer[] = new byte[1024];

            //읽어온다                           ┌ 개(count)
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                //써준다
                bos.write(buffer, 0, read);

            }

            bos.flush(); //남아있는 찌꺼기를 다 outputstream으로 밀어내줌


        } catch (Exception e) {
            e.printStackTrace();

        }finally{
            try {
                //작업이 완료되면
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
