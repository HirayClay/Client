package com.jiqu.data.network.dataformat;

import com.google.gson.annotations.SerializedName;
import com.jiqu.domain.constant.Constant;

/**
 * Created by CJJ on 2017/3/6.
 *
 */
//todo change the serializeName to release one
public class ResponseWrapper<T> {

    @SerializedName("ErrorCode")
    public int errCode;
    @SerializedName("ErrorMessage")
    public String errMsg;

    @SerializedName("Body")
    public T body;


    private static final int CODE_SUCCESS = 0;
    private static final int AUTH_EXPIRED = 1;

    public boolean isSuccess() {

        return errCode == CODE_SUCCESS;
    }

    public boolean isAuthExpired() {
        return errCode == AUTH_EXPIRED;
    }

}
