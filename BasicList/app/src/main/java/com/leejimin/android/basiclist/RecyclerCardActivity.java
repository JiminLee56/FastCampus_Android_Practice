package com.leejimin.android.basiclist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {

    public static ArrayList<RecyclerData> datas = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        datas = new ArrayList<>();

        for(int i=0; i<100; i++) {
            RecyclerData data = new RecyclerData();

            data.title = i + ". rolling in the deep";
            data.name = "adele";
            data.image = R.mipmap.adele;

            datas.add(data);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.carditem);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas, R.layout.activity_recycler_card_item, this);
        recyclerView.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // cardView = (RecyclerView)findViewById(R.id.carditem);
        //RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas, R.layout.activity_recycler_card_item, this);
       // cardView.setAdapter(adapter);
        //cardView.setLayoutManager(new LinearLayoutManager(this));


    }




}
