package com.jumai.antexchange.ui.kyc;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName KYCCertificationPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class KYCCertificationPresenter extends BasePresenter<KYCCertificationView> {
    private AppApi mAppApi;

    @Inject
    public KYCCertificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
