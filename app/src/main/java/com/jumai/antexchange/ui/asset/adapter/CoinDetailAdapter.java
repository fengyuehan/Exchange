package com.jumai.antexchange.ui.asset.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.CoinDetailBean;

import java.util.List;

/**
 * @ClassName CoinDetailAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class CoinDetailAdapter extends BaseQuickAdapter<CoinDetailBean, BaseViewHolder> {
    public CoinDetailAdapter(@Nullable List<CoinDetailBean> data) {
        super(R.layout.item_financial_records,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CoinDetailBean item) {
        helper.setText(R.id.tv_amount,item.getAmount())
                .setText(R.id.tv_status,item.getStatus())
                .setText(R.id.tv_time,item.getTime());
    }
}
