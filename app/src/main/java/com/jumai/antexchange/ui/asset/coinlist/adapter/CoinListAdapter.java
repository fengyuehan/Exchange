package com.jumai.antexchange.ui.asset.coinlist.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.HotCoinBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CoinListAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public class CoinListAdapter extends BaseQuickAdapter<HotCoinBean, BaseViewHolder> {
    private List<HotCoinBean> mList = new ArrayList();

    public CoinListAdapter(@Nullable List<HotCoinBean> data) {
        super(R.layout.item_all_coin_type, data);
        mList = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotCoinBean item) {
        int position = helper.getLayoutPosition();
        if (position < 0) {
            return;
        }
        helper.setText(R.id.tv_coin_type, item.getName());
        helper.setText(R.id.tv_catalog, item.getName_first_letter())
                .setGone(R.id.tv_catalog, !isSame(position));
        if (position == mList.size() - 1) {
            helper.getView(R.id.view).setVisibility(View.GONE);
        } else {
            if (mList.get(position).getName_first_letter().equals(mList.get(position + 1).getName_first_letter())) {
                helper.getView(R.id.view).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.view).setVisibility(View.GONE);
            }
        }
    }

    private boolean isSame(int position) {
        if (position == 0) {
            return false;
        }
        if (mList.get(position).getName_first_letter().equals(mList.get(position - 1).getName_first_letter())) {
            return true;
        } else {
            return false;
        }
    }
}
