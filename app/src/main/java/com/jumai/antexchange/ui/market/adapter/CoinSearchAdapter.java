package com.jumai.antexchange.ui.market.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.bean.HotCoinBean;

import java.util.List;

/**
 * @ClassName CoinSearchAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public class CoinSearchAdapter extends BaseQuickAdapter<HotCoinBean, BaseViewHolder> {
    private CoinCollectionCallBack mCoinCollectionCallBack;
    private int isSelect = 0;

    public CoinSearchAdapter(@Nullable List<HotCoinBean> data) {
        super(R.layout.item_collection,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotCoinBean item) {
        helper.setText(R.id.tv_coin_name,item.getName());
        helper.getView(R.id.iv_collection).setSelected(false);
        isSelect = 0;
        helper.getView(R.id.iv_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelect == 0){
                    isSelect = 1;
                    helper.getView(R.id.iv_collection).setSelected(true);
                    if (mCoinCollectionCallBack != null){
                        mCoinCollectionCallBack.callBack(item.getName());
                    }
                }else {
                    helper.getView(R.id.iv_collection).setSelected(false);
                    isSelect = 0;
                }

            }
        });
    }

    public interface CoinCollectionCallBack{
        void callBack(String coinName);
    }

}
