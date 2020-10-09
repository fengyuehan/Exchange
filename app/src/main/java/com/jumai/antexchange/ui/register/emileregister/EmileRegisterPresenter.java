package com.jumai.antexchange.ui.register.emileregister;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName EmileRegisterPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class EmileRegisterPresenter extends BasePresenter<EmileRegisterView> {
    private AppApi mAppApi;

    @Inject
    public EmileRegisterPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void verificationEmile(String emile){

    }

    public void getVerificationCode(){

    }

}
