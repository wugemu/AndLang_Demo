package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.GoodsStandard;
import com.nyso.sudian.model.api.Subject;

import java.util.List;

public class LimitSaleModel extends BaseLangViewModel {
    public static final int TYPE_BANNERSALE=1;//banner+goods
    public static final int TYPE_GOOD=2;//good
    private int type;//商品展示类型
    private Subject subject;
    private GoodsStandard good;

    public static int getTypeBannersale() {
        return TYPE_BANNERSALE;
    }

    public static int getTypeGood() {
        return TYPE_GOOD;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public GoodsStandard getGood() {
        return good;
    }

    public void setGood(GoodsStandard good) {
        this.good = good;
    }
}
