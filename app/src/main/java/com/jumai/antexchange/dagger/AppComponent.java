package com.jumai.antexchange.dagger;

import com.jumai.antexchange.config.AppApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    AppApi getAppApi();
}
