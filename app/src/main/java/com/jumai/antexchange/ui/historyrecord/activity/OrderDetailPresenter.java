package com.jumai.antexchange.ui.historyrecord.activity;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName OrderDetailPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/11
 * @Version 1.0
 */
public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    private AppApi mAppApi;

    @Inject
    public OrderDetailPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
