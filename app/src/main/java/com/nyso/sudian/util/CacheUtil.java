package com.nyso.sudian.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.test.andlang.util.PreferencesUtil;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.myinterface.CacheResultListener;

import java.util.Map;

/**
 * Created by Bill56 on 2017/12/4.
 */

public class CacheUtil {

    /**
     * 方案1：根据key来判断是否有缓存数据，不做相应处理和回调，默认是携带版本号
     *
     * @param context 上下文环境
     * @param key     缓存key
     * @return
     */
    public static boolean isCacheData(Context context, String key) {
        return isCacheData(context,key,true);
    }

    /**
     * 方案1：根据key来判断是否有缓存数据，不做相应处理和回调
     *
     * @param context     上下文环境
     * @param key         缓存key
     * @param withVersion 是否携带版本号
     * @return
     */
    public static boolean isCacheData(Context context, String key, boolean withVersion) {
        String cacheKey = key;
        if(withVersion) {
            cacheKey = key + "_" + BBCUtil.getVersionCode(context);
        }
        String cacheData = SuDianApp.getInstance().getSpUtil().getString(context,  cacheKey);
        if (!TextUtils.isEmpty(cacheData)) {
            return true;
        }
        return false;
    }

    public static void handleCache(Context context,String key,@NonNull CacheResultListener listener){
        handleCache(context,key,true,listener);
    }

    public static void handleCache(Context context,String key,boolean withVersion,@NonNull CacheResultListener listener) {
        String cacheKey = key;
        if(withVersion) {
            cacheKey = key + "_" + BBCUtil.getVersionCode(context);
        }
        String cacheData = SuDianApp.getInstance().getSpUtil().getString(context, cacheKey);
        if (!TextUtils.isEmpty(cacheData)) {
            listener.cacheResult(true,cacheData);
            return;
        }
        clearOldVersionCache(context);
        listener.cacheResult(false,null);
    }

    /**
     * 默认携带版本号缓存
     * @param context
     * @param key
     * @param value
     */
    public static void cacheData(Context context,String key,String value) {
        cacheData(context,key,value,true);
    }

    public static void cacheData(Context context,String key,String value,boolean withVersion) {
        String cacheKey = key;
        if(withVersion) {
            cacheKey = key + "_" + BBCUtil.getVersionCode(context);
        }
        SuDianApp.getInstance().getSpUtil().putString(context,cacheKey,value);
		
    }

	/**
     * 清除旧缓存
     * @param context
     */
	public static void clearOldVersionCache(Context context){
        Map<String,Object> keyList= PreferencesUtil.getAllData();
        for (String str:keyList.keySet()){
            if(str.startsWith(Constants.HOME_INDEX_DATA)){
                SuDianApp.getInstance().getSpUtil().putString(context,str,null);
            }
        }
	}

}
