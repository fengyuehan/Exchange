package com.jumai.antexchange.base;

import java.lang.ref.WeakReference;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public abstract class BaseRefreshPresenter<V extends IBaseRefreshView> implements IBasePresenter<V> {

    private WeakReference<V> mViewWf;

    @Override
    public void attachView(V view) {
        mViewWf = new WeakReference<>(view);
    }

    protected V getView() {
        return mViewWf == null ? null : mViewWf.get();
    }

    @Override
    public void detachView() {
        if (mViewWf != null) {
            mViewWf.clear();
            mViewWf = null;
        }
    }

    /**
     * 获取刷新/加载更多数据
     *
     * @param isRefresh 是否是刷新
     * @return 刷新/加载更多
     */
    protected abstract void getRefreshData(boolean isRefresh);
}
