package com.example.test.andlang.andlangutil;

import android.app.Application;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.test.andlang.log.AppCrashHandler;
import com.example.test.andlang.util.BaseLangUtil;
import com.example.test.andlang.util.PreferencesUtil;
import com.example.test.andlang.util.imageload.*;
import com.example.test.andlang.util.imageload.IInnerImageSetter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by root on 18-3-27.
 */

public class BaseLangApplication extends Application {
    public static String tmpImageDir = "bbc/Image/tmp";
    public static long NOW_TIME; // 当前服务器时间
    public static String logDir;
    private static BaseLangApplication mApp;
    private PreferencesUtil mSpUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        mApp = this;
        //程序错误日志信息收集
        logDir = Environment.getExternalStorageDirectory().getPath() + "/sudian/crash/";
        AppCrashHandler crashHandler = AppCrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        Logger.addLogAdapter(new AndroidLogAdapter());
        initImageLoad();
    }

    private void initImageLoad() {
        ImageLoadUtils.setImageSetter(new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                if (!BaseLangUtil.isEmpty(url) && url.toLowerCase().contains(".gif")) {
                    GlideUtil.getInstance().displayGif(getApplicationContext(), url, view);
                } else {
                    GlideUtil.getInstance().display(getApplicationContext(), url, view);
                }
            }

            @Override
            public <IMAGE extends ImageView> void doLoadCircleImageUrl(@NonNull IMAGE view, @Nullable String url) {
                if (!BaseLangUtil.isEmpty(url) && url.toLowerCase().contains(".gif")) {
                    GlideUtil.getInstance().displayGif(getApplicationContext(), url, view);
                } else {
                    GlideUtil.getInstance().displayHead(getApplicationContext(), url, view);
                }
            }
        });
    }

    public static BaseLangApplication getInstance() {
        return mApp;
    }

    public synchronized PreferencesUtil getSpUtil() {
        if (mSpUtil == null)
            mSpUtil = new PreferencesUtil(this, PreferencesUtil.PREFERENCES_DEFAULT);
        return mSpUtil;
    }
}
