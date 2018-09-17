package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.Category;

import java.util.List;

/**
 * Created by Bill56 on 2018-9-10.
 */

public class BrandCategoryModel extends BaseLangViewModel {

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
