package com.nyso.sudian.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.nyso.sudian.R;
import com.nyso.sudian.adapter.SelectAdapter;
import com.nyso.sudian.adapter.SelectClassAdapter;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.myinterface.ClassSel;
import com.nyso.sudian.util.BBCUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ClassPopWindow extends PopupWindow {
    @BindView(R.id.gv_class)
    CustomGridView gv_class;
    private Activity activity;
    private View contentView;
    private List<Category> categoryList;
    private SelectClassAdapter adapter;
    private  ClassSel classSel;
    public ClassPopWindow(Activity activity, List<Category> categoryList,ClassSel classSel){
        this.activity=activity;
        this.categoryList=categoryList;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popwindow_class, null);
        ButterKnife.bind(this, contentView);
        this.setContentView(contentView);
        this.setWidth(BBCUtil.getDisplayWidth(activity));
        this.setHeight(BBCUtil.getDisplayHeight(activity));
        this.setContentView(contentView);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.classSel=classSel;
        initData();
    }
    public void initData(){
        adapter=new SelectClassAdapter(activity,categoryList);
        gv_class.setAdapter(adapter);
    }
    public void showWindow(View parent) {
        parent.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int parentHeight = parent.getMeasuredHeight();
        if (!this.isShowing()) {
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            PopupWindowCompat.showAsDropDown(this, parent, 0, 0, Gravity.CENTER);
        } else {
            this.dismiss();
        }
    }

    @OnItemClick(R.id.gv_class)
    public void selectClass(int postion){
        if(classSel!=null){
            classSel.selectOk(postion,categoryList.get(postion));
        }
        this.dismiss();
    }

    @OnClick(R.id.ll_updown)
    public void cancel(){
        this.dismiss();
    }
    @OnClick(R.id.view_other)
    public void onViewClicked() {
        this.dismiss();
    }
}
