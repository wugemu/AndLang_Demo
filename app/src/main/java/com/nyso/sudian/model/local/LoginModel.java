package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.UserAccount;
import com.nyso.sudian.model.api.UserDetail;

import java.util.HashMap;
import java.util.List;

public class LoginModel extends BaseLangViewModel {
    private UserAccount userAccount;
    private UserDetail userDetail;
    private List<UserDetail> userDetailList;
    private HashMap<String, Object> data;
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<UserDetail> getUserDetailList() {
        return userDetailList;
    }

    public void setUserDetailList(List<UserDetail> userDetailList) {
        this.userDetailList = userDetailList;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
