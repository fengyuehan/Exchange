package com.jumai.antexchange.ui.mine.set;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName SetActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class SetActivity extends BaseActivity<SetView, SetPresenter> implements SetView {
    @BindView(R.id.iv_jump)
    ImageView ivJump;
    @BindView(R.id.tv_language_content)
    TextView tvLanguageContent;
    @BindView(R.id.tv_price_content)
    TextView tvPriceContent;
    @BindView(R.id.tv_quote_content)
    TextView tvQuoteContent;
    @BindView(R.id.tv_ups_content)
    TextView tvUpsContent;
    @BindView(R.id.tv_switch)
    Switch tvSwitch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
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

    @OnClick({R.id.iv_jump, R.id.iv_price_jump, R.id.iv_quote_jump, R.id.iv_ups_jump, R.id.iv_net_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jump:

                break;
            case R.id.iv_price_jump:

                break;
            case R.id.iv_quote_jump:

                break;
            case R.id.iv_ups_jump:

                break;
            case R.id.iv_net_jump:

                break;
        }
    }
}
