package com.nyso.sudian.ui.country;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.CommonProductAdapter2;
import com.nyso.sudian.model.api.GoodsStandard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryProductListActivity extends BaseLangActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_hot_sale)
    TextView tvHotSale;
    @BindView(R.id.tv_profit)
    TextView tvProfit;
    @BindView(R.id.tv_up_new)
    TextView tvUpNew;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.iv_arrow_up)
    ImageView ivArrowUp;
    @BindView(R.id.iv_arrow_down)
    ImageView ivArrowDown;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    //    @BindView(R.id.lv_list)
//    ListView lvList;
    @BindView(R.id.rv_list)
    RecyclerView rvHomeOrder;
    private int type;//玩主=1；玩客=0
    private CommonProductAdapter2 adapter;
    private boolean upPrice;
    @Override
    public int getLayoutId() {
        return R.layout.activity_country_product_list;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", 1);
        initTitleBar(true, "日本馆", R.mipmap.share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageLoadUtils.doLoadImageUrl(ivImage, "http://image.mihui365.com/bbc/bannerImg/8022545227416570.jpg");
        if (type == 1) {
            tvProfit.setVisibility(View.VISIBLE);
        } else {
            tvProfit.setVisibility(View.GONE);
        }
        List<GoodsStandard> goods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GoodsStandard good = new GoodsStandard();
            good.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            good.setGoodsName("【 限量版】SK-II 护肤精华露 神仙水   250ML");
            good.setDescription("方便快捷 测试完成");
            good.setTotalSales(222);
            goods.add(good);
        }
        adapter = new CommonProductAdapter2(this, goods, null);
        adapter.setType(type);
        rvHomeOrder.setLayoutManager(new LinearLayoutManager(this));
        rvHomeOrder.setAdapter(adapter);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }


    @OnClick({R.id.tv_hot_sale, R.id.tv_profit, R.id.tv_up_new, R.id.ll_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hot_sale:
                tvHotSale.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvProfit.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvUpNew.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorBlackText3));
                ivArrowDown.setImageResource(R.mipmap.order_black_down);
                ivArrowUp.setImageResource(R.mipmap.order_black_up);
                upPrice=false;
                break;
            case R.id.tv_profit:
                tvProfit.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvUpNew.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorBlackText3));
                ivArrowDown.setImageResource(R.mipmap.order_black_down);
                ivArrowUp.setImageResource(R.mipmap.order_black_up);
                upPrice=false;
                break;
            case R.id.tv_up_new:
                tvUpNew.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvProfit.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorBlackText3));
                ivArrowDown.setImageResource(R.mipmap.order_black_down);
                ivArrowUp.setImageResource(R.mipmap.order_black_up);
                upPrice=false;
                break;
            case R.id.ll_price:
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvProfit.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvUpNew.setTextColor(getResources().getColor(R.color.colorBlackText3));
                upPrice=!upPrice;
                if (upPrice){
                    ivArrowUp.setImageResource(R.mipmap.order_red_up);
                    ivArrowDown.setImageResource(R.mipmap.order_black_down);
                }else{
                    ivArrowUp.setImageResource(R.mipmap.order_black_up);
                    ivArrowDown.setImageResource(R.mipmap.order_red_down);
                }
                break;
        }
    }




}
