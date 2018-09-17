package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.ui.country.CountryProductListActivity;
import com.nyso.sudian.util.BBCUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 1 on 2016/2/26.
 * 国家馆横向商品列表
 */
public class CountryShopProductAdapter extends RecyclerView.Adapter<CountryShopProductAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private Activity activity;
    private Subject subject;
    private int width;

    public CountryShopProductAdapter(Activity activity, Subject subject) {
        this.activity = activity;
        this.subject=subject;
        width = (int) ((BBCUtil.getDisplayWidth(activity) - (int) activity.getResources().getDimension(R.dimen.fab_margin) - 3 * (int) activity.getResources().getDimension(R.dimen.view_toview_two)) / 3.5);
        this.inflater = LayoutInflater.from(activity);
    }

    public void setSubject(Subject subject){
        if(subject!=null){
            this.subject=subject;
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.adapter_horizontal_product,
                viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        if(subject!=null){
            if (subject.getGoodsList().size() - 1 != i) {
                myViewHolder.llProduct.setVisibility(View.VISIBLE);
                myViewHolder.view_lastitem.setVisibility(View.GONE);
                final GoodsStandard product = subject.getGoodsList().get(i);
                ImageLoadUtils.doLoadImageUrl(myViewHolder.ivProductImg,product.getImgUrl());
                myViewHolder.tvProductName.setText(product.getGoodsName());
                myViewHolder.tvPrice.setText("￥" + product.getShowPrice());
                LinearLayout.LayoutParams p2 = (LinearLayout.LayoutParams) myViewHolder.ivProductImg.getLayoutParams();
                p2.width = width;
                p2.height = width;
                myViewHolder.ivProductImg.setLayoutParams(p2);
//                if (product.getRealStock() > 0) {
//                    myViewHolder.tvEmpty.setVisibility(View.GONE);
//                } else {
//                    myViewHolder.tvEmpty.setVisibility(View.VISIBLE);
//                }
                myViewHolder.tvOldPrice.setVisibility(View.GONE);
                myViewHolder.llProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, CountryProductListActivity.class);
                        HashMap<String,Object> map=new HashMap<String, Object>();
                        map.put("countryId",subject.getExtendId());
                        intent.putExtra("title",subject.getTitle());
                        intent.putExtra("actionParams",map);
                        ActivityUtil.getInstance().start(activity,intent);

                    }
                });
            } else {
                myViewHolder.llProduct.setVisibility(View.GONE);
                myViewHolder.view_lastitem.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public int getItemCount() {
        if(subject!=null){
            return subject.getGoodsList().size();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product_img)
        ImageView ivProductImg;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_old_price)
        TextView tvOldPrice;
        @BindView(R.id.ll_product)
        LinearLayout llProduct;
        @BindView(R.id.view_lastitem)
        View view_lastitem;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
