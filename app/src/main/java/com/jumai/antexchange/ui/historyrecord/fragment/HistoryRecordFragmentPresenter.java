package com.jumai.antexchange.ui.historyrecord.fragment;

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
public class HistoryRecordFragmentPresenter extends BasePresenter<HistoryRecortFragmentView> {
    private AppApi mAppApi;

    @Inject
    public HistoryRecordFragmentPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
