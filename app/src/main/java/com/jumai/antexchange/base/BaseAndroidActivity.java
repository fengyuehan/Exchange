package com.jumai.antexchange.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jumai.antexchange.R;
import com.jumai.antexchange.dagger.ActivityComponent;
import com.jumai.antexchange.dagger.DaggerActivityComponent;
import com.jumai.antexchange.dialog.LoadingDialog;
import com.jumai.antexchange.ui.widget.ICancelImmersion;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.Calendar;

import butterknife.ButterKnife;
import me.jessyan.autosize.AutoSizeCompat;

/**
 * @author yf
 * @date 2019/9/17/017
 * 描述：
 */
public abstract class BaseAndroidActivity extends RxAppCompatActivity implements IBaseView {

    protected ActivityComponent mActivityComponent;
    // 两次点击间隔不能少于1000ms
    private static final int MIN_DELAY_TIME = 1000;
    private long mLastClickTime;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //初始化mPresenter
        mActivityComponent = DaggerActivityComponent.builder().appComponent(AntApplication.getAppComponent()).build();
        initInject();
        //初始化
        setStatusBarTextBlack();
        initView();
        initRefresh();
        initData();
        initListener();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void inject();

    protected abstract void initView();

    protected void initRefresh() {
    }

    protected abstract void initData();

    protected void initListener() {
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void closeLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.stopLoading();
        }
    }


    /**
     * 设置状态栏为白底黑字
     */
    private void setStatusBarTextBlack() {
        if (!(this instanceof ICancelImmersion)) {
            ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).statusBarColor(R.color.white).fitsSystemWindows(true).init();
        }
    }

    /**
     * 隐藏输入法
     */
    public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view != null) {
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 开启输入法
     */
    public void showSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 开启输入法
     */
    public void showSoftKeyBoard(View v) {
        v.setFocusable(true);
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        //AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity((super.getResources()), 375, true);//如果有自定义需求就用这个方法
        return super.getResources();
    }

    protected boolean isFastClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - mLastClickTime > MIN_DELAY_TIME) {
            mLastClickTime = currentTime;
            return false;
        } else {
            return true;
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
