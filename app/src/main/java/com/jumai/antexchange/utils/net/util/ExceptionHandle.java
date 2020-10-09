package com.jumai.antexchange.utils.net.util;

import android.net.ParseException;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.AntApplication;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import retrofit2.HttpException;


/**
 * Created by yf on 2017/11/29.
 * Email：yunfei10306@163.com
 * 描述：异常处理，和服务器约定的异常由于每个公司返回的Response结构都不一样这里就没有统一封装,可在回调onSuccess前处理
 */

public class ExceptionHandle {
    //未知错误
    private static final int UNKNOWN = 1000;
    //解析错误
    private static final int PARSE_ERROR = 1001;
    //网络错误
    private static final int NETWORD_ERROR = 1002;
    //证书出错
    private static final int SSL_ERROR = 1003;
    //网络无连接
    private static final int NO_NET = 1004;
    //未登录
    public static final int NO_LOGIN = 1005;

    private static ResponseThrowable ex;

    public static ResponseThrowable handleException(Throwable e) {
        if (ex == null) {
            ex = new ResponseThrowable();
        }
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort(R.string.please_confirm_net);
            ex.code = NO_NET;
            ex.message = AntApplication.getInstance().getString(R.string.please_confirm_net);
            return ex;
        }

        //协议HTTP异常
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex.code = httpException.code();
            ex.message = convertStatusCode(httpException);
        } else if (e instanceof JSONException || e instanceof JsonParseException || e instanceof ParseException) {
            ex.code = PARSE_ERROR;
            ex.message = AntApplication.getInstance().getString(R.string.tips_http_error1);
        } else if (e instanceof ConnectException) {
            ex.code = NETWORD_ERROR;
            ex.message = AntApplication.getInstance().getString(R.string.tips_http_error2);
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex.code = SSL_ERROR;
            ex.message = AntApplication.getInstance().getString(R.string.tips_http_error3);
        } else {
            ex.message = AntApplication.getInstance().getString(R.string.tips_http_error4);
            ex.code = UNKNOWN;
        }

        if (!ex.message.isEmpty()) {
            ToastUtils.showShort(ex.message);
        }

        return ex;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;
    }
}
