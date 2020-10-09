package com.jumai.antexchange.ui.mine;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MinePresenter extends BasePresenter<MineView> {
    private AppApi mAppApi;

    @Inject
    public MinePresenter(AppApi appApi) {
        mAppApi = appApi;
    }

}
