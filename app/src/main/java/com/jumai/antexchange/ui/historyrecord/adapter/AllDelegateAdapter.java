package com.jumai.antexchange.ui.historyrecord.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.AllDelegateBean;

import java.util.List;

/**
 * @ClassName AllDelegateAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class AllDelegateAdapter extends BaseQuickAdapter<AllDelegateBean, BaseViewHolder> {
    public AllDelegateAdapter(@Nullable List<AllDelegateBean> data) {
        super(R.layout.item_entrust,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllDelegateBean item) {
        helper.setText(R.id.tv_buy,item.getCoinName())
                .setText(R.id.tv_buy_coin_type,item.getCoinName())
                .setText(R.id.tv_time,item.getTime())
                .setText(R.id.tv_price,item.getPrice())
                .setText(R.id.tv_amount,item.getAmount())
                .setText(R.id.tv_buy_amount,item.getActualTransaction());
        helper.addOnClickListener(R.id.tv_cancel).setText(R.id.tv_cancel,item.getStatus());
    }
}
