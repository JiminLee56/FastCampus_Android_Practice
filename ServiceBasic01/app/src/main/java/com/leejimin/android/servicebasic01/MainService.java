package com.leejimin.android.servicebasic01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

    private static final String TAG = "MainService";

    public MainService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG, "==================== onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand ===========");


            for (int i = 0; i < 100; i++) {
                try{
                    Thread.sleep(500);
                }catch(Exception e){}

        }

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "==================== onDestroy");

    }




    @Override
    public IBinder onBind(Intent intent) { //bounded 서비스에서 호출될때 사용됨
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
