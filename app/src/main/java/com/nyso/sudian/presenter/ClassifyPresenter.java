package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.local.ClassifyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bill56 on 2018-9-11.
 */

public class ClassifyPresenter extends BaseLangPresenter<ClassifyModel> {

    public static final String TAG_CLASSIFY1_SUCCESS = "TAG_CLASSIFY1_SUCCESS";

    public ClassifyPresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {
        // 模拟分类
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("品牌墙");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(2);
        category.setCategoryName("美妆个护");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(3);
        category.setCategoryName("食品饮料");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(4);
        category.setCategoryName("服饰箱包");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(5);
        category.setCategoryName("母婴玩具");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(6);
        category.setCategoryName("家居生活");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(7);
        category.setCategoryName("品牌墙");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(8);
        category.setCategoryName("蔬果生鲜");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(9);
        category.setCategoryName("日用家纺");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(10);
        category.setCategoryName("手机数码");
        categoryList.add(category);
        category = new Category();
        category.setCategoryId(11);
        category.setCategoryName("生活服务");
        categoryList.add(category);
        model.setCategoryList(categoryList);
        model.notifyData(TAG_CLASSIFY1_SUCCESS);
    }
}
