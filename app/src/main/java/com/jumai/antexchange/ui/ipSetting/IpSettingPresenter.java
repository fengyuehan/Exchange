package com.jumai.antexchange.ui.ipSetting;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName IpSettingPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/9/19
 * @Version 1.0
 */
public class IpSettingPresenter extends BasePresenter<IpSettingView>  {
    private AppApi mAppApi;

    @Inject
    public IpSettingPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
