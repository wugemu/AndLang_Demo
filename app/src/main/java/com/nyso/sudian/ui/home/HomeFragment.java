package com.nyso.sudian.ui.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.ahtrun.mytablayout.HomeTablayout;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.myinterface.ClassSel;
import com.nyso.sudian.ui.home.homeFragment.ClassFragment;
import com.nyso.sudian.ui.home.homeFragment.EDayNewFragment;
import com.nyso.sudian.ui.home.homeFragment.TodaySaleFragment;
import com.nyso.sudian.ui.widget.ClassPopWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseLangFragment {
    @BindView(R.id.ct_home_layout)
    HomeTablayout ct_order_layout;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    private BaseLangFragment[] mFragments;
    private ClassPopWindow classPopWindow;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mFragments = new BaseLangFragment[6];
        for (int i = 0; i < 6; i++) {
            if(i==0){
                mFragments[i] = new TodaySaleFragment();
            }else if(i==1){
                mFragments[i] = new EDayNewFragment();
            }else {
                mFragments[i] = new ClassFragment();
            }
        }
        ct_order_layout.init(TabLayout.MODE_SCROLLABLE, mFragments, new String[]{"今日秒杀", "每日上新", "美妆个护", "食品饮料", "服饰箱包", "母婴玩具"}, getChildFragmentManager());
        ct_order_layout.reflex(activity);
        ct_order_layout.select(0);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ll_updown)
    public void showClassWindow(){
        if (classPopWindow == null) {
            classPopWindow = new ClassPopWindow(activity, getCategoryList(), new ClassSel() {
                @Override
                public void selectOk(int postion, Category category) {

                }
            });

        }
        classPopWindow.showWindow(ll_top);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public List<Category> getCategoryList(){
        List<Category> categoryList=new ArrayList<Category>();
        for (int i=0;i<11;i++){
            Category category = new Category();
            category.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            category.setThreeName("分类");
            categoryList.add(category);
        }
        Category moreCategory = new Category();
        categoryList.add(moreCategory);
        return categoryList;
    }
}
