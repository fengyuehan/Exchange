package com.jumai.antexchange.dagger;

import com.jumai.antexchange.config.AppApi;
import com.jumai.antexchange.utils.net.RetrofitEngine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：
 */
@Module
public class AppModule {
    @Singleton
    @Provides
    AppApi providerApi() {
        return RetrofitEngine.getInstance().create(AppApi.class);
    }
}
