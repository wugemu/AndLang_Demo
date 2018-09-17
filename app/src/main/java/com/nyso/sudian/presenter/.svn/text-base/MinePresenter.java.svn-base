package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.nyso.sudian.model.api.UserAccount;
import com.nyso.sudian.model.local.MainModel;
import com.nyso.sudian.model.local.MineModel;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class MinePresenter extends BaseLangPresenter<MineModel> {
    public MinePresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    public MinePresenter(BaseLangFragment fragment, BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(fragment, activity, modelClass);
    }

    @Override
    public void initModel() {

    }

    public void getOrderState(){

    }

    public void getUserAccountInfo(){
        //网络请求
        Map<String, Object> param = new HashMap<String, Object>();
        BBCHttpUtil.postHttp(activity, Constants.GET_USER_INFO, param, UserAccount.class, new LangHttpInterface<UserAccount>() {
            @Override
            public void success(UserAccount response) {
                model.setUserAccount(response);
                model.notifyData("getUserAccountInfo");
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
