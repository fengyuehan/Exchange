package com.jumai.antexchange.ui.transcation;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.model.bean.CommissionTxBean;
import com.jumai.antexchange.model.bean.TxBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public interface TransactionView extends IBaseView {
    void setBuyRv(List<TxBean> txBeans);

    void setSellRv(List<TxBean> txBeans);

    void setCommissionTxRecordsRv(List<CommissionTxBean> txBeans);
}
