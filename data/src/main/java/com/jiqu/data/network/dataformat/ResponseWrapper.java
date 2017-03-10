package com.jiqu.data.network.dataformat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CJJ on 2017/3/6.
 *
 */
//todo change the serializeName to release one
public class ResponseWrapper<T> {

    @SerializedName("ErrorCode")
    public String errCode;
    @SerializedName("ErrorMessage")
    public String errMsg;

    @SerializedName("Body")
    public T body;
}
