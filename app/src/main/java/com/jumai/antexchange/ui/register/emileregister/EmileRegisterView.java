package com.jumai.antexchange.ui.register.emileregister;

import com.jumai.antexchange.base.IBaseView;

/**
 * @ClassName EmileRegisterView
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public interface EmileRegisterView extends IBaseView {
    void getEmileCodeSuccess();
    void onError(String message);
    void getCode();
}
