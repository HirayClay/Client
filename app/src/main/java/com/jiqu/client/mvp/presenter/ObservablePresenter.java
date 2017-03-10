package com.jiqu.client.mvp.presenter;

import rx.Observable;

/**
 * Created by CJJ on 2017/3/10.
 *
 */

public class ObservablePresenter<T> extends Observable<T>{
    /**
     * Creates an Observable with a Function to execute when it is subscribed to.
     * <p>
     * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead of this constructor,
     * unless you specifically have a need for inheritance.
     *
     * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is called
     */
    protected ObservablePresenter(OnSubscribe<T> f) {
        super(f);
    }
}
