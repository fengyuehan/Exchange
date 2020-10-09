package com.jumai.antexchange.ui.main;

import android.os.Bundle;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.widget.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.nvb)
    NavigationBar nvb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        nvb.init(R.id.fl_container);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void Upgrade() {

    }
}
