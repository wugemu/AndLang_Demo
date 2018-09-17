package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.model.local.NewCentral;
import com.nyso.sudian.ui.news.NewsDetialActivity;

import java.util.List;

/**
 * Created by lenovo on 2018/9/7.
 */

public class NewsCentralAdapter extends BaseLangAdapter<NewCentral> {


    public NewsCentralAdapter(Activity context, List<NewCentral> data) {
        super(context, R.layout.adapter_news_central, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, final int postion, NewCentral item) {
        View vDot = helper.getView(R.id.read_dot);
        if (item.isShowDot()) {
            vDot.setVisibility(View.VISIBLE);
        } else {
            vDot.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_subtitle, item.getSubTitle());
        helper.setText(R.id.tv_date, item.getDate());
        ImageView icon = helper.getView(R.id.iv_icon);
        icon.setImageResource(item.getIcon());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetialActivity.class);
                intent.putExtra("type",postion);
                ActivityUtil.getInstance().start(context,intent);
            }
        });
    }
}
