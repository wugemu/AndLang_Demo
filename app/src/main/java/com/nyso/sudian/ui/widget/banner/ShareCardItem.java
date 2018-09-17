package com.nyso.sudian.ui.widget.banner;

import com.nyso.sudian.model.api.Subject;

import java.util.List;

/**
 * @author SoBan
 * @create 2017/4/13 11:20.
 */

public class ShareCardItem {

    private Subject data;
    private List<Subject> dataList;

    public Subject getData() {
        return data;
    }

    public void setData(Subject data) {
        this.data = data;
    }

    public List<Subject> getDataList() {
        return dataList;
    }

    public void setDataList(List<Subject> dataList) {
        this.dataList = dataList;
    }

    public static class ZSCardItem {
        private double todayIndex;
        private int amount;
        private int shopNum;
        private int memberNum;
        private String indexDate;

        public double getTodayIndex() {
            return todayIndex;
        }

        public void setTodayIndex(double todayIndex) {
            this.todayIndex = todayIndex;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getShopNum() {
            return shopNum;
        }

        public void setShopNum(int shopNum) {
            this.shopNum = shopNum;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }

        public String getIndexDate() {
            return indexDate;
        }

        public void setIndexDate(String indexDate) {
            this.indexDate = indexDate;
        }
    }


}
