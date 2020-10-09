package com.jumai.antexchange.ui.asset.detail;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName SingleDetailPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class SingleDetailPresenter extends BasePresenter<SingleDetailView> {
    private AppApi mAppApi;

    @Inject
    public SingleDetailPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
