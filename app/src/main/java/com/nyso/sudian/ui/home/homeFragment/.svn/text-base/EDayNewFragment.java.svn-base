package com.nyso.sudian.ui.home.homeFragment;

import android.animation.ObjectAnimator;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.CommonProductAdapter2;
import com.nyso.sudian.model.api.GoodsStandard;
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
public class EDayNewFragment extends BaseLangFragment {
    @BindView(R.id.iv_edaynew)
    ImageView iv_edaynew;
    @BindView(R.id.lv_edaynew)
    RecyclerView lv_edaynew;
    @BindView(R.id.sw_edaynew)
    RefreshLayout sw_edaynew;
    @BindView(R.id.appbar_edaynew)
    AppBarLayout appbar_edaynew;

    private int type;//玩主=1；玩客=0
    private CommonProductAdapter2 adapter;
    private ObjectAnimator animator;
    private float percentage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_eday_new;
    }

    @Override
    public void initView() {
        //设置下拉刷新
        View header = LayoutInflater.from(activity).inflate(R.layout.include_loading, null);
        final View image = header.findViewById(R.id.iv_loading);
        animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        sw_edaynew.setRefreshHeader(header);
        if (sw_edaynew != null) {
            // 刷新状态的回调
            sw_edaynew.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    sw_edaynew.startAnim(animator);
                }
            });

        }
        //设置可下拉刷新位置
        appbar_edaynew.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                if (percentage == 0.0f ) {
                    sw_edaynew.setEnabled(true);
                } else {
                    sw_edaynew.setEnabled(false);
                }
            }
        });

        //设置头部图片比例
        int w= BBCUtil.getDisplayWidth(activity);
        int h= (int) (w*170/375);
        CollapsingToolbarLayout.LayoutParams params=new CollapsingToolbarLayout.LayoutParams(w,h);
        iv_edaynew.setLayoutParams(params);
        ImageLoadUtils.doLoadImageUrl(iv_edaynew,"http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");

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
        lv_edaynew.setLayoutManager(new LinearLayoutManager(activity));
        lv_edaynew.setAdapter(adapter);
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
}
