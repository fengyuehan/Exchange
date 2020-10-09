package com.jumai.antexchange.ui.resetpassword;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName ResetPasswordPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/28
 * @Version 1.0
 */
public class ResetPasswordPresenter extends BasePresenter<ResetPasswordView> {
    private AppApi mAppApi;

    @Inject
    public ResetPasswordPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
    public void resetPassword(String phone,String code,String password,String newPassword){

    }
}
