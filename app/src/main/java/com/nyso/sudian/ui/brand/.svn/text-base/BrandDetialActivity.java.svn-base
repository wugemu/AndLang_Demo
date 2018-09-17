package com.nyso.sudian.ui.brand;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.StatusBarUtils;
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

public class BrandDetialActivity extends BaseLangActivity {
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
    @BindView(R.id.rv_list)
    RecyclerView rvHomeOrder;
    @BindView(R.id.iv_brand_logo)
    ImageView ivBrandLogo;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_sale_num)
    TextView tvSaleNum;
    @BindView(R.id.tv_brand_story)
    TextView tvBrandStory;
    @BindView(R.id.tv_extend)
    TextView tvExtend;
    @BindView(R.id.iv_extend)
    ImageView ivExtend;
    @BindView(R.id.tab)
    LinearLayout tab;
    private int type;//玩主=1；玩客=0
    private CommonProductAdapter2 adapter;
    private boolean upPrice;
    private boolean isExtend;
    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_detial;
    }

    /**  设置状态栏高度  */
    protected void setStatusBar(int flag,int colorId) {
        View mStatusBar=ButterKnife.findById(this, R.id.m_statusBar);
        if(flag==1){
            if(mStatusBar != null) {
                StatusBarUtils.translateStatusBar(this);
                mStatusBar.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
                layoutParams.height = StatusBarUtils.getStatusHeight(this);
                mStatusBar.setLayoutParams(layoutParams);
                mStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
                StatusBarUtils.setTextColorStatusBar(this, true);

            } else {
                StatusBarUtils.setWindowStatusBarColor(this, com.example.test.andlang.R.color.lang_colorWhite);
            }
        } else{
            StatusBarUtils.setWindowStatusBarColor(this, com.example.test.andlang.R.color.lang_colorWhite);
        }
    }
    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            tvProfit.setVisibility(View.VISIBLE);
        } else {
            tvProfit.setVisibility(View.GONE);
        }
        List<GoodsStandard> goods = new ArrayList<>();
        tvSaleNum.setText("在售商品 108");
        tvBrandName.setText("大麦若耶");
        tvBrandStory.setText("ReFa由MTG有限公司制造，MTG有限公司成立于1996年，专门从事设计和开发的美容护理、美容设备、护肤品和准药品。觉得很精神低价格哈说过的哈睡不好好吃 v看电视剧都比较失败的哈更好今年九十年代就是博大精深的百货商店跨境电商狂欢倒计时。");
        for (int i = 0; i < 10; i++) {
            GoodsStandard good = new GoodsStandard();
            good.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            good.setGoodsName("【 限量版】SK-II 护肤精华露 神仙水   250ML");
            good.setDescription("方便快捷 测试完成");
            good.setTotalSales(222);
            goods.add(good);
        }
        ImageLoadUtils.doLoadImageUrl(ivBrandLogo, "http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
        if (Build.VERSION.SDK_INT>=21){
            ivBrandLogo.setElevation(getResources().getDimension(R.dimen.padding3dp));
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
                upPrice = false;
                break;
            case R.id.tv_profit:
                tvProfit.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvUpNew.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorBlackText3));
                ivArrowDown.setImageResource(R.mipmap.order_black_down);
                ivArrowUp.setImageResource(R.mipmap.order_black_up);
                upPrice = false;
                break;
            case R.id.tv_up_new:
                tvUpNew.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvProfit.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorBlackText3));
                ivArrowDown.setImageResource(R.mipmap.order_black_down);
                ivArrowUp.setImageResource(R.mipmap.order_black_up);
                upPrice = false;
                break;
            case R.id.ll_price:
                tvOrderPrice.setTextColor(getResources().getColor(R.color.colorRedMain));
                tvProfit.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvHotSale.setTextColor(getResources().getColor(R.color.colorBlackText3));
                tvUpNew.setTextColor(getResources().getColor(R.color.colorBlackText3));
                upPrice = !upPrice;
                if (upPrice) {
                    ivArrowUp.setImageResource(R.mipmap.order_red_up);
                    ivArrowDown.setImageResource(R.mipmap.order_black_down);
                } else {
                    ivArrowUp.setImageResource(R.mipmap.order_black_up);
                    ivArrowDown.setImageResource(R.mipmap.order_red_down);
                }
                break;
        }
    }



    @OnClick(R.id.ll_extend)
    public void onViewClicked() {
        isExtend=!isExtend;
        if(isExtend){
            tvBrandStory.setSingleLine(false);
            tvExtend.setText("点击收起");
            ivExtend.setImageResource(R.mipmap.arrow_up);
        }else{
            tvExtend.setText("点击展开");
            tvBrandStory.setLines(2);
            ivExtend.setImageResource(R.mipmap.arrow_down);
        }
    }
}
