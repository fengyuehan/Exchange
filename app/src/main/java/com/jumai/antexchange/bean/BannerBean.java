package com.jumai.antexchange.bean;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

/**
 * @author yf
 * @date 2019/9/23/023
 * 描述：
 */
public class BannerBean extends SimpleBannerInfo {
    public String img;
    public String url;

    public BannerBean(String img, String url) {
        this.img = img;
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }

    @Override
    public String getXBannerTitle() {
        return null;
    }
}
