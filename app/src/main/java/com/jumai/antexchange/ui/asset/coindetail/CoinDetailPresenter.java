package com.jumai.antexchange.ui.asset.coindetail;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.base.BaseRefreshPresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName CoinDetailPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class CoinDetailPresenter extends BaseRefreshPresenter<CoinDetailView> {
    private AppApi mAppApi;

    @Inject
    public CoinDetailPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    @Override
    protected void getRefreshData(boolean isRefresh) {

    }
}
