package com.jiqu.client.di.component;

import com.jiqu.client.di.module.ApplicationModule;
import com.jiqu.client.di.module.DataModule;
import com.jiqu.client.di.module.DomainModule;
import com.jiqu.client.di.module.mock.MockModule;
import com.jiqu.client.ui.BaseActivity;
import com.jiqu.data.network.RestApiHelper;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by CJJ on 2017/3/7.
 *
 */
@Singleton
@Component(modules = {ApplicationModule.class,DataModule.class, DomainModule.class, MockModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    //all below will expose to sub
    @Named("official")
    AccountRepo accountRepo();

    //mock if for test environment
    @Named("mock")
    AccountRepo mockAccountRepo();


}
