package com.jumai.antexchange.ui.areacode.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.AreaCodeBean;

import java.util.List;

/**
 * @ClassName AreaCodeAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class AreaCodeAdapter extends BaseQuickAdapter<AreaCodeBean, BaseViewHolder> {
    public AreaCodeAdapter(@Nullable List<AreaCodeBean> data) {
        super(R.layout.item_area_code,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AreaCodeBean item) {
        helper.setText(R.id.tv_country,item.getCountry())
                .setText(R.id.tv_code,String.format("+%s", item.getAreaCode()));
    }
}
