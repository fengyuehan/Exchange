package com.jumai.antexchange.ui.market.edit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.EditCoinBean;
import com.jumai.antexchange.ui.market.adapter.EditAdapter;
import com.jumai.antexchange.ui.market.search.CoinSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName CoinEditActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class CoinEditActivity extends BaseActivity<CoinEditView, CoinEditPresenter> implements CoinEditView {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.rl_coin)
    RelativeLayout rlCoin;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.iv_all)
    ImageView ivAll;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;


    private List<EditCoinBean> mData = new ArrayList<>();
    private EditAdapter mEditAdapter;
    private int mSelectAmount = 0;
    private int mItemAmount;
    private int mCount = 0;//标志，判断ivAll是否选中，0表示未选中，1表示选中
    private List<Integer> mPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        ivAll.setSelected(false);
        ivCancel.setSelected(false);
        tvCancel.setSelected(false);
        mCount = 0;
        mPosition = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EditCoinBean editCoinBean = new EditCoinBean();
            editCoinBean.setAmount("1" + i);
            editCoinBean.setCoinName("2" + i);
            editCoinBean.setOtherName("3" + i);
            mData.add(editCoinBean);
        }
        rv.setLayoutManager(new LinearLayoutManager(CoinEditActivity.this));
        mEditAdapter = new EditAdapter(mData);
        rv.setAdapter(mEditAdapter);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mEditAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);
        mEditAdapter.enableDragItem(itemTouchHelper);
        OnItemDragListener onItemDragListener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {

            }
        };
        mEditAdapter.setOnItemDragListener(onItemDragListener);
        mEditAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到K线
            }
        });
        mItemAmount = mEditAdapter.getItemCount();
        mEditAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                EditCoinBean editCoinBean = (EditCoinBean) adapter.getData().get(position);
                if (editCoinBean.getIsSelect() == 0) {
                    ivCancel.setSelected(true);
                    tvCancel.setSelected(true);
                    editCoinBean.setIsSelect(1);
                    mSelectAmount++;
                    mPosition.add(position);
                } else {
                    ivCancel.setSelected(false);
                    tvCancel.setSelected(false);
                    editCoinBean.setIsSelect(0);
                    mSelectAmount--;
                    mPosition.remove(position);
                }
                mEditAdapter.notifyItemChanged(position);
                if (mSelectAmount == mItemAmount) {
                    ivAll.setSelected(true);
                    mCount = 1;
                } else {
                    ivAll.setSelected(false);
                    mCount = 0;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_add, R.id.tv_complete, R.id.ll_all_choose, R.id.ll_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                startActivity(new Intent(CoinEditActivity.this, CoinSearchActivity.class));
                break;
            case R.id.tv_complete:
                finish();
                break;
            case R.id.ll_all_choose:
                if (mEditAdapter == null) {
                    return;
                }

                if(mItemAmount == 0){
                    ivCancel.setSelected(false);
                    tvCancel.setSelected(false);
                    ivAll.setSelected(false);
                    mCount = 0;
                }else {
                    if (mCount == 1) {
                        //全选中
                        for (int i = 0; i < mItemAmount; i++) {
                            mEditAdapter.getViewByPosition(rv, i, R.id.iv_coin_choose).setSelected(false);
                            mEditAdapter.getData().get(i).setIsSelect(0);
                        }
                        ivCancel.setSelected(false);
                        tvCancel.setSelected(false);
                        ivAll.setSelected(false);
                        mCount = 0;
                        mPosition.clear();
                    } else {
                        //不全选中
                        for (int j = 0; j < mItemAmount; j++) {
                            if (mEditAdapter.getData().get(j).getIsSelect() != 1) {
                                mEditAdapter.getViewByPosition(rv, j, R.id.iv_coin_choose).setSelected(true);
                                mEditAdapter.getData().get(j).setIsSelect(1);
                            }
                            mPosition.add(j);
                        }
                        ivCancel.setSelected(true);
                        tvCancel.setSelected(true);
                        ivAll.setSelected(true);
                        mCount = 1;

                    }
                }
                mEditAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_cancel:
                Log.e("zzf", mPosition.size() + " = mPosition");

                if (mPosition.size() == mItemAmount) {
                    //全选
                    mItemAmount = 0;
                    mData.clear();
                    ivAll.setSelected(false);
                    mCount = 0;
                    ivCancel.setSelected(false);
                    tvCancel.setSelected(false);
                    mPosition.clear();
                } else {
                    if (mPosition.size() != 0) {
                        for (int i = 0; i < mPosition.size(); i++) {
                            EditCoinBean editCoinBean = mEditAdapter.getData().get(mPosition.get(i));
                            mData.remove(editCoinBean);
                            mItemAmount--;
                        }
                    }
                    mPosition.clear();
                    ivCancel.setSelected(false);
                    tvCancel.setSelected(false);
                }
                mEditAdapter.notifyDataSetChanged();
                break;
        }

    }

    @Override
    public void getEditCoinData(List<EditCoinBean> mList) {
        mData = mList;
    }
}
