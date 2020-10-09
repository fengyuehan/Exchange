package com.jumai.antexchange.ui.areacode;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName AreaCodePresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class AreaCodePresenter extends BasePresenter<AreaCodeView> {
    private AppApi mAppApi;

    @Inject
    public AreaCodePresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getAreaCode(String keyword){

    }
}
