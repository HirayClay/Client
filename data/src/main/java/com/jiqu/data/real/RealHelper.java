package com.jiqu.data.real;

import io.realm.Realm;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
public class RealHelper {
    public static Realm realm = null;

    public static Realm getDefaultInstance(){
        if (realm == null)
            realm = Realm.getDefaultInstance();
        return realm;
    }
}
