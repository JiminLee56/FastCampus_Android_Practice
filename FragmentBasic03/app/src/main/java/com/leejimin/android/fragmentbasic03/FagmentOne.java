package com.leejimin.android.fragmentbasic03;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FagmentOne extends Fragment {

    ListView listView;

  //  ArrayList<ListData> datas;



    public FagmentOne() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity mainActivity = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_fagment_one, container, false);

        listView = (ListView) view.findViewById(R.id.listView); //리스트뷰를 찾아온다


        CustomAdapter adapter = new CustomAdapter(getContext(), mainActivity.datas);
        //CustomAdapter adapter = new CustomAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.position = position;
                mainActivity.goDetail();


            }
        });


        return view;


    }

}



class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ListData> datas;
    LayoutInflater inflater;



    //생성자
    public CustomAdapter(Context context, ArrayList<ListData> datas) {

        this.context = context;
        this.datas = datas;

        //시스템에서 xml을 객체화 시켜주는 인플레이터를 가져온다
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return datas.size();

    }

    @Override
    public Object getItem(int position) {
        //자식 뷰의 순서


        return datas.get(position);

    }

    @Override
    public long getItemId(int position) { //자식 뷰의 id값을 리턴해준다

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null)  //없으면 만들어준다
            convertView = inflater.inflate(R.layout.fragment_fagment_one_item, null);


        TextView tv = (TextView) convertView.findViewById(R.id.textView);
        tv.setText(datas.get(position).title);

        return convertView;

    }

}