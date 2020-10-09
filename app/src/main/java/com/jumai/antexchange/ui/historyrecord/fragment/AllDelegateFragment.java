package com.jumai.antexchange.ui.historyrecord.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseFragment;
import com.jumai.antexchange.bean.AllDelegateBean;
import com.jumai.antexchange.ui.historyrecord.activity.HistoryRecordActivity;
import com.jumai.antexchange.ui.historyrecord.adapter.AllDelegateAdapter;
import com.jumai.antexchange.view.AllDelegateCallBack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @ClassName AllDelegateFragment
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class AllDelegateFragment extends BaseFragment<AllDelegateView, AllDelegatePresenter> implements AllDelegateView, HistoryRecordActivity.DelegateCallBack {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private AllDelegateAdapter mAllDelegateAdapter;
    private List<AllDelegateBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_delegate;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAllDelegateAdapter = new AllDelegateAdapter(mList);
        rv.setAdapter(mAllDelegateAdapter);
        mAllDelegateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.getStatusData();
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    public void getAllDelegateData(List<AllDelegateBean> list) {
        mList = list;
    }

    @Override
    public void getStatusDataSucces(List<AllDelegateBean> list) {
        if (list != null){
            mList.clear();
            mList.addAll(list);
            mAllDelegateAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String msg) {

    }


    @Override
    public void callBack(List<AllDelegateBean> list) {
        if (list != null){
            mList.clear();
            mList.addAll(list);
            mAllDelegateAdapter.notifyDataSetChanged();
        }
    }
}
