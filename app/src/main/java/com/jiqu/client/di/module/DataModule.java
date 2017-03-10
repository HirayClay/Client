package com.jiqu.client.di.module;

import com.jiqu.data.network.RestApiHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/7.
 *
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    RestApiHelper provideRestApiHelper(){
        return new RestApiHelper();
    }
}
