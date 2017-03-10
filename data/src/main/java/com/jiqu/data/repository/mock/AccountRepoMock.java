package com.jiqu.data.repository.mock;

import com.jiqu.domain.param.LoginParam;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by CJJ on 2017/3/10.
 *
 */
@Singleton
public class AccountRepoMock implements AccountRepo {

    @Inject
    public AccountRepoMock() {
    }

    @Override
    public Observable<String> login(LoginParam param) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
                subscriber.onNext("Computing Done!");
            }
        });
    }
}
