package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.UserDetail;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;
import com.nyso.sudian.ui.widget.CleanableEditText;
import com.nyso.sudian.util.BBCUtil;

import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class InviteCodeActivity extends BaseLangActivity<LoginPresenter> {
    @BindView(R.id.ce_invite_code)
    CleanableEditText ce_invite_code;
    @BindView(R.id.btn_invite_code)
    Button btn_invite_code;
    private boolean isWXLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_code;
    }

    @Override
    public void initView() {
        initTitleBar(true,"请输入邀请码");
        initLoading();
        ce_invite_code.addTextChangedListener(new TextWatcher() {
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
        presenter=new LoginPresenter(InviteCodeActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null) {
            isWXLogin = intent.getBooleanExtra("isWXLogin", false);
        }
    }

    @OnClick(R.id.btn_invite_code)
    public void bindInviteCode(){
        //获取邀请用户信息
        showWaitDialog();
        presenter.reqInvitePInfo(ce_invite_code.getText().toString().trim());
    }

    @OnClick(R.id.btn_invite_other)
    public void goInvitePList(){
        //其他推荐人邀请码
        Intent intent=new Intent(InviteCodeActivity.this,InvitePeopleActivity.class);
        intent.putExtra("isWXLogin",isWXLogin);
        ActivityUtil.getInstance().start(InviteCodeActivity.this,intent);
    }
    
    public void changeButtonState(){
        if(!BBCUtil.isEmpty(ce_invite_code.getText().toString().trim())){
            btn_invite_code.setBackgroundResource(R.drawable.bg_rect_new);
            btn_invite_code.setEnabled(true);
        }else {
            btn_invite_code.setBackgroundResource(R.drawable.bg_rect_new3);
            btn_invite_code.setEnabled(false);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if("reqInvitePInfo".equals(arg)){
            UserDetail userDetail=presenter.model.getUserDetail();
            if(userDetail!=null){
                Intent intent=new Intent(InviteCodeActivity.this,BindUserinfoActivity.class);
                intent.putExtra("isWXLogin",isWXLogin);
                Bundle bundle = new Bundle();
                bundle.putSerializable("UserDetail",userDetail);
                intent.putExtras(bundle);
                ActivityUtil.getInstance().start(InviteCodeActivity.this,intent);
            }
        }
    }
}
