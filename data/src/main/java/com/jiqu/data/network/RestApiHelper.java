package com.jiqu.data.network;

import android.content.SharedPreferences;
import android.support.annotation.IntDef;

import com.google.gson.Gson;
import com.jiqu.data.network.dataformat.ResponseWrapper;
import com.jiqu.domain.exception.AuthException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by CJJ on 2017/3/6.
 */
@Singleton
public class RestApiHelper {
    private static final String TAG = "RestApiHelper";
    private static final String TOKEN_KEY = "KEY_TOKEN";
    RestApi restApi;

    Gson gson;

    Url url;

    @Inject
    SharedPreferences sharedPreferences;

    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    @Inject
    public RestApiHelper() {

        gson = new Gson();
        url = new Url();
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain
                        .request()
                        .newBuilder()
                        .addHeader("Source", "Value")
                        .build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://copen.zhujiash.com/api/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        restApi = retrofit.create(RestApi.class);
    }

    public static class NetFunc<T> implements Func1<ResponseWrapper<T>, Observable<T>> {

        @Override
        public Observable<T> call(final ResponseWrapper<T> tResponseWrapper) {

            return Observable.create(new Observable.OnSubscribe<T>() {
                @Override
                public void call(Subscriber<? super T> subscriber) {
                    //if request success,check the meta info first
                    subscriber.onNext(tResponseWrapper.body);
                    subscriber.onCompleted();
                }
            });
        }
    }

    //apart from parsing network data ,and persist those data
    @SuppressWarnings("unchecked")
    public static class LocalFunc<T> implements Func1<ResponseWrapper<T>, Observable<T>> {

        @Override
        public Observable<T> call(final ResponseWrapper<T> tResponseWrapper) {

            return Observable.create(new Observable.OnSubscribe<T>() {
                @Override
                public void call(Subscriber<? super T> subscriber) {
                    if (tResponseWrapper.isSuccess()) {

                        final T body = tResponseWrapper.body;
                        if (body != null) {
                            if (body instanceof RealmObject ||
                                    body instanceof Iterable) {

                                Realm realm = Realm.getDefaultInstance();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        if (body instanceof RealmObject)
                                            realm.copyToRealmOrUpdate((RealmObject) body);
                                        else
                                            realm.copyToRealmOrUpdate((Iterable<RealmModel>) body);
                                    }
                                });
                                realm.close();
                            } else
                                throw new RuntimeException(body.getClass() + " " +
                                        "is not instance of RealmObject,thus can't be saved to realm");
                        }
                        subscriber.onNext(body);
                    } else if (tResponseWrapper.isAuthExpired()) {
                        subscriber.onError(new AuthException(tResponseWrapper.errMsg));
                    }
                }
            });
        }
    }

    //保存token  T类型视具体Token的返回所在对象的类型而定
    public  final class TokenFunc<T> implements Func1<ResponseWrapper<T>, Observable<String>> {

        @Override
        public Observable<String> call(ResponseWrapper<T> responseWrapper) {
            final T body = responseWrapper.body;
            return Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    sharedPreferences.edit().putString(TOKEN_KEY, (String) body).apply();
                    subscriber.onNext("");
                }
            });
        }
    }

    public RestApi restApi() {
        return restApi;
    }
}
