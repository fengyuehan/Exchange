package com.jumai.antexchange.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.TxCompletedBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：成交
 */
public class TxCompletedView extends LinearLayout {
    @BindView(R.id.rv)
    RecyclerView rv;
    private TxAdapter mAdapter;

    public TxCompletedView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TxCompletedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TxCompletedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        View.inflate(context, R.layout.l_tx_completed_view, this);
        ButterKnife.bind(this);
        mAdapter = new TxAdapter(R.layout.item_tx_completed, new ArrayList<>());
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtils.showShort("position:" + position);
        });

        getData();
    }

    private void getData() {
        ArrayList<TxCompletedBean> data = new ArrayList<>();
        data.add(new TxCompletedBean("5:05:45", 1032.45, 5.12));
        data.add(new TxCompletedBean("15:45:23", 252.89, 0.23));
        data.add(new TxCompletedBean("15:05:45", 45.23, 5.86));
        data.add(new TxCompletedBean("15:05:45", 5698.12, 0.56));
        data.add(new TxCompletedBean("15:05:45", 4153.89, 2.36));
        data.add(new TxCompletedBean("15:05:45", 1568.78, 5.89));
        data.add(new TxCompletedBean("15:05:45", 45.69, 4.12));
        data.add(new TxCompletedBean("15:05:45", 555.23, 7.89));
        mAdapter.replaceData(data);
    }

    public static class TxAdapter extends BaseQuickAdapter<TxCompletedBean, BaseViewHolder> {
        private TxAdapter(int layoutResId, List<TxCompletedBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TxCompletedBean bean) {
            helper.setText(R.id.tv_time, bean.time)
                    .setText(R.id.tv_price, String.valueOf(bean.price))
                    .setText(R.id.tv_amount, String.valueOf(bean.amount));
        }
    }
}
