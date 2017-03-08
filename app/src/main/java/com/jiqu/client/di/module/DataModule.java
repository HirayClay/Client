package com.jiqu.client.di.module;

import com.jiqu.data.network.RestApiHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CJJ on 2017/3/7.
 *
 */
@Module
public class DataModule {

    @Provides
    RestApiHelper provideRestApiHelper(){
        return new RestApiHelper();
    }
}
