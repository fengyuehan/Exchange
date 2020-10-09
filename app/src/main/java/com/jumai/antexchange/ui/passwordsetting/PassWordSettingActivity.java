package com.jumai.antexchange.ui.passwordsetting;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.login.LoginActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName PassWordSettingActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/27
 * @Version 1.0
 */
public class PassWordSettingActivity extends BaseActivity<PassWordSettingView, PassWordSettingPresenter> implements PassWordSettingView {

    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.iv_password_close)
    ImageView ivPasswordClose;
    @BindView(R.id.iv_eyes_open_or_close)
    ImageView ivEyesOpenOrClose;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.et_new_password)
    TextInputEditText etNewPassword;
    @BindView(R.id.iv_password_close1)
    ImageView ivPasswordClose1;
    @BindView(R.id.iv_eyes_open_or_close1)
    ImageView ivEyesOpenOrClose1;
    @BindView(R.id.tv_tips_not_same)
    TextView tvTipsNotSame;
    @BindView(R.id.et_invite_password)
    TextInputEditText etInvitePassword;
    @BindView(R.id.iv_invite_password_close)
    ImageView ivInvitePasswordClose;
    @BindView(R.id.iv_coin_choose)
    ImageView ivCoinChoose;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_password)
    View viewPassword;
    @BindView(R.id.view_new_password)
    View viewNewPassword;
    @BindView(R.id.view_inivte_code)
    View viewInivteCode;

    private String mPhone;
    private String mCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra("phone");
        mCode = getIntent().getStringExtra("code");
        ivEyesOpenOrClose.setSelected(false);
        ivEyesOpenOrClose1.setSelected(false);
        ivCoinChoose.setSelected(false);
        ivJump.setSelected(false);
        ivJump.setClickable(false);
        viewPassword.setSelected(false);
        viewNewPassword.setSelected(false);
        viewInivteCode.setSelected(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etPassword.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    ivPasswordClose.setVisibility(View.VISIBLE);
                    viewPassword.setSelected(true);
                    if (isMatch(s.toString())) {
                        tvTips.setVisibility(View.GONE);
                    } else {
                        tvTips.setVisibility(View.VISIBLE);
                    }
                } else {
                    viewPassword.setSelected(false);
                    tvTips.setVisibility(View.VISIBLE);
                    ivPasswordClose.setVisibility(View.GONE);
                }
            }
        });
        etNewPassword.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    ivPasswordClose1.setVisibility(View.VISIBLE);
                    viewNewPassword.setSelected(true);
                    if (isMatch(s.toString())) {
                        tvTipsNotSame.setVisibility(View.GONE);
                    } else {
                        tvTipsNotSame.setVisibility(View.VISIBLE);
                    }
                } else {
                    viewNewPassword.setSelected(false);
                    tvTipsNotSame.setVisibility(View.VISIBLE);
                    ivPasswordClose1.setVisibility(View.GONE);
                }
            }
        });
        etInvitePassword.addTextChangedListener(new TextWatchListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    viewInivteCode.setSelected(true);
                    ivInvitePasswordClose.setVisibility(View.VISIBLE);
                } else {
                    viewInivteCode.setSelected(false);
                    ivInvitePasswordClose.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.iv_password_close, R.id.iv_eyes_open_or_close, R.id.iv_password_close1, R.id.iv_eyes_open_or_close1, R.id.iv_coin_choose, R.id.tv_user_agreement, R.id.iv_jump, R.id.iv_invite_password_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_password_close:
                etPassword.setText("");
                ivPasswordClose.setVisibility(View.GONE);
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
            case R.id.iv_password_close1:
                etNewPassword.setText("");
                ivPasswordClose1.setVisibility(View.GONE);
                break;
            case R.id.iv_eyes_open_or_close1:
                if (ivEyesOpenOrClose1.isSelected()) {
                    ivEyesOpenOrClose1.setSelected(false);
                    etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivEyesOpenOrClose1.setSelected(true);
                    etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                etNewPassword.setSelection(etPassword.getText().length());
                break;
            case R.id.iv_coin_choose:
                if (ivCoinChoose.isSelected()) {
                    ivCoinChoose.setSelected(false);
                } else {
                    ivCoinChoose.setSelected(true);
                }
                break;
            case R.id.iv_invite_password_close:
                etInvitePassword.setText("");
                ivInvitePasswordClose.setVisibility(View.GONE);
                break;
            case R.id.tv_user_agreement:

                break;
            case R.id.iv_jump:
                String password = etPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String inviteCode = etInvitePassword.getText().toString().trim();
                if (password == null || newPassword == null) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(newPassword)) {
                    Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isMatch(password) || !isMatch(newPassword)) {
                    Toast.makeText(this, "密码必须是8-20位字母与数字结合", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password != null && newPassword != null && ivCoinChoose.isSelected()) {
                    ivJump.setSelected(true);
                    ivJump.setClickable(true);
                } else {
                    ivJump.setSelected(false);
                    ivJump.setClickable(false);
                }

                if (mCode != null && mPhone != null) {
                    mPresenter.setPassword(mPhone, mCode, password, newPassword, inviteCode);
                }
                break;
        }
    }

    private boolean isMatch(String str) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public void setPasswordSuccess(String message) {
        Toast.makeText(PassWordSettingActivity.this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(PassWordSettingActivity.this, LoginActivity.class));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(PassWordSettingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
