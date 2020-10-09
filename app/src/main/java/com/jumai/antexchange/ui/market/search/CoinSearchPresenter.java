package com.jumai.antexchange.ui.market.search;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName CoinSearchPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class CoinSearchPresenter extends BasePresenter<CoinSearchView> {
    private AppApi mAppApi;

    @Inject
    public CoinSearchPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
