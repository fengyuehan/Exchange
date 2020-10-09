package com.jumai.antexchange.bean;

/**
 * @ClassName AllDelegateBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class AllDelegateBean {
    private String coinName;
    private String time;
    private String status;
    private String price;
    private String amount;
    private String actualTransaction;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStitus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualTransaction() {
        return actualTransaction;
    }

    public void setActualTransaction(String actualTransaction) {
        this.actualTransaction = actualTransaction;
    }
}
