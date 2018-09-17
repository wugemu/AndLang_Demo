package com.nyso.sudian.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.test.andlang.util.ActivityUtil;
import com.example.test.andlang.util.imageload.GlideUtil;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.UserLablePopup;
import com.nyso.sudian.ui.web.WebViewActivity;
import com.nyso.sudian.util.BBCUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1 on 2017/9/20.
 */

public class HomeDialog {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    private Activity context;
    private Dialog overdialog;
    private UserLablePopup subject;

    public HomeDialog(Activity context, UserLablePopup subject) {
        this.subject = subject;
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.dialog_home, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
            }
        });
        ButterKnife.bind(this, view);
        overdialog = new Dialog(context, R.style.dialog_lhp);
        overdialog.setContentView(view);
        GlideUtil.getInstance().displayByScreenWidth(context,subject.getPopUpImg(),ivImage, BBCUtil.getDisplayWidth(context));
        showDialog();
    }

    public void showDialog() {
        if (overdialog != null) {
            Window win = overdialog.getWindow();
            win.setGravity(Gravity.CENTER);
            WindowManager m = context.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = win.getAttributes(); // 获取对话框当前的参数值
            win.getDecorView().setPadding(0, 0, 0, 0);
            p.height = d.getHeight();
            p.width = d.getWidth(); // 宽度设置为屏幕的0.85
            win.setAttributes(p);
            overdialog.show();

        }
    }

    @OnClick(R.id.iv_image)
    public void ok() {
        //跳转到对应H5页面
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", subject.getAdrUrl());
        ActivityUtil.getInstance().start(context, intent);
        cancelDialog();
    }

    @OnClick(R.id.iv_close)
    public void cancel() {
        cancelDialog();

    }

    public void cancelDialog() {
        if (overdialog != null) {
            overdialog.dismiss();
        }
    }
}
