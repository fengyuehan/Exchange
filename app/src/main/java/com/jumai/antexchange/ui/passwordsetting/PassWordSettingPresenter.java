package com.jumai.antexchange.ui.passwordsetting;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName PassWordSettingPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class PassWordSettingPresenter extends BasePresenter<PassWordSettingView> {
    private AppApi mAppApi;

    @Inject
    public PassWordSettingPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void setPassword(String phone,String code,String password,String newPassword,String inviteCode){

    }
}
