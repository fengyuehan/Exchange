package com.jumai.antexchange.ui.register.phoneregister;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName PhoneRegisterPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class PhoneRegisterPresenter extends BasePresenter<PhoneRegisterView> {
    private AppApi mAppApi;

    @Inject
    public PhoneRegisterPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void verificationPhone(String phone){

    }

    public void getVerificationCode(){

    }
}
