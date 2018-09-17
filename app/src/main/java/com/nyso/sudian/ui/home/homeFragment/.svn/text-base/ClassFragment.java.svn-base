package com.nyso.sudian.ui.home.homeFragment;

import android.animation.ObjectAnimator;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.Category3Adapter;
import com.nyso.sudian.adapter.CommonProductAdapter2;
import com.nyso.sudian.model.api.Category;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.ui.widget.CustomGridView;
import com.nyso.sudian.ui.widget.swipe.RefreshLayout;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ClassFragment extends BaseLangFragment {
    @BindView(R.id.sw_classfragment)
    RefreshLayout sw_classfragment;
    @BindView(R.id.appbar_class_fragment)
    AppBarLayout appbar_class_fragment;
    @BindView(R.id.iv_class_fragment)
    ImageView iv_class_fragment;
    @BindView(R.id.gv_class_fragment)
    CustomGridView gv_class_fragment;
    @BindView(R.id.rv_classfragment)
    RecyclerView rv_classfragment;
    @BindView(R.id.tv_profit)
    TextView tvProfit;

    private int type=0;//玩主=1；玩客=0
    private CommonProductAdapter2 adapter;
    private ObjectAnimator animator;
    private float percentage;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void initView() {
        //设置下拉刷新
        View header = LayoutInflater.from(activity).inflate(R.layout.include_loading, null);
        final View image = header.findViewById(R.id.iv_loading);
        animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        sw_classfragment.setRefreshHeader(header);
        if (sw_classfragment != null) {
            // 刷新状态的回调
            sw_classfragment.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    sw_classfragment.startAnim(animator);
                }
            });

        }
        //设置可下拉刷新位置
        appbar_class_fragment.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                if (percentage == 0.0f ) {
                    sw_classfragment.setEnabled(true);
                } else {
                    sw_classfragment.setEnabled(false);
                }
            }
        });
        //分类grid
        Category3Adapter category3Adapter = new Category3Adapter(activity, getCategoryList());
        gv_class_fragment.setAdapter(category3Adapter);

        //设置头部图片比例
        int w= BBCUtil.getDisplayWidth(activity);
        int h= (int) (w*170/375);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(w,h);
        iv_class_fragment.setLayoutParams(params);
        ImageLoadUtils.doLoadImageUrl(iv_class_fragment,"http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");

        //是否显示筛选利润
        if (type == 1) {
            tvProfit.setVisibility(View.VISIBLE);
        } else {
            tvProfit.setVisibility(View.GONE);
        }
        //加载商品
        List<GoodsStandard> goods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GoodsStandard good = new GoodsStandard();
            good.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            good.setGoodsName("【 限量版】SK-II 护肤精华露 神仙水   250ML");
            good.setDescription("方便快捷 测试完成");
            good.setTotalSales(222);
            goods.add(good);
        }
        adapter = new CommonProductAdapter2(activity, goods, null);
        adapter.setType(type);
        rv_classfragment.setLayoutManager(new LinearLayoutManager(activity));
        rv_classfragment.setAdapter(adapter);

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

    public List<Category> getCategoryList(){
        List<Category> categoryList=new ArrayList<Category>();
        for (int i=0;i<7;i++){
            Category category = new Category();
            category.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            category.setThreeName("分类");
            categoryList.add(category);
        }
        Category moreCategory = new Category();
        categoryList.add(moreCategory);
        return categoryList;
    }
}
