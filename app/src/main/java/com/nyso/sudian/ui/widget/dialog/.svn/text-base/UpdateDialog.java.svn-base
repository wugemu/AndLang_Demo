package com.nyso.sudian.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyso.sudian.R;
import com.nyso.sudian.model.api.SysVer;
import com.nyso.sudian.util.BBCUtil;

/**
 * Created by 1 on 2018/2/1.
 */

public class UpdateDialog {
    private View overdiaView;
    private Dialog updateDialog;
    private Activity activity;
    private SysVer version;

    public UpdateDialog(Activity activity, SysVer version) {
        this.activity = activity;
        this.version = version;
        initView();

    }

    private void initView(){
        View overdiaView = View.inflate(activity, R.layout.dialog_update_version, null);
        TextView tvUpdate = (TextView) overdiaView.findViewById(R.id.tv_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //执行更新操作
                    if (BBCUtil.isAvilible(activity, "com.tencent.android.qqdownloader")) {
                        BBCUtil.launchAppDetail(activity.getApplication(), "com.nyso.supply", "com.tencent.android.qqdownloader");
                    } else {
                        BBCUtil.openWebBrowser(activity, version.getDownloadUrl());
                    }
                    if (!version.isUpdateFlag()) {
                        updateDialog.dismiss();
                    }

            }
        });
        TextView reamrk = (TextView) overdiaView.findViewById(R.id.tv_remark);
        ImageView ivClose = (ImageView) overdiaView.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });
        reamrk.setText(version.getRemark());
        updateDialog = new Dialog(activity, R.style.dialog_lhp2);
        updateDialog.setContentView(overdiaView);
        updateDialog.setCancelable(false);
        if (version.isUpdateFlag()) {
            ivClose.setVisibility(View.GONE);
        } else {
            ivClose.setVisibility(View.VISIBLE);
        }

        updateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (!version.isUpdateFlag()) {
                        dialog.dismiss();
                    }
                    return false;
                } else {
                    return true;
                }
            }
        });
        showDialog();
    }

    private void showDialog(){
        if(updateDialog!=null){
            updateDialog.show();
            Window win = updateDialog.getWindow();
            WindowManager m = activity.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = win.getAttributes(); // 获取对话框当前的参数值
            win.getDecorView().setPadding(0, 0, 0, 0);
            p.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 高度设置为屏幕的0.6
            p.width = d.getWidth(); // 宽度设置为屏幕的0.65
            win.setAttributes(p);
        }
    }

}
