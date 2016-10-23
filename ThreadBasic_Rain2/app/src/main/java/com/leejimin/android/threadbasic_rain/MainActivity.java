package com.leejimin.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;

    FrameLayout ground;
    Button btnStart;
    Button btnStop;

    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels; //화면의 가로 픽셀 개수
        deviceHeight = metrics.heightPixels; //화면의 세로 픽셀 개수


        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running = true;
                new MakeDrop(cv).start();


//                //빗방울 Thread 생성 후 동작
//                RainDrop rainDrop = new RainDrop();
//                cv.add(rainDrop);
//                new Thread(rainDrop).start();
//
//                //화면을 그리는 Thread 생성 후 동작
//                CallDraw cd = new CallDraw(cv,10);
//                new Thread(cd).start();

            }
        });


        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

    }


    //자동으로 빗방울을 만들고 화면을 그려주는 Thread 를 동작시키는 Sub Thread
    class MakeDrop extends Thread {
        CustomView cv;

        public MakeDrop(CustomView cv) {
            this.cv = cv;
        }

        public void run() {
            // 화면을 그리는 Thread 생성 후 동작
            CallDraw cd = new CallDraw(cv, 10);
            new Thread(cd).start();

            while (running) {
                // 빗방울 Thread 생성 후 동작
                RainDrop rainDrop = new RainDrop(cv);
//                cv.add(rainDrop);
                new Thread(rainDrop).start();

                // 0.5초에 1번씩 비를 생성한다
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    class CustomView extends View {

        //  ArrayList<RainDrop> rainDrops = new ArrayList<>();

        //동기화로 초기화 >>
        List<RainDrop> rainDrops = Collections.synchronizedList(new ArrayList<RainDrop>());
        //(or) List<RainDrop> rainDrops = new CopyOnWriteArrayList<RainDrop>();


        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.BLUE);
        }


        public void add(RainDrop rd) {
            rainDrops.add(rd);
        }


        public void remove(RainDrop rd) {
            rainDrops.remove(rd);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //컬렉션 clone함수 = Collections.synchronizedList(targetCollection);
            //동기화
            synchronized (rainDrops) {
                for (RainDrop rainDrop : rainDrops) {
                    //하나씩 꺼내서 circle을 그려준다
                    canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.size, paint);
                }
            }
        }
    }


    // 빗방울 1개
    class RainDrop implements Runnable {

        int size_limit; //최대크기
        int speed_limit; //최고속도
        int x;
        int y;
        int size;
        int speed;
        CustomView cv;


        public RainDrop(CustomView cv) {
            this.cv = cv;
            cv.add(this);

            Random random = new Random();
            x = random.nextInt(deviceWidth);
            y = 0; //고정
            size_limit = deviceWidth / 20; //빗방울의 최대크키가 화면사이즈의 1/20보다 작게 만든다
            size = random.nextInt(size_limit);

            speed_limit = deviceHeight / 100; // 한번에 이동가능한 최대거리가 화면
            speed = random.nextInt(speed_limit) + 1;
        }

        @Override
        public void run() {
            while (y <= deviceHeight) { //화면 밖으로 벗어나면 while문 빠져나가 쓰레드 종료되도록
                // 러닝 상태에서만 y값이 증가한다
                if (running) {
                    y = y + speed;
                }
                try {
                    //0.1초에 한번 이동
                    Thread.sleep(10); //y축으로 이동후 그려지는 간격을 조절한다.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            cv.remove(this);
        }
    }


    // 화면을 그려주는 thread
    class CallDraw implements Runnable {


        CustomView cv;
        int interval;

        public CallDraw(CustomView cv, int interval) {
            this.cv = cv;
            this.interval = interval;
        }

        public void setStatus(boolean flag) {

        }


        @Override
        public void run() {
            //interval 에 입력된 값만큼 쉰 후에 화면을 반복해서 그려준다
            while (running) {
                cv.postInvalidate();
                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }


}
