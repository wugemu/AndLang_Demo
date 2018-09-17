package com.nyso.sudian.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.example.test.andlang.andlangutil.LangImageUpInterface;
import com.example.test.andlang.http.Des3;
import com.example.test.andlang.http.ExecutorServiceUtil;
import com.example.test.andlang.http.HttpCallback;
import com.example.test.andlang.http.HttpU;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.BaseLangUtil;
import com.example.test.andlang.util.LogUtil;
import com.example.test.andlang.util.ToastUtil;
import com.example.test.andlang.util.VersionUtil;
import com.google.gson.Gson;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.ui.login.LoginActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BBCHttpUtil {

    public static Gson gson=new Gson();
    public static void postHttp(final BaseLangActivity context, final String url, Map<String, Object> params, final Type type, final LangHttpInterface listener){
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put("version", VersionUtil.getVersionCode(context));
        params.put("clientType", "3");
        HttpU.getInstance().post(context, Constants.HOST+url, params, context, new HttpCallback() {

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                context.dismissWaitDialog();
            }

            @Override
            public void onResponse(String response) {
                try {
                    if(BaseLangUtil.isEmpty(response)){
                        if(listener!=null) {
                            listener.empty();
                        }
                    } else{
                        if (JsonParseUtil.isSuccessResponse(response)) {
                            String result=JsonParseUtil.getStringValue(response,"result");
                            if(listener!=null) {
                                if(type==String.class||type==Integer.class){
                                    listener.success( result);
                                }else {
                                    listener.success(gson.fromJson(result, type));
                                }
                            }
                        }else {
                            String msg=JsonParseUtil.getMsgValue(response);
                            if(!BBCUtil.isEmpty(msg)){
                                ToastUtil.show(context,msg);
                            }
                            listener.fail();
                        }
                    }
                }catch (Exception e){
                    Log.e(BaseLangPresenter.TAG,e.getMessage());
                    if(listener!=null) {
                        listener.error();
                    }
                }
            }

            @Override
            public void onError(Request request, Exception e, Context context) {
                super.onError(request, e, context);
                if(listener!=null){
                    listener.error();
                }
            }

            @Override
            public void loginFail() {
                super.loginFail();
                SuDianApp.getInstance().getSpUtil().putBoolean(context,Constants.ISLOGIN, false);
                Intent intent=new Intent(context, LoginActivity.class);
                intent.putExtra("goHome","1");
                ActivityUtil.getInstance().start((Activity)context, intent);
            }
        });
    }
    public static void getHttp(Context context, final String url, Map<String, Object> params, LangHttpInterface listener){
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put("version", VersionUtil.getVersionCode(context));
        params.put("clientType", "3");
        HttpU.getInstance().get(context, url, new HttpCallback() {

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
            }

            @Override
            public void onResponse(String response) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void upImage(final BaseLangActivity context, File file, String serverUrl,final boolean isUpHeadImg, String type,final LangImageUpInterface upInterface){
        HttpU.getInstance().uploadImage(context, file, serverUrl, type, new LangImageUpInterface() {
            @Override
            public void success(String result) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.dismissWaitDialog();
                    }
                });
                try {
                    if (isUpHeadImg) {
                        //保存上传成功后的图片路径
                        if (JsonParseUtil.isSuccessResponse(result)) {
                            String imgStr = JsonParseUtil.getStringValue(result,"result");
                            if (!TextUtils.isEmpty(imgStr)) {
                                SuDianApp.getInstance().getSpUtil().putString(context, Constants.USER_HEADIMG, imgStr);
                            }
                            if(upInterface!=null){
                                upInterface.success(imgStr);
                            }
                        } else {
                            ToastUtil.show(context, JsonParseUtil.getMsgValue(result));
                        }
                    }
                }catch (Exception e){

                }
            }
        });
    }
}
