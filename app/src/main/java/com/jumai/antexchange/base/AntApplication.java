package com.jumai.antexchange.base;


import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.jumai.antexchange.BuildConfig;
import com.jumai.antexchange.dagger.AppComponent;
import com.jumai.antexchange.dagger.AppModule;
import com.jumai.antexchange.dagger.DaggerAppComponent;
import com.jumai.antexchange.utils.net.util.RxUtil;
import com.orhanobut.logger.LogPrintStyle;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.jetbrains.annotations.NotNull;

/**
 * Application
 */

public class AntApplication extends Application {

    private static AntApplication mContext;
    private static AppComponent mAppComponent;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context).setTextSizeTitle(12));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setTextSizeTitle(12));
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //LanguageManage.changeLanguages(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RxUtil.io(() -> {
            initUtils();
            initLog();
            registerActivityLifecycleCallbacks(LifeCircleHandler.getInstance());
        });

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 初始化日志
     */
    private void initLog() {
        Logger.initialize(
                new Settings()
                        .setStyle(new LogPrintStyle())
                        .isShowMethodLink(true)
                        .isShowThreadInfo(true)
                        .setMethodOffset(3)
                        .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT)
        );
    }

    /**
     * 初始化日志类
     */
    private void initUtils() {
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
    }

    /**
     * 获取ApplicationContext
     */
    public static Application getInstance() {
        return mContext;
    }
}
