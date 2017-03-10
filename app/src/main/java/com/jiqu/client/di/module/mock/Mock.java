package com.jiqu.client.di.module.mock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by CJJ on 2017/3/10.
 * just for temporary test ,testing the whole procedure
 * switch to another environment,just wipe out this marker annotation
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {

    String value() default "test environment";
}
