package com.jumai.antexchange.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author yf
 * @date 2019/9/19/019
 * 描述：
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {

}
