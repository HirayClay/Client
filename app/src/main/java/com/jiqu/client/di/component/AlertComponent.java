package com.jiqu.client.di.component;

import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.widget.AlertCheckCode;

import dagger.Component;

/**
 * @author: CJJ
 * @date 2017/4/18
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,modules = {})
public interface AlertComponent {
    void inject(AlertCheckCode host);
}
