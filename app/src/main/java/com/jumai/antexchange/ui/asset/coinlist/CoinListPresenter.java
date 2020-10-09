package com.jumai.antexchange.ui.asset.coinlist;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName CoinListPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public class CoinListPresenter extends BasePresenter<CoinListView> {
    private AppApi mAppApi;

    @Inject
    public CoinListPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getCoinListData(){

    }
    public void getSearchCoinListData(String str){

    }


}
