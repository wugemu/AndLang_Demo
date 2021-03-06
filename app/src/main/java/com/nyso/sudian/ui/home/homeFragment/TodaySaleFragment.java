package com.nyso.sudian.ui.home.homeFragment;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.util.LogUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.nyso.sudian.R;
import com.nyso.sudian.adapter.HomeThemeAdapter;
import com.nyso.sudian.adapter.TodaySaleNewAdapter;
import com.nyso.sudian.adapter.TodaySaleTimeAdapter;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.local.LimitSaleModel;
import com.nyso.sudian.ui.widget.CenterLayoutManager;
import com.nyso.sudian.ui.widget.CustomGridView;
import com.nyso.sudian.ui.widget.banner.ShareCardItem;
import com.nyso.sudian.ui.widget.banner.ShareCardView;
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
public class TodaySaleFragment extends BaseLangFragment {
    @BindView(R.id.sw_todaysale)
    RefreshLayout sw_todaysale;
    @BindView(R.id.appbar_todaysale)
    AppBarLayout appbar_todaysale;
    @BindView(R.id.home_banner)
    ShareCardView home_banner;
    @BindView(R.id.home_theme)
    CustomGridView home_theme;
    @BindView(R.id.ry_limittime)
    PullToRefreshRecyclerView ry_limittime;
    @BindView(R.id.rv_todaysale)
    RecyclerView rv_todaysale;

    private RecyclerView rv_limittime;
    private TodaySaleTimeAdapter todaySaleTimeAdapter;
    private TodaySaleNewAdapter goodAdapter;
    private int type=1;//玩主=1；玩客=0
    private ObjectAnimator animator;
    private float percentage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_today_sale_new;
    }

    @Override
    public void initView() {

        //设置下拉刷新
        View header = LayoutInflater.from(activity).inflate(R.layout.include_loading, null);
        final View image = header.findViewById(R.id.iv_loading);
        animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        sw_todaysale.setRefreshHeader(header);
        if (sw_todaysale != null) {
            // 刷新状态的回调
            sw_todaysale.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    sw_todaysale.startAnim(animator);
                }
            });

        }
        //设置可下拉刷新位置
        appbar_todaysale.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                if (percentage == 0.0f ) {
                    sw_todaysale.setEnabled(true);
                } else {
                    sw_todaysale.setEnabled(false);
                }
            }
        });
        //banner 设置
        int width = (int) (BBCUtil.getDisplayWidth(activity) - activity.getResources().getDimension(R.dimen.banner_margin));
        int height = (int) (((width * 155 / 345) + activity.getResources().getDimension(R.dimen.fab_margin)) * 1.05);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        home_banner.setLayoutParams(params);
        List<Subject> bannerList=getBannerList();
        if(bannerList!=null&&bannerList.size()>0) {
            home_banner.setVisibility(View.VISIBLE);
            ShareCardItem shareCardItem = new ShareCardItem();
            shareCardItem.setDataList(bannerList);
            home_banner.setCardData(shareCardItem);
        }else {
            home_banner.setVisibility(View.GONE);
        }
        //头部5个设置
        //主题场设置
        HomeThemeAdapter homeThemeAdapter=new HomeThemeAdapter(activity,getBannerList());
        home_theme.setAdapter(homeThemeAdapter);
        //秒杀时间列表
        CenterLayoutManager centerLayoutManager = new CenterLayoutManager(activity);
        centerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ry_limittime.setHasPullDownFriction(true); // 设置有上拉阻力
        rv_limittime = ry_limittime.getRefreshableView();
        rv_limittime.setLayoutManager(centerLayoutManager);
        todaySaleTimeAdapter=new TodaySaleTimeAdapter(activity,getTimeList(),handler);
        rv_limittime.setAdapter(todaySaleTimeAdapter);
        rv_limittime.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int firstItemPosition = manager.findFirstVisibleItemPosition();

                    View firstVisiableChildView = manager.findViewByPosition(firstItemPosition);
                    int itemWidth=(int) activity.getResources().getDimension(R.dimen.limittime_width);
                    int screenWidth=BBCUtil.getDisplayWidth(activity);
                    int leftwidth=(screenWidth-itemWidth)/2;
                    int absoulutWidth=0;
                    if(firstItemPosition>0){
                        absoulutWidth=leftwidth;
                    }
                    absoulutWidth=absoulutWidth+firstItemPosition * itemWidth - firstVisiableChildView.getLeft();

                    int selectPostion=absoulutWidth/itemWidth;
                    int section=absoulutWidth%itemWidth;
                    int move=0;
                    if(section>=itemWidth/2){
                        selectPostion=selectPostion+1;
                        move=itemWidth-section;
                    }else {
                        move=-section;
                    }
