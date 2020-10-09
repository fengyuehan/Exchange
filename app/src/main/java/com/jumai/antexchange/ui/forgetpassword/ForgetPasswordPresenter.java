package com.jumai.antexchange.ui.forgetpassword;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName ForgetPasswordPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordView> {
    private AppApi mAppApi;

    @Inject
    public ForgetPasswordPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void foget(String phone,String type){}
}
