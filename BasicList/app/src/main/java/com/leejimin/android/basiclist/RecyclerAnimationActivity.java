package com.leejimin.android.basiclist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAnimationActivity extends AppCompatActivity {


    public static ArrayList<RecyclerData> datas = null;

    //1.리사이클러뷰를 현재 액티비티의 메인 레이아웃에 만든다
    //2.리사이클러뷰에 들어갈 아이템 레이아웃을 만든다
    //들어갈 데이터는 정의되어 있고
    //3.Adapter를 만든다
    //4.리사이클러뷰에 아답터를 세팅한다
    //5.리사이클러뷰에 레이아웃 매니저를 지정한다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animation);

        for(int i=0; i<100; i++){
            RecyclerData data = new RecyclerData();

            data.title = i + ". rolling in the deep";
            data.name = "adele";
            data.image = R.mipmap.adele;

            datas.add(data);


        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAnimationAdapter adapter = new RecyclerAnimationAdapter(datas, R.layout.recycler_item2, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        //RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);
        //RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager);



    }
}
