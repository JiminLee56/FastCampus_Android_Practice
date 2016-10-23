package com.leejimin.android.customview_pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int GROUND_LIMIT = 10;
    private int groundUnit = 0;
    private int unit = 0;

    private int player_x = 0; //플레이어 좌표
    private int player_y = 0;


    FrameLayout ground;
    CustomView cv;

    Button btnUp;
    Button btnDown;
    Button btnLeft;
    Button btnRight;

    //int map[][] = new int[GROUND_LIMIT][GROUND_LIMIT];
    int map[][] = {

            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        groundUnit = dp.widthPixels;
        unit = groundUnit / GROUND_LIMIT;

        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);

        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);


        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);


    }


//    public void mapSetting(){
//    //장애물 세팅
//        map[5][3] = 1;
//
//
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                //player_y = player_y - unit;
                player_y = player_y + checkCollision("y", -1);
                break;
            case R.id.btnDown:
                player_y = player_y + checkCollision("y", 1);
                break;
            case R.id.btnLeft:
                player_x = player_x + checkCollision("x", -1);
                break;
            case R.id.btnRight:
                player_x = player_x + checkCollision("x", 1);
                break;
        }
        cv.invalidate();
    }

    //충돌체크
    private int checkCollision(String direction, int nextValue) { //버튼을 눌렀을때 다음값을 넘겨줌


        //그라운드 외곽선 체크
        if (direction.equals("y")) { // y축
            //y축에서 다음 이동하는 곳의 좌표가
            //0보다 작거나, groundlimit 즉 canvas보다 크면 0을 리턴해서 이용하지 않게 한다
            if ((player_y + nextValue) < 0 || (player_y + nextValue) >= GROUND_LIMIT)
                return 0;

        } else { //x축
            if ((player_x + nextValue) < 0 || (player_x + nextValue) >= GROUND_LIMIT)
                return 0;
        }


        //장애물 체크
        if (direction.equals("y")) {  // y축

            //내가 진행할 y축 방향의 다음칸
            int temp_y = player_y + nextValue;

            //내가 진행할 y축 방향의 다음칸에 장애물이 있을때
            if (map[temp_y][player_x] == 1) {

                //다음다음칸에 장애물이 있거나, 범위를 넘어서면 0
                if (temp_y + nextValue < 0
                        || temp_y + nextValue >= GROUND_LIMIT
                        || map[temp_y + nextValue][player_x] != 0) {
                    return 0;
                    //다음다음칸이 빈칸이면 다음칸의 장애물을 다음다음칸으로 이동시킨다(pushpush)
                } else {
                    map[temp_y][player_x] = 0; //다음칸은 0으로
                    map[temp_y + nextValue][player_x] = 1; //다음다음칸은 1로
                }
            }

        } else { //x축
            int temp_x = player_x + nextValue;
            if (map[player_y][temp_x] == 1) {
                if (temp_x + nextValue < 0
                        || temp_x + nextValue >= GROUND_LIMIT
                        || map[player_y][temp_x + nextValue] != 0) {
                    return 0;
                } else {
                    map[player_y][temp_x] = 0;
                    map[player_y][temp_x + nextValue] = 1;

                }

            }
        }

        return nextValue;

    }


    class CustomView extends View {

        Paint paint = new Paint(); //그림을 그리려면 도구가있어야한다 - 물감


        public CustomView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 운동장 배경 그리기
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);


            //map에 세팅된 장애물 그리기
            paint.setColor(Color.BLACK);

            for (int i = 0; i < GROUND_LIMIT; i++) {
                for (int j = 0; j < GROUND_LIMIT; j++) {
                    if (map[i][j] == 1) {
                        canvas.drawRect(j * unit, i * unit, j * unit + unit, i * unit + unit, paint);
                    }
                }
            }


            // 플레이어 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(player_x * unit, player_y * unit, player_x * unit + unit, player_y * unit + unit, paint);

        }

    }


}


