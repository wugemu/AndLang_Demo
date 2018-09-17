package com.nyso.sudian.ui.brand;


import android.app.Activity;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahtrun.mytablayout.MyFragmentPagerAdapter;
import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.ui.widget.OrderFliterPopupWindow;
import com.nyso.sudian.ui.widget.ProxyDrawable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryProductLsitActivity extends BaseLangActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.pager_content)
    ViewPager viewPager;

    private Category category;
    private Fragment[] fragments;
    private MyFragmentPagerAdapter adapter;
    private OrderFliterPopupWindow popupWindow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_category_product_lsit;
    }

    @Override
    public void initView() {
//        category = (Category) getIntent().getExtras().get("category");
        category = new Category();
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Category cls = new Category();
            cls.setThreeName("分类" + i);
            categoryList.add(cls);
        }
        category.setThreeCategoryList(categoryList);
        category.setCategoryName("美妆个护");
        initTitleBar(true, category.getCategoryName(), R.mipmap.search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        fragments = new Fragment[category.getThreeCategoryList().size()];
        String[] mTitles = new String[]{"全部", "美妆", "面膜", "美妆", "补水", "护肤", "美白保养", "补了水", "护肤", "护肤"};
        fragments=new ClassifyCategoryFragment[category.getThreeCategoryList().size()];
        for (int i=0;i<fragments.length;i++){
            tabLayout.addTab(tabLayout.newTab());
//            mTitles[i]=category.getThreeCategoryList().get(i).getThreeName();
            fragments[i] = new CategoryFragment();
        }
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        adapter.setmTitles(mTitles);
        viewPager.addOnPageChangeListener(pageChangeListener);
        tabLayout.setupWithViewPager(viewPager);
        reflex(this);
        select(0);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeDisplay(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void changeDisplay(final int postion) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void select(int position) {
        if (tabLayout != null && position < tabLayout.getTabCount()) {
            tabLayout.getTabAt(position).select();
            changeDisplay(position);
        }
    }

    public void reflex(final Activity activity) {
        if (tabLayout != null) {
            //了解源码得知 线的宽度是根据 tabView的宽度来设置的
            tabLayout.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        //拿到tabLayout的mTabStrip属性
                        LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                        mTabStrip.setBackground(new ProxyDrawable(mTabStrip));

                        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                            View tabView = mTabStrip.getChildAt(i);

                            //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                            Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                            mTextViewField.setAccessible(true);

                            TextView mTextView = (TextView) mTextViewField.get(tabView);

                            tabView.setPadding(0, 0, 0, 0);

                            //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                            int width = 0;
                            width = mTextView.getWidth();
                            if (width == 0) {
                                mTextView.measure(0, 0);
                                width = mTextView.getMeasuredWidth();
                            }

                            //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
//                        int screenWidth= BBCUtil.getDisplayWidth(OrderListActivity.this);
                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                            params.width = width;
                            int margin = (int) getResources().getDimension(R.dimen.view_15dp);

                            params.leftMargin = margin;
                            params.rightMargin = margin;
                            tabView.setLayoutParams(params);

                            tabView.invalidate();
                        }

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    @OnClick(R.id.iv_select)
    public void onViewClicked() {
        if (popupWindow == null) {
            popupWindow = new OrderFliterPopupWindow(this, handler);

        }
        popupWindow.setType(1);
        popupWindow.showWindow(viewPager);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
        }
    };


}
