package com.leejimin.android.materialdesigns_propertyanimation00;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSpin;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpin = (Button) findViewById(R.id.btnSpin);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }


//    boolean SPREADED = false;
//    public void spin(View v){
//
//        if(!SPREADED) {
//            rotate();
////            rotate(btn2, btn2.getWidth() / 2, -(btn2.getHeight() / 2));
////            rotate(btn3, -btn3.getWidth() / 2, btn3.getHeight() / 2);
////            rotate(btn4, btn4.getWidth() / 2, btn4.getHeight() / 2);
////            btnSpin.setText("Combine");
//            SPREADED = true;
//        } else{
//            combine();
//            btnSpin.setText("Spin");
//            SPREADED = false;
//        }
//    }

    int r=0;
    public void rotate(View v){
        r = r + 360;
        ObjectAnimator ani = ObjectAnimator.ofFloat(btn1, "rotation", r);
        ani.setDuration(5000);
        ani.start();

    }




    public void move(View player, int x, int y){
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "translationX", x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "translationY", y);
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(3000);
        aniSet.playTogether(ani1, ani2);
        aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
        aniSet.start();

    }


    }




