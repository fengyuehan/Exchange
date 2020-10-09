package com.jumai.antexchange.dagger;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @ActivityScoped
    @Provides
    Activity provider() {
        return mActivity;
    }
}
