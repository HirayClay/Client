package com.jiqu.client.mvp.view;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by CJJ on 2017/3/10.
 *
 */

public class SubscriberProxy implements InvocationHandler {

    private static final String TAG = "SubscriberProxy";
    private WrapperSubscriber subscriber;

    public SubscriberProxy() {
    }

    public SubscriberProxy(WrapperSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        Log.i(TAG, "invoke: " + method);
        if (method.getName().startsWith("on")) {
            Log.i(TAG, "invoke:-----------------reflection onComplete ");
            subscriber.disableView();
        }
        if (method != null)
            res = method.invoke(subscriber, args);
        subscriber.enableView();
        return res;
    }
}