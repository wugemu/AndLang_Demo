package com.nyso.sudian.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.ui.search.SearchActivity;
import com.nyso.sudian.ui.widget.PredicateLayout;
import com.nyso.sudian.util.BBCUtil;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */
@Deprecated
public class CommonProductAdapter extends BaseLangAdapter<GoodsStandard> {
    private int type;//1=玩主,0=玩客

    public CommonProductAdapter(Activity context, List data) {
        super(context, R.layout.adapter_common_product, data);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, GoodsStandard item) {
        ImageView imageView = helper.getView(R.id.iv_image);
        doLoadImage(imageView, item.getImgUrl());
        helper.setText(R.id.tv_product_name, item.getGoodsName());
        helper.setText(R.id.tv_product_desc, item.getDescription());
        helper.setText(R.id.tv_sale_volume, "销量" + item.getTotalSales());
        PredicateLayout plTags = helper.getView(R.id.pl_taglist);
        int padding1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1.5f, context.getResources().getDisplayMetrics());
        int padding2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                3, context.getResources().getDisplayMetrics());
        String[] tagList = new String[]{"爆款", "新品", "保税", "清仓"};
        for (int i = 0; i < tagList.length; i++) {
            String key = tagList[i];
            TextView txt = new TextView(context);
            switch (i) {
                case 0:
                    txt.setBackgroundResource(R.drawable.tag_bg_red);
                    txt.setTextColor(context.getResources().getColor(R.color.colorRedMain));
                    break;
                case 1:
                    txt.setBackgroundResource(R.drawable.tag_bg_orange);
                    txt.setTextColor(context.getResources().getColor(R.color.colorOrageText));
                    break;
                case 2:
                    txt.setBackgroundResource(R.drawable.tag_bg_purple);
                    txt.setTextColor(context.getResources().getColor(R.color.purple));
                    break;
                case 3:
                    txt.setBackgroundResource(R.drawable.tag_bg_green);
                    txt.setTextColor(context.getResources().getColor(R.color.colorGreenText));
                    break;
            }

            txt.setText(key);
            txt.setClickable(true);
            txt.setTextSize(10);
            txt.setPadding(padding2, padding1, padding2, padding1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(params);
            plTags.addView(txt);
        }

        if (type == 1) {
            helper.getView(R.id.ll_guest_price).setVisibility(View.GONE);
            helper.getView(R.id.ll_host_price).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_guest_buy).setVisibility(View.GONE);
            helper.getView(R.id.btn_buy).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_share).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.ll_host_price).setVisibility(View.GONE);
            helper.getView(R.id.ll_guest_price).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_guest_buy).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_buy).setVisibility(View.GONE);
            helper.getView(R.id.btn_share).setVisibility(View.GONE);
        }
        TextView tvPrice=helper.getView(R.id.tv_price);
        tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//下划线
        tvPrice.getPaint().setAntiAlias(true);// 抗锯齿
    }
}
