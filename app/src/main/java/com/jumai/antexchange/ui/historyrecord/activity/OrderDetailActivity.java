package com.jumai.antexchange.ui.historyrecord.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName OrderDetailActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/11
 * @Version 1.0
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailView, OrderDetailPresenter> implements OrderDetailView {
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_average_price)
    TextView tvAveragePrice;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_remain)
    TextView tvRemain;
    @BindView(R.id.tv_handling_fee)
    TextView tvHandlingFee;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
    }
}
