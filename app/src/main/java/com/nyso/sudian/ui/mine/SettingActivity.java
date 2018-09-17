package com.nyso.sudian.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.local.SettingModel;
import com.nyso.sudian.myinterface.ConfirmOKI;
import com.nyso.sudian.presenter.SettingPresenter;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.ui.widget.dialog.ConfirmDialog;
import com.nyso.sudian.util.Constants;

import java.util.Observable;

import butterknife.OnClick;

public class SettingActivity extends BaseLangActivity<SettingPresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        initTitleBar(true,"设置");
    }

    @Override
    public void initPresenter() {
        presenter=new SettingPresenter(SettingActivity.this, SettingModel.class);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_logout)
    public void goLogout(){
        // 退出登录或登录
        new ConfirmDialog(this, "您要退出登录吗？", new ConfirmOKI() {
            @Override
            public void executeOk() {
                presenter.logoutOut();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if("logoutOut".equals(arg)){
            SuDianApp.getInstance().getSpUtil().putString(SettingActivity.this, Constants.NICK_NAME, null);
            SuDianApp.getInstance().getSpUtil().putString(SettingActivity.this,  Constants.PHONE_NUMBER, null);
            SuDianApp.getInstance().getSpUtil().putString(SettingActivity.this,  Constants.USER_HEADIMG, null);
            SuDianApp.getInstance().getSpUtil().putBoolean(SettingActivity.this, Constants.ISLOGIN, false);
            SuDianApp.getInstance().getSpUtil().putBoolean(SettingActivity.this,  Constants.ISLOGIN_FIRST, false);
            SuDianApp.getInstance().getSpUtil().putString(SettingActivity.this,  Constants.COOKIE, null);
            Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
            ActivityUtil.getInstance().start(SettingActivity.this, intent);
        }
    }
}
