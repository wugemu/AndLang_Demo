package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.local.LimitSaleModel;
import com.nyso.sudian.ui.widget.LoadingMoreFootLayout;
import com.nyso.sudian.ui.widget.PredicateLayout;
import com.nyso.sudian.util.BBCUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodaySaleNewAdapter extends RecyclerView.Adapter<TodaySaleNewAdapter.TodaySaleVH> {
    private List<LimitSaleModel> limitSaleModels;//商品列表
    private int type;//1=玩主,0=玩客
    private LayoutInflater inflater;
    private Activity context;
    public TodaySaleNewAdapter(BaseLangActivity activity, List<LimitSaleModel> limitSaleModels,int type){
        this.limitSaleModels=limitSaleModels;
        this.type=type;
        this.context=activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public TodaySaleNewAdapter.TodaySaleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodaySaleNewAdapter.TodaySaleVH(inflater.inflate(R.layout.layout_todaysale_good, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull TodaySaleVH holder, int position) {
        LimitSaleModel limitSaleModel=limitSaleModels.get(position);
        if(limitSaleModel.getType()==LimitSaleModel.TYPE_BANNERSALE){
            holder.layout_todaysale_bannergood.setVisibility(View.VISIBLE);
            holder.layout_todaysale_product.setVisibility(View.GONE);
            //banner 商品列表
            loadBannerGood(holder,limitSaleModel.getSubject());

        }else if(limitSaleModel.getType()==LimitSaleModel.TYPE_GOOD){
            holder.layout_todaysale_bannergood.setVisibility(View.GONE);
            holder.layout_todaysale_product.setVisibility(View.VISIBLE);
            //商品
            loadLimitGood(holder,limitSaleModel.getGood());

        }
    }

    public void loadBannerGood(TodaySaleVH holder, Subject subject){
        int w= BBCUtil.getDisplayWidth(context);
        int h= (int) (w*350.0/750);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(w,h);
        holder.iv_good_banner.setLayoutParams(params);
        ImageLoadUtils.doLoadImageUrl(holder.iv_good_banner,subject.getUrl());

        holder.rv_bannergood=holder.ry_bannergood_list.getRefreshableView();
        holder.ry_bannergood_list.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.ry_bannergood_list.setHasPullDownFriction(true); // 设置没有上拉阻力
        holder.rv_bannergood.setLayoutManager(linearLayoutManager);
        holder.adapter=new CountryShopProductAdapter(context,subject);
        holder.rv_bannergood.setAdapter(holder.adapter);
    }

    public void loadLimitGood(TodaySaleVH limitGoodVH, GoodsStandard goodsStandard){
        //商品信息
        ImageLoadUtils.doLoadImageUrl(limitGoodVH.iv_image, goodsStandard.getImgUrl());
        limitGoodVH.tv_product_name.setText(goodsStandard.getGoodsName());
        limitGoodVH.tv_product_desc.setText(goodsStandard.getDescription());
        limitGoodVH.tv_sale_volume.setText("销量" + goodsStandard.getTotalSales());
        int padding1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1.5f, context.getResources().getDisplayMetrics());
        int padding2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                3, context.getResources().getDisplayMetrics());
        String[] tagList = new String[]{"爆款", "新品", "保税", "清仓"};
        for (int i = 0; i < tagList.length; i++) {
            String key = tagList[i];
            TextView txt = new TextView(context);
            switch (i) {
                case 0:
                    txt.setBackgroundResource(R.drawable.tag_bg_red);
                    txt.setTextColor(context.getResources().getColor(R.color.colorRedMain));
                    break;
                case 1:
                    txt.setBackgroundResource(R.drawable.tag_bg_orange);
                    txt.setTextColor(context.getResources().getColor(R.color.colorOrageText));
                    break;
                case 2:
                    txt.setBackgroundResource(R.drawable.tag_bg_purple);
                    txt.setTextColor(context.getResources().getColor(R.color.purple));
                    break;
                case 3:
                    txt.setBackgroundResource(R.drawable.tag_bg_green);
                    txt.setTextColor(context.getResources().getColor(R.color.colorGreenText));
                    break;
            }

            txt.setText(key);
            txt.setClickable(true);
            txt.setTextSize(10);
            txt.setPadding(padding2, padding1, padding2, padding1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(params);
            limitGoodVH.pl_taglist.addView(txt);
        }

        if (type == 1) {
            limitGoodVH.ll_guest_price.setVisibility(View.GONE);
            limitGoodVH.ll_host_price.setVisibility(View.VISIBLE);
            limitGoodVH.btn_guest_buy.setVisibility(View.GONE);
            limitGoodVH.btn_buy.setVisibility(View.VISIBLE);
            limitGoodVH.btn_share.setVisibility(View.VISIBLE);
        } else {
            limitGoodVH.ll_host_price.setVisibility(View.GONE);
            limitGoodVH.ll_guest_price.setVisibility(View.VISIBLE);
            limitGoodVH.btn_guest_buy.setVisibility(View.VISIBLE);
            limitGoodVH.btn_buy.setVisibility(View.GONE);
            limitGoodVH.btn_share.setVisibility(View.GONE);
        }
        limitGoodVH.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//下划线
        limitGoodVH.tv_price.getPaint().setAntiAlias(true);// 抗锯齿
    }

    @Override
    public int getItemCount() {
        return limitSaleModels.size();
    }

    public class TodaySaleVH extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_todaysale_bannergood)
        LinearLayout layout_todaysale_bannergood;
        @BindView(R.id.layout_todaysale_product)
        RelativeLayout layout_todaysale_product;

        @BindView(R.id.iv_good_banner)
        ImageView iv_good_banner;
        @BindView(R.id.rv_bannergood_list)
        PullToRefreshRecyclerView ry_bannergood_list;
        private RecyclerView rv_bannergood;
        private CountryShopProductAdapter adapter;

        @BindView(R.id.iv_image)
        ImageView iv_image;
        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_product_desc)
        TextView tv_product_desc;
        @BindView(R.id.tv_sale_volume)
        TextView tv_sale_volume;
        @BindView(R.id.pl_taglist)
        PredicateLayout pl_taglist;
        @BindView(R.id.ll_guest_price)
        LinearLayout ll_guest_price;
        @BindView(R.id.ll_host_price)
        LinearLayout ll_host_price;
        @BindView(R.id.btn_guest_buy)
        TextView btn_guest_buy;
        @BindView(R.id.btn_buy)
        TextView btn_buy;
        @BindView(R.id.btn_share)
        TextView btn_share;
        @BindView(R.id.tv_price)
        TextView tv_price;

        public TodaySaleVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
