
package com.nyso.sudian.ui.brand;

import android.widget.ListView;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.Category2Adapter;
import com.nyso.sudian.adapter.CategoryPresenter;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.local.CategoryModel;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * Created by Bill56 on 2018-9-11.
 */

public class ClassifyCategoryFragment extends BaseLangFragment<CategoryPresenter> {

    @BindView(R.id.lv_class)
    ListView lvClass;
    private Category2Adapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify_category;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        presenter = new CategoryPresenter(this,activity, CategoryModel.class);
        presenter.model.setCategoryId(getArguments().getInt("categoryId", 0) + "");

    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(CategoryPresenter.TAG_CATEGORY2_SUCCESS.equals(arg) && (o instanceof CategoryModel)) {
            CategoryModel model = (CategoryModel) o;
            List<Category> categoryList = model.getCategoryList();
            adapter=new Category2Adapter(activity,categoryList);
            lvClass.setAdapter(adapter);
        }
    }
}
