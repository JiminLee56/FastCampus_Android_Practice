package com.example.jimin.remote_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


/*

    gradle
   com.squareup.retrofit2:retrofit:(insert latest version)


    key = 68646b4c426a616535315673596963

    http://openapi.seoul.go.kr:8088/68646b4c426a616535315673596963/json/CardSubwayStatisticsService/1/5/201306/
                                                                ㄴ서비스명
*/


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "68646b4c426a616535315673596963";
        String serviceName = "CardSubwayStatisticsService";
        int begin = 1;
        int end = 5;

        String url = "http://openapi.seoul.go.kr:8088/" + key + "/json/" + serviceName + "/" + begin + "/" + end + "/201306";

        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.seoul.go.kr:8088")
                .addConverterFactory(GsonConverterFactory.create()).build();
        //2. Retrofit client에서 사용할 interface 지정
        ISeoulOpenDAta service = client.create(ISeoulOpenDAta.class);
        //3. Interface(서비스)를 통해서 데이터를 호출한다
        Call<RemoteData> remoteData = service.getData(key, serviceName, begin, end);
        //4. 비동기 데이터를 받기 위한 리스너 세팅
        remoteData.enqueue(new Callback<RemoteData>() {

            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if (response.isSuccessful()) {
                    RemoteData data = response.body();
                    for (RemoteData.CardSubwayStatisticsService.Row row : data.getCardSubwayStatisticsService().getRow()) {
                        Log.i("Remote Data Result", "line_num" + row.getLINE_NUM());
                    }

                } else {
                    Log.e("RemoteDAta", response.message());
                }

            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}

interface ISeoulOpenDAta {

    @GET("/{key}/json/{serviceName}/{begin}/{end}/201306")
    Call<RemoteData> getData(@Path("key") String key, @Path("serviceName") String serviceName,
                             @Path("begin") int begin, @Path("end") int end);

}


