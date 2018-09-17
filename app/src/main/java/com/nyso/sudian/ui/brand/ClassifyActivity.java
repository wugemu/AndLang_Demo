package com.nyso.sudian.ui.brand;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.ListView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.Category1Adapter;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.local.ClassifyModel;
import com.nyso.sudian.presenter.ClassifyPresenter;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * Created by Bill56 on 2018-9-11.
 */

public class ClassifyActivity extends BaseLangActivity<ClassifyPresenter> {

    @BindView(R.id.listview)
    ListView listview;
    private ClassifyCategoryFragment[] fragments;
    private Category1Adapter categoryAdapter;
    private FragmentManager fManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_classify;
    }

    @Override
    public void initView() {
        initTitleBar("分类");
        fManager = getSupportFragmentManager();
    }

    @Override
    public void initPresenter() {
        presenter = new ClassifyPresenter(this, ClassifyModel.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(ClassifyPresenter.TAG_CLASSIFY1_SUCCESS.equals(arg) && o instanceof ClassifyModel) {
            ClassifyModel model = (ClassifyModel) o;
            List<Category> categoryList = model.getCategoryList();
            fragments = new ClassifyCategoryFragment[categoryList.size()];
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = new ClassifyCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", categoryList.get(i).getCategoryId());
                fragments[i].setArguments(bundle);
            }
            categoryAdapter = new Category1Adapter(this, categoryList,handler);
            listview.setAdapter(categoryAdapter);
            if (categoryList != null && categoryList.size() > 0) {
                fManager.beginTransaction().replace(R.id.mContainer, fragments[0]).commit();
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//                    categoryId = msg.arg1 + "";
                    fManager.beginTransaction().replace(R.id.mContainer, fragments[msg.arg2]).commit();
                    break;

            }
        }
    };
}
