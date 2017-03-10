package com.jiqu.data.network;

import com.jiqu.data.network.dataformat.ResponseWrapper;
import com.google.gson.Gson;
import com.jiqu.domain.param.LoginParam;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by CJJ on 2017/3/6.
 *
 */
@Singleton
public class RestApiHelper {


    RestApi restApi;

    Gson gson;

    Url url;

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
                .baseUrl(url.defaultUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        restApi = retrofit.create(RestApi.class);
    }

    public static class ResponseParser<T> implements Func1<ResponseWrapper<T>, Observable<T>>{

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

    public RestApi restApi(){
        return restApi;
    }
}
