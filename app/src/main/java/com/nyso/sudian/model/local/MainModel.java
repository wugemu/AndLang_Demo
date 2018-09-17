package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.api.SysVer;
import com.nyso.sudian.model.api.UserLablePopup;

public class MainModel extends BaseLangViewModel {
    private SysVer sys;
    private UserLablePopup userLablePopup;
    private Subject subject;

    public SysVer getSys() {
        return sys;
    }

    public void setSys(SysVer sys) {
        this.sys = sys;
    }

    public UserLablePopup getUserLablePopup() {
        return userLablePopup;
    }

    public void setUserLablePopup(UserLablePopup userLablePopup) {
        this.userLablePopup = userLablePopup;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
