package com.jumai.antexchange.base;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.dagger.DaggerFragmentComponent;
import com.jumai.antexchange.dagger.FragmentComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import static com.jumai.antexchange.Constants.LOAD_MAX_SIZE;

/**
 * @author yf
 * @date 2019/6/25/025
 * 描述：适合需要刷新或加载更多的Fragment
 */
public abstract class BaseRefreshFragment<T, V extends IBaseRefreshView, P extends BaseRefreshPresenter<V>> extends BaseAndroidFragment implements IBaseRefreshView {
    @Inject
    protected P mPresenter;
    protected int mPageIndex = 1;
    protected int mSize = LOAD_MAX_SIZE;
    protected SmartRefreshLayout mRefreshLayout;
    private BaseQuickAdapter mRefreshAdapter;
    private List<T> mData;
    protected FragmentComponent mFragmentComponent;

    @Override
    protected void initInject() {
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(AntApplication.getAppComponent()).build();
        inject();
        mPresenter.attachView(getUiView());
    }

    @Override
    protected void initRefresh() {
        super.initRefresh();
        mRefreshLayout = setRefreshLayout();
        if (mRefreshLayout == null) {
            throw new IllegalStateException("please set refreshLayout!");
        }
        mRefreshAdapter = setRefreshAdapter();
        if (mRefreshAdapter == null) {
            throw new IllegalStateException("please set refreshAdapter!");
        }
        setSmartRefreshLayoutListener();
    }

    protected abstract SmartRefreshLayout setRefreshLayout();

    protected abstract BaseQuickAdapter setRefreshAdapter();

    private V getUiView() {
        return (V) this;
    }

    /**
     * 触发刷新或加载更多监听
     */
    private void setSmartRefreshLayoutListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshAdapter.removeAllFooterView();
                mPageIndex++;
                mPresenter.getRefreshData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPageIndex = 1;
                mPresenter.getRefreshData(true);
            }
        });
    }

    /**
     * 刷新或加载更多成功
     */
    @Override
    public void refreshOrLoadMoreSuccess(List data) {
        mData = data;
        if (mPageIndex == 1) {
            mRefreshAdapter.setNewData(data);
            mRefreshLayout.finishRefresh();
        } else {
            if (data != null) {
                mRefreshAdapter.addData(data);
            }
            mRefreshLayout.finishLoadMore();
        }
        //数据为空或者回来的数据小于最大允许回来数据，则没有更多数据了
        if (data == null || data.size() < mSize) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    /**
     * 请求成功，但是服务器code没返回0000
     */
    @Override
    public void refreshOrLoadMoreFail() {
        if (mPageIndex == 1) {
            mRefreshLayout.finishRefresh(false);
        } else {
            mRefreshLayout.finishLoadMore(false);
        }
    }

    /**
     * 请求失败
     */
    public void refreshOrLoadMoreError() {
        //请求失败mPageIndex已经加1，mPageIndex要回退
        if (mPageIndex > 1) {
            mPageIndex--;
            mRefreshLayout.finishLoadMore(false);
        } else {
            mRefreshLayout.finishRefresh(false);
        }
    }

    protected List<T> getListData() {
        return mData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    /**
     * 设置空布局
     */
    /*protected void setEmptyView() {
        //空布局
        if (mRefreshAdapter.getEmptyViewCount() == 0) {
            mRefreshAdapter.setEmptyView(getEmptyView());
        }
    }

    *//**
     * 设置空布局
     *//*
    protected void setEmptyWithImg(int resId) {
        //空布局
        if (mRefreshAdapter.getEmptyViewCount() == 0) {
            mRefreshAdapter.setEmptyView(getEmptyWithImg(resId));
        }
    }

    protected void setEmptyWithImgSmall(int resId) {
        //空布局
        if (mRefreshAdapter.getEmptyViewCount() == 0) {
            mRefreshAdapter.setEmptyView(getEmptyWithImgSmall(resId));
        }
    }

    private View getEmptyView() {
        return View.inflate(mActivity, R.layout.l_wallet_empty1, null);
    }*/
}
