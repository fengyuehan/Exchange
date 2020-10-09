package com.jumai.antexchange.ui.mine.account_center;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName TradeSettingPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/30
 * @Version 1.0
 */
public class TradeSettingPresenter extends BasePresenter<TradeSettingView> {
    private AppApi mAppApi;

    @Inject
    public TradeSettingPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
