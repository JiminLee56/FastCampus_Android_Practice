package com.leejimin.android.viewbasic_customviewwithpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    CustomView cv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new CustomView(this));

        cv = new CustomView(this);

        FrameLayout ground = (FrameLayout)findViewById(R.id.ground);
        ground.addView(cv);

        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //지우는 함수
                cv.reset();
            }
        });



    }
}



class CustomView extends View {

    Paint paint = new Paint();
    Path path = new Path();

    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE); // 선
        paint.setStrokeWidth(10f);

    }

    //path를 지우는 함수
    public void reset(){
        path = new Path();
        invalidate();
        //패스를 새로 만들고 화면 갱신
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 안드로이드는 캔버스 재사용x 다시그린다
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:  //터치하면 패스를 그릴 시작좌표를 찍어줌
                path.moveTo(x,y); //패스를 이동한다, x->y로
                break;
            case MotionEvent.ACTION_MOVE: //터치해서 드래그하면
                path.lineTo(x,y);  //드래그 이동 경로상에 라인을 그려준다
                break;
        }
        invalidate();
        return true;
    }


}