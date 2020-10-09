package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.IBaseRefreshView;
import com.jumai.antexchange.bean.AssetDetailBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public interface AssetView extends IBaseRefreshView {
    void setAssetDetailData(List<AssetDetailBean> data);
}
