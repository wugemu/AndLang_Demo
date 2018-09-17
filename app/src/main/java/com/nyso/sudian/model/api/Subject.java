package com.nyso.sudian.model.api;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {
    private int id;
    private int themeId;//专题id
    private String title;//标题
    private String extendId;//国家产地id(countryId)
    private String url;//最里面跳转之后的大图
    private String url2;//Banner显示图片
    private int type;//banner类型
    private int sec;//广告倒计时（秒）
    //    private List<GoodsStandard> goodsStandList;//国家馆商品列表
    private int lastOffset;//滚动最终的位置
    private int lastPosition;
    private String adImgUrl;//banner详情页头图
    private int targetType;//0-h5,1-appbanner,2-app主题场,99,不跳转
    private String adrUrl;//跳转url
    private List<GoodsStandard> goodsList;//国家馆商品列表

    public List<GoodsStandard> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsStandard> goodsList) {
        this.goodsList = goodsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(int lastOffset) {
        this.lastOffset = lastOffset;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public String getAdImgUrl() {
        return adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getAdrUrl() {
        return adrUrl;
    }

    public void setAdrUrl(String adrUrl) {
        this.adrUrl = adrUrl;
    }
}
