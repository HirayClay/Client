package com.jiqu.client.mvp.view;

import android.view.View;

import rx.Subscriber;

/**
 * Created by CJJ on 2017/3/10.
 * this subscriber is for throttling view when user click button frequently ,the view won't response
 * before the background task returns,so ensuring the task is running well in case the task is cancel
 * or just crash,and the view will always be unable to response user interaction
 */

public abstract class WrapperSubscriber<T> extends Subscriber<T> {

    private Subscriber<T> subscriber;
    private View throttleView;

    public WrapperSubscriber() {
    }

    public WrapperSubscriber(View throttleView, Subscriber<T> subscriber) {
        this.subscriber = subscriber;
        this.throttleView = throttleView;
    }

    public void enableView() {
        if (throttleView != null)
            throttleView.setEnabled(true);
    }

    public void disableView()
    {
        if (throttleView != null)
            throttleView.setEnabled(false);
    }
}
