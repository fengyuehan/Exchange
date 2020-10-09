package com.jumai.antexchange.ui.kyc.IDcardcertification;

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
public class IDCardCertificationPresenter extends BasePresenter<IDCardCertificationView> {
    private AppApi mAppApi;

    @Inject
    public IDCardCertificationPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
