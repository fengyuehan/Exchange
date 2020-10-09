package com.jumai.antexchange.ui.asset.transferrecord.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.TransferCoinRecordBean;

import java.util.List;

/**
 * @ClassName TransferCoinRecordAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferCoinRecordAdapter extends BaseQuickAdapter<TransferCoinRecordBean, BaseViewHolder> {
    public TransferCoinRecordAdapter(@Nullable List<TransferCoinRecordBean> data) {
        super(R.layout.item_transfer_record,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TransferCoinRecordBean item) {
        helper.setText(R.id.tv_name,item.getCoinName())
                .setText(R.id.tv_time,item.getTime())
                .setText(R.id.tv_amount,item.getAmount())
                .setText(R.id.tv_type,item.getType());
    }
}
