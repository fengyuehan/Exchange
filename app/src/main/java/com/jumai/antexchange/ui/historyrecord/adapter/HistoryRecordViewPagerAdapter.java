package com.jumai.antexchange.ui.historyrecord.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ClassName HistoryRecordViewPagerAdapter
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitle;
    private List<Fragment> mFragmentList;

    public HistoryRecordViewPagerAdapter(@NonNull FragmentManager fm,String[] mTitle,List<Fragment> mFragmentList) {
        super(fm);
        this.mTitle = mTitle;
        this.mFragmentList = mFragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
