package com.example.jimin.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    int deviceWidth = 0;
    int deviceHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        CustomSurfaceView surfaceView = new CustomSurfaceView(this);
        setContentView(surfaceView);


    }


    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback { ///
        //서피스뷰는 콜백함수 같이 구현해줘야한다. 변경사항있을때 맞춰서 그려줌
        private SurfaceThread thread;

        public CustomSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder()); //홀더그림그려놓으면 통째로 복사해 뷰에 보여줌(빠름)

        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }



        //뷰에 서피스가 보여지고 있다 (생성됐다)
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            thread.running = true;
            thread.start();
        }


        //뷰에 변경사항이 생겼다(사이즈 변경)
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }


        //화면에서 뷰가 보이지 않는 시점(화면이 생명주기가 끝났다)
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

            boolean retry = true;
            thread.running = false;
            while (retry) {
                try {
                    thread.join();
                    retry = false;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    } //CustomSurfaceView




    public class SurfaceThread extends Thread { ///
        private SurfaceHolder surfaceHolder;
        Paint paint = new Paint();

        public boolean running = true;
        int x = 0;
        int y = 0;

        public SurfaceThread(SurfaceHolder holder) {
            //surface뷰에서 넘겨준 홀더를 가지고 작업을한다
            surfaceHolder = holder;
            paint.setColor(Color.BLUE);

        }

        @Override
        public void run() {
            Canvas canvas = null;
            try {
                //무한 반복하면서 그림을 그려준다
                while (running) {
                    //그림을 그릴 캔버스를 홀더에서 가져오고
                    canvas = surfaceHolder.lockCanvas(null);

                    //그림을 그릴 홀더를 동기화한다
                    synchronized (surfaceHolder) {

                        paint.setColor(Color.WHITE);
                        //canvas.drawRect(0, 0, deviceWidth, deviceHeight, paint);
                        canvas.drawRect(x - 1, y - 1, x + 50, y + 50, paint);

                        paint.setColor(Color.BLUE);
                        canvas.drawRect(x, y, x + 50, y + 50, paint);

                    }
                    x++;
                    y++;
                    if (x > deviceWidth) {
                        x = 0;
                        y = 0;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                if (canvas != null) {
                    //캔버스의 락을 해제하면 실제 뷰에 그림을 그리게 된다
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    } //SurfaceThread


}
