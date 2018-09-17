package com.nyso.sudian.presenter;

import android.content.Context;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.example.test.andlang.andlangutil.LangHttpInterface;
import com.example.test.andlang.andlangutil.LangImageUpInterface;
import com.example.test.andlang.util.BaseLangUtil;
import com.google.gson.reflect.TypeToken;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.UserAccount;
import com.nyso.sudian.model.api.UserDetail;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.util.BBCHttpUtil;
import com.nyso.sudian.util.Constants;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPresenter extends BaseLangPresenter<LoginModel> {
    public LoginPresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {

    }

    public void loginAndRegist(String mobile,String captcha){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile",mobile);
        params.put("captcha", captcha);
        params.put("loginType","sms");
        BBCHttpUtil.postHttp(activity, Constants.REQ_LOGIN_NEW, params, UserAccount.class, new LangHttpInterface<UserAccount>() {
            @Override
            public void success(UserAccount response) {
                model.setUserAccount(response);
                model.notifyData("loginAndRegist");
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

    public void authLogin(final Map<String, String> data, String type){
        //联登
        model.setData(new HashMap<String, Object>());//初始化本地联登录数据
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("connectionType", type);
        params.put("loginType","connect");
        params.put("openId", data.get("uid"));
        params.put("nickName", data.get("name"));
        params.put("headUrl", data.get("iconurl"));
        SuDianApp.otherHeadImg=data.get("iconurl");
        SuDianApp.otherNickName=data.get("name");
        BBCHttpUtil.postHttp(activity, Constants.REQ_LOGIN_NEW, params, UserAccount.class, new LangHttpInterface<UserAccount>() {
            @Override
            public void success(UserAccount response) {
                model.setUserAccount(response);
                model.setData(params);
                model.notifyData("authLogin");
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

    //更新手机号码
    public void updateMobile(String mobile,String captcha,Map<String, String> data){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile",mobile);
        params.put("captcha", captcha);
        params.put("loginType","connect");
        params.putAll(data);
        BBCHttpUtil.postHttp(activity, Constants.REQ_LOGIN_NEW, params, UserAccount.class, new LangHttpInterface<UserAccount>() {
            @Override
            public void success(UserAccount response) {
                model.setUserAccount(response);
                model.notifyData("updateMobile");
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

    //发送验证码
    public void sendCode(String mobile){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile",mobile);
        params.put("type",2);
        BBCHttpUtil.postHttp(activity, Constants.REQ_LOGIN_NEW, params, UserAccount.class, new LangHttpInterface<UserAccount>() {
            @Override
            public void success(UserAccount response) {
                model.notifyData("sendCode");
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

    //查询上级用户信息
    public void reqInvitePInfo(String inviteCode){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("inviteCode", inviteCode);
        BBCHttpUtil.postHttp(activity, Constants.REQ_FINDBYINVITECODE, params, UserDetail.class, new LangHttpInterface<UserDetail>() {
            @Override
            public void success(UserDetail response) {
                model.setUserDetail(response);
                model.notifyData("reqInvitePInfo");
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

    //获取推荐人列表
    public void reqInvitePList(){
        HashMap<String, Object> params = new HashMap<String, Object>();
        Type type=new TypeToken<List<UserDetail>>(){}.getType();
        BBCHttpUtil.postHttp(activity, Constants.REQ_INVITEPLIST, params, type, new LangHttpInterface<List<UserDetail>>() {
            @Override
            public void success(List<UserDetail> response) {
                model.setUserDetailList(response);
                model.notifyData("reqInvitePList");
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

    //绑定上级用户
    public void  bindInviteP(String parUserId,boolean isWXLogin){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("parUserId", parUserId);
        if(isWXLogin) {
            params.put("loginType", "connect");
        }else {
            params.put("loginType", "sms");
        }
        BBCHttpUtil.postHttp(activity, Constants.REQ_BINDPARENTUSER, params, String.class, new LangHttpInterface<String>() {
            @Override
            public void success(String response) {
                model.notifyData("bindInviteP");
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
    //更新性别
    public void updateSex(int sex){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("sex", sex);
        BBCHttpUtil.postHttp(activity, Constants.REQ_UPDATESEX, params, String.class, new LangHttpInterface<String>() {
            @Override
            public void success(String response) {
                model.notifyData("updateSex");
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

    //修改头像昵称
    public void updateNickName(final String headUrl,final String nickName){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("headUrl", headUrl);
        params.put("nickName",nickName);
        BBCHttpUtil.postHttp(activity, Constants.REQ_UPDATNICKNAME, params, String.class, new LangHttpInterface<String>() {
            @Override
            public void success(String response) {
                SuDianApp.getInstance().getSpUtil().putString(activity,  Constants.NICK_NAME, nickName);
                SuDianApp.getInstance().getSpUtil().putString(activity,  Constants.USER_HEADIMG, headUrl);
                model.notifyData("updateNickName");
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

    //头像图片上传
    public void uploadImage(BaseLangActivity context, File file, String serverUrl, final boolean isUpHeadImg, String type){
        BBCHttpUtil.upImage(context, file, serverUrl, isUpHeadImg, type, new LangImageUpInterface() {
            @Override
            public void success(String resp) {
                model.notifyData("uploadImage");
            }
        });
    }

}
