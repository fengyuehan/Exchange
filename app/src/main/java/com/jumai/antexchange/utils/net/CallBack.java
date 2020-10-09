package com.jumai.antexchange.utils.net;

import com.jumai.antexchange.utils.net.util.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by yf on 2017/11/29.
 * Email：yunfei10306@163.com
 * 描述：联网接口结果回调
 */

public abstract class CallBack<T> implements Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onFail(int code, String message);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        ExceptionHandle.ResponseThrowable responseThrowable = ExceptionHandle.handleException(e);
        onFail(responseThrowable.code, responseThrowable.message);
    }

    @Override
    public void onComplete() {

    }
}
