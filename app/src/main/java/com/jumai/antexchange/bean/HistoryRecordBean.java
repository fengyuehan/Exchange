package com.jumai.antexchange.bean;

/**
 * @ClassName HistoryRecordBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordBean {
    private String status;
    private String time;
    private String coinName;
    private String entrustmentPrice;
    private String amount;
    private String entrustmentQuantity;
    private String totalTransactionVolume;
    private String averagePrice;

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

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getEntrustmentPrice() {
        return entrustmentPrice;
    }

    public void setEntrustmentPrice(String entrustmentPrice) {
        this.entrustmentPrice = entrustmentPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEntrustmentQuantity() {
        return entrustmentQuantity;
    }

    public void setEntrustmentQuantity(String entrustmentQuantity) {
        this.entrustmentQuantity = entrustmentQuantity;
    }

    public String getTotalTransactionVolume() {
        return totalTransactionVolume;
    }

    public void setTotalTransactionVolume(String totalTransactionVolume) {
        this.totalTransactionVolume = totalTransactionVolume;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }
}
