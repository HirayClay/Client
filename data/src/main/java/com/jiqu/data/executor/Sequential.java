package com.jiqu.data.executor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by CJJ on 2017/3/9.
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Sequential {
    String value() default "sequential";
}
