package com.jiqu.domain.repository;

import com.jiqu.domain.param.LoginParam;

import rx.Observable;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public interface AccountRepo {
     Observable<String> login(LoginParam param);
}
