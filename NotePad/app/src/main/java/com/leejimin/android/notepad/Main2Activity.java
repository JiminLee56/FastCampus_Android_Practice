package com.leejimin.android.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    public static ArrayList<Data> datas = null;

    RelativeLayout contentMain;
    RelativeLayout contentWrite;
    FloatingActionButton fab;
    boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentMain = (RelativeLayout)findViewById(R.id.contentMain);
        contentWrite = (RelativeLayout)findViewById(R.id.contentWrite);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if(clicked) {
                    contentMain.setVisibility(View.GONE);
                    contentWrite.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.VISIBLE);
                    clicked = false;
                }else{
                    contentMain.setVisibility(View.VISIBLE);
                    contentWrite.setVisibility(View.GONE);
                    fab.setVisibility(View.VISIBLE);
                    clicked = true;

                }
            }
        });


       datas = new ArrayList<>();
        for(int i=0; i<100; i++) {
            Data data = new Data();
            data.notes = "note" + i;
            datas.add(data);
        }


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.content_main2, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }


}
