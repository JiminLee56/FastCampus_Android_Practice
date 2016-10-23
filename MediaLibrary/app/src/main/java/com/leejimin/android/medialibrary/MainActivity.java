package com.leejimin.android.medialibrary;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE=100;

    public static ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            initDAta();
        else
            checkPermissions();


    }

    @TargetApi(Build.VERSION_CODES.M)
    private  void checkPermissions(){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            String permissionArray[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);

        } else {
            initDAta();
        }
    }

}  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initDAta();
                }
                break;

        }




    public void initDAta(){

        datas = getMusicinfo();
//
//        for(int i=0; i<100; i++) {
//            RecyclerData data = new RecyclerData();
//
//            data.title = i + ". rolling in the deep";
//            data.artist = "adele";
//            datas.add(data);
//        }

        //리사이클러뷰, 리사이클러카드어댑터(item xml), 리사이클러뷰에 어댑터달기
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas, R.layout.activity_recycler_card_item, this);
        recyclerView.setAdapter(adapter);

        //레이아웃매니저(리사이클러뷰의 레이아웃매니저), 리사이클러뷰에 매니저달기
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }





    public ArrayList<RecyclerData> getMusicinfo(){
        ArrayList<RecyclerData> datas = new ArrayList<>();

        String projections[] = {
                MediaStore.Audio.Media._ID,//노래아이디
                MediaStore.Audio.Media.ALBUM_ID, //앨범아이디
                MediaStore.Audio.Media.TITLE, //제목
                MediaStore.Audio.Media.ARTIST //가수

        };

       // getContentResolver().query(주소, 검색해올컬럼명들, 조건절, 조건절에매핑되는값, 정렬);
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null, null );

        /*
        -url : content://스키마 형태로 정해져 있는 곳의 데이터를 가져온다.
        - projection : 가져올 컬럼 이름들의 배열, null을 입력하면 모든 값을 가져온다
        - selection : 조걵걸 (where)에 해당하는 내용
        - selectionArgs : 조건절이 preparedstatedment 형태일 때 ? 에 매핑되는 값의 배열
        - sort order :정렬 조건

         */



        if(cursor != null){

            while(cursor.moveToNext()){
                RecyclerData data = new RecyclerData();
                //데이터에 가수이름을 입력
                //1. 가수 이름 컬럼의 순서(index를 가져온다)
                int idx = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                //2. 해당index를 가진 컬럼의 실제값을 가져온다
                data.artist = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                data.title = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                data.albumId = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                data.musicId = cursor.getString(idx);

            }

        }

        cursor.close();

        return datas;


    }



}
