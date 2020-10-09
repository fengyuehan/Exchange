package com.jumai.antexchange.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jumai.antexchange.R;
import com.jumai.antexchange.model.bean.HomeRecommendBean;

import java.util.ArrayList;
import java.util.List;

import static com.jumai.antexchange.model.adapter.VpRecommendAdapter.SIZE;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：首页推荐
 */
public class HomeRecommendView extends RecyclerView {
    private RecommendAdapter mAdapter;

    public HomeRecommendView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public HomeRecommendView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeRecommendView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mAdapter = new RecommendAdapter(R.layout.item_home_recommend, new ArrayList<>());
        setLayoutManager(new GridLayoutManager(context, SIZE));
        setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtils.showShort("position:" + position);
        });
    }

    public void setData(List<HomeRecommendBean> data) {
        mAdapter.replaceData(data);
    }

    public static class RecommendAdapter extends BaseQuickAdapter<HomeRecommendBean, BaseViewHolder> {
        private RecommendAdapter(int layoutResId, List<HomeRecommendBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeRecommendBean bean) {
            helper.setText(R.id.tv_name, bean.name)
                    .setText(R.id.tv_price, String.valueOf(bean.price))
                    .setText(R.id.tv_value, String.valueOf(bean.price));
        }
    }
}
