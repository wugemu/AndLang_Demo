package com.nyso.sudian.adapter;

import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.ui.widget.PredicateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/9/11.
 */

public class CommonProductAdapter2 extends RecyclerView.Adapter<CommonProductAdapter2.CommonProductAdapter2ViewHolder> {

    private int type;//1=玩主,0=玩客
    private BaseLangActivity activity;
    private List<GoodsStandard> goodList;
    private LayoutInflater inflater;
    private Handler handler;

    public CommonProductAdapter2(BaseLangActivity activity, List<GoodsStandard> goodList, Handler handler) {
        this.activity = activity;
        if (goodList == null) {
            this.goodList = new ArrayList<>();
        } else {
            this.goodList = goodList;
        }
        this.handler = handler;
        this.inflater = LayoutInflater.from(activity);
    }
    public void setType(int type) {
        this.type = type;
    }
    @Override
    public CommonProductAdapter2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonProductAdapter2ViewHolder(inflater.inflate(R.layout.adapter_common_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommonProductAdapter2ViewHolder holder, int position) {
        GoodsStandard good=goodList.get(position);
        ImageLoadUtils.doLoadImageUrl(holder.ivImage,good.getImgUrl());
        holder.tvProductName.setText(good.getGoodsName());
        holder.tvProductDesc.setText(good.getDescription());
        holder.tvSaleVolume.setText("销量"+good.getTotalSales());
        int padding1 = (int) activity.getResources().getDimension(R.dimen.padding1_5dp);
        int padding2 = (int) activity.getResources().getDimension(R.dimen.padding3dp);
        int margin1 = (int) activity.getResources().getDimension(R.dimen.padding5dp);
        int margin2 = (int) activity.getResources().getDimension(R.dimen.padding4dp);
        holder.plTaglist.removeAllViews();
        holder.plTaglist.setDividerLine(margin1);
        holder.plTaglist.setDividerCol(margin2);
        String[] tagList = new String[]{"爆款", "新品", "保税", "清仓","爆款", "新品", "保税", "清仓"};
        for (int i = 0; i < tagList.length; i++) {
            String key = tagList[i];
            TextView txt = new TextView(activity);
            switch (i%4) {
                case 0:
                    txt.setBackgroundResource(R.drawable.tag_bg_red);
                    txt.setTextColor(activity.getResources().getColor(R.color.colorRedMain));
                    break;
                case 1:
                    txt.setBackgroundResource(R.drawable.tag_bg_orange);
                    txt.setTextColor(activity.getResources().getColor(R.color.colorOrageText));
                    break;
                case 2:
                    txt.setBackgroundResource(R.drawable.tag_bg_purple);
                    txt.setTextColor(activity.getResources().getColor(R.color.purple));
                    break;
                case 3:
                    txt.setBackgroundResource(R.drawable.tag_bg_green);
                    txt.setTextColor(activity.getResources().getColor(R.color.colorGreenText));
                    break;
            }

            txt.setText(key);
            txt.setClickable(true);
            txt.setTextSize(10);
            txt.setPadding(padding2, padding1, padding2, padding1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(params);
            holder.plTaglist.addView(txt);
        }

        if (type == 1) {
            holder.llGuestPrice.setVisibility(View.GONE);
            holder.llHostPrice.setVisibility(View.VISIBLE);
            holder.btnGuestBuy.setVisibility(View.GONE);
            holder.btnBuy.setVisibility(View.VISIBLE);
            holder.btnShare.setVisibility(View.VISIBLE);
        } else {
            holder.llHostPrice.setVisibility(View.GONE);
            holder.llGuestPrice.setVisibility(View.VISIBLE);
            holder.btnGuestBuy.setVisibility(View.VISIBLE);
            holder.btnBuy.setVisibility(View.GONE);
            holder.btnShare.setVisibility(View.GONE);
        }

        holder.tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//下划线
        holder.tvPrice.getPaint().setAntiAlias(true);// 抗锯齿
    }

    @Override
    public int getItemCount() {
        return goodList.size();
    }

    public class CommonProductAdapter2ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_product_desc)
        TextView tvProductDesc;
        @BindView(R.id.pl_taglist)
        PredicateLayout plTaglist;
        @BindView(R.id.tv_sale_price)
        TextView tvSalePrice;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.ll_guest_price)
        LinearLayout llGuestPrice;
        @BindView(R.id.tv_host_price)
        TextView tvHostPrice;
        @BindView(R.id.tv_host_get_price)
        TextView tvHostGetPrice;
        @BindView(R.id.ll_host_price)
        LinearLayout llHostPrice;
        @BindView(R.id.ll_content)
        LinearLayout llContent;
        @BindView(R.id.btn_guest_buy)
        TextView btnGuestBuy;
        @BindView(R.id.btn_buy)
        TextView btnBuy;
        @BindView(R.id.btn_share)
        TextView btnShare;
        @BindView(R.id.ll_button)
        LinearLayout llButton;
        @BindView(R.id.tv_sale_volume)
        TextView tvSaleVolume;

        public CommonProductAdapter2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
