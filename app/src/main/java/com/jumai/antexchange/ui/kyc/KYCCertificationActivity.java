package com.jumai.antexchange.ui.kyc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.kyc.countrycertification.CountryCertificationActivity;
import com.jumai.antexchange.ui.kyc.emilecertification.EmileCertificationActivity;
import com.jumai.antexchange.ui.kyc.phonecertification.PhoneCertificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName KYCCertificationActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/17
 * @Version 1.0
 */
public class KYCCertificationActivity extends BaseActivity<KYCCertificationView, KYCCertificationPresenter> implements KYCCertificationView {
    @BindView(R.id.tv_kyc)
    TextView tvKyc;
    @BindView(R.id.iv_certification)
    ImageView ivCertification;
    @BindView(R.id.tv_certification)
    TextView tvCertification;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_kyc2)
    TextView tvKyc2;
    @BindView(R.id.iv_certification2)
    ImageView ivCertification2;
    @BindView(R.id.tv_certification2)
    TextView tvCertification2;
    @BindView(R.id.rl_kyc2_certification)
    RelativeLayout rlKyc2Certification;
    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.rl_kyc1_certification)
    RelativeLayout rlKyc1Certification;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    private String mPhoneOrEmile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kyc_certification;
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

    }

    @OnClick({R.id.iv_go_back, R.id.rl_kyc1_certification, R.id.rl_kyc2_certification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.rl_kyc1_certification:
                if (mPhoneOrEmile.contains("@")) {
                    startActivity(new Intent(KYCCertificationActivity.this, PhoneCertificationActivity.class));
                } else {
                    startActivity(new Intent(KYCCertificationActivity.this, EmileCertificationActivity.class));
                }
                break;
            case R.id.rl_kyc2_certification:
                startActivity(new Intent(KYCCertificationActivity.this, CountryCertificationActivity.class));
                break;
        }
    }
}
