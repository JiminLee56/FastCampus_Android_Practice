package com.leejimin.android.basiclist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicList1Activity extends AppCompatActivity {


    //데이터 배열
    String datas[] = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};

    //데이터를 담을 어답터 객체( 데이터를 어레어어답터에 넣어서 리스트뷰에 어답터를 달아준다)
    ArrayAdapter<String> adapter;

    //데이터를 담은 ArrayAdapter를 받는 리스트뷰
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list1);

        //                                1.컨텍스트 2.아이템레이아웃                 3.데이터
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        //어답터에 데이터를 넣는다.                                   ㄴ한줄을 보여주는것

        //리스트뷰의 각 리스트 칸을 아이템이라고 하고 각각은 레이아웃으로 들어간다. 데이터를 각 아이템에 넣는다.

        listView = (ListView)findViewById(R.id.listView);//리스트뷰를 찾아와서 어답터를 달아준다->리스트뷰에 데이터가 들어간다
        listView.setAdapter(adapter);


        // 아이템 레이아웃 종류
        // simple_list_item_2 텍스트뷰 두개로 구성
        // simple_list_item_checked 끝에 체크박스가 생성
        // simple_list_item_single_choice 끝에 라디오버튼 생성
        // simple_list_item_multiple_choice 끝에 체크박스가 생성

        //선택모드 활성화 = ListView.XXX_MODE 형태로 상수로 정의되어있음

    }
}
