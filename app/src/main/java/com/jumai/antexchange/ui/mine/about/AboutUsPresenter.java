package com.jumai.antexchange.ui.mine.about;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName AboutUsPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class AboutUsPresenter extends BasePresenter<AboutUsView> {
    private AppApi mAppApi;

    @Inject
    public AboutUsPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
