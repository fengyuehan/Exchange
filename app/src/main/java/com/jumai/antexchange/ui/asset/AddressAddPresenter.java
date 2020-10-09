package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class AddressAddPresenter extends BasePresenter<AddressAddView> {

    private AppApi mAppApi;

    @Inject
    public AddressAddPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

}
