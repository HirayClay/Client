package com.jiqu.data.repository.mock;

import android.util.Log;

import com.jiqu.data.network.RestApiHelper;
import com.jiqu.domain.entity.Service;
import com.jiqu.domain.entity.ServiceMapper;
import com.jiqu.domain.entity.UserInfo;
import com.jiqu.domain.param.LoginParam;
import com.jiqu.domain.repository.AccountRepo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by CJJ on 2017/3/10.
 *
 */
@Singleton
public class AccountRepoMock implements AccountRepo {
    private static final String TAG = "AccountRepoMock";

    @Inject
    RestApiHelper restApiHelper;


    @Inject
    ServiceMapper serviceMapper;


    @Inject
    public AccountRepoMock() {
    }

    @Override
    public Observable<String> login(LoginParam param) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
                subscriber.onNext("Computing Done!");
            }
        });
    }

    @Override
    public Observable<UserInfo> getUserInfo(String userId) {
        return null;
    }

    @Override
    public Observable<List<Service>> getService() {
        return Observable.create(new Observable.OnSubscribe<List<Service>>() {
            @Override
            public void call(final Subscriber<? super List<Service>> subscriber) {

                Log.i(TAG, "call: "+Thread.currentThread().getName());
                //query from realm,if ok ,pass to subscriber
                Realm realm = Realm.getDefaultInstance();
                RealmQuery<Service> where = realm.where(Service.class);
                RealmResults<Service> all = where.findAll();
                if (all.isEmpty()) {
                    //from network and save
                    restApiHelper.restApi().getServices().flatMap(new RestApiHelper.LocalFunc<List<Service>>()).subscribe(subscriber);
                }else {
                    subscriber.onCompleted();
                    Log.i(TAG, "call: "+all);
                    subscriber.onNext(serviceMapper.map(all));
                }
                realm.close();

            }
        });

    }


}
