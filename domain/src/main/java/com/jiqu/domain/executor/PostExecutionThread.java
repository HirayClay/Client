package com.jiqu.domain.executor;

import rx.Scheduler;

/**
 * Created by CJJ on 2017/3/9.
 */

public interface PostExecutionThread  {
    Scheduler getScheduler();
}
