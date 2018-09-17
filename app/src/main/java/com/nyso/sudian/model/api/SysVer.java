/**
 *
 */
package com.nyso.sudian.model.api;

import java.io.Serializable;

/**
 * @author wangchao
 * @Des
 * @date 2016-12-21
 */
public class SysVer implements Serializable {

    private int id;

    // 版本类型 ( 1-ios 2-andriod)
//    private int verType;

    // 版本号
//    private String verNo;

    // 备注
    private String remark;


    // 更新地址
    private String downloadUrl;

    // 更新标志(false:不强制，true：强制更新)
    private boolean updateFlag;

    // 版本值
    private int verValue;

    public SysVer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public int getVerValue() {
        return verValue;
    }

    public void setVerValue(int verValue) {
        this.verValue = verValue;
    }
}
