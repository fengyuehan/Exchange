package com.jumai.antexchange.ui.kyc.countrycertification;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName CountryCertificationPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class CountryCertificationPresenter extends BasePresenter<CountryCertificationView> {
    private AppApi mAppApi;

    @Inject
    public CountryCertificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
