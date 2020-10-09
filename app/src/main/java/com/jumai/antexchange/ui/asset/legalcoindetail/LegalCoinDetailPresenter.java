package com.jumai.antexchange.ui.asset.legalcoindetail;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.base.BaseRefreshPresenter;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.ui.asset.coindetail.CoinDetailView;

import javax.inject.Inject;

/**
 * @ClassName CoinDetailPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class LegalCoinDetailPresenter extends BaseRefreshPresenter<LegalCoinDetailView> {
    private AppApi mAppApi;

    @Inject
    public LegalCoinDetailPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    @Override
    protected void getRefreshData(boolean isRefresh) {

    }
}
