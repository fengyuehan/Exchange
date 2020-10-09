package com.jumai.antexchange.utils.net.intercepter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yf on 2017/11/28.
 * Email：yunfei10306@163.com
 * 添加请求头
 */

public class HeadInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .header("app", "1")
                .header("deviceNo", "122121")
                .method(request.method(), request.body()).build();
        return chain.proceed(request);
    }
}
