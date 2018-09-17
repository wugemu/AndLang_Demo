package com.example.test.andlang.andlangutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.R;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.BaseLangUtil;
import com.example.test.andlang.util.LogUtil;
import com.example.test.andlang.util.NetUtil;
import com.example.test.andlang.util.StatusBarUtils;
import com.example.test.andlang.util.ToastUtil;

import java.util.Observer;

import butterknife.ButterKnife;


/**
 * Created by lang on 18-3-7.
 */

public abstract class BaseLangActivity<T extends BaseLangPresenter> extends AppCompatActivity implements Observer {
    public T presenter;
    public RelativeLayout rlLoading;
    private ImageView ivLoading;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        ActivityUtil.getInstance().pushOneActivity(this);
        LogUtil.d("当前创建的Activity:" + getClass().getSimpleName());
        LogUtil.d("当前activity数量：" + ActivityUtil.getInstance().getActivitySize());
        setStatusBar(1,R.color.lang_colorWhite);
        initView();
        initPresenter();
        setObserModelForTag();
        initData();
        initModel();
    }

    private void initModel(){
        if(presenter==null){
            Log.e(BaseLangPresenter.TAG,"presenter 未创建 ");
        }else {
            presenter.initModel();
        }
    }
    private void setObserModelForTag(){
        if(presenter!=null&&presenter.model!=null){
            presenter.model.addObserver(this);
        }else {
            Log.e(BaseLangPresenter.TAG,"presenter 未初始化");
        }
    }
    public void initTitleBar(String title){
        initTitleBar(true,title);
    }
    public void initTitleBar(boolean isCanBack,String title){
        setStatusBar(1,R.color.colorLangTitle);
        LinearLayout llBack = ButterKnife.findById(this,R.id.lang_ll_back);
        TextView tvTitle = ButterKnife.findById(this,R.id.lang_tv_title);
        ImageView ivEdit = ButterKnife.findById(this,R.id.lang_iv_right);
        TextView tvEdit = ButterKnife.findById(this,R.id.lang_tv_right);
        if(tvTitle!=null&& !BaseLangUtil.isEmpty(title)){
            tvTitle.setText(title);
        }
        if(llBack!=null){
            if(!isCanBack){
                llBack.setVisibility(View.GONE);
            }else {
                llBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goBack();
                    }
                });
            }
        }
    }
    public void initTitleBar(boolean isCanBack, String title, int imageId, View.OnClickListener listener){
        setStatusBar(1,R.color.colorLangTitle);
        LinearLayout llBack = ButterKnife.findById(this,R.id.lang_ll_back);
        TextView tvTitle = ButterKnife.findById(this,R.id.lang_tv_title);
        ImageView ivEdit = ButterKnife.findById(this,R.id.lang_iv_right);
        TextView tvEdit = ButterKnife.findById(this,R.id.lang_tv_right);
        if(tvTitle!=null&& !BaseLangUtil.isEmpty(title)){
            tvTitle.setText(title);
        }
        if(ivEdit!=null){
            ivEdit.setVisibility(View.VISIBLE);
            ivEdit.setImageResource(imageId);
            if(listener!=null){
                ivEdit.setOnClickListener(listener);
            }
        }
        if(llBack!=null){
            if(!isCanBack){
                llBack.setVisibility(View.GONE);
            }else {
                llBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goBack();
                    }
                });
            }
        }
    }
    public void initTitleBar(boolean isCanBack, String title, String rightTitle, View.OnClickListener listener){
        setStatusBar(1,R.color.colorLangTitle);
        LinearLayout llBack = ButterKnife.findById(this,R.id.lang_ll_back);
        TextView tvTitle = ButterKnife.findById(this,R.id.lang_tv_title);
        ImageView ivEdit = ButterKnife.findById(this,R.id.lang_iv_right);
        TextView tvEdit = ButterKnife.findById(this,R.id.lang_tv_right);
        if(tvTitle!=null&& !BaseLangUtil.isEmpty(title)){
            tvTitle.setText(title);
        }
        if(tvEdit!=null&& !BaseLangUtil.isEmpty(rightTitle)){
            tvEdit.setVisibility(View.VISIBLE);
            tvEdit.setText(rightTitle);
            if(listener!=null){
                tvEdit.setOnClickListener(listener);
            }
        }
        if(llBack!=null){
            if(!isCanBack){
                llBack.setVisibility(View.GONE);
            }else {
                llBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goBack();
                    }
                });
            }
        }
    }
    public void initLoading(){
        rlLoading=ButterKnife.findById(this,R.id.rl_loading);
        ivLoading=ButterKnife.findById(this,R.id.iv_loading);
    }

    /**  设置状态栏高度  */
    protected void setStatusBar(int flag,int colorId) {
        View mStatusBar=ButterKnife.findById(this,R.id.m_statusBar);
        if(flag==1){
            if(mStatusBar != null) {
                StatusBarUtils.translateStatusBar(this);
                mStatusBar.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
                layoutParams.height = StatusBarUtils.getStatusHeight(this);
                mStatusBar.setLayoutParams(layoutParams);
                mStatusBar.setBackgroundResource(colorId);
                StatusBarUtils.setTextColorStatusBar(this, true);
            } else {
                StatusBarUtils.setWindowStatusBarColor(this, R.color.lang_colorWhite);
            }
        } else{
            StatusBarUtils.setWindowStatusBarColor(this, R.color.lang_colorWhite);
        }
    }
    /**
     * 检测网络是否可用
     * @return
     */
    private boolean checkNet() {
        if (NetUtil.isNetworkAvailable(this)) {
            return true;
        } else {
            ToastUtil.show(this, R.string.tip_no_network);
            return false;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkNet();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null&&presenter.model!=null){
            presenter.model.deleteObserver(this);
        }
        ActivityUtil.getInstance().popOneActivity(this);
    }

    /**
     * 展示网络加载动画
     */
    public void showWaitDialog() {
        if (rlLoading != null && ivLoading != null) {
            final Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.loading);
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            rlLoading.post(new Runnable() {
                @Override
                public void run() {
                    ivLoading.startAnimation(operatingAnim);
                }
            });
            rlLoading.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 关闭网络加载动画
     */
    public void dismissWaitDialog() {
        if (rlLoading != null && ivLoading != null) {
            ivLoading.clearAnimation();
            rlLoading.setVisibility(View.GONE);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    protected void goBack() {
        ActivityUtil.getInstance().exit(this);
    }

    public abstract int getLayoutId();//设置布局id
    public abstract void initView();
    public abstract void initPresenter();
    public abstract void initData();
}
