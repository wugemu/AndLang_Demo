package com.nyso.sudian.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.GlideUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.TagModel;
import com.nyso.sudian.model.api.UserDetail;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.CacheUtil;
import com.nyso.sudian.util.Constants;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class BindUserinfoActivity extends BaseLangActivity<LoginPresenter> {
    @BindView(R.id.mine_image)
    ImageView mine_image;
    @BindView(R.id.tv_userinfo_name)
    TextView tv_userinfo_name;
    @BindView(R.id.iv_userinfo_sex)
    ImageView iv_userinfo_sex;
    @BindView(R.id.tv_userinfo_invitecode)
    TextView tv_userinfo_invitecode;
    @BindView(R.id.tv_userinfo_addr)
    TextView tv_userinfo_addr;
    @BindView(R.id.tv_userinfo_sign)
    TextView tv_userinfo_sign;
    @BindView(R.id.tv_userinfo_tag)
    TextView tv_userinfo_tag;
    @BindView(R.id.tv_userinfo_taglist)
    TextView tv_userinfo_taglist;

    private UserDetail userDetail;
    private boolean isWXLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_userinfo;
    }

    @Override
    public void initView() {
        initTitleBar(true,"TA的主页");
        initLoading();
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(BindUserinfoActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            userDetail=(UserDetail)intent.getSerializableExtra("UserDetail");
            isWXLogin=intent.getBooleanExtra("isWXLogin",false);
        }
        if(userDetail!=null){
            ImageLoadUtils.doLoadCircleImageUrl(mine_image,userDetail.getHeadUrl());
            tv_userinfo_name.setText(userDetail.getNickName());
            if(userDetail.getSex()==0){
                iv_userinfo_sex.setImageResource(R.mipmap.gril_tip);
            }else {
                iv_userinfo_sex.setImageResource(R.mipmap.boy_tip);
            }
            tv_userinfo_invitecode.setText("邀请码:"+userDetail.getInviteCode());
            if(!BBCUtil.isEmpty(userDetail.getAddress())){
                tv_userinfo_addr.setText(userDetail.getAddress());
            }
            if(!BBCUtil.isEmpty(userDetail.getSignature())){
                tv_userinfo_sign.setText(userDetail.getSignature());
            }
            List<TagModel> userTagsList=userDetail.getUserTagsList();
            if(userTagsList!=null&&userTagsList.size()>0){
                String tagStr="";
                for (TagModel model:userTagsList
                        ) {
                    tagStr=tagStr+model.getTagName()+"  ";
                }
                tv_userinfo_taglist.setText(tagStr);
                tv_userinfo_tag.setVisibility(View.GONE);
                tv_userinfo_taglist.setVisibility(View.VISIBLE);
            }else {
                tv_userinfo_tag.setVisibility(View.VISIBLE);
                tv_userinfo_taglist.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.btn_binduser)
    public void goSex(){
        if(userDetail!=null) {
            showWaitDialog();
            presenter.bindInviteP(userDetail.getUserId(),isWXLogin);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if("bindInviteP".equals(arg)){
            //绑定成功登录成功
            SuDianApp.getInstance().getSpUtil().putBoolean(BindUserinfoActivity.this,  Constants.ISLOGIN, true);
            SuDianApp.getInstance().getSpUtil().putBoolean(BindUserinfoActivity.this,  Constants.ISLOGIN_FIRST, true);
            CacheUtil.clearOldVersionCache(BindUserinfoActivity.this);

            Intent intent=new Intent(BindUserinfoActivity.this,SexActivity.class);
            ActivityUtil.getInstance().start(BindUserinfoActivity.this,intent);
        }
    }
}
