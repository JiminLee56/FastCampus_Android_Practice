package com.leejimin.android.basiclist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicList2Activity extends AppCompatActivity {

    ArrayList<Map<String, String>> datas;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list2);

        listView = (ListView)findViewById(R.id.listView);  //리스트뷰 찾아옴

        setDatas(); //리스트 뷰에 데이타 넣음


    }


    //어레이리스트에 맵 데이터를 넣는 함수를 하나 만든다 (심플 어답터로 넣는다)
    private void setDatas(){

        datas = new ArrayList<>();

        char asc = 65;
        //맵 데이터를 만들어 어레이리스트에 담는다
        for(int i=0; i<26; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("number", i+"");   //key가 number일때는 값 0,1,2,3~
            map.put("char", (char)(asc+i)+""); //key가 char일때는  값 A,B,C,,,, 반복문을 돌면서A-Z까지 담음
            datas.add(map); //어레이리스트에 담는다
        }


        //심플 어답터 선언하고, 리스트뷰에 어답터를 달아준다
        SimpleAdapter adapter = new SimpleAdapter(
            this, //1.컨텍스트
            datas, //2.데이터 (맵이 담긴 어레이리스트)
            R.layout.activity_basic_list2_item,        //커스텀 아이템레이아웃. //3.레이아웃
            new String[]{"number", "char"},            //datas에 들어가있는 맵의 kew값들
            new int[]{R.id.text1, R.id.text2}          //데이터를 꽂아줄 커스텀레이아웃의_view_아이디
                                                       // (키umber값을 text1에, 키char의값을 text2에)
        );
        listView.setAdapter(adapter);

    }




}
