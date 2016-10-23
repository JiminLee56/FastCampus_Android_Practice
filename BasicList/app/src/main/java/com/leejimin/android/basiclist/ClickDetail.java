package com.leejimin.android.basiclist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ClickDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_detail);

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");


        if(RecyclerAnimationActivity.datas != null) {
            RecyclerData data = RecyclerAnimationActivity.datas.get(position);

            ImageView iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(data.image);
            TextView tv1 = (TextView) findViewById(R.id.textView1);
            tv1.setText(data.title);
            TextView tv2 = (TextView) findViewById(R.id.textView2);
            tv2.setText(data.name);
        }

    }
}
