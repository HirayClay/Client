package com.jiqu.client.mvp.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.jiqu.client.di.ActivityScope;
import com.jiqu.client.mvp.view.LoginView;
import com.jiqu.client.mvp.view.WrapperSubscriber;
import com.jiqu.domain.param.LoginParam;
import com.jiqu.domain.repository.AccountRepo;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CJJ on 2017/3/6.
 *
 */
@ActivityScope
public class LoginPresenter extends PresenterAdapter {


    private static final String TAG = "LoginPresenter";
    @Named("mock")
    @Inject
    AccountRepo accountRepo;

    @Inject
    LoginView loginView;

    @Inject
    public LoginPresenter() {
    }

    @SuppressWarnings("unchecked")
    public void login(String userName, String password, @Nullable String captcha) {
        loginView.disable();
        accountRepo.login(new LoginParam(userName, password, captcha))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(createSubscriberProxy(new WrapperSubscriber<String>(loginView) {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted:  It is Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String o) {
                        Log.i(TAG, "onNext: the result is " + o);
                        loginView.onLogin("Login Success");
                    }
                }));
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
