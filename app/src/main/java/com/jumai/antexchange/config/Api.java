package com.jumai.antexchange.config;

import android.text.TextUtils;

import com.jumai.antexchange.BuildConfig;
import com.jumai.antexchange.db.DataManage;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：
 */
public class Api {
    //服务器地址
    public static String HTTP_URL;
    //云端地址
    public static String WALLET_CLOUD_BASE_URL = "";
    //测试地址
    public static String WALLET_TEST_BASE_URL = "";
    //开发环境
    public static String WALLET_DEV_BASE_URL = "";

    static {
        if(BuildConfig.DEBUG){
            if (TextUtils.isEmpty(DataManage.getIp())){
                HTTP_URL = WALLET_TEST_BASE_URL;
            }else {
                HTTP_URL = DataManage.getIp();
            }
        } else {
            HTTP_URL = WALLET_CLOUD_BASE_URL;
        }
    }
}
