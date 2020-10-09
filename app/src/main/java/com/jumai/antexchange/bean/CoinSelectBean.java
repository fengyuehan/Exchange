package com.jumai.antexchange.bean;

/**
 * @ClassName CoinSelectBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class CoinSelectBean {
    private String coinName;
    private String coinOtherName;
    private String coinPrice;

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

    public String getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(String coinPrice) {
        this.coinPrice = coinPrice;
    }
}
