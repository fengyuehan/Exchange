package com.jumai.antexchange.ui.verification;

import com.jumai.antexchange.base.IBaseView;

/**
 * @ClassName SafeVerificationView
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public interface SafeVerificationView extends IBaseView {
    void verificationSuccess(String message);
    void onError();
}
