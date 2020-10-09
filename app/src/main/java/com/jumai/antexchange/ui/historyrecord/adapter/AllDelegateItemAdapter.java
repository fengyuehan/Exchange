package com.jumai.antexchange.ui.historyrecord.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.CoinUnitBean;

import java.util.List;

/**
 * @ClassName AllDelegateItemAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class AllDelegateItemAdapter extends BaseQuickAdapter<CoinUnitBean, BaseViewHolder> {
    public AllDelegateItemAdapter(@Nullable List<CoinUnitBean> data) {
        super(R.layout.item_price_unit,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinUnitBean item) {
        helper.setText(R.id.tv_coin_price_unit,item.getCoinPriceUnit());
    }
}
