package com.jumai.antexchange.ui.mine.set;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;

import javax.inject.Inject;

/**
 * @ClassName SetPresenter
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class SetPresenter extends BasePresenter<SetView> {
    private AppApi mAppApi;

    @Inject
    public SetPresenter(AppApi appApi) {
        mAppApi = appApi;
    }
}
