package com.jumai.antexchange.model.bean;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：
 */
public class HomeFunctionBean {
    public String img;
    public String title;
    public boolean hot;
    public int resId;

    public HomeFunctionBean(String img, String title, boolean hot, int resId) {
        this.img = img;
        this.title = title;
        this.hot = hot;
        this.resId = resId;
    }

    public HomeFunctionBean(String img, String title, boolean hot) {
        this.img = img;
        this.title = title;
        this.hot = hot;
    }
}
