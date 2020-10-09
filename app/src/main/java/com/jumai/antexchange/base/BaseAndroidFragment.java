package com.jumai.antexchange.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.jumai.antexchange.dialog.LoadingDialog;
import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public abstract class BaseAndroidFragment extends RxFragment implements IBaseView {
    private Unbinder mUnbinder;
    protected BaseActivity mActivity;
    private LoadingDialog mLoadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化mPresenter
        initInject();
        //初始化
        initView();
        initRefresh();
        initData();
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void inject();

    protected abstract void initView();

    protected void initRefresh() {}

    protected abstract void initData();

    protected abstract void initInject();
    protected void initListener() {}

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mActivity);
        }
        mLoadingDialog.show();
    }

    @Override
    public void closeLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.stopLoading();
        }
    }

    @Override
    public void onNetError(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onNetFailed(String msg) {
        ToastUtils.showShort(msg);
    }
}
