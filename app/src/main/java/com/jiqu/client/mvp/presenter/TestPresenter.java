package com.jiqu.client.mvp.presenter;

import android.util.Log;

import com.jiqu.client.BuildConfig;
import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.di.module.mock.Mock;
import com.jiqu.client.mvp.view.ServiceView;
import com.jiqu.data.executor.Concurrent;
import com.jiqu.data.executor.Sequential;
import com.jiqu.domain.entity.Service;
import com.jiqu.domain.executor.JobExecutor;
import com.jiqu.domain.repository.AccountRepo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
@ActivityScope
public class TestPresenter {

    private static final String TAG = "TestPresenter";

    @Inject
    @Sequential
    JobExecutor seqExecutor;

    @Inject
    @Named("mock")
    AccountRepo accountRepo;

    @Inject
    ServiceView serviceView;

    @Inject
    public TestPresenter() {
    }

    public void getSingleService() {
        accountRepo.getService()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.from(seqExecutor))
                .subscribe(new Subscriber<List<Service>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Service> services) {
                        serviceView.onServiceLoad(services);
                        if (BuildConfig.DEBUG)
                            Log.i(TAG, "onNext: " + services);
                        if (services != null && !services.isEmpty()) {
                            Log.i(TAG, "onNext: " + services.get(0));
                        }

                    }
                });

    }

}
