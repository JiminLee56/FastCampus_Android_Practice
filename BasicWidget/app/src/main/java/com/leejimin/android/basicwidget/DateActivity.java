package com.leejimin.android.basicwidget;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class DateActivity extends AppCompatActivity {

    Chronometer timer;
    Button start;
    Button stop;
    Button pause;
    //현재 일시정지 버튼이 눌렸는지 체크
    boolean Pause_Flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        timer = (Chronometer) findViewById(R.id.chronometer);
        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        pause = (Button) findViewById(R.id.btnPause);

        start.setOnClickListener(onClickListener);
        stop.setOnClickListener(onClickListener);
        pause.setOnClickListener(onClickListener);


    }


    long pauseTime =0;

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()){
                    case R.id.btnStart:
                        timer.setBase(SystemClock.elapsedRealtime());
                        timer.start();
                        break;

                    case R.id.btnStop:
                        timer.stop();
                        break;

                    case R.id.btnPause:
                        if(Pause_Flag){
                            //start 1초 > pause 5초 > restart 8초

                            //리스타트하는 현재시간 가져오기
                            long now = SystemClock.elapsedRealtime();
                            //현재시간에서 일시정지한 시간을 배면 정지된 시간값을 가져올수있다
                            long gap = now - pauseTime;

                            //정지된 시간만큼 크로노미터의 시작시간을 증가시켜준다.
                            timer.setBase(SystemClock.elapsedRealtime());
                            timer.start();

                            pause.setText("pause");
                            Pause_Flag = false;

                        }else{
                            pauseTime = SystemClock.elapsedRealtime();//멈춘 시간 저장해놓음
                            timer.stop();
                            pause.setText("Restart");
                            Pause_Flag = true;
                        }

                        break;



                }
            }
        };



}
