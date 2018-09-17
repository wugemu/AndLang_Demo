package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nyso.sudian.R;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodaySaleTimeAdapter  extends RecyclerView.Adapter<TodaySaleTimeAdapter.LimitTimeVH> {


    private LayoutInflater inflater;
    private Activity activity;
    private List<String> timeList;
    private int selectPostition;
    private Handler handler;
    public TodaySaleTimeAdapter(Activity activity, List<String> timeList, Handler handler) {
        this.activity = activity;
        if(timeList!=null){
            this.timeList = timeList;
        }else{
            this.timeList = new ArrayList<String>();
        }
        this.handler=handler;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public LimitTimeVH onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.child_limittime_item,
                viewGroup, false);
        LimitTimeVH viewHolder = new LimitTimeVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LimitTimeVH limitTimeVH,final int position) {
        int width = (int) (BBCUtil.getDisplayWidth(activity) - activity.getResources().getDimension(R.dimen.limittime_width))/2;
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
        limitTimeVH.view_limitime_top.setLayoutParams(params);
        limitTimeVH.view_limitime_bottom.setLayoutParams(params);
        if(position==0){
            limitTimeVH.view_limitime_top.setVisibility(View.VISIBLE);
        }else {
            limitTimeVH.view_limitime_top.setVisibility(View.GONE);
        }
        if(position==getItemCount()-1){
            limitTimeVH.view_limitime_bottom.setVisibility(View.VISIBLE);
        }else {
            limitTimeVH.view_limitime_bottom.setVisibility(View.GONE);
        }
        if(selectPostition==position){
            limitTimeVH.tv_limite_time.setTextColor(activity.getResources().getColor(R.color.colorRedMain));
            limitTimeVH.tv_limite_tip.setTextColor(activity.getResources().getColor(R.color.colorRedMain));
        }else {
            limitTimeVH.tv_limite_time.setTextColor(activity.getResources().getColor(R.color.colorBlackText));
            limitTimeVH.tv_limite_tip.setTextColor(activity.getResources().getColor(R.color.colorBlackText2));
        }
        limitTimeVH.tv_limite_time.setText(timeList.get(position));
        limitTimeVH.ll_saletime_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPostition=position;
                notifyDataSetChanged();
                if(handler!=null) {
                    Message message=new Message();
                    message.what=1;
                    message.arg1=position;
                    handler.sendMessage(message);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    static class LimitTimeVH extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_saletime_time)
        LinearLayout ll_saletime_time;
        @BindView(R.id.view_limitime_top)
        View view_limitime_top;
        @BindView(R.id.view_limitime_bottom)
        View view_limitime_bottom;
        @BindView(R.id.tv_limite_time)
        TextView tv_limite_time;
        @BindView(R.id.tv_limite_tip)
        TextView tv_limite_tip;
        public LimitTimeVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setSelectPostition(int postition){
        selectPostition=postition;
        notifyDataSetChanged();
    }
    public int getSelectPostition(){
        return selectPostition;
    }
}
