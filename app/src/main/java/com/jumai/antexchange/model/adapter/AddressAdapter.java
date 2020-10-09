package com.jumai.antexchange.model.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.AddressBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/10/08/008
 * 描述：收款地址
 */
public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
    public AddressAdapter(@Nullable List<AddressBean> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean bean) {
        helper.setText(R.id.tv_memo, bean.memo)
                .setText(R.id.tv_address, bean.address)
                .setText(R.id.tv_chain_name, bean.chainName)
                .addOnClickListener(R.id.iv_delete);
    }
}
