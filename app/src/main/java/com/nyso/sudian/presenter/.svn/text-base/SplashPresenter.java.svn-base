package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.nyso.sudian.model.local.SplashModel;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class SplashPresenter extends BaseLangPresenter<SplashModel>{
    public SplashPresenter(BaseLangActivity activity, Class modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {

    }

    public void reqVersion(){
        //网络请求
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", "6");
        BBCHttpUtil.postHttp(activity, Constants.GET_VERSION, param, String.class, new LangHttpInterface<String>() {
            @Override
            public void success(String response) {
                model.setResult(response);
                model.notifyData("reqVersion");
            }

            @Override
            public void empty() {

            }

            @Override
            public void error() {
                model.notifyData("reqVersionError");
            }

            @Override
            public void fail() {
                model.notifyData("reqVersionError");
            }
        });
    }

}
