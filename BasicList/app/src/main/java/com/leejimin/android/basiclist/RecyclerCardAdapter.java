package com.leejimin.android.basiclist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jasmine on 2016-09-28.
 */

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;


    public RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }


    //view를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }


    //일반 listadapter의 getView를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        RecyclerData data = datas.get(position);
        holder.image.setBackgroundResource(data.image);
        holder.title.setText(data.title);
        holder.itemView.setTag(data);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ClickDetail.class);
                intent.putExtra("position", position);
                context.startActivity(intent);

                //Toast.makeText(context, "이미지가 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        setAnimation(holder.cardView, position);

    }


    int lastPosition = -1;
    public void setAnimation(View view, int position){

        if(position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            cardView = (CardView) itemView.findViewById(R.id.carditem);

        }
    }

}
