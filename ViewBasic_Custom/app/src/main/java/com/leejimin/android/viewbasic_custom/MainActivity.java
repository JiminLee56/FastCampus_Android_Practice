package com.leejimin.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new CustomView(this));
    }
}

//뷰 객체를 만들어 setContentView
class CustomView extends View{

    Paint paint = new Paint();
    int x =-1;
    int y =-1;
    int rad =100;

    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {  //canvas - 붓
        //super.onDraw(canvas);
       // Paint paint = new Paint(); //그림을 그리려면 도구가있어야한다 - 물감

                     // left,top,right,bottom
        //canvas.drawRect(0,0,300,300,paint);
                        // x좌표,y좌표,반지름
        if(x >=0) //x가 0보다 작으면 그리지 않게(최초호출시 안그려짐)
            canvas.drawCircle(x,y,rad,paint);

    }


    @Override //내가 터치한 곳의 좌표 가져올 수 있다, 터치한 곳에 원 그리기
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_UP: //터치가 떨어졌을때
                x = (int)event.getX(); //action_up일때의 x,y좌표를 가져와
                y = (int)event.getY();
                invalidate(); //갱신사항이 있을때(어떤 액션이있을때) 화면을 다시 그려주는 함수
                // -> action_up 후 onDraw 호출됨(터치한다고 그냥 onDraw호출되지 x)
                break;

        }
        return true;
    }



}