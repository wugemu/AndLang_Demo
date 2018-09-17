package com.nyso.sudian.adapter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.local.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bill56 on 2018-9-11.
 */

public class CategoryPresenter extends BaseLangPresenter<CategoryModel> {

    public static final String TAG_CATEGORY2_SUCCESS = "TAG_CATEGORY2_SUCCESS";

    public CategoryPresenter(BaseLangFragment fragment, BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(fragment, activity, modelClass);
    }

    @Override
    public void initModel() {
        List<Category> secondList = new ArrayList<>();
        Category category = new Category();
        category.setName("基础护理");
        List<Category> threeList = new ArrayList<>();
        Category threeCategory = new Category();
        threeCategory.setThreeName("面部清洗");
        threeList.add(threeCategory);
        threeCategory = new Category();
        threeCategory.setThreeName("化妆水");
        threeList.add(threeCategory);
        threeCategory = new Category();
        threeCategory.setThreeName("乳液");
        threeList.add(threeCategory);
        threeCategory = new Category();
        threeCategory.setThreeName("卸妆水");
        threeList.add(threeCategory);
        category.setThreeCategoryList(threeList);
        secondList.add(category);
        category = new Category();
        category.setName("精选品牌");
        category.setShowMore(true);
        List<Category> threeList2 = new ArrayList<>();
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        threeCategory = new Category();
        threeList2.add(threeCategory);
        category.setThreeCategoryList(threeList2);
        secondList.add(category);
        model.setCategoryList(secondList);
        model.notifyData(TAG_CATEGORY2_SUCCESS);
    }
}
