package com.leejimin.android.medialibrary_contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

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
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){

            String permissionArray[] = {Manifest.permission.READ_CONTACTS};
            requestPermissions(permissionArray, REQUEST_CODE);

        } else {
            initDAta();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    initDAta();

                }
                break;
        }

    }




    public void initDAta(){

        datas = getPhoneNumbers();

//        ArrayList<RecyclerData> datas = getContact();
//
//        for(int i=0; i<100; i++){
//            RecyclerData data = new RecyclerData();
//
//            data.name = "홍길동";
//            data.tel = "010-123-4567";
//
//            datas.add(data);
//        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.recycler_item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    public ArrayList<RecyclerData> getPhoneNumbers() {  //주소록 말고 전화번호만 가져오게(성능향상)

        ArrayList<RecyclerData> datas = new ArrayList<>();

        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc"
        );


        if (c != null) {
            while (c.moveToNext()) {  //커서가 null일때 moveToNext하면 널포인트익셉션이므로
                RecyclerData data = new RecyclerData();

                data.name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                data.tel = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                datas.add(data);

                // HashSet 데이터 형태로 생성되면서 중복 제거됨
                HashSet hs = new HashSet(datas);

                // ArrayList 형태로 다시 생성
                ArrayList<RecyclerData> datas2 = new ArrayList<>(hs);
                datas = datas2;

            }
        }
        c.close();

        return  datas;
    }



//    public ArrayList<RecyclerData> getContact(){
//        ArrayList<RecyclerData> datas = new ArrayList<>();
//
//        String projections[] = {
//
//                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.Contacts._ID
//        };


        /*          조건절
        String selection = MediaStore.Audio.Media.TITLE + " ! = '야생화'";  //제목이 야생화가 아닌것
        getContentResolver().query(주소, 검색해올컬럼명들, 조건절, 조건절에매핑되는값, 정렬);

        - url        : content    //스키마 형태로 정해져 있는 곳의 데이터를 가져온다.
        - projection : 가져올 컬럼 이름들의 배열, null을 입력하면 모든 컬럼을 가져온다
        - selection  : 조건걸 (where)에 해당하는 내용
        - selectionArgs : 조건절이 prepared statement 형태일 때 ? 에 매핑되는 값의 배열
        - sort order :정렬 조건
        */

                                                         //  ┌>contents provider
        // Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projections, null, null, null );

//        if(cursor != null){
//
//            while(cursor.moveToNext()){  //주소록의 커서가 도는 동안
//                RecyclerData data = new RecyclerData();

//                // 아이디 컬럼인덱스를 가져온 다음,                             ┌ 컬럼이름
//                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                  // 실제 아이디값을 가져온다
//                String contactId = cursor.getString(idIndex);
//
//                          내부에서 커서 하나 더돌려준다(현재 내 아이디에 해당하는 전화번호 꺼내옴)
//                        Cursor phoneCursor = getContentResolver().query(
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,  // 실제 문자열 쿼리식으로 써준다
                                                // "폰의 아이디 = 주소록의 아이디"인것만 가져오게
//                        null,
//                        null );
//
//
//                if(phoneCursor.moveToNext()){  //moveToFirst(첫번째 커서로 보내겠다) -하나만 가져오겠다
//                    //전화번호를 하나만 가져와서 세팅          컬럼 인덱스(안에 전화번호의 컬럼이름을 가져옴)
//                    String tel = phoneCursor.getString( phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    data.tel = tel;
//                }
//                phoneCursor.close();
//
//                //이름을 세팅                              컬럼 인덱스(안에 이름의 컬럼이름을 가져옴)
//                data.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                datas.add(data);
//            }
//
//        }
//
//        cursor.close();
//        return datas;
//
//
//    }



}
