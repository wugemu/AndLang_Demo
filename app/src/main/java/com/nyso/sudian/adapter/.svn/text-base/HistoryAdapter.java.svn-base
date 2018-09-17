package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/2/15.
 * 历史记录
 */

public class HistoryAdapter extends BaseLangAdapter<String> {
//    private String[] hotKey;
    private Handler handler;
    public HistoryAdapter(Activity context, List<String> mDatas, Handler handler) {
        super(context,R.layout.adapter_history, mDatas);
//        this.hotKey=hotKey;
        this.handler=handler;
    }
    public void clearHistory(){
       this.data.clear();
        notifyDataSetChanged();
    }
    public void setHistory(List<String> history){
        this.data=history;
        notifyDataSetChanged();
    }


    @Override
    public void convert(BaseLangViewHolder helper, int postion, final String item) {
        helper.setText(R.id.tv_key,item);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg=new Message();
                msg.what=1;
                msg.obj=item;
                handler.sendMessage(msg);
            }
        });

        helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BBCUtil.deleteRecord(context,item);
                data.remove(item);
                notifyDataSetChanged();
            }
        });
    }
}
