package com.jumai.antexchange.ui.asset;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.model.bean.AddressBean;

import java.util.List;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public interface AddressView extends IBaseView {
    void setAddress(List<AddressBean> data);
}
