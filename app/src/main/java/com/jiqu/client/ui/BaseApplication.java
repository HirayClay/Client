package com.jiqu.client.ui;

import android.app.Application;

import com.jiqu.client.di.component.ApplicationComponent;
import com.jiqu.client.di.component.DaggerApplicationComponent;
import com.jiqu.client.di.module.ApplicationModule;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public class BaseApplication extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

    }

    ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeInjector() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
