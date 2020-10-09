package com.jumai.antexchange.bean;

import java.io.Serializable;

/**
 * @ClassName CoinDetailBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class CoinDetailBean implements Serializable {
    private String amount;
    private String status;
    private String time;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
