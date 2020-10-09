package com.jumai.antexchange.ui.historyrecord.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseFragment;
import com.jumai.antexchange.bean.HistoryRecordBean;
import com.jumai.antexchange.ui.historyrecord.activity.HistoryRecordActivity;
import com.jumai.antexchange.ui.historyrecord.activity.OrderDetailActivity;
import com.jumai.antexchange.ui.historyrecord.adapter.HistoryRecordAdapter;
import com.jumai.antexchange.view.HistoryRecordCallBack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @ClassName HistoryRecordFragment
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordFragment extends BaseFragment<HistoryRecortFragmentView, HistoryRecordFragmentPresenter> implements HistoryRecortFragmentView, HistoryRecordActivity.HistoryCallBack {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private HistoryRecordAdapter mHistoryRecordAdapter;
    private List<HistoryRecordBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history_record;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHistoryRecordAdapter = new HistoryRecordAdapter(mList);
        rv.setAdapter(mHistoryRecordAdapter);
        mHistoryRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), OrderDetailActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getHistoryRecordData(List<HistoryRecordBean> list) {
        mList = list;
    }

    @Override
    public void callBack(List<HistoryRecordBean> list) {
        if (list != null){
            mList.clear();
            mList.addAll(list);
            mHistoryRecordAdapter.notifyDataSetChanged();
        }
    }
}
