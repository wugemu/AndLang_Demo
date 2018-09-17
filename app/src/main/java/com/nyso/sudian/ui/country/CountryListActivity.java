package com.nyso.sudian.ui.country;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.CountryListAdapter;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListActivity extends BaseLangActivity {

    @BindView(R.id.lv_result)
    ListView lvResult;
    private CountryListAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_country_list;
    }

    @Override
    public void initView() {
        initTitleBar("环球馆");


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        List<Subject> subjects=new ArrayList<>();
        for (int i=0;i<5;i++){
            Subject subject=new Subject();
            subject.setUrl("http://image.mihui365.com/bbc/bannerImg/8022545227416570.jpg");
            List<GoodsStandard> goods=new ArrayList<>();
            for (int j=0;j<6;j++){
                GoodsStandard goodsStandard=new GoodsStandard();
                goodsStandard.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
                goodsStandard.setGoodsName("【 限量版】SK-II 护肤精华露 神仙水   250ML");
                goodsStandard.setShowPrice("￥12");
               goods.add(goodsStandard);
            }
            subject.setGoodsList(goods);
            subjects.add(subject);
        }
        for (Subject subject:subjects){
            if(subject.getGoodsList()!=null&&subject.getGoodsList().size()>0){
                subject.getGoodsList().add(new GoodsStandard());
            }
        }
        adapter=new CountryListAdapter(subjects,this,handler);
        lvResult.setAdapter(adapter);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                  //加载下一页
                    break;
            }
        }
    };
    @Override
    public void update(Observable o, Object arg) {

    }


}
