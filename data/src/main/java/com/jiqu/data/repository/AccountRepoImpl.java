package com.jiqu.data.repository;

import com.jiqu.data.network.RestApiHelper;
import com.jiqu.domain.entity.Service;
import com.jiqu.domain.entity.UserInfo;
import com.jiqu.domain.param.LoginParam;
import com.jiqu.domain.repository.AccountRepo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by CJJ on 2017/3/7.
 */
@Singleton
public class AccountRepoImpl implements AccountRepo {


    @Inject
    RestApiHelper restApiHelper;

    @Inject
    public AccountRepoImpl() {
    }

    @Override
    public Observable<String> login(LoginParam param) {
        return restApiHelper.restApi()
                .login(param).flatMap(new RestApiHelper.TokenFunc<String>());
    }

    @Override
    public Observable<UserInfo> getUserInfo(final String userId) {
        return Observable.create(new Observable.OnSubscribe<UserInfo>() {
            @Override
            public void call(Subscriber<? super UserInfo> subscriber) {


            }
        });
    }

    @Override
    public Observable<List<Service>> getService() {
        return null;
    }
}
