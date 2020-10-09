package com.jumai.antexchange.ui.transcation;

import com.jumai.antexchange.base.BaseRefreshPresenter;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.model.bean.CommissionTxBean;
import com.jumai.antexchange.model.bean.TxBean;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class TransactionPresenter extends BaseRefreshPresenter<TransactionRefreshView> {
    private AppApi mAppApi;

    @Inject
    public TransactionPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getTxBuyData() {
        ArrayList<TxBean> txBeans = new ArrayList<>();
        txBeans.add(new TxBean(8596.26, 2.00, 20));
        txBeans.add(new TxBean(8294.56, 4.23, 50));
        txBeans.add(new TxBean(4589.23, 2.56, 70));
        txBeans.add(new TxBean(5689.23, 1.52, 20));
        txBeans.add(new TxBean(8569.12, 4.56, 90));
        getView().setBuyRv(txBeans);
    }

    public void getTxSellData() {
        ArrayList<TxBean> txBeans = new ArrayList<>();
        txBeans.add(new TxBean(8566.13, 4.00, 80));
        txBeans.add(new TxBean(8589.45, 8.85, 56));
        txBeans.add(new TxBean(4458.89, 2.36, 23));
        txBeans.add(new TxBean(5489.13, 7.56, 15));
        txBeans.add(new TxBean(4899.10, 8.00, 45));
        getView().setSellRv(txBeans);
    }

    public void getCommissionTxRecords(boolean var) {
        ArrayList<CommissionTxBean> txBeans = new ArrayList<>();
        txBeans.add(new CommissionTxBean());
        txBeans.add(new CommissionTxBean());
        txBeans.add(new CommissionTxBean());
        txBeans.add(new CommissionTxBean());
        txBeans.add(new CommissionTxBean());
        txBeans.add(new CommissionTxBean());
        if (var) {
            getView().refreshOrLoadMoreSuccess(txBeans);
        } else {
            getView().setCommissionTxRecordsRv(txBeans);
        }
    }

    @Override
    protected void getRefreshData(boolean isRefresh) {
        getCommissionTxRecords(true);
    }
}
