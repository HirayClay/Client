package com.jiqu.client.di.module;

import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.mvp.view.AuthLoadView;
import com.jiqu.client.mvp.view.LoginView;
import com.jiqu.client.mvp.view.ServiceView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/9.
 *
 */
@Module
public class AccountModule {

    AuthLoadView view;

    public AccountModule() {
    }

    public AccountModule(AuthLoadView loginView) {
        this.view = loginView;
    }

    @ActivityScope
    @Provides
    LoginView loginView() {

        return (LoginView) view;
    }

    @ActivityScope
    @Provides
    ServiceView serviceView() {

        return (ServiceView) view;
    }
}
