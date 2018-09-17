package com.nyso.sudian.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nyso.sudian.R;
import com.nyso.sudian.myinterface.ConfirmOKI;
import com.nyso.sudian.util.BBCUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1 on 2016/11/1.
 * 确认弹框
 */
public class ConfirmDialog {

    @BindView(R.id.dialog_cancel_btn)
    Button dialogCancelBtn;
    @BindView(R.id.dialog_ok_btn)
    Button dialogOkBtn;
    private Activity context;
    private Dialog overdialog;
    private ConfirmOKI oki;
    private Handler handler;
    private Integer what;
    private String title;
    private String btnOk;
    @BindView(R.id.TextTip)
    TextView tvTitle;

    public ConfirmDialog(Activity context, String title,ConfirmOKI oki) {
        this.context = context;
        this.oki = oki;
        this.title = title;
        initView();

    }

    public ConfirmDialog(Activity context, Handler handler, String title, Integer what) {
        this.what = what;
        this.context = context;
        this.handler = handler;
        this.title = title;
        initView();

    }

    public ConfirmDialog(Activity context, Handler handler, String title, String btnOk, Integer what) {
        this.what = what;
        this.context = context;
        this.handler = handler;
        this.title = title;
        this.btnOk=btnOk;
        initView();

    }




    private void initView() {
        View view = View.inflate(context, R.layout.dialog_confirm, null);
        ButterKnife.bind(this, view);
        overdialog = new Dialog(context, R.style.dialog_lhp);
        if (BBCUtil.isEmpty(title)) {
            title = "是否确认？";
        }
        if(!BBCUtil.isEmpty(btnOk)){
            dialogOkBtn.setText(btnOk);
        }
        tvTitle.setText(title);
        overdialog.setContentView(view);
      showDialog();
    }

    @OnClick(R.id.dialog_ok_btn)
    public void ok() {
        if (oki != null) {
            oki.executeOk();
        }
        if (handler != null) {
            handler.sendEmptyMessage(what);
        }
        cancelDialog();
    }

    public void showDialog() {
        if (overdialog != null) {
            overdialog.show();
            Window win = overdialog.getWindow();
            WindowManager m = context.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = win.getAttributes(); // 获取对话框当前的参数值
            win.getDecorView().setPadding(0, 0, 0, 0);
            p.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 高度设置为包裹内容
//            p.height = (int) (d.getHeight() * 0.4);
            p.width = (int)(d.getWidth()*0.7); // 宽度设置为屏幕的0.85
            win.setAttributes(p);

        }
    }
    @OnClick(R.id.dialog_cancel_btn)
    public void cancel() {
        cancelDialog();

    }

    public void cancelDialog() {
        if (overdialog != null) {
            overdialog.dismiss();
        }
    }

}
