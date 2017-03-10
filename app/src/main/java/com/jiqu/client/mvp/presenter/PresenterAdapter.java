package com.jiqu.client.mvp.presenter;

import com.jiqu.client.mvp.view.SubscriberProxy;
import com.jiqu.client.mvp.view.WrapperSubscriber;

import java.lang.reflect.Proxy;
import rx.Observer;

/**
 * Created by CJJ on 2017/3/10.
 *expose the {@link #createSubscriberProxy(WrapperSubscriber subscriber)}method to sub presenter
 * that's what this class means
 */

public class PresenterAdapter implements IPresenter {


    public Observer createSubscriberProxy(WrapperSubscriber subscriber){

        return (Observer) Proxy.newProxyInstance(subscriber.getClass().getClassLoader(),new Class[]{Observer.class},new SubscriberProxy(subscriber));

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
