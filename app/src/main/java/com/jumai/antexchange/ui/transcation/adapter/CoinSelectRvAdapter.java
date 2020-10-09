package com.jumai.antexchange.ui.transcation.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.CoinSelectBean;

import java.util.List;

/**
 * @ClassName CoinSelectRvAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class CoinSelectRvAdapter extends BaseQuickAdapter<CoinSelectBean, BaseViewHolder> {

    public CoinSelectRvAdapter(@Nullable List<CoinSelectBean> data) {
        super(R.layout.item_coin_type,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinSelectBean item) {
        helper.setText(R.id.tv_coin,item.getCoinName())
                .setText(R.id.tv_other_coin,item.getCoinOtherName())
                .setText(R.id.tv_money,item.getCoinPrice());
    }
}
