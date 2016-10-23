package com.leejimin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class BasicGridActivity extends AppCompatActivity {


    String datas[] = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};

    //데이터를 담을 객체
    ArrayAdapter<String> adapter;

    //데이터를 담은 adapter를 받는 리스트뷰
    GridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_grid);

        //                                1.컨텍스트 2.아이템레이아웃                 3.데이터
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);


        gridView = (GridView) findViewById(R.id.gridView);
        //선택모드 활성화 = ListView.XXX_MODE 형태로 상수로 저의되어있음


        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);






    }
}
