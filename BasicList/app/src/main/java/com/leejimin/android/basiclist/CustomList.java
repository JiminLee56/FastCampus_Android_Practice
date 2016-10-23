package com.leejimin.android.basiclist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView)findViewById(R.id.listView); //리스트뷰를 찾아온다

        ArrayList<String> datas = new ArrayList<>();  //들어갈 데이터를 만든다
        for(int i=0; i<30; i++){
            datas.add("data"+i);
        }

        adapter = new CustomAdapter(this, datas);
        listView.setAdapter(adapter);
        //커스텀 어답터를 생성하고, 리스트뷰에 달아준다
        //(어답터는 리스트뷰에 들어갈 아이템레이아웃에 데이터를 넣고 리스트뷰에 넣어준다.)

    }
}


//리스트뷰를 커스텀하는게 아니고 아답터를 커스텀 하는 것이다. 기본 아답터를 상속받아야함.
class CustomAdapter extends BaseAdapter{

    Context context;         //컨텍스트
    ArrayList datas;         //데이터 배열
    LayoutInflater inflater; //xml파일을 인스턴스화 해서 메모리에 올려준다


    //생성자
    public CustomAdapter(Context context, ArrayList datas) {
        this.context = context;
        this.datas = datas;

        //시스템에서 xml을 객체화 시켜주는 인플레이터를 가져온다
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {   //자식 뷰들의 개수를 리턴해준다
        return datas.size();

    }

    @Override
    public Object getItem(int position) {  //자식 뷰를 리턴해준다
                              //자식 뷰의 순서


        return datas.get(position);

    }

    @Override
    public long getItemId(int position) { //자식 뷰의 id값을 리턴해준다

        return position;
    }

    @Override
                                        //  ┌ 호출하는 시점의 자식뷰
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("GETVIEW", "-----------------position"+position);
        Log.i("VIEWGROUP", ">>>>>>>>>position"+parent.getId());


        if(convertView == null)  //없으면 만들어준다
        convertView = inflater.inflate(R.layout.activity_custom_list_item, null);

        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(datas.get(position).toString());


        return convertView;
    }
}





