package com.jumai.antexchange.ui.mine.account_center;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.view.SecurityLevelView;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/10/9/009
 * 描述：
 */
public class AccountSettingPresenter extends BasePresenter<AccountSettingView> {

    private AppApi mAppApi;

    @Inject
    public AccountSettingPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getSecurityLevel() {
        getView().setLevel(SecurityLevelView.LEVEL_MEDIUM);
    }
}
