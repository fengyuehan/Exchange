package com.jumai.antexchange.ui.mine.account_center;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.config.Global;
import com.jumai.antexchange.view.SecurityLevelView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/10/9/009
 * 描述：账户中心
 */
public class AccountSettingActivity extends BaseActivity<AccountSettingView, AccountSettingPresenter> implements AccountSettingView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_phone_status)
    TextView tvPhoneStatus;
    @BindView(R.id.tv_email_status)
    TextView tvEmailStatus;
    @BindView(R.id.tv_asset_pwd_status)
    TextView tvAssetPwdStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_setting;
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
        mPresenter.getSecurityLevel();
    }

    @Override
    public void setLevel(int level) {
        switch (level) {
            default:
            case SecurityLevelView.LEVEL_LOW:
                tvLevel.setText("低");
                tvLevel.setTextColor(ContextCompat.getColor(this, R.color.color_F0486F));
                tvTips.setTextColor(ContextCompat.getColor(this, R.color.color_F0486F));
                break;
            case SecurityLevelView.LEVEL_MEDIUM:
                tvLevel.setText("中");
                tvLevel.setTextColor(ContextCompat.getColor(this, R.color.color_FFB521));
                tvTips.setTextColor(ContextCompat.getColor(this, R.color.color_FFB521));
                break;
            case SecurityLevelView.LEVEL_HIGH:
                tvLevel.setText("高");
                tvLevel.setTextColor(ContextCompat.getColor(this, R.color.color_16B887));
                tvTips.setTextColor(ContextCompat.getColor(this, R.color.color_16B887));
                break;
        }
    }

    @Override
    public void setStatus(int level) {

    }


    @OnClick({R.id.iv_back, R.id.fl_phone, R.id.fl_email, R.id.fl_asset_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fl_phone:
                startActivity(new Intent(this, PhoneEmailVerificationActivity.class).putExtra(Global.INTENT_FROM, 0));
                break;
            case R.id.fl_email:
                break;
            case R.id.fl_asset_pwd:
                startActivity(new Intent(this,TradeSettingActivity.class));
                break;
        }
    }
}
