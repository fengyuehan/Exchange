package com.jumai.antexchange.base;

/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);

    void detachView();
}
