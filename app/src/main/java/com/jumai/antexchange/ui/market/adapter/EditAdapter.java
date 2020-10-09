package com.jumai.antexchange.ui.market.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.EditCoinBean;

import java.util.List;

/**
 * @ClassName EditAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class EditAdapter extends BaseItemDraggableAdapter<EditCoinBean, BaseViewHolder> {

    public EditAdapter(@Nullable List<EditCoinBean> data) {
        super(R.layout.item_edit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditCoinBean item) {
        helper.setText(R.id.tv_coin_name, item.getCoinName())
                .setText(R.id.tv_coin_other_name, item.getOtherName())
                .setText(R.id.tv_coin_amount, item.getAmount()).addOnClickListener(R.id.iv_coin_choose);
        helper.getView(R.id.iv_coin_choose).setSelected(item.getIsSelect() != 0);
    }
}
