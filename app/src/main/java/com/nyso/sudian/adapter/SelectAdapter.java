package com.nyso.sudian.adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.local.SelectModel;

import java.util.List;

/**
 * Created by lenovo on 2018/9/11.
 */

public class SelectAdapter extends BaseLangAdapter<SelectModel> {
    private Handler handler;

    public SelectAdapter(Activity context, List<SelectModel> data, Handler handler) {
        super(context, R.layout.adapter_select, data);
        this.handler = handler;
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, final SelectModel item) {
        ImageView icon=helper.getView(R.id.iv_icon);
        TextView tvText=helper.getView(R.id.tv_content);
        ImageView ivSelect=helper.getView(R.id.iv_select);
        tvText.setText(item.getText());
        if (item.isSelect()) {
            ivSelect.setVisibility(View.VISIBLE);
            icon.setImageResource(item.getSelectIcon());
            tvText.setTextColor(context.getResources().getColor(R.color.colorBlackText));
        } else {
            icon.setImageResource(item.getUnSelectIcon());
            ivSelect.setVisibility(View.GONE);
            tvText.setTextColor(context.getResources().getColor(R.color.light_black));
        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (SelectModel model : data) {
                    model.setSelect(false);
                }
                item.setSelect(true);
                notifyDataSetChanged();
                //发送handler
            }
        });
    }
}