//                    LogUtil.d("0.0","selectPostion："+selectPostion+",section:"+section+",itemWidth:"+itemWidth);
//                    LogUtil.d("0.0","选中："+selectPostion+",设置x位移："+move);
                    selectLimitTimeItem(selectPostion,move);
                }

            }
        });

        //商品信息
        goodAdapter=new TodaySaleNewAdapter(activity,getListSaleModeList(),type);
        rv_todaysale.setLayoutManager(new LinearLayoutManager(activity));
        rv_todaysale.setAdapter(goodAdapter);

        selectLimitTimeItem(5,0);
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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    int select=msg.arg1;
                    selectLimitTimeItem(select,0);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void selectLimitTimeItem(int position,int move){
        if(move!=0){
            rv_limittime.scrollBy(move, 0);
        }else {
            rv_limittime.smoothScrollToPosition(position);
        }
        if(todaySaleTimeAdapter!=null){
            todaySaleTimeAdapter.setSelectPostition(position);
        }
        LogUtil.d("0.0","选中："+position+",设置x位移："+move);
    }

    //banner数据
    public List<Subject> getBannerList(){
        List<Subject> bannerList=new ArrayList<Subject>();
        Subject subject1=new Subject();
        subject1.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject1);
        Subject subject2=new Subject();
        subject2.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject2);
        Subject subject3=new Subject();
        subject3.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject3);
        Subject subject4=new Subject();
        subject4.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject4);
        Subject subject5=new Subject();
        subject5.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject5);
        Subject subject6=new Subject();
        subject6.setUrl2("http://image.mihui365.com/bbc/bannerImg/20536884660683738.jpg");
        bannerList.add(subject6);
        return bannerList;
    }

    //获取特卖时间列表
    public List<String> getTimeList(){
        List<String> timeList=new ArrayList<String>();
        timeList.add("1:30");
        timeList.add("2:30");
        timeList.add("3:30");
        timeList.add("4:30");
        timeList.add("5:30");
        timeList.add("6:30");
        timeList.add("7:30");
        timeList.add("8:30");
        timeList.add("9:30");
        timeList.add("10:30");
        timeList.add("11:30");
        timeList.add("12:30");
        return timeList;
    }

    public List<LimitSaleModel> getListSaleModeList(){
        List<LimitSaleModel> limitSaleModelList=new ArrayList<>();
        for (int i=0;i<5;i++){
            LimitSaleModel model=new LimitSaleModel();
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
            model.setSubject(subject);
            model.setType(LimitSaleModel.TYPE_BANNERSALE);
            limitSaleModelList.add(model);
        }

        for (int i=0;i<10;i++){
            LimitSaleModel model=new LimitSaleModel();
            GoodsStandard good=new GoodsStandard();
            good.setImgUrl("http://image.mihui365.com/bbc/middleImg/1110333287016088.jpg");
            good.setGoodsName("【 限量版】SK-II 护肤精华露 神仙水   250ML");
            good.setDescription("方便快捷 测试完成");
            good.setTotalSales(222);
            model.setGood(good);
            model.setType(LimitSaleModel.TYPE_GOOD);
            limitSaleModelList.add(model);
        }


        return limitSaleModelList;
    }
}
