package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.local.SettingModel;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.Constants;

import java.util.HashMap;

public class SettingPresenter extends BaseLangPresenter<SettingModel> {
    public SettingPresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {

    }

    public void logoutOut(){
        HashMap<String, Object> params = new HashMap<String, Object>();
        BBCHttpUtil.postHttp(activity, Constants.LOGOUT, params, String.class, new LangHttpInterface<String>() {
            @Override
            public void success(String response) {
                model.notifyData("logoutOut");
            }

            @Override
            public void empty() {

            }

            @Override
            public void error() {

            }

            @Override
            public void fail() {

            }
        });
    }
}
