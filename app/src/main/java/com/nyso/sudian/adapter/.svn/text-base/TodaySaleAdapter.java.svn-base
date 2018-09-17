package com.nyso.sudian.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.local.LimitSaleModel;
import com.nyso.sudian.ui.widget.CustomGridView;
import com.nyso.sudian.ui.widget.PredicateLayout;
import com.nyso.sudian.ui.widget.banner.ShareCardItem;
import com.nyso.sudian.ui.widget.banner.ShareCardView;
import com.nyso.sudian.util.BBCUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
@Deprecated
public class TodaySaleAdapter extends BaseAdapter {
    private static final int TYPE_COUNT = 6;

    private static final int TYPE_BANNER = 0;//banner
    private static final int TYPE_TOPICON = 1;//头部5个icon
    private static final int TYPE_THEME = 2;//主题场
    private static final int TYPE_LIMITTIME = 3;//显示秒杀时间
    private static final int TYPE_BANNERSALE=4;//Banner+商品
    private static final int TYPE_GOODSALE=5;//商品


    private LayoutInflater inflater;
    private Activity context;
    private List<Subject> bannerList;//banner
    private List<String> iconList;//头部5个icon
    private List<Subject> themeList;//主题场
    private List<String> timeList;//特卖时间列表
    private List<LimitSaleModel> limitSaleModels;//商品列表
    private int type;//1=玩主,0=玩客

