package com.jumai.antexchange.bean;

/**
 * @ClassName CoinAccountBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/21
 * @Version 1.0
 */
public class CoinAccountBean {
    private String banalce;
    private String amount;
    private int type; // 1:币币 2：法币

    public String getBanalce() {
        return banalce;
    }

    public void setBanalce(String banalce) {
        this.banalce = banalce;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
