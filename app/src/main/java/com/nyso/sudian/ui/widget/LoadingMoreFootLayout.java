package com.nyso.sudian.ui.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.LoadingLayoutBase;
import com.nyso.sudian.R;

/**
 * Created by 1 on 2017/5/22.
 */

public class LoadingMoreFootLayout extends LoadingLayoutBase {
    private LinearLayout mInnerLayout;
    public LoadingMoreFootLayout(Context context) {
        super(context);
       View view= LayoutInflater.from(context).inflate(R.layout.foot_look_more, this);
        mInnerLayout= (LinearLayout) view.findViewById(R.id.fl_inner);
    }

    @Override
    public void setPullLabel(CharSequence pullLabel) {
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
//        this.mRefreshingLabel=refreshingLabel;
    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {
//        this.mReleaseLabel=releaseLabel;
    }

    @Override
    public int getContentSize() {
        return mInnerLayout.getWidth();
    }

    @Override
    public void pullToRefresh() {
        Log.i("","");
    }

    @Override
    public void releaseToRefresh() {
        Log.i("","");

    }

    @Override
    public void onPull(float scaleOfLayout) {
        Log.i("","");
    }

    @Override
    public void refreshing() {
        Log.i("","");
    }

    @Override
    public void reset() {

    }
}
