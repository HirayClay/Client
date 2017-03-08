package com.jiqu.data.network;

import com.jiqu.data.network.dataformat.ResponseWrapper;
import com.jiqu.domain.param.LoginParam;
import com.squareup.okhttp.RequestBody;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by CJJ on 2017/3/6.
 *
 */

public interface RestApi {


    @POST("account/login")
    Observable<ResponseWrapper<String>> login(@Body LoginParam loginParam);

}
