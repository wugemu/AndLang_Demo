package com.nyso.sudian.ui.brand;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.CommonProductAdapter2;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.ui.widget.swipe.RefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/9/11.
 */

public class CategoryFragment extends BaseLangFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.m_refersh)
    RefreshLayout mRefersh;
    private int type=1;//玩主=1；玩客=0
    private CommonProductAdapter2 adapter;
    private ObjectAnimator animator;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView() {
        //设置下拉刷新
        View header = LayoutInflater.from(activity).inflate(R.layout.include_loading, null);
        final View image = header.findViewById(R.id.iv_loading);
        animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        mRefersh.setRefreshHeader(header);
        if (mRefersh != null) {
            // 刷新状态的回调
            mRefersh.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mRefersh.startAnim(animator);
                }
            });

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
        adapter = new CommonProductAdapter2((BaseLangActivity) getActivity(), goods, null);
        adapter.setType(type);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(adapter);

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
