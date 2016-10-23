package com.leejimin.android.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jasmine on 2016-09-28.
 */
public class CustomGridAdapter extends BaseAdapter {

    //기본 속성값 설정
    Context context; //1.컨텍스트
    ArrayList<GridItem> datas; //2.데이터
    int gridItem;    //3. 레이아웃 아이템
    LayoutInflater inflater; //4.인플레이터

    //생성자
    public CustomGridAdapter(Context context, ArrayList datas, int gridItem) {
        this.context = context;
        this.datas = datas;
        this.gridItem = gridItem;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null)
            //1.뷰를생성
            convertView = inflater.inflate(gridItem, null);

            //2.뷰에있는 위젯아이디 가져와서 세팅
            TextView tv1 = (TextView) convertView.findViewById(R.id.text1);
            TextView tv2 = (TextView) convertView.findViewById(R.id.text2);

            //3.값을 세팅
            tv1.setText(datas.get(position).title);
            tv2.setText(datas.get(position).num + "");

            return convertView;
        }

}


