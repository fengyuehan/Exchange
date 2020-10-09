package com.jumai.antexchange.bean;

/**
 * @ClassName QuotationBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/23
 * @Version 1.0
 */
public class QuotationBean {
    private String coinName;
    private String coinOtherName;
    private String amount;
    private String money;
    private String price;
    private String fee;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinOtherName() {
        return coinOtherName;
    }

    public void setCoinOtherName(String coinOtherName) {
        this.coinOtherName = coinOtherName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
