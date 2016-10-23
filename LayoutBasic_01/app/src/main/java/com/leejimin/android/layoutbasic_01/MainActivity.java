package com.leejimin.android.layoutbasic_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void open(View v){
        Intent intent = new Intent(MainActivity.this, DynamicGrid.class);
        startActivity(intent);
    }
}
