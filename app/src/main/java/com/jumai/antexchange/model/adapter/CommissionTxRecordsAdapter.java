package com.jumai.antexchange.model.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.CommissionTxBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：委托交易记录
 */
public class CommissionTxRecordsAdapter extends BaseQuickAdapter<CommissionTxBean, BaseViewHolder> {
    public CommissionTxRecordsAdapter(@Nullable List<CommissionTxBean> data) {
        super(R.layout.item_entrusted_transaction, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommissionTxBean bean) {

    }
}
