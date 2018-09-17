package com.nyso.sudian.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.util.BBCUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class HomeThemeAdapter extends BaseLangAdapter<Subject> {
    public HomeThemeAdapter(Activity context,  List<Subject> data) {
        super(context, R.layout.gridview_theme_item, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, Subject item) {
        ImageView iv_theme_item=helper.getView(R.id.iv_theme_item);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) iv_theme_item.getLayoutParams();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, context.getResources().getDisplayMetrics());
        int width= (BBCUtil.getDisplayWidth((Activity) context)-px)/2;
        layoutParams.width=width;
        layoutParams.height= (int) (width*97.5/137);
        iv_theme_item.setLayoutParams(layoutParams);
        ImageLoadUtils.doLoadImageUrl(iv_theme_item,item.getUrl2());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转专题详情

            }
        });
    }
}
