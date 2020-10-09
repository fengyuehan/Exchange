package com.jumai.antexchange.ui.mine.account_center;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

/**
 * @author yf
 * @date 2019/10/9/009
 * 描述：交易设置-->手机验证/邮箱验证
 */
public class PhoneEmailVerificationActivity extends BaseActivity<PhoneEmailVerificationView, PhoneEmailVerificationPresenter> implements PhoneEmailVerificationView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_email_verification;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.getSecurityLevel();
    }


}
