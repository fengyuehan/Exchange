package com.jumai.antexchange.ui.splash;

import android.content.Intent;
import android.widget.TextView;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
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

    @OnClick(R.id.tv)
    public void onViewClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }


}
