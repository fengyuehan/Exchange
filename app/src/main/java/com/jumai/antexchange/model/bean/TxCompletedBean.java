package com.jumai.antexchange.model.bean;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：成交
 */
public class TxCompletedBean {
    public String time;
    public double price;
    public double amount;

    public TxCompletedBean(String time, double price, double amount) {
        this.time = time;
        this.price = price;
        this.amount = amount;
    }
}
