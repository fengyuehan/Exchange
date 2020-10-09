package com.jumai.antexchange.ui.transcation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @ClassName CoinSelectAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public abstract class CoinSelectAdapter<T> extends PagerAdapter {
    private String[] mTitle;
    private List<T> mList;
    private Context mContext;
    private int mConverId;
    private LruCache<Integer, View> mViewWeakHashMap;

    public CoinSelectAdapter(Context mContext,String[] mTitle,List<T> mList,int mConverId){
        super();
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mConverId = mConverId;
        this.mList = mList;
        mViewWeakHashMap = new LruCache<>(20);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(getView(container, position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    private View getView(ViewGroup container, int position) {
        View view = mViewWeakHashMap.get(position);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(mConverId, container, false);
        }
        view.setTag(position);
        return view;
    }

    public abstract void bindView(View view, T data, int position);
}
