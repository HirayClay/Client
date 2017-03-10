package com.jiqu.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jiqu.client.di.component.ApplicationComponent;
import com.jiqu.client.mvp.view.AuthLoadView;

/**
 * Created by CJJ on 2017/3/7.
 *
 */

public class BaseActivity extends AppCompatActivity implements AuthLoadView {

    DialogProgressFragment progressFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    protected void initializeInjector() {

    }


    ApplicationComponent applicationComponent() {
        return ((BaseApplication) getApplicationContext()).getApplicationComponent();
    }

    public boolean defaultLoading() {
        return true;
    }

    @Override
    public void onAuthFail() {
        if (/*使用loginDialogFragment 登录*/false) {

        }
    }

    public void showLoading() {

//        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (progressFragment == null) {
            progressFragment = DialogProgressFragment.show(this);
        } else {
            progressFragment.dismissAllowingStateLoss();
            progressFragment.show(this);
        }

    }

    public void hideLoading() {
        if (progressFragment == null)
            return;
        else progressFragment.dismissAllowingStateLoss();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String errMsg) {

    }


    //登录失效后再次登录的回调，如再次请求数据等
    protected void retrieve() {

    }
}
