package com.leejimin.android.optimization_rander;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Debug.startMethodTracing("trace20161014");


        Thread another01 = new Thread() {
            @Override
            public void run() {
                // 다른 Thread에서 돌아가는 로직
                print1000("another01");
            }
        };
        another01.start(); //  main thread가 아닌 다른 thread를 만들어 로직을 실행한다
        //start 하면 run 도 실행됨


        Thread another02 = new Thread() {
            @Override
            public void run() {
                // 다른 Thread에서 돌아가는 로직
                print1000("another02");
            }
        };
        another02.start();


        print1000("main");


    }


    public void print1000(String tag) {
        for (int i=0; i<1000; i++) {
            Log.i("PerformanceTest", tag + "i = " + i);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}