package com.leejimin.android.fragmentbasic03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    TextView title;
    TextView contents;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        MainActivity activity = (MainActivity) getActivity();
        ListData data = activity.datas.get(activity.position);

        title.setText(data.title);
        contents.setText(data.contents);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);


        title = (TextView)view.findViewById(R.id.title);
        contents = (TextView)view.findViewById(R.id.contents);


        return view;
    }


//    public  void setData(){
//
//    }




}
