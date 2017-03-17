package com.jiqu.client.di.module;

import com.jiqu.domain.entity.ServiceMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: CJJ
 * @date 2017/3/17
 */
@Module
public class MapperModule {

    @Provides
    @Singleton
    ServiceMapper providerMapper(){
        return new ServiceMapper();
    }
}
