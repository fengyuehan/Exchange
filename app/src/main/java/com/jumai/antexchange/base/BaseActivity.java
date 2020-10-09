package com.jumai.antexchange.base;

import android.view.View;
import android.widget.TextView;

import com.jumai.antexchange.R;

import javax.inject.Inject;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseAndroidActivity implements IBaseView {
    @Inject
    protected P mPresenter;

    @Override
    public void initInject() {
        inject();
        mPresenter.attachView(getUiView());
    }

    protected V getUiView() {
        return (V) this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public View getEmptyView(int resId) {
        View view = View.inflate(this, R.layout.l_ant_exchange_empty, null);
        TextView tv = view.findViewById(R.id.tv_empty);
        tv.setText(resId);
        return view;
    }
}
