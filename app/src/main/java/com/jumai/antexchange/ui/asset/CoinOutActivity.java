package com.jumai.antexchange.ui.asset;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.dialog.DSelectedListener;
import com.jumai.antexchange.dialog.SafetyVerificationDialog;
import com.jumai.antexchange.dialog.TipsDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/29/029
 * 描述：
 */
public class CoinOutActivity extends BaseActivity<CoinOutView, CoinOutPresenter> implements CoinOutView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_symbol_img)
    ImageView ivSymbolImg;
    @BindView(R.id.tv_address_tip)
    TextView tvAddressTip;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_amount_tip)
    TextView tvAmountTip;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_symbol)
    TextView tvSymbol;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.tv_real_amount)
    TextView tvRealAmount;
    private TipsDialog mTipsDialog;
    private SafetyVerificationDialog mSafetyVerificationDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin_out;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText(R.string.title_coin_out);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.iv_address, R.id.iv_scan, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
            case R.id.iv_scan:
                break;
            case R.id.btn_next:
                showTips();
                break;
        }
    }


    private void showTips() {
        if (mTipsDialog == null) {
            mTipsDialog = new TipsDialog(this);
            mTipsDialog.setSelectedListener(mSelectedListener);
        }
        mTipsDialog.show();
    }

    private void showSafetyVerificationDialog() {
        if (mSafetyVerificationDialog == null) {
            mSafetyVerificationDialog = new SafetyVerificationDialog(this);
        }
        mSafetyVerificationDialog.show();
    }

    private DSelectedListener mSelectedListener = new DSelectedListener() {
        @Override
        public void onCancel() {

        }

        @Override
        public void onConfirm() {
            showSafetyVerificationDialog();
        }
    };
}
