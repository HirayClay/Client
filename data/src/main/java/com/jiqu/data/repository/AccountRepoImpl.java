package com.jiqu.data.repository;

import com.jiqu.data.network.RestApiHelper;
import com.jiqu.domain.param.LoginParam;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by CJJ on 2017/3/7.
 *
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
                .login(param).flatMap(new RestApiHelper.ResponseParser<String>());
    }
}
