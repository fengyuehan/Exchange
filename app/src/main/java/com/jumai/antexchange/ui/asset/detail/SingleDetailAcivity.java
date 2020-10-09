package com.jumai.antexchange.ui.asset.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.CoinDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName SingleDetailAcivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/30
 * @Version 1.0
 */
public class SingleDetailAcivity extends BaseActivity<SingleDetailView, SingleDetailPresenter> implements SingleDetailView {
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_txid)
    TextView tvTxid;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    private CoinDetailBean mCoinDetailBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_asset_detail;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mCoinDetailBean = (CoinDetailBean) getIntent().getSerializableExtra("bean");
        if (mCoinDetailBean != null) {
            tvTime.setText(mCoinDetailBean.getTime());
            tvAmount.setText("+" + mCoinDetailBean.getAmount());
            tvStatus.setText(mCoinDetailBean.getStatus());
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
