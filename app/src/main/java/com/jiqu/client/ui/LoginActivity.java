package com.jiqu.client.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiqu.client.R;
import com.jiqu.client.di.component.DaggerAccountComponent;
import com.jiqu.client.di.module.AccountModule;
import com.jiqu.client.mvp.presenter.LoginPresenter;
import com.jiqu.client.mvp.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginView {


    @Inject
    LoginPresenter loginPresenter;
    @Bind(R.id.msg)
    TextView msgText;
    @Bind(R.id.login_button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initializeInjector() {
        DaggerAccountComponent.builder()
                .applicationComponent(applicationComponent())
                .accountModule(new AccountModule(this))
                .build()
                .inject(this);
    }

    public void login(View view) {
        loginPresenter.login(null, null, null);
    }

    @Override
    public void onLogin(String message) {

        msgText.setText(message);
    }

    @Override
    public void enable() {
        loginButton.setEnabled(true);
    }

    @Override
    public void disable() {
        loginButton.setEnabled(false);
    }
}
