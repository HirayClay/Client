package com.jiqu.client.di.module.mock;

import com.jiqu.data.repository.mock.AccountRepoMock;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/10.
 *
 */
@Module
public class MockModule {

    @Singleton
    @Provides
    @Named("mock")
    AccountRepo provideAccountRepo(AccountRepoMock mock) {
        return mock;
    }
}
