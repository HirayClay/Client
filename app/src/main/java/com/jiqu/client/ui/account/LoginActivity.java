package com.jiqu.client.ui.account;

import android.os.Bundle;

import com.jiqu.client.R;
import com.jiqu.client.di.component.DaggerAccountComponent;
import com.jiqu.client.di.module.AccountModule;
import com.jiqu.client.mvp.presenter.LoginPresenter;
import com.jiqu.client.mvp.view.LoginView;
import com.jiqu.client.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginView {


    @Inject
    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initializeInjector() {
        DaggerAccountComponent.builder()
                .accountModule(new AccountModule(this))
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
    }


    @Override
    public void onLogin(String message) {

    }


}
