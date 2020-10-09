package com.jumai.antexchange.ui.market;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MarketPresenter extends BasePresenter<MarketView> {
    private AppApi mAppApi;

    @Inject
    public MarketPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

}
