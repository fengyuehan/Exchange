package com.jumai.antexchange.ui.mine.account_center;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName TradeSettingActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/30
 * @Version 1.0
 */
public class TradeSettingActivity extends BaseActivity<TradeSettingView, TradeSettingPresenter> implements TradeSettingView {
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.iv_eye_new)
    ImageView ivEyeNew;
    @BindView(R.id.tv_new_password_tips)
    TextView tvNewPasswordTips;
    @BindView(R.id.iv_jump)
    ImageView ivJump;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        ivJump.setEnabled(false);
        ivEye.setSelected(false);
        ivEyeNew.setSelected(false);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.iv_eye, R.id.iv_eye_new, R.id.iv_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_eye:
                if (ivEye.isSelected()) {
                    ivEye.setSelected(false);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivEye.setSelected(true);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.getText().length());
                break;
            case R.id.iv_eye_new:
                if (ivEyeNew.isSelected()) {
                    ivEyeNew.setSelected(false);
                    etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivEyeNew.setSelected(true);
                    etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                etNewPassword.setSelection(etNewPassword.getText().length());
                break;
            case R.id.iv_jump:
                String password = etPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(newPassword)){
                    ivJump.setEnabled(true);
                }else {
                    ivJump.setEnabled(false);
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(TradeSettingActivity.this,"",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newPassword)){
                    Toast.makeText(TradeSettingActivity.this,"",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.matches("^[a-zA-Z][0-9a-zA-Z]{8,32}$")){
                    tvTips.setVisibility(View.VISIBLE);
                    return;
                }

                if (!password.equals(newPassword)){
                    tvNewPasswordTips.setVisibility(View.VISIBLE);
                    return;
                }


                break;
        }
    }
}
