package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.BaseRefreshPresenter;
import com.jumai.antexchange.bean.AssetDetailBean;
import com.jumai.antexchange.config.AppApi;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class AssetPresenter extends BaseRefreshPresenter<AssetView> {
    private AppApi mAppApi;

    @Inject
    public AssetPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    @Override
    protected void getRefreshData(boolean isRefresh) {
        ArrayList<AssetDetailBean> data = new ArrayList<>();
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        data.add(new AssetDetailBean());
        getView().refreshOrLoadMoreSuccess(data);
    }
}
