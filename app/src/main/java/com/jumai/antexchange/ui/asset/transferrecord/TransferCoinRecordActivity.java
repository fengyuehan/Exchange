package com.jumai.antexchange.ui.asset.transferrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseRefreshActivity;
import com.jumai.antexchange.bean.TransferCoinRecordBean;
import com.jumai.antexchange.dialog.BottomListDialog;
import com.jumai.antexchange.ui.asset.transferrecord.adapter.TransferCoinRecordAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName TransferCoinRecordActivity
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public class TransferCoinRecordActivity extends BaseRefreshActivity<TransferCoinRecordBean, TransferCoinRecordView, TransferCoinRecordPresenter> implements TransferCoinRecordView, BottomListDialog.CheckedListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private TransferCoinRecordAdapter mTransferCoinRecordAdapter;
    private List<TransferCoinRecordBean> mData = new ArrayList<>();
    private String[] mDialogTitle = {"全部", "币币账户到法币账户", "法币账户到币币账户"};
    private List<String> mList;
    private BottomListDialog mBottomListDialog;

    @Override
    protected SmartRefreshLayout setRefreshLayout() {
        return srl;
    }

    @Override
    protected BaseQuickAdapter setRefreshAdapter() {
        return mTransferCoinRecordAdapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfer_record;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mPresenter.getCoinRecordData(0,false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mTransferCoinRecordAdapter = new TransferCoinRecordAdapter(mData);
        rv.setAdapter(mTransferCoinRecordAdapter);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList = Arrays.asList(mDialogTitle);
    }

    @OnClick({R.id.iv_back, R.id.iv_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                if (mBottomListDialog == null) {
                    mBottomListDialog = new BottomListDialog(this);
                    mBottomListDialog.setData(0, mList);
                }
                mBottomListDialog.show();
                break;
        }
    }

    @Override
    public void getCoinRecordDataSuccess(List<TransferCoinRecordBean> list) {
            if (list != null){
                if (list.size() == 0){
                    mTransferCoinRecordAdapter.setEmptyView(getEmptyView(R.string.tip_empty));
                }else {
                    mData.clear();
                    mData.addAll(list);
                }
                mTransferCoinRecordAdapter.notifyDataSetChanged();
            }
    }

    @Override
    public void checked(int position, String info) {
        mPresenter.getCoinRecordData(position,false);
    }
}
