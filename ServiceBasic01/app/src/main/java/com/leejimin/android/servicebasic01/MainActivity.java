package com.leejimin.android.servicebasic01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void startService(View v){
        //1. 서비스 실행
        Intent intent = new Intent(this, MainService.class);

        startService(intent);


    }

    public void stopService(View v){
        //2. 서비스 중지
        Intent intent = new Intent(this, MainService.class);
        stopService(intent);
    }


    public void startIntentService(View v){

        Intent intent = new Intent(this, SubService.class);
        intent.putExtra("phone", inputnumber);
        startService(intent);


    }

   /* public  void stopIntentService(View v){  //종료시킬 필요 x

        Intent intent = new Intent(this, SubService.class);
        stopService(intent);
    }*/







}
