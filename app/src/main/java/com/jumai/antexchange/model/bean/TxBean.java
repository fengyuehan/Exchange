package com.jumai.antexchange.model.bean;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class TxBean {
    public double price;
    public double amount;
    public int progress;

    public TxBean(double price, double amount, int progress) {
        this.price = price;
        this.amount = amount;
        this.progress = progress;
    }
}
