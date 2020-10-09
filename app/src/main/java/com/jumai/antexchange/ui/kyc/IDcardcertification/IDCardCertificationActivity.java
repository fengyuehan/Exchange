package com.jumai.antexchange.ui.kyc.IDcardcertification;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

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
public class IDCardCertificationActivity extends BaseActivity<IDCardCertificationView, IDCardCertificationPresenter> implements IDCardCertificationView {
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.iv_card)
    ImageView ivCard;
    @BindView(R.id.tv_click_upload)
    TextView tvClickUpload;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.ll_card_back)
    LinearLayout llCardBack;
    @BindView(R.id.iv_card_back)
    ImageView ivCardBack;
    @BindView(R.id.ll_click_back)
    LinearLayout llClickBack;
    @BindView(R.id.ll_card_passport)
    LinearLayout llCardPassport;
    @BindView(R.id.iv_card_passport)
    ImageView ivCardPassport;
    @BindView(R.id.ll_click_passport)
    LinearLayout llClickPassport;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_idcard_certification;
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

    @OnClick({R.id.iv_go_back, R.id.tv_sure_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.tv_sure_commit:

                break;
        }
    }
}
