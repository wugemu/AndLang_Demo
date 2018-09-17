package com.nyso.sudian.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.example.test.andlang.util.ActivityUtil;
import com.nyso.sudian.ui.home.HomeActivity;
import com.nyso.sudian.ui.payresult.PayFinishActivity;
import com.nyso.sudian.util.NativeHelper;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    private String flag;//2=成功，1=失败

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, NativeHelper.getWXAPPID());

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (resp.errCode == 0) {//支付成功
                flag = "1";
            } else if (resp.errCode == -1) {//支付出错
                flag = "0";
            } else if (resp.errCode == -2) {//支付取消
                flag = "0";
            }
            Intent i2 = new Intent("PAY_RESULT");
            i2.putExtra("result", flag);
            i2.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            if ("1".equals(flag)) {//成功
                Intent intent = new Intent(WXPayEntryActivity.this, PayFinishActivity.class);
                intent.putExtra("type", "1");
                ActivityUtil.getInstance().start(WXPayEntryActivity.this, intent);
                LocalBroadcastManager.getInstance(this).sendBroadcast(i2);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("status",1);
                intent.putExtra("type", "2");
                ActivityUtil.getInstance().start(this, intent);
//                Intent intent = new Intent(WXPayEntryActivity.this, PayFinishActivity.class);
//                intent.putExtra("type", "2");
//                ActivityUtil.getInstance().start(WXPayEntryActivity.this, intent);
            }

            finish();
        }
    }
}