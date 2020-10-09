package com.jumai.antexchange.model.bean;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class AddressBean {
    public String address;
    public String memo;
    public String chainName;

    public AddressBean(String address, String memo, String chainName) {
        this.address = address;
        this.memo = memo;
        this.chainName = chainName;
    }
}
