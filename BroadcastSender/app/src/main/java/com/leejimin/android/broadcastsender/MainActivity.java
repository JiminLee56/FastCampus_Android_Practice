package com.leejimin.android.broadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST_ACTION = "com.leejimin.android..MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendBroadcast(View v){
        Intent intent = new Intent(BROADCAST_ACTION);
        intent.putExtra("msg", "hello guys~");
        sendBroadcast(intent);
    }

}
