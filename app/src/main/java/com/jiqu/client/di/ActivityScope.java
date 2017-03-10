package com.jiqu.client.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by CJJ on 2017/3/9.
 *
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
