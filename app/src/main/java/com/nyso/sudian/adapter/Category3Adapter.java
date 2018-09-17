package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;


import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Category;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 1 on 2017/2/23.
 * 三级分类
 */

public class Category3Adapter extends BaseLangAdapter<Category> {

    public Category3Adapter(Activity context, List<Category> mDatas) {
        super(context, R.layout.listview_category_3,mDatas);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, final Category item) {
        helper.setText(R.id.tv_category,item.getThreeName());
        ImageView imageView=helper.getView(R.id.iv_category);
        ImageLoadUtils.doLoadCircleImageUrl(imageView,item.getImgUrl());

        /*helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 暂时注释以下代码
//                MobclickAgent.onEvent(mContext,"thirdcategory_"+item.getId()+"_click");
                Intent intent=new Intent(context, ProductListActivity.class);
                HashMap<String,Object> map=new HashMap<String, Object>();
                map.put("threeCategory",item.getId());
                intent.putExtra("title",item.getThreeName());
                intent.putExtra("actionParams",map);
                ActivityUtil.getInstance().start(context,intent);
            }
        });*/
    }
}
