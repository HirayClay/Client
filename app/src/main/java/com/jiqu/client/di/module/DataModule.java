package com.jiqu.client.di.module;

import android.content.Context;

import com.jiqu.data.mapper.ServiceMapper;
import com.jiqu.data.network.RestApiHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by CJJ on 2017/3/7.
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    RestApiHelper provideRestApiHelper() {
        return new RestApiHelper();
    }

//    @Singleton
//    @Provides
//    @Deprecated// realm must be init on the same thread,but most work happened in the background
//    Realm provideRealm(Context context) {
//        Realm.init(context);
//        return Realm.getDefaultInstance();
//    }


    /*********Mapper**********/
    @Singleton
    @Provides
    ServiceMapper serviceMapper() {
        return new ServiceMapper();
    }
}
