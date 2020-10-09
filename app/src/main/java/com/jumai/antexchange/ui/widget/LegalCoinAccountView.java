package com.jumai.antexchange.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jumai.antexchange.R;
import com.jumai.antexchange.ui.asset.adapter.AccountsAdapter;
import com.jumai.antexchange.ui.asset.rechargecoin.RechargeCoinActivity;
import com.jumai.antexchange.ui.asset.transfer.TransferCoinActivity;
import com.jumai.antexchange.view.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/12/18/018
 * 描述：法币账户
 */
public class LegalCoinAccountView extends ConstraintLayout {

    @BindView(R.id.cb_show_hide)
    CheckBox cbShowHide;
    @BindView(R.id.tv_btc_amount)
    TextView tvBtcAmount;
    @BindView(R.id.tv_cny_amount)
    TextView tvCnyAmount;
    private Context mContext;
    private AccountsAdapter.AssetShowHideListener mAccountsShowHideListener;

    public LegalCoinAccountView(Context context) {
        super(context);
        initView(context);
    }

    public LegalCoinAccountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LegalCoinAccountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_legal_coin_account, this);
        ButterKnife.bind(this, view);
        mContext = context;
        setBackgroundResource(R.drawable.shape_legal_coin_account_bg);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.dp2px(context, 162));
        setLayoutParams(lp);
        initListener();
    }

    private void initListener() {
        cbShowHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvBtcAmount.setText("10.24890089 BTC");
                    tvCnyAmount.setText("≈887909.84 CNY");
                } else {
                    tvBtcAmount.setText("*****");
                    tvCnyAmount.setText("*****");
                }

                if (mAccountsShowHideListener != null) {
                    mAccountsShowHideListener.onLegalCoinAccountShowHide(isChecked);
                }
            }
        });
    }

    @OnClick({R.id.tv_coin_recharge, R.id.tv_coin_withdraw, R.id.tv_coin_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_coin_recharge:
                mContext.startActivity(new Intent(mContext, RechargeCoinActivity.class));
                break;
            case R.id.tv_coin_withdraw:
                break;
            case R.id.tv_coin_transfer:
                mContext.startActivity(new Intent(mContext, TransferCoinActivity.class));
                break;
        }
    }

    public void setAccountsShowHideListener(AccountsAdapter.AssetShowHideListener accountsShowHideListener) {
        mAccountsShowHideListener = accountsShowHideListener;
    }
}
