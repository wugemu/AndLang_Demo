package com.nyso.sudian.ui.brand;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.ahtrun.mytablayout.CustomeTablayout;
import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.local.BrandCategoryModel;
import com.nyso.sudian.model.local.BrandCategoryPresenter;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * Created by Bill56 on 2018-9-10.
 */

public class BrandCategoryActivity extends BaseLangActivity<BrandCategoryPresenter> {

    @BindView(R.id.ct_layout)
    CustomeTablayout customeTablayout;
    private BrandListFragment[] fragments;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            outState.remove(FRAGMENTS_TAG);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_category;
    }

    @Override
    public void initView() {
        initTitleBar("品牌");
    }

    @Override
    public void initPresenter() {
        presenter = new BrandCategoryPresenter(this,BrandCategoryModel.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(BrandCategoryPresenter.TAG_CATEGORY_SUCCESS.equals(arg) && o instanceof BrandCategoryModel) {
            BrandCategoryModel model = (BrandCategoryModel) o;
            List<Category> categoryList = model.getCategoryList();
            if (categoryList != null && categoryList.size() > 0) {
                fragments = new BrandListFragment[categoryList.size()];
                String[] titles = new String[categoryList.size()];
                for (int i = 0; i < fragments.length; i++) {
                    fragments[i] = new BrandListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("categoryId", categoryList.get(i).getCategoryId());
                    fragments[i].setArguments(bundle);
                    titles[i] = categoryList.get(i).getCategoryName();
                }

                customeTablayout.init(TabLayout.MODE_SCROLLABLE,fragments,titles,getSupportFragmentManager());
                customeTablayout.reflex(this);
                customeTablayout.select(0);
            }
        }
    }
}
