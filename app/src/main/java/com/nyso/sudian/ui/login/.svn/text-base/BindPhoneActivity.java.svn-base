package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

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
import com.nyso.sudian.ui.widget.CleanableEditText;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;

import java.util.Map;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseLangActivity<LoginPresenter> {
    @BindView(R.id.ce_bind_mobile)
    CleanableEditText ce_bind_mobile;
    @BindView(R.id.ce_bind_code)
    CleanableEditText ce_bind_code;
    @BindView(R.id.btn_register_send_code)
    Button btn_register_send_code;
    @BindView(R.id.btn_bindphone)
    Button btn_bindphone;

    private Map<String, String> data;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView() {
        initTitleBar(true,"绑定手机号码");
        initLoading();
        ce_bind_mobile.addTextChangedListener(new TextWatcher() {
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
        ce_bind_code.addTextChangedListener(new TextWatcher() {
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

    public void changeButtonState(){
        if(!BBCUtil.isEmpty(ce_bind_mobile.getText().toString().trim())&&!BBCUtil.isEmpty(ce_bind_code.getText().toString().trim())){
            btn_bindphone.setBackgroundResource(R.drawable.bg_rect_new);
            btn_bindphone.setEnabled(true);
        }else {
            btn_bindphone.setBackgroundResource(R.drawable.bg_rect_new3);
            btn_bindphone.setEnabled(false);
        }
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(BindPhoneActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            data = (Map<String, String>) intent.getSerializableExtra("data");
        }
    }

    @OnClick(R.id.btn_bindphone)
    public void goBindPhone(){
        if (ButtonUtil.isFastDoubleClick(R.id.btn_bindphone)) {
            ToastUtil.show(this, R.string.tip_btn_fast);
            return;
        }
        String loginname = ce_bind_mobile.getText().toString().trim();
        String code = ce_bind_code.getText().toString().trim();
        if (TextUtils.isEmpty(loginname)) {
            ToastUtil.show(this, "请输入手机号");
            return;
        }
        showWaitDialog();
        presenter.updateMobile(loginname,code,data);
    }

    @OnClick(R.id.btn_register_send_code)
    public void sendCode() {
        //发送验证码
        String mobile=ce_bind_mobile.getText().toString().trim();
        if (!BaseLangUtil.isMobile(mobile)) {
            ToastUtil.show(this, "请输入正确的手机号码");
            return;
        }
        showWaitDialog();
        presenter.sendCode(mobile);
    }
    @Override
    public void update(Observable o, Object arg) {
        if("updateMobile".equals(arg)){
            ToastUtil.show(BindPhoneActivity.this, "绑定成功");
            UserAccount userAccount =presenter.model.getUserAccount();
            if(userAccount!=null){
                if(!userAccount.isNeedBindParentUser()){
                    //已绑定上级
                    BBCUtil.syncCookie(BindPhoneActivity.this);
                    if (BBCUtil.isEmpty(userAccount.getNickName())) {
                        SuDianApp.getInstance().getSpUtil().putString(BindPhoneActivity.this, Constants.NICK_NAME, userAccount.getMobile());
                    } else {
                        SuDianApp.getInstance().getSpUtil().putString(BindPhoneActivity.this, Constants.NICK_NAME, userAccount.getNickName());
                    }
                    SuDianApp.getInstance().getSpUtil().putString(BindPhoneActivity.this, Constants.USER_HEADIMG, userAccount.getHeadUrl());
                    SuDianApp.getInstance().getSpUtil().putString(BindPhoneActivity.this,  Constants.PHONE_NUMBER, userAccount.getMobile());
                    SuDianApp.getInstance().getSpUtil().putBoolean(BindPhoneActivity.this, Constants.ISLOGIN, true);
                    ActivityUtil.getInstance().exitResult(BindPhoneActivity.this, getIntent(), 200);
                }else {
                    //未绑定上级
                    Intent intent=new Intent(BindPhoneActivity.this,InviteCodeActivity.class);
                    intent.putExtra("isWXLogin",true);
                    ActivityUtil.getInstance().start(BindPhoneActivity.this,intent);
                }

            }
        }else if("sendCode".equals(arg)){
            //发送验证码成功
            btn_register_send_code.setBackgroundResource(R.drawable.bg_rect_stroke_new3);
            ToastUtil.show(BindPhoneActivity.this, "短信发送成功！");
            ExecutorServiceUtil.getInstence().execute(timeTask);
        }
    }
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
}
