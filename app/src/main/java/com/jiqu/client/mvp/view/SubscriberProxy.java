package com.jiqu.client.mvp.view;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by CJJ on 2017/3/10.
 *i wish i could wrap all subscriber when i need to refresh the widget's enable/disable status after
 * background task return,then i do not have write repeat code like "button.setEnable" in
 * subscriber's callback(onComplete() or onNext())
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
        if (method.getName().startsWith("on")) {
            subscriber.enable();
        }
        return method.invoke(subscriber, args);
    }
}
