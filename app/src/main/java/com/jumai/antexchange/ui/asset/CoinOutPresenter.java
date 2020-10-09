package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/29/029
 * 描述：
 */
public class CoinOutPresenter extends BasePresenter<CoinOutView> {
    private AppApi mAppApi;

    @Inject
    public CoinOutPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
