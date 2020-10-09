package com.jumai.antexchange.base;

import com.jumai.antexchange.dagger.DaggerFragmentComponent;
import com.jumai.antexchange.dagger.FragmentComponent;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends BaseAndroidFragment implements IBaseView {
    @Inject
    protected P mPresenter;
    protected FragmentComponent mFragmentComponent;

    @Override
    public void initInject() {
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(AntApplication.getAppComponent()).build();
        inject();
        mPresenter.attachView(getUiView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    private V getUiView() {
        return (V) this;
    }
}
