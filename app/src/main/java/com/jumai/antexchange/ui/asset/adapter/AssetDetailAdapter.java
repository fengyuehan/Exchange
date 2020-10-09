package com.jumai.antexchange.ui.asset.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.AssetDetailBean;
import com.jumai.antexchange.utils.DataProcess;
import com.jumai.antexchange.utils.ImageUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author yf
 * @date 2019/12/18/018
 * 描述：资产详情
 */
public class AssetDetailAdapter extends BaseQuickAdapter<AssetDetailBean, BaseViewHolder> {
    private boolean mShowDetail = true;

    public AssetDetailAdapter(@Nullable List<AssetDetailBean> data) {
        super(R.layout.item_asset_detail, data);
    }

    public void setShowDetail(boolean showDetail) {
        mShowDetail = showDetail;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, AssetDetailBean item) {
        /*if (mShowDetail) {
            helper.setText(R.id.tv_available, DataProcess.keepFourDecimals(item.available))
                    .setText(R.id.tv_frozen, DataProcess.keepFourDecimals(item.frozen))
                    .setText(R.id.tv_cny, DataProcess.keepFourDecimals(item.cny))
                    .setText(R.id.tv_symbol, item.symbol);
        } else {
            helper.setText(R.id.tv_available, "******")
                    .setText(R.id.tv_frozen, "******")
                    .setText(R.id.tv_cny, "******")
                    .setText(R.id.tv_symbol, item.symbol);
        }
        ImageUtil.loadHolder(mContext, item.symbolImg, helper.getView(R.id.iv_coin_image), R.drawable.icon_universal_place);*/
    }
}
