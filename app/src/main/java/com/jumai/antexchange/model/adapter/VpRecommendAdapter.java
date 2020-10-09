package com.jumai.antexchange.model.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.jumai.antexchange.model.bean.HomeRecommendBean;
import com.jumai.antexchange.view.HomeRecommendView;

import java.util.List;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：首页
 */
public class VpRecommendAdapter extends PagerAdapter {
    private List<HomeRecommendBean> mData;
    private Context mContext;
    private SparseArray<HomeRecommendView> mContainer;
    //一屏最多数量
    public static final int SIZE = 3;

    public VpRecommendAdapter(Context context, List<HomeRecommendBean> data) {
        mContext = context;
        mData = data;
        mContainer = new SparseArray<>(data.size());
    }

    @Override
    public int getCount() {
        return (int) Math.ceil(mData.size() / (SIZE * 1f));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        HomeRecommendView view = new HomeRecommendView(mContext);
        if (mData.size() <= SIZE) {
            view.setData(mData);
        } else {
            if (position == (int) Math.ceil(mData.size() / (SIZE * 1f)) - 1) {
                view.setData(mData.subList(SIZE * position, mData.size()));
            } else {
                view.setData(mData.subList(SIZE * position, SIZE * position + SIZE));
            }
        }
        container.addView(view);
        mContainer.put(position, view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mContainer.get(position));
    }

    public HomeRecommendView getItem(int position) {
        return mContainer.get(position);
    }
}
