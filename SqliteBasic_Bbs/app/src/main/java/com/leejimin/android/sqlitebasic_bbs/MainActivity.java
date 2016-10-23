package com.leejimin.android.sqlitebasic_bbs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    ListFragment lf; // 목록 프래그먼트
    EditFragment ef; // 쓰기 프래그먼트

    ViewPager pager;


    //앱 초기화(oncreate에서 호출)
    private void init() {

        // DB 파일이 internal files 디렉토리에 있는지 여부 검사
        File file = new File(DataUtil.getFullpath(this,DataUtil.DB_NAME));
        // 파일이 없을때만 db 파일 생성
        if(!file.exists())
            DataUtil.assetToDisk(this,DataUtil.DB_NAME);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //앱 초기화 - DB 생성 등...
        init();

        setContentView(R.layout.activity_main);

        lf = new ListFragment();
        ef = new EditFragment();

        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

    }


//    public void write() {
//
//
//        // Toast.makeText(this, "Write 페이지로 이동", Toast.LENGTH_SHORT).show();
//    }
//
//    public void edit() {
//        Toast.makeText(this, "edit 페이지로 이동", Toast.LENGTH_SHORT).show();
//    }
//
//    public void cancel() {
//        pager.setCurrentItem(0);
//
//        //Toast.makeText(this, "입력취소 후 List 이동", Toast.LENGTH_SHORT).show();
//    }
//
//    public void save() {
//        Toast.makeText(this, "저장 후 List로 이동", Toast.LENGTH_SHORT).show();
//
//    }


    public static final int ACTION_WRITE = 0;
    public static final int ACTION_CANCEL = 1;
    public static final int ACTION_GOLIST = 2;
    public static final int ACTION_GOEDIT = 3;
    public static final int ACTION_GOLIST_WITH_REFRESH = 4;


    @Override
    public void action(int flag) {
        switch (flag) {
            case ACTION_WRITE:
                //write();
                pager.setCurrentItem(1);
                break;
            case ACTION_CANCEL:
                pager.setCurrentItem(0);
                break;
            case ACTION_GOLIST:
                pager.setCurrentItem(0);
                break;
            case ACTION_GOEDIT:
                pager.setCurrentItem(1);
                break;
            case ACTION_GOLIST_WITH_REFRESH:

                lf.setList(lf.listCount);
                //목록갱신
                lf.adapter.notifyDataSetChanged();
                pager.setCurrentItem(0);
                break;

        }
    }


//    public final static int SAVE_INSERT = 0;
//    public final static int SAVE_UPDATE = 1;
//    @Override
//    public void actionSave(BbsData data, int flag){
//        Toast.makeText(this, "actionSave 클릭됨", Toast.LENGTH_SHORT).show();
//        switch (flag){
//            case SAVE_INSERT:
//                //1.넘겨받은 data를 database에 입력
//
//
//                //2.리스트로 이동한다
//                pager.setCurrentItem(0);
//                break;
//
//
//            case SAVE_UPDATE:
//
//                break;
//
//        }
//
//
//    }


    // 리스트프래그먼트에서 리스트의 아이템을 클릭하면 호출되는 함수
    @Override
    public void actionEdit(int bbsno) {

        ef.setData(bbsno); //에디트 프래그먼트의 setData함수를 호출하여 텍스트뷰에 데이터를 setText해줌
        action(ACTION_GOEDIT); //화면을 에디트 프래그먼트로 이동시킴

    }




    /* ----------------------------------------------
     여기서 부터 ViewPager용 Adapter
   ---------------------------------------------- */
    class CustomAdapter extends FragmentStatePagerAdapter {

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = lf;
                    break;
                case 1:
                    fragment = ef;
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}


