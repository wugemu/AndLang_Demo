package com.nyso.sudian.model.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/2/17.
 */

public class Category implements Serializable {
//    private int type;//一级类目id（筛选页面）
    private String categoryName;//一级类目名称
    private int id;//二级/三级分类Id
    private String name;//二级分类名称
    private String imgUrl;//三级分类图片地址
    private int categoryId;//一级类目id（分类页面）
    private String threeName;//三级分类名称
    private List<Category> threeCategoryList;//三级分类列表
    private boolean isShowMore;

    public Category() {
    }

//    public Category(Integer id, String name, String imgUrl) {
//        this.id = id;
//        this.name = name;
//        this.imgUrl = imgUrl;
//    }

//    public Category(Integer type, String categoryName) {
//        this.type = type;
//        this.categoryName = categoryName;
//    }

//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getThreeName() {
        return threeName;
    }

    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }

    public List<Category> getThreeCategoryList() {
        return threeCategoryList;
    }

    public void setThreeCategoryList(List<Category> threeCategoryList) {
        this.threeCategoryList = threeCategoryList;
    }

    public boolean isShowMore() {
        return isShowMore;
    }

    public void setShowMore(boolean showMore) {
        isShowMore = showMore;
    }
}
