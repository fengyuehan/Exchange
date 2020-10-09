package com.jumai.antexchange.ui.register.phoneregister;

import com.jumai.antexchange.base.IBaseView;

/**
 * @ClassName PhoneRegisterView
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public interface PhoneRegisterView extends IBaseView {
    void phoneVerification();
    void getCode();
    void error(String message);
}
