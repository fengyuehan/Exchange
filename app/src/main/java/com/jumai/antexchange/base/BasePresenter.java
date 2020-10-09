package com.jumai.antexchange.base;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.lang.ref.WeakReference;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

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
}
