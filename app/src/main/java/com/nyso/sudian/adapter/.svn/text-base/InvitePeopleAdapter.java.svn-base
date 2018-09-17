package com.nyso.sudian.adapter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.andlang.andlangutil.BaseLangAdapter;
import com.example.test.andlang.andlangutil.BaseLangViewHolder;
import com.example.test.andlang.util.imageload.GlideUtil;
import com.example.test.andlang.util.imageload.ImageLoadUtils;
import com.nyso.sudian.R;
import com.nyso.sudian.model.api.UserDetail;

import java.util.List;

public class InvitePeopleAdapter extends BaseLangAdapter<UserDetail> {

    public InvitePeopleAdapter(Activity context, List<UserDetail> data) {
        super(context, R.layout.listview_invitepeople_item, data);
    }

    @Override
    public void convert(BaseLangViewHolder helper, int postion, UserDetail item) {
        ImageView mine_image=helper.getView(R.id.mine_image);
        TextView tv_invite_name=helper.getView(R.id.tv_invite_name);
        ImageView iv_userinfo_sex=helper.getView(R.id.iv_userinfo_sex);
        TextView tv_invite_code=helper.getView(R.id.tv_invite_code);

        ImageLoadUtils.doLoadCircleImageUrl(mine_image,item.getHeadUrl());
        tv_invite_name.setText(item.getNickName());
        if(item.getSex()==0){
            iv_userinfo_sex.setImageResource(R.mipmap.gril_tip);
        }else {
            iv_userinfo_sex.setImageResource(R.mipmap.boy_tip);
        }
        tv_invite_code.setText("邀请码:"+item.getRandomCode());
    }
}
