package com.jumai.antexchange.ui.historyrecord.fragment;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName AllDelegatePresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class AllDelegatePresenter extends BasePresenter<AllDelegateView> {
    private AppApi mAppApi;

    @Inject
    public AllDelegatePresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getStatusData(){

    }
}
