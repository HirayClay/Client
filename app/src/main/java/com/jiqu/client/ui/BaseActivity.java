package com.jiqu.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public class BaseActivity extends AppCompatActivity {

    DialogProgressFragment progressFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    protected void initializeInjector() {

    }

    public boolean defaultLoading() {
        return true;
    }

    protected void showLoading() {

//        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (progressFragment == null)
        {
            progressFragment= DialogProgressFragment.show(this);
        }else {
            progressFragment.dismissAllowingStateLoss();
            progressFragment.show(this);
        }

    }

    protected void hideLoading(){
        if (progressFragment == null)
            return;
        else progressFragment.dismissAllowingStateLoss();
    }


    //登录失效后再次登录的回调，如再次请求数据等
    protected void retrieve() {

    }
}
