package com.jumai.antexchange.model.bean;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class HomeRecommendBean {
    public String name;
    public double price;
    public double value;

    public HomeRecommendBean(String name, double price, double value) {
        this.name = name;
        this.price = price;
        this.value = value;
    }
}
