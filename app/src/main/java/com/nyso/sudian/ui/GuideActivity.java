package com.nyso.sudian.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.widget.common.ViewPagerAdapter;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;

import java.util.ArrayList;
import java.util.Observable;

import butterknife.BindView;

public class GuideActivity extends BaseLangActivity implements
        ViewPager.OnPageChangeListener {

    // 定义ViewPager对象
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.btn_start)
    ImageView btnStart;

    // 定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;

    // 定义一个ArrayList来存放View
    private ArrayList<View> views;
    // 底部小点的图片
    private ImageView[] points;

    // 记录当前选中位置
    private int currentIndex=-1;
    private LinearLayout linearLayout;
    // 引导图片资源
    private static final int[] pics = {R.mipmap.load_image1, R.mipmap.load_image2,
            R.mipmap.load_image3, R.mipmap.load_image4};

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        // 实例化ArrayList对象
        views = new ArrayList<View>();
        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(views);
        btnStart.setVisibility(View.GONE);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuDianApp.getInstance().getSpUtil().putString(GuideActivity.this,
                         Constants.VERSION, BBCUtil.getVersionName(GuideActivity.this));
                Intent intent = getIntent();
                intent.setClass(GuideActivity.this,HomeActivity.class);
                ActivityUtil.getInstance().start(GuideActivity.this,intent);
                ActivityUtil.getInstance().exit(GuideActivity.this);
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(pics[i]);
            iv.setBackgroundColor(Color.WHITE);
            views.add(iv);
        }

        // 设置数据
        viewPager.setAdapter(vpAdapter);
        // 设置监听
        viewPager.addOnPageChangeListener(GuideActivity.this);
        // 初始化底部小点
        initPoint();
    }

    /**
     * 初始化底部小点
     */
    private void initPoint() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        points = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            // 默认都设为灰色
            points[i].setEnabled(true);
            // 给每个小点设置监听
//            points[i].setOnClickListener(GuideActivity.this);
            // 设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        // 设置当面默认的位置
        currentIndex = 0;
        // 设置为白色，即选中状态
        points[currentIndex].setEnabled(false);
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     * 当滑动状态改变时调用
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    /**
     * 当当前页面被滑动时调用
     */

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }


    /**
     * 当新的页面被选中时调用
     */

    @Override
    public void onPageSelected(int position) {
        // 设置底部小点选中状态
        setCurDot(position);
        if(position==pics.length-1){
            btnStart.setVisibility(View.VISIBLE);
        }else{
            btnStart.setVisibility(View.GONE);
        }

    }



    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position) {
        // 排除异常情况
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon) {
        // 排除异常情况
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }
        points[positon].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = positon;

        if (positon == pics.length - 1) {
            btnStart.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        } else {
            btnStart.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }

    }

}
