package com.nyso.sudian;

import com.example.test.andlang.andlangutil.BaseLangApplication;
import com.nyso.sudian.util.NativeHelper;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class SuDianApp extends BaseLangApplication {
    public static String otherHeadImg="";
    public static String otherNickName="";

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }
    public void initData() {
        PlatformConfig.setWeixin(NativeHelper.getWXAPPID(), NativeHelper.getWXAPPKEY());
        PlatformConfig.setQQZone(NativeHelper.getQQAPPID(), NativeHelper.getQQAPPKEY());
        PlatformConfig.setSinaWeibo(NativeHelper.getWBAPPID(),NativeHelper.getWBSecrt(), "http://sns.whalecloud.com/sina2/callback");
        UMShareAPI.get(this);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        MobclickAgent.enableEncrypt(true);
    }
}
