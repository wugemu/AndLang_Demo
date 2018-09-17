package com.nyso.sudian.ui.news;

import android.os.Bundle;
import android.widget.ListView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.BaseActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.NewsCentralAdapter;
import com.nyso.sudian.model.local.NewCentral;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息盒子
 */
public class NewsCentralActivity extends BaseLangActivity {
    @BindView(R.id.lv_list)
    ListView lvList;
    private NewsCentralAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_central;
    }

    @Override
    public void initView() {
        initTitleBar("消息盒子");
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        List<NewCentral> newCentrals=new ArrayList<>();
        NewCentral gonggao=new NewCentral(R.mipmap.gonggao_message,"素店公告","08月28日 20:20","温馨提示：实际上计算机设计师结算结算计算机",true);
        newCentrals.add(gonggao);
        NewCentral notice=new NewCentral(R.mipmap.notice_message,"秒杀提醒","08月28日 20:20","温馨提示：实际上计算机设计师结算结算计算机",true);
        newCentrals.add(notice);
        NewCentral peixun=new NewCentral(R.mipmap.peixun_message,"培训信息","08月28日 20:20","温馨提示：实际上计算机设计师结算结算计算机",true);
        newCentrals.add(peixun);
        NewCentral order=new NewCentral(R.mipmap.order_message,"订单信息","08月28日 20:20","温馨提示：实际上计算机设计师结算结算计算机实际上计算机设计",true);
        newCentrals.add(order);
        adapter=new NewsCentralAdapter(this,newCentrals);
        lvList.setAdapter(adapter);


    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
