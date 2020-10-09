package com.jumai.antexchange.ui.mine.account_center;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/10/9/009
 * 描述：
 */
public class PhoneEmailVerificationPresenter extends BasePresenter<PhoneEmailVerificationView> {

    private AppApi mAppApi;

    @Inject
    public PhoneEmailVerificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getSecurityLevel() {
    }
}
