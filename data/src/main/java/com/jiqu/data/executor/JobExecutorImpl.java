package com.jiqu.data.executor;

import android.support.annotation.NonNull;

import com.jiqu.domain.executor.JobExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by CJJ on 2017/3/9.
 *
 */

public class JobExecutorImpl implements JobExecutor {

    private ThreadPoolExecutor threadPoolExecutor;

    public JobExecutorImpl(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, LinkedBlockingQueue<Runnable> queue) {
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,queue);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        threadPoolExecutor.execute(command);
    }
}
