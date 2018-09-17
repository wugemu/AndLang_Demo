package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.BaseLangUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Brand;

import java.util.List;

/**
 * Created by 1 on 2018/7/5.
 */

public class AHBrandAdapter extends BaseLangAdapter<Brand> {

    private int count;

    public AHBrandAdapter(Activity context, List<Brand> mDatas) {
        super(context, R.layout.gridview_brand_item,mDatas);
        if(mDatas.size()>12){
            count=12;
        }
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();

    }

    @Override
    public int getCount()
    {
        if(count==0){
            return super.getCount();
        }else{
            return count;
        }
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, final Brand item) {
        helper.setText(R.id.tv_brand_name, BaseLangUtil.getLenthTxt(item.getBrandName(),5));
//        helper.setText(R.id.tv_brand_name,"哈啊啊啊啊额");
        ImageView ivLogo=helper.getView(R.id.iv_brand_logo);
        // ImageLoader.getInstance().displayImage(item.getBrandLogo(),ivLogo, FarmApplication.BOUTIQUE_OPTIPON);
       /* helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BrandProductActivity.class);
                intent.putExtra("brand",item);
                intent.putExtra("type","1");
                ActivityUtil.getInstance().start((Activity) context,intent);
            }
        });*/

    }
}
