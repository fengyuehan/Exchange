package com.jumai.antexchange.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * @author yf
 * @date 2019/7/16
 * 描述：Application生命周期管理
 */
public class LifeCircleHandler implements Application.ActivityLifecycleCallbacks {
    //开启的activity数量
    private int mCount = 0;
    //app是否处于前台
    private boolean mAppForeground = true;
    private WeakReference<Activity> mCurrentActivityWf;
    private Stack<Activity> mActivities;

    private LifeCircleHandler() {
    }

    public static LifeCircleHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final LifeCircleHandler INSTANCE = new LifeCircleHandler();
    }

    /**
     * 获取当前activity
     */
    public Activity getCurrentActivity() {
        if (mCurrentActivityWf != null) {
            return mCurrentActivityWf.get();
        }
        return null;
    }

    private void setCurrentActivity(Activity mCurrentActivity) {
        mCurrentActivityWf = new WeakReference<>(mCurrentActivity);
    }


    @Override
    public void onActivityCreated(@NotNull Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(@NotNull Activity activity) {
        if (mCount == 0) {
            mAppForeground = true;
        }
        mCount++;
        setCurrentActivity(activity);
    }


    @Override
    public void onActivityResumed(@NotNull Activity activity) {
    }

    @Override
    public void onActivityPaused(@NotNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NotNull Activity activity) {
        mCount--;
        if (mCount == 0) {
            mAppForeground = false;
        }
    }

    @Override
    public void onActivityDestroyed(@NotNull Activity activity) {
        removeActivity(activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
    }

    /**
     * 当前app是否是前台进程
     */
    public boolean isAppForeground() {
        return mAppForeground;
    }

    private void addActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new Stack<>();
        }

        if (mActivities.search(activity) == -1) {
            mActivities.push(activity);
        }
    }

    private void removeActivity(Activity activity) {
        if (mActivities != null && mActivities.size() > 0) {
            mActivities.remove(activity);
        }
    }

    public void finishAllActivity() {
        if (mActivities != null && mActivities.size() > 0) {
            while (!mActivities.empty()) {
                Activity activity = mActivities.pop();
                if (activity != null) {
                    activity.finish();
                }
            }
            mActivities.clear();
            mActivities = null;
        }
    }

    /**
     * 当前Activity之上的Activity全部出栈，自己为栈顶Activity
     */
    public void setTopActivity() {
        if (mActivities != null && mActivities.size() > 1) {
            Activity currentActivity = mActivities.lastElement();
            while (!mActivities.empty()) {
                Activity activity = mActivities.pop();
                if (activity != null) {
                    if (activity != currentActivity) {
                        activity.finish();
                    }
                }
            }
            mActivities.clear();
            mActivities.push(currentActivity);
        }
    }


    /**
     * 设置该activity栈以上的activity出栈
     */
    public void setTopActivity(Activity topActivity) {
        if (mActivities != null && mActivities.size() > 0) {
            while (!mActivities.empty()) {
                Activity activity = mActivities.pop();
                if (activity != null && topActivity != activity) {
                    activity.finish();
                }
            }
            mActivities.clear();
            mActivities = null;
        }
    }
}