package com.jiqu.client.di.module;

import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.mvp.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/9.
 *
 */
@Module
public class AccountModule {

    LoginView loginView;

    public AccountModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @ActivityScope
    @Provides
    LoginView loginView() {

        return loginView;
    }
}
