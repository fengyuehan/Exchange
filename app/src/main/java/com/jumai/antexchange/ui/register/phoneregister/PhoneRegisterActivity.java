package com.jumai.antexchange.ui.register.phoneregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.areacode.AreaCodeActivity;
import com.jumai.antexchange.ui.login.LoginActivity;
import com.jumai.antexchange.ui.register.emileregister.EmileRegisterActivity;
import com.jumai.antexchange.ui.verification.SafeVerificationActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName PhoneRegisterActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class PhoneRegisterActivity extends BaseActivity<PhoneRegisterView, PhoneRegisterPresenter> implements PhoneRegisterView {

    @BindView(R.id.tv_area_code)
    TextView tvAreaCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_phone)
    View viewPhone;

    String mPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_register;
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
                    ivCancel.setVisibility(View.VISIBLE);
                    viewPhone.setSelected(true);
                } else {
                    ivCancel.setVisibility(View.GONE);
                    viewPhone.setSelected(false);
                }
            }
        });
    }

    @OnClick({R.id.iv_close, R.id.iv_down, R.id.iv_cancel, R.id.tv_emile_register, R.id.iv_jump, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_down:
                startActivity(new Intent(PhoneRegisterActivity.this, AreaCodeActivity.class));
                break;
            case R.id.iv_cancel:
                etPhone.setText("");
                ivCancel.setVisibility(View.GONE);
                break;
            case R.id.tv_emile_register:
                startActivity(new Intent(PhoneRegisterActivity.this, EmileRegisterActivity.class));
                break;
            case R.id.iv_jump:
                mPhone = etPhone.getText().toString().trim();
                if (mPhone != null) {
                    ivJump.setSelected(true);
                    ivJump.setClickable(true);
                } else {
                    ivJump.setSelected(false);
                    ivJump.setClickable(false);
                }
                mPresenter.verificationPhone(mPhone);
                break;
            case R.id.tv_register:
                startActivity(new Intent(PhoneRegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                String areaCode = data.getStringExtra(AreaCodeActivity.AREA_CODE);
                if (areaCode != null) {
                    tvAreaCode.setText(String.format("+%s", areaCode));
                }
            }
        }
    }

    @Override
    public void phoneVerification() {
        mPresenter.getVerificationCode();
    }

    @Override
    public void getCode() {
        startActivity(new Intent(PhoneRegisterActivity.this, SafeVerificationActivity.class).putExtra("phone", mPhone).putExtra("type", "1"));
        finish();
    }

    @Override
    public void error(String message) {
        Toast.makeText(PhoneRegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
