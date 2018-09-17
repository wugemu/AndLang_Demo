package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;

import java.util.List;

/**
 * Created by 1 on 2017/2/23.
 * 一级分类
 */

public class Category1Adapter extends BaseLangAdapter<Category> {
    private Integer selectP=0;
    private Handler handler;
    public Category1Adapter(Activity context, List<Category> mDatas,Handler handler) {
        super(context,R.layout.listview_category_1, mDatas);
        this.handler=handler;
    }

    @Override
    public void convert(BaseLangViewHolder helper, final int postion, final Category item) {

        ImageView iv=helper.getView(R.id.iv_class_arrow);
        TextView textView=helper.getView(R.id.tv_category);
        if(postion==selectP){
            textView.setTextColor(context.getResources().getColor(R.color.colorRedMain));
            iv.setVisibility(View.VISIBLE);
            helper.getConvertView().setBackgroundResource(R.color.colorWhite);
        }else{
            iv.setVisibility(View.GONE);
            textView.setTextColor(context.getResources().getColor(R.color.colorBlackText3));
        }
        textView.setText(item.getCategoryName());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 暂时注释友盟点击 MobclickAgent.onEvent(mContext,"topcategory_"+item.getCategoryId()+"_click");
                selectP=postion;
                Message msg=new Message();
                msg.what=1;
                msg.arg2=postion;
                msg.arg1=item.getCategoryId();
                handler.sendMessage(msg);
                notifyDataSetChanged();
            }
        });
    }
}
