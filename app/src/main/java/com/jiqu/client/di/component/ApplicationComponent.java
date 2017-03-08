package com.jiqu.client.di.component;

import com.jiqu.client.di.module.ApplicationModule;
import com.jiqu.client.di.module.DataModule;
import com.jiqu.client.di.module.DomainModule;
import com.jiqu.client.ui.BaseActivity;

import dagger.Component;

/**
 * Created by CJJ on 2017/3/7.
 */
@Component(dependencies = {ApplicationModule.class},modules = {DataModule.class, DomainModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);


}
