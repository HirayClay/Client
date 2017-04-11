package com.jiqu.data.network;

import com.jiqu.data.network.dataformat.ResponseWrapper;
import com.jiqu.domain.entity.Service;
import com.jiqu.domain.param.LoginParam;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by CJJ on 2017/3/6.
 *
 */

public interface RestApi {

    @POST("account/login")
    Observable<ResponseWrapper<String>> login(@Body LoginParam loginParam);


    @POST("SystemService/InfoListEx")
    Observable<ResponseWrapper<List<Service>>> getServices();


}
