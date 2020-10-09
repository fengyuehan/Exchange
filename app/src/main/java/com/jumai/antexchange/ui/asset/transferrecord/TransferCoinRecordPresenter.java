package com.jumai.antexchange.ui.asset.transferrecord;

import com.jumai.antexchange.base.BaseRefreshPresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName TransferCoinRecordPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferCoinRecordPresenter extends BaseRefreshPresenter<TransferCoinRecordView> {
    private AppApi mAppApi;
    private int mType;

    @Inject
    public TransferCoinRecordPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getCoinRecordData(int type,boolean var){
        mType = type;
    }

    @Override
    protected void getRefreshData(boolean isRefresh) {
            switch (mType){
                case 0:
                    getCoinRecordData(0,true);
                    break;
                case 1:
                    getCoinRecordData(1,true);
                    break;
                case 2:
                    getCoinRecordData(2,true);
                    break;
                default:
                    getCoinRecordData(0,true);
                    break;
            }
    }


}
