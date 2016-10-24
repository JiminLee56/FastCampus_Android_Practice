package com.example.jimin.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNaver();
            }
        });

    }

    private void getNaver() {
        new AsyncTask<Void, Void, String>(){

            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("댜운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }


            @Override
            protected String doInBackground(Void... voids) {
                String result ="";
                try {
                    result = Remote.getData("http://openapi.seoul.go.kr:8088/6262736a556a696d3130386858675376/json/SearchFirstAndLastTrainInfobyLineService/1/5/1/1/1/");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return result;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                StringBuffer sb = new StringBuffer();

                try {

                    //전체 문자열을 JSON오브젝트로 변환
                    JSONObject json = new JSONObject(s);
                    //특정 키를 가진 단일값을 꺼낸다
                    JSONObject topObject = json.getJSONObject("SearchFirstAndLastTrainInfobyLineService");
                    // 특정 키를 가진 배열형태의 값을 꺼낸다
                    JSONArray rows = topObject.getJSONArray("row");

                    int rows_count = rows.length();
                    for (int i = 0; i < rows_count; i++) {
                        //배열을 반복문을 돌면서 배열의 index로 값을 꺼낸다
                        JSONObject result = (JSONObject) rows.get(i);
                        //최종적으로 각열의 컬럼의 키 이름에 해당되는 값을 꺼낸다
                        String stationName = result.getString("STATION_NM");
                        sb.append(stationName + "\n");

                    }
                }catch (Exception e){

                    e.printStackTrace();
                }


                tv.setText(sb.toString());
                progress.dismiss();

            }
        }.execute(); //doInBackground()호출



    }


}
