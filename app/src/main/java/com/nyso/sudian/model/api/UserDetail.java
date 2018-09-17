package com.nyso.sudian.model.api;

import java.io.Serializable;
import java.util.List;

public class UserDetail implements Serializable {
    private String realName;
    private String cardNo;
    private String balance;
    private double lockAmount;
    private List<String> urlList;
    private boolean personalAuth;
    private String inviteCode;
    private String address;
    private String nickName;
    private  String signature;
    private String userId;
    private List<TagModel> userTagsList;
    private String randomCode;
    private String headUrl;
    private int sex;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public double getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(double lockAmount) {
        this.lockAmount = lockAmount;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public boolean isPersonalAuth() {
        return personalAuth;
    }

    public void setPersonalAuth(boolean personalAuth) {
        this.personalAuth = personalAuth;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TagModel> getUserTagsList() {
        return userTagsList;
    }

    public void setUserTagsList(List<TagModel> userTagsList) {
        this.userTagsList = userTagsList;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
