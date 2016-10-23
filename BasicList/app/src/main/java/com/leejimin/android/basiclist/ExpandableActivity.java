package com.leejimin.android.basiclist;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);

        //아답터에 넘겨줄 데이터 정의
        ArrayList<ExpandData> datas = new ArrayList<>();

        ExpandData data = new ExpandData();
        data.cityName = "서울";
        data.guNames.add("강동");
        data.guNames.add("강서");
        data.guNames.add("강남");
        data.guNames.add("강북");
        data.guNames.add("마포");
        data.guNames.add("서초");
        data.guNames.add("동작");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "광주";
        data.guNames.add("광산");
        data.guNames.add("중구");
        data.guNames.add("북구");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "부산";
        data.guNames.add("강동");
        data.guNames.add("강서");
        data.guNames.add("강남");
        data.guNames.add("강북");
        data.guNames.add("마포");
        data.guNames.add("서초");
        data.guNames.add("동작");
        datas.add(data);

        ExpandableAdapter ea = new ExpandableAdapter(this, R.layout.expand_parent_item, R.layout.expand_child_item, datas);




        DisplayMetrics metrics = new DisplayMetrics(); // metrics - 실제 픽셀 사이즈를 가져올때

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
            listView.setIndicatorBounds(width - GetPixelFromDips(50), width);
        else
            listView.setIndicatorBoundsRelative(width - GetPixelFromDips(50), width);

        listView.setAdapter(ea);

        //dp를 px로 변경할 때
        int convertedPixel = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()
        //                                ↑ dp값 입력
        );


    }



    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    public int pxToDp(Context context, int px){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px/(metrics).xdpi/ DisplayMetrics.DENSITY_DEFAULT);
        return 0;
    }

    public int dpToPx(Context context, int dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp*(metrics).xdpi/ DisplayMetrics.DENSITY_DEFAULT);
        return 0;

    }


}


