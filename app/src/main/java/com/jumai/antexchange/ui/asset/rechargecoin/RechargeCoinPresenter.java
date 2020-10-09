package com.jumai.antexchange.ui.asset.rechargecoin;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName RechargeCoinPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public class RechargeCoinPresenter extends BasePresenter<RechargeCoinView> {
    private AppApi mAppApi;

    @Inject
    public RechargeCoinPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

}
