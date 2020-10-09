package com.jumai.antexchange.bean;

/**
 * @ClassName EditCoinBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class EditCoinBean {
    private String coinName;
    private String otherName;
    private String amount;
    private int isSelect;

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
