package com.jumai.antexchange.ui.main;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MainPresenter extends BasePresenter<MainView> {
    private AppApi mAppApi;

    @Inject
    public MainPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

}
