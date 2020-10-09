package com.jumai.antexchange.dagger;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @FragmentScoped
    @Provides
    Fragment provider() {
        return mFragment;
    }
}
