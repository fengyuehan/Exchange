package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.BasePresenter;
import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.model.bean.AddressBean;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class AddressPresenter extends BasePresenter<AddressView> {

    private AppApi mAppApi;

    @Inject
    public AddressPresenter(AppApi appApi) {
        mAppApi = appApi;
    }

    public void getAddress() {
        ArrayList<AddressBean> data = new ArrayList<>();
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "舒姐的BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "猫的地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "猫的地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        data.add(new AddressBean("sdsads546a5s62das1da54d5a4s1a21242ewq", "周老板的交易所BTC地址", "OMNI链"));
        getView().setAddress(data);
    }
}
