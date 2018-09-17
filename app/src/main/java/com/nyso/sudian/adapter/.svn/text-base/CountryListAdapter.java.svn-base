package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.ui.country.CountryProductListActivity;
import com.nyso.sudian.ui.widget.LoadingMoreFootLayout;
import com.nyso.sudian.util.BBCUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2017/5/23.
 */

public class CountryListAdapter extends BaseAdapter {
    private List<Subject> subjectList;
    private Activity activity;
    private LayoutInflater inflater;
    private Handler handler;
    private int currP;

    public CountryListAdapter(List<Subject> subjectList, Activity activity, Handler handler) {
        if(subjectList!=null){
            this.subjectList = subjectList;
        }else{
            this.subjectList = new ArrayList<Subject>();
        }
        this.activity = activity;
        this.handler=handler;
        inflater= LayoutInflater.from(activity);
    }

    public void addSubjectList(List<Subject> subjects) {
        if (subjects != null) {
            subjectList.addAll(subjects);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Subject getItem(int position) {
        return subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_country_list, null);
            holder = new ViewHolder(convertView);
            int w= BBCUtil.getDisplayWidth(activity);
            int h= (int) (w*350.0/750);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(w,h);
            holder.ivCountryImage.setLayoutParams(params);

            holder.recyclerView=holder.rvCountryShopProductList.getRefreshableView();
            holder.rvCountryShopProductList.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rvCountryShopProductList.setFooterLayout(new LoadingMoreFootLayout(activity));
            holder.rvCountryShopProductList.setHasPullDownFriction(true); // 设置没有上拉阻力
            holder.recyclerView.setLayoutManager(linearLayoutManager);
            holder.adapter=new CountryShopProductAdapter(activity,null);
            holder.recyclerView.setAdapter(holder.adapter);
            convertView.setTag(holder);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        final Subject subject=getItem(position);
        holder.adapter.setSubject(subject);
        ImageLoadUtils.doLoadImageUrl(holder.ivCountryImage,subject.getUrl());
        final ViewHolder finalHolder=holder;
//        holder.recyclerView.setTag(subject);
//        holder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if(recyclerView.getLayoutManager() != null) {
//                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    //获取可视的第一个view
//                    View topView = layoutManager.getChildAt(0);
//                    if(topView != null) {
//                        Subject sub= (Subject) recyclerView.getTag();
//                        //获取与该view的顶部的偏移量
//                        sub.setLastOffset(topView.getLeft());
//                        //得到该View的数组位置
//                        sub.setLastPosition(layoutManager.getPosition(topView));
//                    }
//                }
//            }
//        });
//        Subject sub= (Subject) holder.recyclerView.getTag();
//        if(sub!=null&&holder.recyclerView.getLayoutManager() != null && sub.getLastPosition() >= 0) {
//            ((LinearLayoutManager) holder.recyclerView.getLayoutManager()).scrollToPositionWithOffset(sub.getLastPosition(), sub.getLastOffset());
//        }else if(sub==null&&holder.recyclerView.getLayoutManager() != null){
//            ((LinearLayoutManager) holder.recyclerView.getLayoutManager()).scrollToPositionWithOffset(0,0);
//        }
        holder.rvCountryShopProductList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Intent intent = new Intent(activity, CountryProductListActivity.class);
                HashMap<String,Object> map=new HashMap<String, Object>();
                map.put("countryId",subject.getExtendId());
                intent.putExtra("title",subject.getTitle());
                intent.putExtra("actionParams",map);
                ActivityUtil.getInstance().start(activity,intent);
                finalHolder.rvCountryShopProductList.onRefreshComplete();
            }
        });


        if(getCount()%20==0&&position==getCount()-1&&position!=currP){
            handler.sendEmptyMessage(2);
            currP=position;
        }
        holder.ivCountryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CountryProductListActivity.class);
                HashMap<String,Object> map=new HashMap<String, Object>();
                map.put("countryId",subject.getExtendId());
                intent.putExtra("title",subject.getTitle());
                intent.putExtra("actionParams",map);
                ActivityUtil.getInstance().start(activity,intent);
            }
        });

        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.iv_country_image)
        ImageView ivCountryImage;
        @BindView(R.id.rv_country_shop_product_list)
        PullToRefreshRecyclerView rvCountryShopProductList;
        private RecyclerView recyclerView;
        private CountryShopProductAdapter adapter;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
