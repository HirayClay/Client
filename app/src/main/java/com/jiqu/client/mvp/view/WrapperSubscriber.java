package com.jiqu.client.mvp.view;

import rx.Subscriber;

/**
 * Created by CJJ on 2017/3/10.
 * this subscriber is for throttling view when user click button frequently ,the view won't response
 * before the background task returns,so ensuring the task is running well in case the task is cancel
 * or just crash,and the view will always be unable to response user interaction
 */

public abstract class WrapperSubscriber<T> extends Subscriber<T> {

    private ReactView reactView;

    public WrapperSubscriber() {
    }

    public WrapperSubscriber(ReactView reactView) {
        this.reactView = reactView;
    }
//
//    public void disable() {
//        if (reactView != null)
//            reactView.disable();
//    }

    public void enable() {
        if (reactView != null)
            reactView.enable();
    }
}
