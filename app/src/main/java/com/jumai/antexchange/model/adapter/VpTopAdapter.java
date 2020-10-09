package com.jumai.antexchange.model.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.jumai.antexchange.view.HomeTopView;

/**
 * @author yf
 * @date 2019/9/24/024
 * 描述：首页
 */
public class VpTopAdapter extends PagerAdapter {
    private Context mContext;
    private SparseArray<HomeTopView> mContainer;
    private final String[] mPageTitles;

    public VpTopAdapter(Context context) {
        mContext = context;
        mContainer = new SparseArray<>(2);

        mPageTitles = new String[]{"涨幅榜", "跌幅榜"};
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        HomeTopView view = new HomeTopView(mContext, position);
        container.addView(view);
        mContainer.put(position, view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mContainer.get(position));
    }

    public HomeTopView getItem(int position) {
        return mContainer.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}
