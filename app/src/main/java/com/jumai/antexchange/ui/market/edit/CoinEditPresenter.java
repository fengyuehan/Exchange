package com.jumai.antexchange.ui.market.edit;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName CoinEditPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class CoinEditPresenter extends BasePresenter<CoinEditView> {
    private AppApi mAppApi;

    @Inject
    public CoinEditPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
