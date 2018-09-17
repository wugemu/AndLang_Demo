package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bill56 on 2018-9-10.
 */

public class BrandCategoryPresenter extends BaseLangPresenter<BrandCategoryModel> {

    public static final String TAG_CATEGORY_SUCCESS = "TAG_CATEGORY_SUCCESS";

    public BrandCategoryPresenter(BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(activity, modelClass);
    }

    @Override
    public void initModel() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("全部");
        categoryList.add(category);
        category = new Category();
        category.setId(2);
        category.setCategoryName("美妆个护");
        categoryList.add(category);
        category = new Category();
        category.setId(3);
        category.setCategoryName("食品饮料");
        categoryList.add(category);
        category = new Category();
        category.setId(4);
        category.setCategoryName("服饰箱包");
        categoryList.add(category);
        category = new Category();
        category.setId(5);
        category.setCategoryName("母婴玩具");
        categoryList.add(category);
        category = new Category();
        category.setId(6);
        category.setCategoryName("居家百货");
        categoryList.add(category);
        category = new Category();
        category.setId(7);
        category.setCategoryName("国际轻奢");
        categoryList.add(category);
        category = new Category();
        category.setId(8);
        category.setCategoryName("营养保健");
        categoryList.add(category);
        model.setCategoryList(categoryList);
        model.notifyData(TAG_CATEGORY_SUCCESS);
    }
}
