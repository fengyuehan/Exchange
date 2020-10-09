package com.jumai.antexchange.ui.kyc.phonecertification;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.PhoneSafetyVerificationDialog;
import com.jumai.antexchange.ui.areacode.AreaCodeActivity;
import com.jumai.antexchange.ui.register.emileregister.EmileRegisterActivity;
import com.jumai.antexchange.view.VertificationSuccessCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName PhoneCertificationActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/19
 * @Version 1.0
 */
public class PhoneCertificationActivity extends BaseActivity<PhoneCertificationView, PhoneCertificationPresenter> implements PhoneCertificationView {
    @BindView(R.id.tv_area_code)
    TextView tvAreaCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_emile)
    TextView tvEmile;
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.view_phone)
    View viewPhone;

    private PhoneSafetyVerificationDialog mPhoneSafetyVerificationDialog;
    private String mPhone;
    private String mEmile;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_kyc_phone_certification;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        viewPhone.setSelected(false);
        mPhone = etPhone.getText().toString().trim();
        mEmile = tvEmile.getText().toString().trim();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    ivJump.setEnabled(true);
                    viewPhone.setSelected(true);
                } else {
                    ivJump.setEnabled(false);
                    viewPhone.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ll_area_code, R.id.iv_go_back, R.id.iv_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_area_code:
                startActivity(new Intent(PhoneCertificationActivity.this, AreaCodeActivity.class));
                break;
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.iv_jump:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        if (mPhoneSafetyVerificationDialog == null) {
            mPhoneSafetyVerificationDialog = new PhoneSafetyVerificationDialog(PhoneCertificationActivity.this, mPhone, mEmile, new VertificationSuccessCallBack() {
                @Override
                public void callBack(String phoneCode, String emileCode) {
                    //请求接口

                }
            });
        }
        mPhoneSafetyVerificationDialog.show();
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
}
