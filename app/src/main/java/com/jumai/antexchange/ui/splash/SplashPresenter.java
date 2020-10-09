package com.jumai.antexchange.ui.splash;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName SplashPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/21
 * @Version 1.0
 */
public class SplashPresenter extends BasePresenter<SplashView> {
    private AppApi mAppApi;

    @Inject
    public SplashPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
