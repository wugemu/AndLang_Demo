package com.nyso.sudian.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.News;

import java.util.List;

/**
 * Created by lenovo on 2018/9/7.
 * 素店公告和培训消息
 */

public class NewsAdapter extends BaseLangAdapter<News> {
    public NewsAdapter(Activity context,  List<News> data) {
        super(context, R.layout.adapter_news, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, News item) {
        ImageView icon=helper.getView(R.id.iv_icon);
        if ("2".equals(item.getType())){
            icon.setImageResource(R.mipmap.peixun_message);
        }else {
            icon.setImageResource(R.mipmap.gonggao_message);
        }
    }
}
