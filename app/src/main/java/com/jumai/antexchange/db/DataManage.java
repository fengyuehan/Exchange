package com.jumai.antexchange.db;

import com.blankj.utilcode.util.SPUtils;

/**
 * @ClassName DataManage
 * @Description TODO
 * @Author user
 * @Date 2019/9/19
 * @Version 1.0
 */
public class DataManage {
    private static final String SSP_KEY = "configuration";

    public static void setIp(String ip) {
        SPUtils spUtils = SPUtils.getInstance(SSP_KEY);
        spUtils.put("ip", ip);
    }

    public static String getIp() {
        SPUtils spUtils = SPUtils.getInstance(SSP_KEY);
        return spUtils.getString("ip");
    }

    public static void setCoinName(String coinName) {
        SPUtils spUtils = SPUtils.getInstance(SSP_KEY);
        spUtils.put("coinName", coinName);
    }

    public static String getCoinName() {
        SPUtils spUtils = SPUtils.getInstance(SSP_KEY);
        return spUtils.getString("coinName");
    }

    public static void clearHistoryRecord(){
        SPUtils spUtils = SPUtils.getInstance(SSP_KEY);
        spUtils.clear();
    }
}
