package com.jumai.antexchange.ui.passwordsetting;

import com.jumai.antexchange.base.IBaseView;

/**
 * @ClassName PassWordSettingView
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public interface PassWordSettingView extends IBaseView {
    void setPasswordSuccess(String message);
    void onError(String message);
}
