package com.nyso.sudian.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.test.andlang.widget.CustomListView;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.SelectAdapter;
import com.nyso.sudian.model.local.SelectModel;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lhp on 2018/8/1.
 */

public class OrderFliterPopupWindow extends PopupWindow {


    @BindView(R.id.lv_list)
    CustomListView lvList;
    private View contentView;
    private Activity activity;
    private int type;
    private SelectAdapter adapter;

    public void setType(int type) {
        this.type = type;
    }

    public OrderFliterPopupWindow(Activity activity,Handler handler) {
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popupwindow_order_fliter, null);
        ButterKnife.bind(this, contentView);
        this.setContentView(contentView);
        initView();
        this.setWidth(BBCUtil.getDisplayWidth(activity));
        this.setHeight(BBCUtil.getDisplayHeight(activity));
        this.setContentView(contentView);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        List<SelectModel> list=new ArrayList<>();
        SelectModel sm=new SelectModel();
        sm.setSelectIcon(R.mipmap.class_hot_down_select);
        sm.setUnSelectIcon(R.mipmap.class_hot_down_unselect);
        sm.setText("按热销排序");
        list.add(sm);
        if (type==1){//玩主
            sm=new SelectModel();
            sm.setSelectIcon(R.mipmap.class_profit_down_select);
            sm.setUnSelectIcon(R.mipmap.class_profit_down_unselect);
            sm.setText("利润由高到低");
            list.add(sm);
        }
        sm=new SelectModel();
        sm.setSelectIcon(R.mipmap.class_new_select);
        sm.setUnSelectIcon(R.mipmap.class_new_unselect);
        sm.setText("查看最新上架");
        list.add(sm);

        sm=new SelectModel();
        sm.setSelectIcon(R.mipmap.class_price_down_select);
        sm.setUnSelectIcon(R.mipmap.class_price_down_unselect);
        sm.setText("价格由高到低");
        list.add(sm);

        sm=new SelectModel();
        sm.setSelectIcon(R.mipmap.class_price_up_select);
        sm.setUnSelectIcon(R.mipmap.class_price_up_unselect);
        sm.setText("价格由低到高");
        list.add(sm);
        adapter=new SelectAdapter(activity,list,handler);
        lvList.setAdapter(adapter);
    }

    public void initView() {

    }

    public void showWindow(View parent) {
        parent.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int parentHeight = parent.getMeasuredHeight();
        if (!this.isShowing()) {
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1] + parentHeight);
        } else {
            this.dismiss();
        }
    }

    @OnClick(R.id.view_other)
    public void onViewClicked() {
        this.dismiss();
    }
}
