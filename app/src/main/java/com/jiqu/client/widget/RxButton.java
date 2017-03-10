package com.jiqu.client.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import rx.Observer;

/**
 * Created by CJJ on 2017/3/10.
 *
 */

public class RxButton extends Button implements Observer {

    public RxButton(Context context) {
        super(context);
    }

    public RxButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RxButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onCompleted() {
        setEnabled(true);

    }

    @Override
    public void onError(Throwable e) {
        setEnabled(true);

    }

    @Override
    public void onNext(Object o) {

        setEnabled(true);
    }
}
