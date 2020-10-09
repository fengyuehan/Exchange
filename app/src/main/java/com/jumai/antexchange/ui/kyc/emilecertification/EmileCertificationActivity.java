package com.jumai.antexchange.ui.kyc.emilecertification;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.EmileSafetyVerificationDialog;
import com.jumai.antexchange.view.VertificationSuccessCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName CountryCertificationActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class EmileCertificationActivity extends BaseActivity<EmileCertificationView, EmileCertificationPresenter> implements EmileCertificationView {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_emile)
    EditText etEmile;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_emile)
    View viewEmile;

    private EmileSafetyVerificationDialog mEmileSafetyVerificationDialog;
    private String mPhone;
    private String mEmile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kyc_emile_certification;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        viewEmile.setSelected(false);
        mPhone = tvPhone.getText().toString().trim();
        mEmile = etEmile.getText().toString().trim();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etEmile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    ivJump.setEnabled(true);
                    viewEmile.setSelected(true);
                } else {
                    ivJump.setEnabled(false);
                    viewEmile.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_jump, R.id.iv_go_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jump:
                if (mEmileSafetyVerificationDialog == null) {
                    mEmileSafetyVerificationDialog = new EmileSafetyVerificationDialog(EmileCertificationActivity.this, mPhone, mEmile, new VertificationSuccessCallBack() {
                        @Override
                        public void callBack(String phoneCode, String emileCode) {

                        }
                    });
                }
                mEmileSafetyVerificationDialog.show();
                break;
            case R.id.iv_go_back:
                finish();
                break;
        }
    }
}
