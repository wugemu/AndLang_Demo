package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.InvitePeopleAdapter;
import com.nyso.sudian.model.api.UserDetail;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;
import com.nyso.sudian.ui.widget.MyListView;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnItemClick;

public class InvitePeopleActivity extends BaseLangActivity<LoginPresenter> {
    @BindView(R.id.lv_invitepeople)
    MyListView lv_invitepeople;

    private InvitePeopleAdapter adapter;
    private boolean isWXLogin;
    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_people;
    }

    @Override
    public void initView() {
        initTitleBar(true,"其他推荐人");
        initLoading();
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(InvitePeopleActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null) {
            isWXLogin = intent.getBooleanExtra("isWXLogin", false);
        }
        showWaitDialog();
        presenter.reqInvitePList();
    }

    @OnItemClick(R.id.lv_invitepeople)
    public void bindInviteCode(int position){
        UserDetail userDetail=adapter.getItem(position);
        String inviteCode=userDetail.getRandomCode();
        showWaitDialog();
        presenter.reqInvitePInfo(inviteCode);
    }

    @Override
    public void update(Observable o, Object arg) {
        if("reqInvitePList".equals(arg)){
            List<UserDetail> modelList=presenter.model.getUserDetailList();
            adapter=new InvitePeopleAdapter(InvitePeopleActivity.this,modelList);
            lv_invitepeople.setAdapter(adapter);
        }else if("reqInvitePInfo".equals(arg)){
            UserDetail userDetail=presenter.model.getUserDetail();
            if(userDetail!=null){
                Intent intent=new Intent(InvitePeopleActivity.this,BindUserinfoActivity.class);
                intent.putExtra("isWXLogin",isWXLogin);
                Bundle bundle = new Bundle();
                bundle.putSerializable("UserDetail",userDetail);
                intent.putExtras(bundle);
                ActivityUtil.getInstance().start(InvitePeopleActivity.this,intent);
            }
        }
    }
}
