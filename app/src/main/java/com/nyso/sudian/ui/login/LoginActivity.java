package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.http.ExecutorServiceUtil;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.BaseLangUtil;
import com.example.test.andlang.util.ButtonUtil;
import com.example.test.andlang.util.ToastUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.UserAccount;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.ui.widget.CleanableEditText;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.CacheUtil;
import com.nyso.sudian.util.Constants;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseLangActivity<LoginPresenter> {

    @BindView(R.id.ce_login_mobile)
    CleanableEditText ceLoginMobile;
    @BindView(R.id.ce_login_code)
    CleanableEditText ce_login_code;
    @BindView(R.id.btn_register_send_code)
    Button btn_register_send_code;
    @BindView(R.id.btn_login)
    Button btn_login;

    private String type;//联登类型1-qq   2-新浪微博   3-微信  4-支付宝 5 短信连登
    private String goHome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        initLoading();
        ceLoginMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });
        ce_login_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(LoginActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            goHome=intent.getStringExtra("goHome");
            if("1".equals(goHome)){
                ActivityUtil.getInstance().finishBeforActivity();
            }
        }
        SuDianApp.otherHeadImg="";
        SuDianApp.otherNickName="";
    }

    public void changeButtonState(){
        if(!BBCUtil.isEmpty(ceLoginMobile.getText().toString().trim())&&!BBCUtil.isEmpty(ce_login_code.getText().toString().trim())){
            btn_login.setBackgroundResource(R.drawable.bg_rect_new);
            btn_login.setEnabled(true);
        }else {
            btn_login.setBackgroundResource(R.drawable.bg_rect_new3);
            btn_login.setEnabled(false);
        }
    }

    @OnClick(R.id.iv_login_close)
    public void close() {
        onBackPressed();
    }

    @OnClick(R.id.btn_register_send_code)
    public void sendCode(){
        String mobile=ceLoginMobile.getText().toString().trim();
        if(!BaseLangUtil.isMobile(mobile)){
            ToastUtil.show(this,"请输入正确的手机号码");
            return;
        }
        presenter.sendCode(mobile);
    }
    @OnClick(R.id.btn_login)
    public void login() {
        //判断button连续点击的问题
        if (ButtonUtil.isFastDoubleClick(R.id.btn_login)) {
            ToastUtil.show(this, R.string.tip_btn_fast);
            return;
        }
        String mobile = ceLoginMobile.getText().toString().trim();
        String code = ce_login_code.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show(this, "请输入手机号");
            return;
        }
        showWaitDialog();
        presenter.loginAndRegist(mobile, code);
    }
    @OnClick({R.id.ll_auth_wx})
    public void onClick(View view) {
        ToastUtil.show(this,"授权登录中，请稍候...");
        switch (view.getId()) {
            case R.id.ll_auth_wx:
                type = "3";
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
//            case R.id.iv_auth_qq:
//                type = "1";
//                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
//                break;
//            case R.id.iv_wb:
//                type = "2";
//                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
//                break;
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //授权成功
            presenter.authLogin(data,type);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    private Runnable timeTask = new Runnable() {
        @Override
        public void run() {
            for (int i = 60; i >= 0; i--) {
                try {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (btn_register_send_code != null) {
                        if (msg.arg1 > 0) {
                            btn_register_send_code.setTextColor(getResources().getColor(R.color.colorBlackHint));
                            btn_register_send_code.setBackgroundResource(R.drawable.bg_rect_stroke_new3);
                            btn_register_send_code.setClickable(false);
                            btn_register_send_code.setText(msg.arg1 + "S");
                        } else {
                            btn_register_send_code.setTextColor(getResources().getColor(R.color.colorBlackText));
                            btn_register_send_code.setBackgroundResource(R.drawable.bg_rect_stroke_new);
                            btn_register_send_code.setText("获取验证码");
                            btn_register_send_code.setClickable(true);
                        }
                    }
                    break;

            }
        }
    };

    @Override
    public void update(Observable o, Object arg) {
        if("loginAndRegist".equals(arg)){
            UserAccount userAccount = presenter.model.getUserAccount();
            if(userAccount!=null){
                if(!userAccount.isNeedBindParentUser()) {
                    //已绑定上级
                    BBCUtil.syncCookie(LoginActivity.this);
                    if (userAccount != null) {
                        if (BBCUtil.isEmpty(userAccount.getNickName())) {
                            SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this, Constants.NICK_NAME, userAccount.getMobile());
                        } else {
                            SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this, Constants.NICK_NAME, userAccount.getNickName());
                        }
                        SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this, Constants.USER_HEADIMG, userAccount.getHeadUrl());
                        SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this, Constants.PHONE_NUMBER, userAccount.getMobile());
                        SuDianApp.getInstance().getSpUtil().putBoolean(LoginActivity.this,  Constants.ISLOGIN, true);
                        SuDianApp.getInstance().getSpUtil().putBoolean(LoginActivity.this, Constants.ISLOGIN_FIRST, true);
                    }
                    CacheUtil.clearOldVersionCache(LoginActivity.this);
                    if("1".equals(goHome)){
                        ActivityUtil.getInstance().start(LoginActivity.this,new Intent(LoginActivity.this,HomeActivity.class));
                    }else {
                        ActivityUtil.getInstance().exitResult(this, getIntent(), 200);
                    }
                }else {
                    //未绑定上级
                    Intent intent=new Intent(LoginActivity.this,InviteCodeActivity.class);
                    ActivityUtil.getInstance().start(LoginActivity.this,intent);
                }
            }
        }else if("authLogin".equals(arg)){
            UserAccount userAccount=presenter.model.getUserAccount();
            if(userAccount!=null){
                if (!userAccount.isIfBindMobile()) {
                    Intent intent = new Intent(LoginActivity.this, BindPhoneActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", presenter.model.getData());
                    intent.putExtras(bundle);
                    ActivityUtil.getInstance().startResult(LoginActivity.this, intent,200);
                } else {
                    BBCUtil.syncCookie(LoginActivity.this);
                    if (BBCUtil.isEmpty(userAccount.getNickName())) {
                        SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this,  Constants.NICK_NAME, userAccount.getMobile());
                    } else {
                        SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this, Constants.NICK_NAME, userAccount.getNickName());
                    }
                    SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this,  Constants.USER_HEADIMG, userAccount.getHeadUrl());
                    SuDianApp.getInstance().getSpUtil().putString(LoginActivity.this,  Constants.PHONE_NUMBER, userAccount.getMobile());
                    SuDianApp.getInstance().getSpUtil().putBoolean(LoginActivity.this,  Constants.ISLOGIN, true);
                    SuDianApp.getInstance().getSpUtil().putBoolean(LoginActivity.this,  Constants.ISLOGIN_FIRST, true);
                    if("1".equals(goHome)){
                        ActivityUtil.getInstance().start(LoginActivity.this,new Intent(LoginActivity.this,HomeActivity.class));
                    }else{
                        ActivityUtil.getInstance().exitResult(LoginActivity.this, getIntent(), 200);
                    }
                }
            }
            CacheUtil.clearOldVersionCache(LoginActivity.this);
        }else if("sendCode".equals(arg)){
            //发送验证码成功
            btn_register_send_code.setBackgroundResource(R.drawable.bg_rect_stroke_new3);
            ToastUtil.show(LoginActivity.this, "短信发送成功！");
            ExecutorServiceUtil.getInstence().execute(timeTask);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(getIntent()!=null&&"1".equals(getIntent().getStringExtra("goHome"))){
            ActivityUtil.getInstance().start(this,new Intent(this,HomeActivity.class));
        }else if (200 == requestCode && resultCode == 200) {
            ActivityUtil.getInstance().exitResult(this, getIntent(), 200);
        }
    }
}
