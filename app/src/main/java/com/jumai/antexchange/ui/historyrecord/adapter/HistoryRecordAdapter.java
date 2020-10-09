package com.jumai.antexchange.ui.historyrecord.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.HistoryRecordBean;

import java.util.List;

/**
 * @ClassName HistoryRecordAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordAdapter extends BaseQuickAdapter<HistoryRecordBean, BaseViewHolder> {
    public HistoryRecordAdapter(@Nullable List<HistoryRecordBean> data) {
        super(R.layout.item_history_record,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryRecordBean item) {
        helper.setText(R.id.tv_status,item.getStatus())
                .setText(R.id.tv_buy,item.getCoinName())
                .setText(R.id.tv_buy_coin_type,item.getCoinName())
                .setText(R.id.tv_time,item.getTime())
                .setText(R.id.tv_price,item.getEntrustmentPrice())
                .setText(R.id.tv_amount,item.getEntrustmentQuantity())
                .setText(R.id.tv_all_amount,item.getAmount())
                .setText(R.id.tv_average_price,item.getAveragePrice())
                .setText(R.id.tv_buy_amount,item.getTotalTransactionVolume());
        helper.addOnClickListener(R.id.ll_jump);
    }
}
