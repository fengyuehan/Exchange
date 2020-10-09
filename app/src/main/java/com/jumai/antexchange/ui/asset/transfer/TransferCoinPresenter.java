package com.jumai.antexchange.ui.asset.transfer;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName TransferCoinPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferCoinPresenter extends BasePresenter<TransferCoinView> {
    private AppApi mAppApi;

    @Inject
    public TransferCoinPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void transfer(String amount){

    }
}
