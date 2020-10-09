package com.jumai.antexchange.ui.market.adapter;

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
 * @ClassName QuotationAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/23
 * @Version 1.0
 */
public abstract class QuotationAdapter<T> extends PagerAdapter {
    private String[] mTitle;
    private List<T> mList;
    private Context mContext;
    private int mConverId;
    private LruCache<Integer, View> mViewWeakHashMap;



    public QuotationAdapter(Context mContext,String[] mTitle,List<T> mList,int mConverId){
        super();
        this.mContext = mContext;
        this.mConverId = mConverId;
        this.mList = mList;
        this.mTitle = mTitle;
        mViewWeakHashMap = new LruCache<>(20);
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = getView(container, position);
        container.addView(view);
        bindView(view, mList.get(position), position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(getView(container, position));
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
