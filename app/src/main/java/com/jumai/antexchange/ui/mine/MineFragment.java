package com.jumai.antexchange.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseFragment;
import com.jumai.antexchange.ui.asset.rechargecoin.RechargeCoinActivity;
import com.jumai.antexchange.ui.asset.transfer.TransferCoinActivity;
import com.jumai.antexchange.ui.kyc.KYCCertificationActivity;
import com.jumai.antexchange.ui.login.LoginActivity;
import com.jumai.antexchange.ui.mine.about.AboutUsActivity;
import com.jumai.antexchange.ui.mine.account_center.AccountSettingActivity;
import com.jumai.antexchange.ui.mine.set.SetActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView, AboutUsActivity.VersionCallBack {
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rl_go_to_login, R.id.ll_coin_charging, R.id.ll_withdraw_money, R.id.ll_transfer, R.id.fl_order_manage, R.id.fl_account_center, R.id.fl_about, R.id.fl_help_center, R.id.fl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_go_to_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.ll_coin_charging:
                startActivity(new Intent(getActivity(), RechargeCoinActivity.class));
                break;
            case R.id.ll_withdraw_money:

                break;
            case R.id.ll_transfer:
                startActivity(new Intent(getActivity(), TransferCoinActivity.class));
                break;
            case R.id.fl_order_manage:
                startActivity(new Intent(getActivity(), KYCCertificationActivity.class));
                break;
            case R.id.fl_account_center:
                startActivity(new Intent(mActivity, AccountSettingActivity.class));
                break;
            case R.id.fl_about:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.fl_help_center:

                break;
            case R.id.fl_setting:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
        }
    }

    @Override
    public void callBack(String version) {
        if (version != null) {
            tvVersion.setText("V " + version);
        }
    }
}
