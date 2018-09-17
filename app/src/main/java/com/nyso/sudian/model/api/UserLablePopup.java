package com.nyso.sudian.model.api;

import java.io.Serializable;

/**
 * Created by 1 on 2018/2/1.
 */

public class UserLablePopup implements Serializable{
    private String popUpName;
    private String popUpImg;
    private String adrUrl;

    public String getPopUpName() {
        return popUpName;
    }

    public void setPopUpName(String popUpName) {
        this.popUpName = popUpName;
    }

    public String getPopUpImg() {
        return popUpImg;
    }

    public void setPopUpImg(String popUpImg) {
        this.popUpImg = popUpImg;
    }

    public String getAdrUrl() {
        return adrUrl;
    }

    public void setAdrUrl(String adrUrl) {
        this.adrUrl = adrUrl;
    }
}
