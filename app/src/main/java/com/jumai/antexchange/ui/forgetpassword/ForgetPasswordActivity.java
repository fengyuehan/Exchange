package com.jumai.antexchange.ui.forgetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.verification.SafeVerificationActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName ForgetPasswordActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordView, ForgetPasswordPresenter> implements ForgetPasswordView {
    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.til)
    TextInputLayout til;
    @BindView(R.id.iv_account_close)
    ImageView ivAccountClose;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_phone)
    View viewPhone;

    private String mType;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        ivJump.setSelected(false);
        ivJump.setClickable(false);
        viewPhone.setSelected(false);
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
                    ivJump.setSelected(true);
                    ivJump.setClickable(true);
                    viewPhone.setSelected(true);
                } else {
                    viewPhone.setSelected(false);
                    ivAccountClose.setVisibility(View.GONE);
                    ivJump.setSelected(false);
                    ivJump.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.iv_close, R.id.iv_account_close, R.id.iv_jump})
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
                phone = etPhone.getText().toString().trim();
                if (phone.contains("@")) {
                    mType = "2";
                } else {
                    mType = "1";
                }
                mPresenter.foget(phone, mType);
                break;
        }
    }

    @Override
    public void getCodeSuccess(String msg) {
        Toast.makeText(ForgetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ForgetPasswordActivity.this, SafeVerificationActivity.class).putExtra("phone", phone).putExtra("type", "2"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
