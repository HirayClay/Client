package com.jiqu.data.network.dataformat;

/**
 * Created by CJJ on 2017/3/6.
 *
 */

public class ResponseWrapper<T> {

    public String errCode;
    public String errMsg;

    public T body;
}
