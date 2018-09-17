package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.nyso.sudian.model.local.MainModel;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class MainPresenter extends BaseLangPresenter<MainModel> {
    public MainPresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {

    }

    public void reqVersion(){
        //网络请求
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", "6");
        BBCHttpUtil.postHttp(activity, Constants.GET_VERSION, param, MainModel.class, new LangHttpInterface<MainModel>() {
            @Override
            public void success(MainModel response) {
                model.setUserLablePopup(response.getUserLablePopup());
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
            }
        });
    }

    public void reqCartNum(){

    }
}
