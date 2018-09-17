package com.nyso.sudian.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.StatusBarUtils;
import com.example.test.andlang.util.ToastUtil;
import com.example.test.andlang.util.imageload.GlideUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.SuDianApp;
import com.nyso.sudian.model.api.UserAccount;
import com.nyso.sudian.model.local.MineModel;
import com.nyso.sudian.presenter.MinePresenter;
import com.nyso.sudian.ui.mine.SettingActivity;
import com.nyso.sudian.ui.news.NewsCentralActivity;
import com.nyso.sudian.ui.widget.pullzoomview.PullToZoomScrollViewEx;
import com.nyso.sudian.util.BBCUtil;
import com.nyso.sudian.util.Constants;

import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class NysoFragment extends BaseLangFragment<MinePresenter> {
    @BindView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;
    private HeadViewHolder headViewHolder;
    private ZoomViewHolder zoomViewHolder;
    private ContentViewHolder contentViewHolder;
    private UserAccount userAccount;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nyso;
    }

    @Override
    public void initView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.mynyso_head, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.mynyso_zoom, null, false);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.mynyso_content, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
        headViewHolder = new HeadViewHolder(scrollView.getHeaderView());
        zoomViewHolder = new ZoomViewHolder(scrollView.getZoomView());
        contentViewHolder = new ContentViewHolder(scrollView.getPullRootView());

        if (Build.VERSION.SDK_INT >= 21) {
            int barHeight = StatusBarUtils.getStatusHeight(getContext());
            headViewHolder.rlTop.setPadding(0, barHeight, 0, 0);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) headViewHolder.rlTop.getLayoutParams();
            params.height += barHeight;
            headViewHolder.rlTop.setLayoutParams(params);
        }

        int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                76, getActivity().getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) contentViewHolder.llMynysoContent.getLayoutParams();
        params2.setMargins(0, -paddingTop, 0, 0);
        contentViewHolder.llMynysoContent.setLayoutParams(params2);

        zoomViewHolder.iv_zoom.setImageResource(R.mipmap.mine_head_bg);

        addDotListener();

    }

    @Override
    public void initPresenter() {
        presenter=new MinePresenter(this,activity, MineModel.class);
    }

    @Override
    public void initData() {
        presenter.getOrderState();
    }

    @Override
    public void update(Observable o, Object arg) {
        if("getUserAccountInfo".equals(arg)){
            userAccount=presenter.model.getUserAccount();
            if(userAccount!=null){
                if (BBCUtil.isEmpty(userAccount.getNickName())) {
                    headViewHolder.tvUserName.setText(userAccount.getMobile());
                    SuDianApp.getInstance().getSpUtil().putString(getContext(), Constants.NICK_NAME, userAccount.getMobile());
                } else {
                    headViewHolder.tvUserName.setText(userAccount.getNickName());
                    SuDianApp.getInstance().getSpUtil().putString(getContext(), Constants.NICK_NAME, userAccount.getNickName());
                }
                if(!BBCUtil.isEmpty(userAccount.getRandomCode())){
                    headViewHolder.tv_mine_code.setText("邀请码："+userAccount.getRandomCode());
                    headViewHolder.tv_mine_copy.setVisibility(View.VISIBLE);
                }else {
                    headViewHolder.tv_mine_code.setText("");
                    headViewHolder.tv_mine_copy.setVisibility(View.GONE);
                }
                if(BBCUtil.isEmpty(userAccount.getCompanyName())) {
                    headViewHolder.tvUserCompany.setVisibility(View.GONE);
                } else {
                    headViewHolder.tvUserCompany.setVisibility(View.VISIBLE);
                    headViewHolder.tvUserCompany.setText(userAccount.getCompanyName());
                }
                ImageLoadUtils.doLoadCircleImageUrl(headViewHolder.headImage,userAccount.getHeadUrl());
                SuDianApp.getInstance().getSpUtil().putString(getContext(), Constants.USER_HEADIMG, userAccount.getHeadUrl());
                if (!BBCUtil.isEmpty(userAccount.getCardNo()) && !BBCUtil.isEmpty(userAccount.getRealName())) {
                    // 已经实名认证
                    contentViewHolder.tvMineSmrzValue.setText("已认证");
                } else {
                    // 未实名认证
                    contentViewHolder.tvMineSmrzValue.setText("未认证");
                }
            }
        }
    }

    class HeadViewHolder {
        @BindView(R.id.top)
        RelativeLayout rlTop;
        @BindView(R.id.tv_minename)
        TextView tvUserName;
        @BindView(R.id.tv_mine_code)
        TextView tv_mine_code;
        @BindView(R.id.tv_mine_copy)
        TextView tv_mine_copy;
        @BindView(R.id.tv_mine_company)
        TextView tvUserCompany;
        @BindView(R.id.mine_image)
        ImageView headImage;
        public HeadViewHolder(View headView) {
            ButterKnife.bind(this, headView);
        }
        @OnClick(R.id.ll_head_baseright)
        public void gotoNewsCentrol(){
            //去消息
            SuDianApp.getInstance().getSpUtil().putBoolean(getContext(), Constants.ISREAD, true);
            Intent intent = new Intent(activity, NewsCentralActivity.class);
            ActivityUtil.getInstance().start(activity, intent);
        }
        @OnClick(R.id.tv_mine_copy)
        public void goCopy(){
            //复制
            if(BBCUtil.isEmpty(userAccount.getRandomCode())){
                return;
            }
            BBCUtil.copy(userAccount.getRandomCode(), activity);
            ToastUtil.show(activity, "已经复制到剪切板");
        }

    }
    class ZoomViewHolder {
        @BindView(R.id.iv_zoom)
        ImageView iv_zoom;
        public ZoomViewHolder(View headView) {
            ButterKnife.bind(this, headView);
        }
    }
    class ContentViewHolder {
        @BindView(R.id.ll_mynyso_content)
        LinearLayout llMynysoContent;
        @BindView(R.id.tv_order_dfk_dot)
        TextView tvOrderDfkDot;
        @BindView(R.id.tv_order_dfh_dot)
        TextView tvOrderDfhDot;
        @BindView(R.id.tv_order_dsh_dot)
        TextView tvOrderDshDot;
        @BindView(R.id.tv_order_ywc_dot)
        TextView tvOrderYwcDot;
        @BindView(R.id.tv_order_sh_dot)
        TextView tvOrderShDot;
        @BindView(R.id.tv_mine_smrzvalue)
        TextView tvMineSmrzValue;
        public ContentViewHolder(View headView) {
            ButterKnife.bind(this, headView);
        }

        @OnClick(R.id.rl_mine_yhq)
        public void goCoupon(){
            // 我的优惠券

        }
        @OnClick(R.id.rl_mine_smrz)
        public void goAuth(){
            //实名认证

        }
        @OnClick(R.id.rl_mine_bqk)
        public void goBanquanku(){
            //版权库
        }
        @OnClick(R.id.rl_mine_wdsc)
        public void goCollection(){
            // 我的收藏
        }
        @OnClick(R.id.rl_mine_sybz)
        public void goHelp(){
            // 使用帮助
        }
        @OnClick(R.id.rl_mine_zxkf)
        public void goService(){
            // 联系客服
        }
        @OnClick(R.id.rl_mine_setting)
        public void goSetting(){
            //去设置页面
            Intent intent=new Intent(activity, SettingActivity.class);
            ActivityUtil.getInstance().start(activity,intent);
        }
        @OnClick(R.id.rl_order_all)
        public void goOrderAll(){
            //全部订单
        }
        @OnClick(R.id.ll_order_dfk)
        public void goOrderDFK(){
            //待付款
        }
        @OnClick(R.id.ll_order_dfh)
        public void goOrderDFH(){
            //待发货
        }
        @OnClick(R.id.ll_order_dsh)
        public void goOrderDSH(){
            //待收货
        }
        @OnClick(R.id.ll_order_ywc)
        public void goOrderYWC(){
            //已完成
        }
        @OnClick(R.id.ll_order_sh)
        public void goOrderSH(){
            //售后
        }
    }

    private void addDotListener() {
        contentViewHolder.tvOrderDfkDot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentViewHolder.tvOrderDfkDot.getLayoutParams();
                params.height = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                params.width = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                contentViewHolder.tvOrderDfkDot.setLayoutParams(params);
                contentViewHolder.tvOrderDfkDot.setGravity(Gravity.CENTER);
                if (s == null || s.length() == 0 || "0".equals(s)) {
                    contentViewHolder.tvOrderDfkDot.setVisibility(View.GONE);
                } else if (s.toString().contains("+")) {
                    params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                }
            }
        });

        contentViewHolder.tvOrderDfhDot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentViewHolder.tvOrderDfhDot.getLayoutParams();
                params.height = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                params.width = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                contentViewHolder.tvOrderDfhDot.setLayoutParams(params);
                contentViewHolder.tvOrderDfhDot.setGravity(Gravity.CENTER);
                if (s == null || s.length() == 0 || "0".equals(s)) {
                    contentViewHolder.tvOrderDfhDot.setVisibility(View.GONE);
                } else if (s.toString().contains("+")) {
                    params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                }
            }
        });
        contentViewHolder.tvOrderDshDot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentViewHolder.tvOrderDshDot.getLayoutParams();
                params.height = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                params.width = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                contentViewHolder.tvOrderDshDot.setLayoutParams(params);
                contentViewHolder.tvOrderDshDot.setGravity(Gravity.CENTER);
                if (s == null || s.length() == 0 || "0".equals(s)) {
                    contentViewHolder.tvOrderDshDot.setVisibility(View.GONE);
                } else if (s.toString().contains("+")) {
                    params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                }
            }
        });

        contentViewHolder.tvOrderYwcDot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentViewHolder.tvOrderYwcDot.getLayoutParams();
                params.height = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                params.width = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                contentViewHolder.tvOrderYwcDot.setLayoutParams(params);
                contentViewHolder.tvOrderYwcDot.setGravity(Gravity.CENTER);
                if (s == null || s.length() == 0 || "0".equals(s)) {
                    contentViewHolder.tvOrderYwcDot.setVisibility(View.GONE);
                } else if (s.toString().contains("+")) {
                    params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                }
            }
        });

        contentViewHolder.tvOrderShDot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentViewHolder.tvOrderShDot.getLayoutParams();
                params.height = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                params.width = (int) getResources().getDimension(R.dimen.my_mihui_wait_dot);
                contentViewHolder.tvOrderShDot.setLayoutParams(params);
                contentViewHolder.tvOrderShDot.setGravity(Gravity.CENTER);
                if (s == null || s.length() == 0 || "0".equals(s)) {
                    contentViewHolder.tvOrderShDot.setVisibility(View.GONE);
                } else if (s.toString().contains("+")) {
                    params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserAccountInfo();
    }
}
