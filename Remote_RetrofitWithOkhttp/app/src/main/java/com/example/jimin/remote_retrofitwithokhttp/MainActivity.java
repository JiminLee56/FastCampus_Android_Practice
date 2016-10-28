package com.example.jimin.remote_retrofitwithokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callHttp();
    }


    public void callHttp(){

        String key = "68646b4c426a616535315673596963";
        String serviceName = "CardSubwayStatisticsService";
        int begin = 1;
        int end = 5;

        Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()){
                    RemoteData data = response.body();


                    for(RemoteData.CardSubwayStatisticsService.Row row : data.getCardSubwayStatisticsService().getRow()){

                        Log.w("Okhttp","sub station name"+row.getSUB_STA_NM());
                    }

                }else{
                    Log.e("ERROR", response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {

            }
        });
    }


}
