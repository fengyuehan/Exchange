package com.jumai.antexchange.bean;

import android.text.TextUtils;

import com.jumai.antexchange.R;

/**
 * @ClassName HotCoinBean
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class HotCoinBean{
    private String name;
    private String url;
    private String name_first_letter;

    public HotCoinBean() {

    }

    public void setHotCoinBean(String name) {
        this.name = name;
        name_first_letter = getFirstLetter(this.name);
    }

    private String getFirstLetter(String var) {
        if (TextUtils.isEmpty(var)) {
            return "#";
        }
        String letter = var.substring(0,1).toUpperCase();
        if (!letter.matches("[A-Z]")){
            return "#";
        }
        return letter;
    }

    public String getName_first_letter() {
        return name_first_letter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName_first_letter(String name_first_letter) {
        this.name_first_letter = name_first_letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
