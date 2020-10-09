package com.jumai.antexchange.ui.market.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.QuotationBean;

import java.util.List;

/**
 * @ClassName QuotationRvAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/23
 * @Version 1.0
 */
public class QuotationRvAdapter extends BaseQuickAdapter<QuotationBean, BaseViewHolder> {
    public QuotationRvAdapter(@Nullable List<QuotationBean> data) {
        super(R.layout.item_quotation,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuotationBean item) {
        helper.setText(R.id.tv_name,item.getCoinName())
                .setText(R.id.tv_other_name,item.getCoinOtherName())
                .setText(R.id.tv_amount,item.getAmount())
                .setText(R.id.tv_money,item.getMoney())
                .setText(R.id.tv_price,item.getPrice())
                .setText(R.id.tv_fee,item.getFee());
    }
}
