package com.jumai.antexchange.model.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.TxBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class TxDataAdapter extends BaseQuickAdapter<TxBean, BaseViewHolder> {
    public TxDataAdapter(int layoutResId, @Nullable List<TxBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TxBean bean) {
        helper.setText(R.id.tv_price, String.valueOf(bean.price))
                .setText(R.id.tv_amount, String.valueOf(bean.amount))
                .setProgress(R.id.pb, bean.progress);
    }
}
