package com.jiqu.domain.repository;

import com.jiqu.domain.entity.Service;
import com.jiqu.domain.entity.UserInfo;
import com.jiqu.domain.param.LoginParam;

import java.util.List;

import rx.Observable;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public interface AccountRepo {
     Observable<String> login(LoginParam param);

     Observable<UserInfo> getUserInfo(String userId);

     Observable<List<Service>> getService();
}
