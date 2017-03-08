package com.jiqu.client.di.module;

import android.content.Context;

import dagger.Module;

/**
 * Created by CJJ on 2017/3/7.
 *
 */
@Module
public class ApplicationModule {

    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }
    //任务池等全局对象
}
