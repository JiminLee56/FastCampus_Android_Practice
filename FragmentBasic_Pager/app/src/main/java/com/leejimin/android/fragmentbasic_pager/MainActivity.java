package com.leejimin.android.fragmentbasic_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {

        BlankFragment bf = null;
        GalleryFragment gf = null;

        //프래그먼트 아답터를 생성하기 위해서는 프래그먼트 매니저를 생성자에 넘겨주고
        //부모아답터가 초기화하야여한다

        public PagerAdapter(FragmentManager manager) {

            super(manager);

            bf = new BlankFragment();
            gf = new GalleryFragment();


        }


        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:    //페이저에 프래그먼트를 인덱스(position)로 구분해서 띄워준다
                    fragment = bf;
                    break;
                case 1:
                    fragment = gf;
                    break;

            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 2;  //blank, gallery 2개
        }
    }


}
