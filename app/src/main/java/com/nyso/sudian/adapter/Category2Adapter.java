package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.ui.widget.CustomGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/3/21.
 * 二级分类
 */

public class Category2Adapter extends BaseLangAdapter<Category> {

    public Category2Adapter(Activity context, List mDatas) {
        super(context, R.layout.listview_category_2,mDatas);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, Category item) {
        CustomGridView gridView=helper.getView(R.id.gv_category_list);
        helper.setText(R.id.tv_class,item.getName());
        List<Category> showList = new ArrayList<>();
        if(item.isShowMore()) {
            // 显示加载更多
            for(int i=0;i<item.getThreeCategoryList().size();i++) {
                if(i<8){
                    showList.add(item.getThreeCategoryList().get(i));
                }
            }
            Category moreCategory = new Category();
            showList.add(moreCategory);
        } else {
            // 不显示
            showList.addAll(item.getThreeCategoryList());
        }
        Category3Adapter category3Adapter = new Category3Adapter(context, showList);
        gridView.setAdapter(category3Adapter);
    }

}
