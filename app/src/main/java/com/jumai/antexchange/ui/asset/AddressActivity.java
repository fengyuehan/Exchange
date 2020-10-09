package com.jumai.antexchange.ui.asset;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.model.adapter.AddressAdapter;
import com.jumai.antexchange.model.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class AddressActivity extends BaseActivity<AddressView, AddressPresenter> implements AddressView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_address)
    TextView tvTitleAddress;
    @BindView(R.id.rv)
    RecyclerView rv;
    private AddressAdapter mAddressAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText("收款地址");
        initRv();
    }

    @Override
    protected void initData() {
        mPresenter.getAddress();
    }

    private void initRv() {
        mAddressAdapter = new AddressAdapter(new ArrayList<>());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAddressAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("position:" + position);
            }
        });

        mAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mAddressAdapter.notifyItemRemoved(position);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                startActivity(new Intent(this, AddressAddActivity.class));
                break;
        }
    }

    @Override
    public void setAddress(List<AddressBean> data) {
        mAddressAdapter.setNewData(data);
    }
}
