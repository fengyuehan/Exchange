package com.jumai.antexchange.ui.verification;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName SafeVerificationPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class SafeVerificationPresenter extends BasePresenter<SafeVerificationView> {
    private AppApi mAppApi;

    @Inject
    public SafeVerificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void verification(String verification){

    }



}
