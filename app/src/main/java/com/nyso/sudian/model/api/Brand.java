package com.nyso.sudian.model.api;

import java.io.Serializable;

/**
 * Created by 1 on 2017/2/16.
 */

public class Brand implements Serializable {
    private String brandName;	//品牌名称
    private String brandLogo;	//logo
    private int brandId;//品牌id
//    private String brandStory;//品牌故事
//    private int totalSales;//品牌总销量
    private int id;
    private String name;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
