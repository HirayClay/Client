package com.jiqu.client.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jiqu.data.BuildConfig;
import com.jiqu.data.executor.Concurrent;
import com.jiqu.data.executor.JobExecutorImpl;
import com.jiqu.data.executor.Sequential;
import com.jiqu.domain.executor.JobExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/7.
 */
@Module
public class ApplicationModule {

    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }
    //任务池,上下文等全局对象

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    @Concurrent
    JobExecutor provideConcurrentJobExecutor() {
        return new JobExecutorImpl(BuildConfig.CORE_POOL_SIZE,
                BuildConfig.MAXIMUM_POOL_SIZE,
                BuildConfig.KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    @Singleton
    @Provides
    @Sequential
    JobExecutor provideSequentialJobExecutor() {
        return new JobExecutorImpl(1,
                1,
                BuildConfig.KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    @Singleton
    @Provides
    SharedPreferences provideSharePreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
