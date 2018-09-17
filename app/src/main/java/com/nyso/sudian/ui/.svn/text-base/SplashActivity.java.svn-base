package com.nyso.sudian.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.http.ExecutorServiceUtil;
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.local.SplashModel;
import com.nyso.sudian.presenter.SplashPresenter;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;

import java.util.Observable;

public class SplashActivity extends BaseLangActivity<SplashPresenter> {
    private static final int REQUEST_PERMISSION = 0;
    String result;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void initPresenter() {
        presenter=new SplashPresenter(SplashActivity.this, SplashModel.class);
    }

    @Override
    public void initData() {
        PackageManager pkgManager = getPackageManager();
//        // read phone state用于获取 imei 设备信息
        boolean phoneSatePermission =
                pkgManager.checkPermission(Manifest.permission.READ_PHONE_STATE, getPackageName())
                        == PackageManager.PERMISSION_GRANTED;
//        // 读写 sd card 权限非常重要, android6.0默认禁止的, 建议初始化之前就弹窗让用户赋予该权限
        boolean sdCardWritePermission =
                pkgManager.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if (Build.VERSION.SDK_INT >= 23 && !sdCardWritePermission || !phoneSatePermission) {
            //权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                    REQUEST_PERMISSION);
        } else {
            //获取版本号
            presenter.reqVersion();
        }

    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            goNext();
        }
    };
    private void goNext(){
        String ver = SuDianApp.getInstance().getSpUtil().getString(SplashActivity.this, Constants.VERSION);
        Intent intent = new Intent();
        intent.putExtra("result", result);
        if (!BBCUtil.isEmpty(ver) && BBCUtil.getVersionName(SplashActivity.this).equals(ver)) {//第一次进入，显示引导页
            SuDianApp.getInstance().getSpUtil().putString(SplashActivity.this, Constants.VERSION, BBCUtil.getVersionName(SplashActivity.this));
            intent.setClass(SplashActivity.this, HomeActivity.class);
            ActivityUtil.getInstance().start2(SplashActivity.this, intent);
        }
        else {
            intent.setClass(SplashActivity.this, GuideActivity.class);
            ActivityUtil.getInstance().start2(SplashActivity.this, intent);
        }
        finish();
    }
    private Runnable task=new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public void update(Observable o, Object arg) {
        if("reqVersion".equals(arg)){
            result=presenter.model.getResult();
            //执行倒计时
            ExecutorServiceUtil.getInstence().execute(task);
        }else if("reqVersionError".equals(arg)){
            //执行倒计时
            ExecutorServiceUtil.getInstence().execute(task);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            //获取版本号
            presenter.reqVersion();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
