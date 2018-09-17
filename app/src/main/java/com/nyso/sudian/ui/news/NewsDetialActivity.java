package com.nyso.sudian.ui.news;

import android.os.Bundle;
import android.widget.ListView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.NewsAdapter;
import com.nyso.sudian.adapter.NoticeAdapter;
import com.nyso.sudian.adapter.OrderMessageAdapter;
import com.nyso.sudian.model.api.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetialActivity extends BaseLangActivity {


    @BindView(R.id.lv_list)
    ListView lvList;
    private int type;//0=公告，1=秒杀，2=培训，3=订单
    private NewsAdapter newsAdapter;
    private NoticeAdapter noticeAdapter;
    private OrderMessageAdapter orderMessageAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detial;
    }

    @Override
    public void initView() {



    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        type=getIntent().getIntExtra("type",0);
        List<News> list=new ArrayList<>();
        for (int i=0;i<8;i++){
            News news=new News(type+"","消息标题"+i,"消息内容"+i,"2018年2月2日",true);
            list.add(news);
        }
        switch (type){  //调用相关接口
            case 0:
                initTitleBar("素店公告");
                newsAdapter =new NewsAdapter(this,list);
                lvList.setDividerHeight((int) getResources().getDimension(R.dimen.view_toview_two));
                lvList.setAdapter(newsAdapter);
                break;
            case 1:
                noticeAdapter=new NoticeAdapter(this,list);
                initTitleBar("秒杀提醒");
                lvList.setDividerHeight((int) getResources().getDimension(R.dimen.view_15dp));
                lvList.setAdapter(noticeAdapter);
                break;
            case 2:
                initTitleBar("培训消息");
                newsAdapter =new NewsAdapter(this,list);
                lvList.setDividerHeight((int) getResources().getDimension(R.dimen.view_toview_two));
                lvList.setAdapter(newsAdapter);
                break;
            case 3:
                orderMessageAdapter=new OrderMessageAdapter(this,list);
                initTitleBar("订单消息");
                lvList.setDividerHeight((int) getResources().getDimension(R.dimen.view_15dp));
                lvList.setAdapter(orderMessageAdapter);
                break;
        }


    }



    @Override
    public void update(Observable o, Object arg) {



    }


}
