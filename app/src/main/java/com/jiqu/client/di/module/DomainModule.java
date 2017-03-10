package com.jiqu.client.di.module;

import com.jiqu.data.repository.AccountRepoImpl;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/7.
 * provide data layer repository implementation
 */
@Module
public class DomainModule {

    @Singleton
    @Provides
    @Named("official")
    AccountRepo provideAccountRepo(AccountRepoImpl accountRepo){
        return accountRepo;
    }
}
