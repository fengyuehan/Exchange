package com.jumai.antexchange.ui.kyc.phonecertification;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName PhoneCertificationPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class PhoneCertificationPresenter extends BasePresenter<PhoneCertificationView> {
    private AppApi mAppApi;

    @Inject
    public PhoneCertificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
