 package com.leejimin.android.permission_runtime;

 import android.Manifest;
 import android.annotation.TargetApi;
 import android.content.pm.PackageManager;
 import android.os.Build;
 import android.os.Bundle;
 import android.os.Environment;
 import android.support.annotation.NonNull;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;

 import java.io.File;

 public class MainActivity extends AppCompatActivity {

     private final static int REQUEST_CODE=1;

     //adb 접속 명령어
     //adb -s 디바이스명 shell

     //adb 디바이스목록보기
     //adb devices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            createFile();

        }else{
            checkPermissions();
        }


        //1. 권한 유효성 체크 - 권한을 획득하기 전
        //checkSelfPermission()

        //2. 권한에 대한 부가적인 설명이 필요할 경우 호출됨
        // shouldShowRequestPermissionRationale()

        //3. 권한을 획득하기 위해 호출
        // requestPermissions();

        // 완료후 최종 결과값을 처리하는 callback함수가 자동으로 호출된다.
        // onRequestPermissionsResult();


    }




     @TargetApi(Build.VERSION_CODES.M)
     private  void checkPermissions(){
         if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                 != PackageManager.PERMISSION_GRANTED){

             //쓰기 권한이 없으면 로직 처리
             //중간에 권한 내용에 대한 알림을 처리하는 함수
             //shouldShowRequestPermissionRationale()
             /// /확인창
             String permissionArray[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
             requestPermissions(permissionArray, REQUEST_CODE);



         } else {

             //쓰기 권한이 있으면 파일생성
             createFile();

         }
     }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         switch (requestCode){
             case REQUEST_CODE:
                 if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                     createFile();
                 }
                 break;

         }

     }

     private void createFile(){
        String filePath = Environment.getExternalStorageDirectory().getPath();
        Log.i("Root Path", "="+filePath);

        try {
            File file = new File(filePath + File.separator + "temp.txt");
            if (file.exists()) {
                file.createNewFile();
            }
        }catch (Exception e){
                e.printStackTrace();
            }

    }



}
