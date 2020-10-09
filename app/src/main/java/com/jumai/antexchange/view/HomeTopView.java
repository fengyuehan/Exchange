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
import com.jumai.antexchange.model.bean.TopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：涨幅榜/跌幅榜
 */
public class HomeTopView extends LinearLayout {
    @BindView(R.id.rv)
    RecyclerView rv;
    private TopAdapter mAdapter;

    public HomeTopView(@NonNull Context context, int type) {
        super(context);
        initView(context, type);
    }

    public HomeTopView(@NonNull Context context, @Nullable AttributeSet attrs, int type) {
        super(context, attrs);
        initView(context, type);
    }

    public HomeTopView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int type) {
        super(context, attrs, defStyleAttr);
        initView(context, type);
    }

    private void initView(Context context, int type) {
        setOrientation(VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        View.inflate(context, R.layout.l_top_view, this);
        ButterKnife.bind(this);
        mAdapter = new TopAdapter(R.layout.item_home_top, new ArrayList<>());
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtils.showShort("position:" + position);
        });

        getData(type);
    }

    private void getData(int type) {
        ArrayList<TopBean> data = new ArrayList<>();
        data.add(new TopBean("BTC/USDT", 1032.45, 5.12));
        data.add(new TopBean("ETH/USDT", 252.89, 0.23));
        data.add(new TopBean("OMG/USDT", 45.23, 5.86));
        data.add(new TopBean("ETH/USDT", 5698.12, 0.56));
        data.add(new TopBean("OMG/USDT", 4153.89, 2.36));
        data.add(new TopBean("BTC/USDT", 1568.78, 5.89));
        data.add(new TopBean("OMG/USDT", 45.69, 4.12));
        data.add(new TopBean("ETH/USDT", 555.23, 7.89));
        mAdapter.replaceData(data);
    }

    public static class TopAdapter extends BaseQuickAdapter<TopBean, BaseViewHolder> {
        private TopAdapter(int layoutResId, List<TopBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TopBean bean) {
            helper.setText(R.id.tv_name, bean.name)
                    .setText(R.id.tv_price, String.valueOf(bean.price))
                    .setText(R.id.tv_value, String.valueOf(bean.price));
        }
    }
}
