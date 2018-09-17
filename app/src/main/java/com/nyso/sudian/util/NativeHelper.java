package com.nyso.sudian.util;

/**
 * Created by 1 on 2017/9/12.
 */

public class NativeHelper {
    static {
        // 加载C代码库, 库的名称, 必须是CMakeLists.txt中指定的名称
        System.loadLibrary("bbcshop");
    }
    public static native String getDesKey();

    /**
     * QQ相关
     */
    public static native String getQQAPPID();
    public static native String getQQAPPKEY();

    /**
     * SINA相关
     */
    public static native String getWBAPPID();
    public static native String getWBSecrt();


    /**
     * 微信相关
     */
    public static native String getWXAPPID();
    public static native String getWXAPPKEY();

    /**
     * 百川热更新
     */
    public static native String getHOTFIXAPPID();
    public static native String getHOTFIXAESKEY();


}