    public TodaySaleAdapter(Activity context,List<Subject> bannerList,List<String> iconList,List<Subject> themeList,List<String> timeList,List<LimitSaleModel> limitSaleModels) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.bannerList=bannerList;
        this.iconList=iconList;
        this.themeList=themeList;
        this.timeList=timeList;
        this.limitSaleModels=limitSaleModels;
    }

    @Override
    public int getCount() {
        return 4+limitSaleModels.size();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BannerVH bannerVH = null;
        TopIconVH topIconVH=null;
        ThemeVH themeVH=null;
        LimitTimeVH limitTimeVH=null;
        LimitBannerGoodVH limitBannerGoodVH=null;
        LimitGoodVH limitGoodVH=null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_BANNER:
                    //banner
                    convertView = inflater.inflate(R.layout.include_banner, null);
                    bannerVH = new BannerVH(convertView);
                    loadBanner(bannerVH);
                    convertView.setTag(bannerVH);
                    break;
                case TYPE_TOPICON:
                    //头部5个icon
                    convertView = inflater.inflate(R.layout.include_topicon, null);
                    topIconVH = new TopIconVH(convertView);
                    loadTopIcon(topIconVH);
                    convertView.setTag(topIconVH);
                    break;
                case TYPE_THEME:
                    //主题场
                    convertView = inflater.inflate(R.layout.include_theme, null);
                    themeVH = new ThemeVH(convertView);
                    loadTheme(themeVH);
                    convertView.setTag(themeVH);
                    break;
                case TYPE_LIMITTIME:
                    convertView = inflater.inflate(R.layout.include_limittime, null);
                    limitTimeVH = new LimitTimeVH(convertView);
                    loadLimitTime(limitTimeVH);
                    convertView.setTag(limitTimeVH);
                    break;
                case TYPE_BANNERSALE:
                    LimitSaleModel limitSaleModel=limitSaleModels.get(position-4);
                    convertView = inflater.inflate(R.layout.include_limit_bannergood, null);
                    limitBannerGoodVH = new LimitBannerGoodVH(convertView);
                    loadBannerGood(limitBannerGoodVH,limitSaleModel.getSubject());
                    convertView.setTag(limitBannerGoodVH);
                    break;
                case TYPE_GOODSALE:
                    LimitSaleModel saleModel=limitSaleModels.get(position-4);
                    convertView = inflater.inflate(R.layout.adapter_common_product, null);
                    limitGoodVH = new LimitGoodVH(convertView);
                    loadLimitGood(limitGoodVH,saleModel.getGood());
                    convertView.setTag(limitGoodVH);
                    break;
            }
        }else {
            switch (type) {
                case TYPE_BANNER:
                    //banner
                    bannerVH=(BannerVH) convertView.getTag();
                    loadBanner(bannerVH);
                    break;
                case TYPE_TOPICON:
                    //头部5个icon
                    topIconVH=(TopIconVH)convertView.getTag();
                    loadTopIcon(topIconVH);
                    break;
                case TYPE_THEME:
                    //主题场
                    themeVH=(ThemeVH)convertView.getTag();
                    loadTheme(themeVH);
                    break;
                case TYPE_LIMITTIME:
                    //特卖时间列表
                    limitTimeVH=(LimitTimeVH)convertView.getTag();
                    loadLimitTime(limitTimeVH);
                    break;
                case TYPE_BANNERSALE:
                    //特卖商品
                    LimitSaleModel limitSaleModel=limitSaleModels.get(position-4);
                    limitBannerGoodVH=(LimitBannerGoodVH)convertView.getTag();
                    loadBannerGood(limitBannerGoodVH,limitSaleModel.getSubject());
                    break;
                case TYPE_GOODSALE:
                    //商品
                    LimitSaleModel saleModel=limitSaleModels.get(position-4);
                    limitGoodVH=(LimitGoodVH)convertView.getTag();
                    loadLimitGood(limitGoodVH,saleModel.getGood());
                    break;

            }
        }

        return convertView;
    }

    public void loadBanner(BannerVH bannerVH){
        //banner 设置
        int width = (int) (BBCUtil.getDisplayWidth(context) - context.getResources().getDimension(R.dimen.banner_margin));
        int height = (int) (((width * 155 / 345) + context.getResources().getDimension(R.dimen.fab_margin)) * 1.05);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        bannerVH.home_banner.setLayoutParams(params);
        if(bannerList!=null&&bannerList.size()>0) {
            bannerVH.home_banner.setVisibility(View.VISIBLE);
            ShareCardItem shareCardItem = new ShareCardItem();
            shareCardItem.setDataList(bannerList);
            bannerVH.home_banner.setCardData(shareCardItem);
        }else {
            bannerVH.home_banner.setVisibility(View.GONE);
        }
    }
    public void loadTopIcon(TopIconVH topIconVH){
        //头部5个icon
    }
    public void loadTheme(ThemeVH themeVH){
        //主题场
        HomeThemeAdapter homeThemeAdapter=new HomeThemeAdapter(context,themeList);
        themeVH.home_theme.setAdapter(homeThemeAdapter);
    }
    public void loadLimitTime(LimitTimeVH limitTimeVH){
        //特卖时间列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置布局管理器
//        limitTimeVH.ry_limittime.setFooterLayout(new TimeFootLayout(context));
        limitTimeVH.ry_limittime.setHasPullDownFriction(true); // 设置有上拉阻力
        limitTimeVH.rv_limittime = limitTimeVH.ry_limittime.getRefreshableView();
        limitTimeVH.rv_limittime.setLayoutManager(linearLayoutManager);
        limitTimeVH.ry_limittime.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        limitTimeVH.todaySaleTimeAdapter=new TodaySaleTimeAdapter(context,timeList,null);
        limitTimeVH.rv_limittime.setAdapter(limitTimeVH.todaySaleTimeAdapter);
    }
    public void loadBannerGood(LimitBannerGoodVH limitBannerGoodVH,Subject subject){
        //banner 商品列表
        int w= BBCUtil.getDisplayWidth(context);
        int h= (int) (w*350.0/750);
        RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(w,h);
        limitBannerGoodVH.iv_good_banner.setLayoutParams(params1);
        ImageLoadUtils.doLoadImageUrl(limitBannerGoodVH.iv_good_banner,subject.getUrl());

        limitBannerGoodVH.rv_bannergood=limitBannerGoodVH.ry_bannergood_list.getRefreshableView();
        limitBannerGoodVH.ry_bannergood_list.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
//        limitBannerGoodVH.ry_bannergood_list.setFooterLayout(new LoadingMoreFootLayout(context));
        limitBannerGoodVH.ry_bannergood_list.setHasPullDownFriction(true); // 设置没有上拉阻力
        limitBannerGoodVH.rv_bannergood.setLayoutManager(linearLayoutManager1);
        limitBannerGoodVH.adapter=new CountryShopProductAdapter(context,subject);
        limitBannerGoodVH.rv_bannergood.setAdapter(limitBannerGoodVH.adapter);
    }

    public void loadLimitGood(LimitGoodVH limitGoodVH, GoodsStandard goodsStandard){
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
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_TOPICON;
        } else if (position == 2) {
            return TYPE_THEME;
        }else if(position==3){
            return TYPE_LIMITTIME;
        } else {
            if(position-4<limitSaleModels.size()){
                LimitSaleModel saleModel=limitSaleModels.get(position-4);
                if(saleModel.getType()==LimitSaleModel.TYPE_BANNERSALE){
                    return TYPE_BANNERSALE;
                }else if(saleModel.getType()==LimitSaleModel.TYPE_GOOD){
                    return TYPE_GOODSALE;
                }
            }
        }
        return -1;
    }

    class BannerVH{
        @BindView(R.id.home_banner)
        ShareCardView home_banner;
        public BannerVH(View view) {
            ButterKnife.bind(this, view);
        }
    }
    class TopIconVH{

        public TopIconVH(View view) {
            ButterKnife.bind(this, view);
        }
    }
    class ThemeVH{
        @BindView(R.id.home_theme)
        CustomGridView home_theme;
        public ThemeVH(View view) {
            ButterKnife.bind(this, view);
        }
    }
    class LimitTimeVH{
        @BindView(R.id.ry_limittime)
        PullToRefreshRecyclerView ry_limittime;
        private RecyclerView rv_limittime;
        private TodaySaleTimeAdapter todaySaleTimeAdapter;
        public LimitTimeVH(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class LimitBannerGoodVH{
        @BindView(R.id.iv_good_banner)
        ImageView iv_good_banner;
        @BindView(R.id.rv_bannergood_list)
        PullToRefreshRecyclerView ry_bannergood_list;
        private RecyclerView rv_bannergood;
        private CountryShopProductAdapter adapter;
        public LimitBannerGoodVH(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class LimitGoodVH{
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


        public LimitGoodVH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
