package com.jumai.antexchange.ui.register.emileregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.login.LoginActivity;
import com.jumai.antexchange.ui.register.phoneregister.PhoneRegisterActivity;
import com.jumai.antexchange.ui.verification.SafeVerificationActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName EmileRegisterActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class EmileRegisterActivity extends BaseActivity<EmileRegisterView, EmileRegisterPresenter> implements EmileRegisterView {

    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.iv_account_close)
    ImageView ivAccountClose;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_emile)
    View viewEmile;

    String mEmile;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_emile_register;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        ivJump.setSelected(false);
        ivJump.setClickable(false);
        viewEmile.setSelected(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etPhone.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    ivAccountClose.setVisibility(View.VISIBLE);
                    viewEmile.setSelected(true);
                } else {
                    ivAccountClose.setVisibility(View.GONE);
                    viewEmile.setSelected(false);
                }
            }
        });
    }

    @OnClick({R.id.iv_close, R.id.iv_account_close, R.id.iv_jump, R.id.tv_login, R.id.tv_phone_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_account_close:
                etPhone.setText("");
                ivAccountClose.setVisibility(View.GONE);
                break;
            case R.id.iv_jump:
                mEmile = etPhone.getText().toString().trim();
                if (mEmile != null) {
                    ivJump.setSelected(true);
                    ivJump.setClickable(true);
                } else {
                    ivJump.setSelected(false);
                    ivJump.setClickable(false);
                }
                mPresenter.verificationEmile(mEmile);
                break;
            case R.id.tv_login:
                startActivity(new Intent(EmileRegisterActivity.this, LoginActivity.class));
                break;
            case R.id.tv_phone_register:
                startActivity(new Intent(EmileRegisterActivity.this, PhoneRegisterActivity.class));
                break;
        }
    }

    @Override
    public void getEmileCodeSuccess() {
        mPresenter.getVerificationCode();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(EmileRegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCode() {
        startActivity(new Intent(EmileRegisterActivity.this, SafeVerificationActivity.class).putExtra("phone", mEmile).putExtra("type", "1"));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
