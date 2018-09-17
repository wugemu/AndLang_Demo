package com.nyso.sudian.model.local;

import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.nyso.sudian.model.api.UserAccount;

public class MineModel extends BaseLangViewModel {
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
