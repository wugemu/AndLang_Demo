package com.nyso.sudian.adapter;

import android.app.Activity;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;

import java.util.List;

public class SelectClassAdapter extends BaseLangAdapter<Category> {
    private int selectpostion;
    public SelectClassAdapter(Activity context,  List<Category> data) {
        super(context, R.layout.pop_class_item, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, Category item) {
        TextView tv_popclass_item=helper.getView(R.id.tv_popclass_item);
        if(selectpostion==postion){
            tv_popclass_item.setTextColor(context.getResources().getColor(R.color.colorBlackText));
            tv_popclass_item.setBackgroundResource(R.drawable.rect_white_4dp);
        }else {
            tv_popclass_item.setTextColor(context.getResources().getColor(R.color.colorWhite));
            tv_popclass_item.setBackgroundResource(R.drawable.rect_black_4dp);
        }
    }
}
