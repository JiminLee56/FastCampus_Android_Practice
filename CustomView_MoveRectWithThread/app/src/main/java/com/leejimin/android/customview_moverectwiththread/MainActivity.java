package com.leejimin.android.customview_moverectwiththread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    Button btnAnimate;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);  //프레임레이아웃(ground)안에 커스텀 뷰를 add, 사각형을 그린다

        btnAnimate = (Button) findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomThread thread = new CustomThread(cv);
                thread.start(); //run이 실행되며 cv.moveRect 되어 이동함
            }
        });


    }
}


class CustomView extends View {

    Paint paint = new Paint(); //그림을 그리려면 도구가있어야한다 - 물감

    private int x = 0;
    private int y = 0;

    private static final int WIDTH = 100; //사각형의 크기는 고정
    private static final int HEIGHT = 100;


    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.MAGENTA);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRect(0, 0, x, y, paint);
        canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, paint);
                            //  오른쪽아래모서리
    }

    public void moveRect(int offset) {
        x = x + offset;
        y = y + offset;
    }
}




class CustomThread extends Thread {
    CustomView cv;
    private static final int OFFSET = 2; //2픽셀씩

    public CustomThread(CustomView cv) {
        this.cv = cv;
    }

    @Override
    public void run() {
        int limit = 0;

        while (limit < 1000) {
            //cv에 그려지는 사각형의 좌표값을 조작한다
            cv.moveRect(OFFSET); //CustomView에있는 함수 호출 //offset단위(2픽셀씩)로 x, y 좌표를 이동시키겠다
            cv.postInvalidate(); //그림을 이동한 곳에 다시 그린다
            limit++;

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}