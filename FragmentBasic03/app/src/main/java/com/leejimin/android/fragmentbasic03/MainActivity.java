package com.leejimin.android.fragmentbasic03;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static  ArrayList<ListData> datas = null;
    public static int  position = -1;

    Fragment fragmentOne;
    Fragment fragmentTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //가로뷰인지 세로뷰인지를 결정해서 출력해준다
        setContentView(R.layout.activity_main);

        //데이터
        datas = new ArrayList<>();
        for(int i=0; i<20; i++) {
            ListData data = new ListData();
            data.title = i+ " Item Title";
            data.contents = "디테일";
            datas.add(data);
        }


        fragmentOne = new FagmentOne();

        Bundle args = new Bundle();
        args.putString("key","값");
        fragmentOne.setArguments(args);

        fragmentTwo = new FragmentTwo();

        setOne();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

            Toast.makeText(this,"Landscape",Toast.LENGTH_LONG).show();

        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this,"portrait",Toast.LENGTH_LONG).show();


        }
    }




    public void setOne(){
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, fragmentOne);
        transaction.commit();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.add(R.id.detailfragment, fragmentTwo);
            transaction.commit();
        }


    }

    public void goDetail(){

        //detailfragment.setData();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            transaction.add(R.id.detailfragment, fragmentTwo);
        }else{
            transaction.replace(R.id.fragment, fragmentTwo);

        }

        transaction.commit();

    }

    public void goOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(R.id.fragment, fragmentOne);
        transaction.commit();

    }


}
