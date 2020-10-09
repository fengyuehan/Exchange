package com.jumai.antexchange.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.forgetpassword.ForgetPasswordActivity;
import com.jumai.antexchange.ui.register.phoneregister.PhoneRegisterActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.iv_account_close)
    ImageView ivAccountClose;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.iv_password_close)
    ImageView ivPasswordClose;
    @BindView(R.id.iv_eyes_open_or_close)
    ImageView ivEyesOpenOrClose;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_phone)
    View viewPhone;
    @BindView(R.id.view_password)
    View viewPassword;


    private String mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        ivEyesOpenOrClose.setSelected(false);
        ivJump.setSelected(false);
        ivJump.setClickable(false);
        viewPhone.setSelected(false);
        viewPassword.setSelected(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        etPhone.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    ivAccountClose.setVisibility(View.VISIBLE);
                    viewPhone.setSelected(true);
                } else {
                    ivAccountClose.setVisibility(View.GONE);
                    viewPhone.setSelected(false);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    ivPasswordClose.setVisibility(View.VISIBLE);
                    viewPassword.setSelected(true);
                } else {
                    ivPasswordClose.setVisibility(View.GONE);
                    viewPassword.setSelected(false);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void login(String text) {

    }


    @OnClick({R.id.iv_close, R.id.iv_jump, R.id.tv_register, R.id.tv_foget_password, R.id.iv_eyes_open_or_close, R.id.iv_password_close, R.id.iv_account_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_jump:
                String mPhone = etPhone.getText().toString().trim();
                String mPassWord = etPassword.getText().toString().trim();

                if (mPhone == null) {
                    Toast.makeText(this, "请输入电话号码或邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPassWord == null) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPassWord.length() < 8) {
                    Toast.makeText(this, "密码长度低于8位,请重新输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mPhone != null && mPassWord != null) {
                    ivJump.setSelected(true);
                    ivJump.setClickable(true);
                } else {
                    ivJump.setSelected(false);
                    ivJump.setClickable(false);
                }

                if (mPhone.contains("@")) {
                    mType = "2";
                } else {
                    mType = "1";
                }
                mPresenter.login(mPhone, mPassWord, mType);
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, PhoneRegisterActivity.class));
                break;
            case R.id.tv_foget_password:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.iv_eyes_open_or_close:
                if (ivEyesOpenOrClose.isSelected()) {
                    ivEyesOpenOrClose.setSelected(false);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivEyesOpenOrClose.setSelected(true);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.getText().length());
                break;
            case R.id.iv_password_close:
                etPassword.setText("");
                ivPasswordClose.setVisibility(View.GONE);
                break;
            case R.id.iv_account_close:
                etPhone.setText("");
                ivAccountClose.setVisibility(View.GONE);
                break;
        }
    }
}
