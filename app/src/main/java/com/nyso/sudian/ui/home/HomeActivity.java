package com.nyso.sudian.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.StatusBarUtils;
import com.example.test.andlang.util.ToastUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.api.SysVer;
import com.nyso.sudian.model.api.UserLablePopup;
import com.nyso.sudian.model.local.MainModel;
import com.nyso.sudian.myinterface.HomeI;
import com.nyso.sudian.presenter.MainPresenter;
import com.nyso.sudian.ui.login.LoginActivity;
import com.nyso.sudian.ui.widget.NavRadioButton;
import com.nyso.sudian.ui.widget.dialog.HomeDialog;
import com.nyso.sudian.ui.widget.dialog.NewUserDialog;
import com.nyso.sudian.ui.widget.dialog.UpdateDialog;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;
import com.nyso.sudian.util.JsonParseUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseLangActivity<MainPresenter> implements HomeI{
    @BindView(R.id.mContainer)
    FrameLayout mContainer;
    // 导航按钮
    @BindView(R.id.rb_home_index1)
    NavRadioButton rbIndex1;
    @BindView(R.id.rb_home_index2)
    NavRadioButton rbIndex2;
    @BindView(R.id.rb_home_index3)
    RadioButton rbIndex3;
    @BindView(R.id.tv_bottom_player)
    TextView tv_bottom_player;
    @BindView(R.id.rb_home_index4)
    NavRadioButton rbIndex4;
    @BindView(R.id.rb_home_index5)
    NavRadioButton rbIndex5;
    @BindView(R.id.m_statusBar)
    View mStatusBar;

    private String currentF;
    public final static String KEY_HOME = "home";//首页
    public final static String KEY_BRAND= "brand";//品牌
    public final static String KEY_PLAY = "play";//玩主
    public final static String KEY_CART = "cart";//购物车
    public final static String KEY_NYSO = "nyso";//我的

    private Map<String, BaseLangFragment> fragments = new HashMap<String, BaseLangFragment>();
    private HomeFragment homeF;
    private BrandFragment brandF;
    private PlayFragment playF;
    private CartFragment cartF;
    private NysoFragment nysoF;
    public FragmentManager fManager;
    private String type;
    private String result;//启动页传过来的参数
    private Subject subject;
    private SysVer version;
    private UserLablePopup userLablePopup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
    }

    @Override
    public void initView() {
        ActivityUtil.getInstance().finishBeforActivity();
        fManager = getSupportFragmentManager();
    }

    @Override
    public void initPresenter() {
        presenter=new MainPresenter(HomeActivity.this, MainModel.class);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            type = intent.getStringExtra("type");
            result = intent.getStringExtra("result");
        }

        if (!BBCUtil.isEmpty(result)) {
            try {
                version = JsonParseUtil.parseVersion(JsonParseUtil.getStringValue(result, "sys"));
                userLablePopup = JsonParseUtil.getUserLablePopup(JsonParseUtil.getStringValue(result, "userLablePopup"));
                subject = JsonParseUtil.parseSubject(JsonParseUtil.getStringValue(result, "subject"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (BBCUtil.checkUpdate(this, version)) {//有版本更新弹版本更新
            new UpdateDialog(this, version);
        } else if (subject != null) {//新人有礼
            if (BBCUtil.isLogin(this)) {
                //显示弹框
                new NewUserDialog(this, subject);
            } else {
                if (BBCUtil.isDialogShow(this)) {//显示弹框
                    new NewUserDialog(this, subject);
                    BBCUtil.saveShowDialogTime(this);
                }
            }
        } else if (userLablePopup != null) {//福利
            new HomeDialog(this, userLablePopup);
        }
        //首页跳转
        if (!BBCUtil.isEmpty(type)) {

        }

        rbIndex1.performClick();
    }

    @OnClick({R.id.rl_home, R.id.rl_brand,R.id.rl_play, R.id.rl_cart, R.id.rl_me,
            R.id.rb_home_index1, R.id.rb_home_index2, R.id.rb_home_index3, R.id.rb_home_index4, R.id.rb_home_index5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
            case R.id.rb_home_index1:
                btn1Click();
                break;
            case R.id.rl_brand:
            case R.id.rb_home_index2:
                btn2Click();
                break;
            case R.id.rl_play:
            case R.id.rb_home_index3:
                btn3Click();
                break;
            case R.id.rl_cart:
            case R.id.rb_home_index4:
                btn4Click();
                break;
            case R.id.rl_me:
            case R.id.rb_home_index5:
                btn5Click();
                break;
        }
    }

    // 首页被点击
    private void btn1Click() {
        mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarUtils.translateStatusBar(this);
            mStatusBar.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
            layoutParams.height = StatusBarUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(layoutParams);
            mStatusBar.setBackgroundResource(R.color.colorBlackDark);
            StatusBarUtils.setTextColorStatusBar(this, false);
        }
        rbIndex1.setChecked(true);
        rbIndex2.setChecked(false);
        rbIndex3.setChecked(false);
        rbIndex4.setChecked(false);
        rbIndex5.setChecked(false);
        rbIndex1.setTextColor(getResources().getColor(R.color.colorBlackText));
        rbIndex2.setTextColor(getResources().getColor(R.color.colorBlackText2));
        tv_bottom_player.setTextColor(getResources().getColor(R.color.colorBlackText2));
        rbIndex4.setTextColor(getResources().getColor(R.color.colorBlackText2));
        rbIndex5.setTextColor(getResources().getColor(R.color.colorBlackText2));
        if (homeF == null) {
            homeF = new HomeFragment();
            Bundle bundle = new Bundle();
            homeF.setArguments(bundle);
            fragments.put(KEY_HOME, homeF);
            fManager.beginTransaction().add(R.id.mContainer, homeF, KEY_HOME)
                    .commit();

        } else if (BBCUtil.isLoginFirst(this)) {
            presenter.reqVersion();
        }
        setFragmentVisiable(KEY_HOME);
    }

    // 品牌街被点击
    private void btn2Click() {
        mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarUtils.translateStatusBar(this);
            mStatusBar.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
            layoutParams.height = StatusBarUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(layoutParams);
            mStatusBar.setBackgroundResource(R.color.colorWhite);
            StatusBarUtils.setTextColorStatusBar(this, true);
        }
        rbIndex1.setChecked(false);
        rbIndex2.setChecked(true);
        rbIndex3.setChecked(false);
        rbIndex4.setChecked(false);
        rbIndex5.setChecked(false);
        rbIndex1.setTextColor(getResources().getColor(R.color.colorBlackText2));
        rbIndex2.setTextColor(getResources().getColor(R.color.colorBlackText));
        tv_bottom_player.setTextColor(getResources().getColor(R.color.colorBlackText2));
        rbIndex4.setTextColor(getResources().getColor(R.color.colorBlackText2));
        rbIndex5.setTextColor(getResources().getColor(R.color.colorBlackText2));
        if (brandF == null) {
            brandF = new BrandFragment();
            fragments.put(KEY_BRAND, brandF);
            fManager.beginTransaction().add(R.id.mContainer, brandF, KEY_BRAND)
                    .commit();
        }
        setFragmentVisiable(KEY_BRAND);
    }

    // 玩主被点击
    private void btn3Click() {
        if (BBCUtil.isLogin(this)) {
            mStatusBar.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= 21) {
                StatusBarUtils.translateStatusBar(this);
                mStatusBar.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
                layoutParams.height = StatusBarUtils.getStatusHeight(this);
                mStatusBar.setLayoutParams(layoutParams);
                mStatusBar.setBackgroundResource(R.color.colorWhite);
                StatusBarUtils.setTextColorStatusBar(this, true);
            }
            rbIndex1.setChecked(false);
            rbIndex2.setChecked(false);
            rbIndex3.setChecked(true);
            rbIndex4.setChecked(false);
            rbIndex5.setChecked(false);
            rbIndex1.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex2.setTextColor(getResources().getColor(R.color.colorBlackText2));
            tv_bottom_player.setTextColor(getResources().getColor(R.color.colorBlackText));
            rbIndex4.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex5.setTextColor(getResources().getColor(R.color.colorBlackText2));
            if (playF == null) {
                playF = new PlayFragment();
                fragments.put(KEY_PLAY, playF);
                fManager.beginTransaction().add(R.id.mContainer, playF, KEY_PLAY)
                        .commit();
            }
            setFragmentVisiable(KEY_PLAY);
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.putExtra("target", KEY_PLAY);
            ActivityUtil.getInstance().startResult(HomeActivity.this, intent, Constants.LOGIN_REQUEST);
        }

    }

    // 购物车被点击
    private void btn4Click() {
        if (BBCUtil.isLogin(this)) {
            mStatusBar.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= 21) {
                StatusBarUtils.translateStatusBar(this);
                mStatusBar.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
                layoutParams.height = StatusBarUtils.getStatusHeight(this);
                mStatusBar.setLayoutParams(layoutParams);
                mStatusBar.setBackgroundResource(R.color.colorWhite);
                StatusBarUtils.setTextColorStatusBar(this, true);
            }
            rbIndex1.setChecked(false);
            rbIndex2.setChecked(false);
            rbIndex3.setChecked(false);
            rbIndex4.setChecked(true);
            rbIndex5.setChecked(false);
            rbIndex1.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex2.setTextColor(getResources().getColor(R.color.colorBlackText2));
            tv_bottom_player.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex4.setTextColor(getResources().getColor(R.color.colorBlackText));
            rbIndex5.setTextColor(getResources().getColor(R.color.colorBlackText2));
            if (cartF == null) {
                cartF = new CartFragment();
                cartF.setHomeI(this);
                fragments.put(KEY_CART, cartF);
                fManager.beginTransaction().add(R.id.mContainer, cartF, KEY_CART)
                        .commit();
            }
            setFragmentVisiable(KEY_CART);
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.putExtra("target", KEY_CART);
            ActivityUtil.getInstance().startResult(HomeActivity.this, intent, Constants.LOGIN_REQUEST);
        }

    }

    // 我的被点击
    private void btn5Click() {
        if (BBCUtil.isLogin(this)) {
            mStatusBar.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            rbIndex1.setChecked(false);
            rbIndex2.setChecked(false);
            rbIndex3.setChecked(false);
            rbIndex4.setChecked(false);
            rbIndex5.setChecked(true);
            rbIndex1.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex2.setTextColor(getResources().getColor(R.color.colorBlackText2));
            tv_bottom_player.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex4.setTextColor(getResources().getColor(R.color.colorBlackText2));
            rbIndex5.setTextColor(getResources().getColor(R.color.colorBlackText));
            if (nysoF == null) {
                nysoF = new NysoFragment();
                fragments.put(KEY_NYSO, nysoF);
                fManager.beginTransaction().add(R.id.mContainer, nysoF, KEY_NYSO)
                        .commit();
            }
            setFragmentVisiable(KEY_NYSO);
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.putExtra("target", KEY_NYSO);
            ActivityUtil.getInstance().startResult(HomeActivity.this, intent, Constants.LOGIN_REQUEST);
        }

    }

    /**
     * 设置某个fragment显示
     */
    public void setFragmentVisiable(String key) {
        currentF = key;
        if (fManager == null) {
            fManager = getSupportFragmentManager();
        }

        if (!key.equals(KEY_NYSO) && nysoF != null) {
            Fragment fm = fManager.findFragmentByTag(KEY_NYSO);
            if (fm != null) {
                fManager.beginTransaction().remove(fm)
                        .commit();
            }
            nysoF = null;
        }
        if (!key.equals(KEY_CART) && cartF != null) {
            Fragment fm = fManager.findFragmentByTag(KEY_CART);
            if (fm != null) {
                fManager.beginTransaction().remove(fm)
                        .commit();
            }
            cartF = null;
        }

        Iterator<String> keys = fragments.keySet().iterator();
        while (keys.hasNext()) {
            String currKey = keys.next();
            Fragment fm = fManager.findFragmentByTag(currKey);
            if (fm != null) {
                fManager.beginTransaction().hide(fm).commit();
            }
        }
        Fragment fm = fManager.findFragmentByTag(key);
        if (fm != null) {
            fManager.beginTransaction().show(fm).commit();// 显示这个fragment
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            presenter.reqCartNum();//获取购物车数量
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("UPDATE_CART_NUM"));
        presenter.reqCartNum();//获取购物车数量
        if(BBCUtil.isLoginFirst(this)&&KEY_HOME.equals(currentF)){
            SuDianApp.getInstance().getSpUtil().putBoolean(this, Constants.ISLOGIN_FIRST,false);
            presenter.reqVersion();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.LOGIN_REQUEST && resultCode == 200) {
            String target = data.getStringExtra("target");
            if(KEY_PLAY.equals(target)){
                btn3Click();
            } else if (KEY_CART.equals(target)) {
                btn4Click();
            } else if (KEY_NYSO.equals(target)) {
                btn5Click();
            }
        } else {
            if (KEY_HOME.equals(currentF)) {
                btn1Click();
            } else if (KEY_BRAND.equals(currentF)) {
                btn2Click();
            }
        }


    }

    @Override
    public void click(int i) {
        switch (i) {
            case 1:
                btn1Click();
                break;
            case 2:
                btn2Click();
                break;
            case 3:
                btn3Click();
                break;
            case 4:
                btn4Click();
                break;
            case 5:
                btn4Click();
                break;
        }
    }

    private long mkeyTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                ToastUtil.show(this, R.string.tip_exit);
            } else {
                SuDianApp.getInstance().getSpUtil().putBoolean(this,
                         Constants.EXIT, true);
                ActivityUtil.getInstance().finishAllActivity();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        if("reqVersion".equals(arg)){
            userLablePopup=presenter.model.getUserLablePopup();
            if (userLablePopup != null) {
                new HomeDialog(HomeActivity.this, userLablePopup);
            }
        }
    }
}
