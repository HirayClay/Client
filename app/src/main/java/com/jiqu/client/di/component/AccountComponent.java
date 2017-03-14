package com.jiqu.client.di.component;

import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.di.module.AccountModule;
import com.jiqu.client.ui.LoginActivity;
import com.jiqu.client.ui.MainActivity;

import dagger.Component;

/**
 * Created by CJJ on 2017/3/9.
 *
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = AccountModule.class)
public interface AccountComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}
