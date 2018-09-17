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
import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.model.local.LoginModel;
import com.nyso.sudian.presenter.LoginPresenter;

import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class SexActivity extends BaseLangActivity<LoginPresenter> {
    @BindView(R.id.tv_sex_boy)
    TextView tv_sex_boy;
    @BindView(R.id.iv_sex_boy)
    ImageView iv_sex_boy;
    @BindView(R.id.tv_sex_gril)
    TextView tv_sex_gril;
    @BindView(R.id.iv_sex_gril)
    ImageView iv_sex_gril;

    private int sextype=0;//1男 0女 默认0

    @Override
    public int getLayoutId() {
        return R.layout.activity_sex;
    }

    @Override
    public void initView() {
        initLoading();
    }

    @Override
    public void initPresenter() {
        presenter=new LoginPresenter(SexActivity.this, LoginModel.class);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ll_sex_boy)
    public void clickBoy(){
        if(sextype==1){
            return;
        }
        sextype=1;
        tv_sex_boy.setTextColor(getResources().getColor(R.color.colorBlackText3));
        iv_sex_boy.setImageResource(R.mipmap.checked);
        tv_sex_gril.setTextColor(getResources().getColor(R.color.colorBlackHint));
        iv_sex_gril.setImageResource(R.mipmap.uncheck);
    }
    @OnClick(R.id.ll_sex_gril)
    public void clickGril(){
        if(sextype==0){
            return;
        }
        sextype=0;
        tv_sex_boy.setTextColor(getResources().getColor(R.color.colorBlackHint));
        iv_sex_boy.setImageResource(R.mipmap.uncheck);
        tv_sex_gril.setTextColor(getResources().getColor(R.color.colorBlackText3));
        iv_sex_gril.setImageResource(R.mipmap.checked);
    }

    @OnClick(R.id.btn_usersex)
    public void goNext(){
        //下一步
        showWaitDialog();
        presenter.updateSex(sextype);
    }

    @Override
    public void update(Observable o, Object arg) {
        if("updateSex".equals(arg)){
            Intent intent=new Intent(SexActivity.this,HeadImgActivity.class);
            ActivityUtil.getInstance().start(SexActivity.this,intent);
        }
    }

    @Override
    protected void goBack() {
        //不可以返回
    }
}
