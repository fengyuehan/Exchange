package com.jumai.antexchange.ui.historyrecord.activity;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName HistoryRecordFragmentPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordPresenter extends BasePresenter<HistoryRecordView> {
    private AppApi mAppApi;

    @Inject
    public HistoryRecordPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getAllDelegateData(String coinType, String priceUnit, String status) {
    }

    public void getHistoryRecordData(String coinType, String priceUnit, String status) {

    }
}
