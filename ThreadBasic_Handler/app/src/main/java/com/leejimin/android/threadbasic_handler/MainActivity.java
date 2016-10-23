package com.leejimin.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnStart;
    Button btnCall;
    Button btnStartHandler;

    SubThread thread;
    LooperHandler handlerThread;
    Handler subHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new SubThread(handler);



        tv = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Thread thread = new SubThread(handler);
                thread.start();
            }
        });



        handlerThread = new LooperHandler(this, "Looper Handler");
       // subHandler = handlerThread.looperHandler; //서브쓰레디의 handler를 등록한다
        //handlerThread.start();

        btnStartHandler = (Button)findViewById(R.id.btnStartHandler);
        btnStartHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.start();
                run();
                handlerThread.hideProgress();

                //handlerThread에 메세지 보내기
                //handlerThread.looperHandler.sendEmptyMessage(LooperHandler.SHOW_PROGRESS);
                //subHandler.sendEmptyMessage(LooperHandler.SHOW_PROGRESS);
                //run();
                //handlerThread.looperHandler.sendEmptyMessage(LooperHandler.HIDE_PROGRESS);

            }
        });




        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.printLog();
            }
        });





    }

    public void run(){
        try{
            int sum=0;
            for(int i=0;i<30;i++){
                sum+=i;
                Thread.sleep(100);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static final int SET_RESULT = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_RESULT:
                    int temp = msg.arg1;
                    tv.setText("Result = " + temp);
                    break;
            }
        }
    };





    class LooperHandler extends HandlerThread {

        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;
        Context context;
        Handler looperHandler;

        ProgressDialog progress;


        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;


        }

        @Override
        protected void onLooperPrepared() {

            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("Message");
            progress.setCancelable(false);

            progress.show();
            Log.i("Looper Handler", "진행 상태 확인");
//            looperHandler = new Handler() {
//
//                            @Override
//                            public void handleMessage(Message msg) {
//                                super.handleMessage(msg);
//                                switch (msg.what) {
//                                    case SHOW_PROGRESS:
//                                        showProgress();
//                                        break;
//                                    case HIDE_PROGRESS:
//                                        hideProgress();
//                                        break;
//                    }
//                }
//            };
        }

        private void showProgress() {
            progress.show();
            Log.i("Looper Handler", "진행 상태 확인");
        }

        public void hideProgress() {
            progress.dismiss();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.i("Looper Handler", "진행 상태 해제하기");
            quit();
        }

    }






    class SubThread extends Thread {  //그냥은 메인쓰레드의 ui접근 못하고  handler통해서

        Handler mainHandler;
        //Handler subHandler;


        public SubThread(Handler mHandler) {
            mainHandler = mHandler;
        }

        public void run() {

            int sum = 0;
            for (int i = 0; i < 10000; i++) {
                sum += i;
            }
            Message msg = new Message();
            msg.what = SET_RESULT;
            msg.arg1 = sum;
            mainHandler.sendMessage(msg);


//            Looper.prepare();
//            Log.i("SubThread", "after prepare()");
//
//            subHandler = new Handler() {
//            };
//
//            Log.i("SubThread", "before loop()");
//            Looper.loop();
//            Log.i("SubThread", "after loop()");


//            int sum =0;
//            for (int i = 0; i < 5000; i++) {
//                sum += i;
//            }
//            Message msg = new Message();
//            msg.what =1;
//            msg.arg1 = sum;
//            mainHandler.sendMessage(msg);
//            //tv.setText("Result :"+sum);

        }


        public void printLog() {
            Log.i("SubThread", "called from the main thread");
        } //보통 쓰레드는 익명객체로 쓰이므로 이런거는 못씀


    }


}
