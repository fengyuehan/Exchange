package com.jumai.antexchange.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.utils.TextWatchListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：安全验证密码
 */
public class PwdSafetyVerificationDialog extends BottomSheetDialog {

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.btn_next)
    Button btnNext;
    private BaseActivity mActivity;

    public PwdSafetyVerificationDialog(@NonNull Context context) {
        this(context, 0);
    }


    private PwdSafetyVerificationDialog(@NonNull Context context, int theme) {
        super(context, R.style.CustomDialog);
        initView(context);
    }

    private void initView(Context context) {
        setContentView(R.layout.dialog_pwd_safety_verification);
        ButterKnife.bind(this);
        mActivity = (BaseActivity) context;
        initListener();
    }

    private void initListener() {
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String text = etPwd.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    etPwd.setSelection(text.length());
                }
            }
        });

        etPwd.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                btnNext.setEnabled(!TextUtils.isEmpty(s));
            }
        });

    }

    @OnClick({R.id.tv_cancel, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.btn_next:
                break;
        }
    }
}
