package com.nyso.sudian.adapter;

import android.app.Activity;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.News;

import java.util.List;

/**
 * Created by lenovo on 2018/9/7.
 * 秒杀提醒
 */


public class NoticeAdapter extends BaseLangAdapter<News> {

    public NoticeAdapter(Activity context,List<News> data) {
        super(context, R.layout.adapter_notice, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, News item) {

    }
}
