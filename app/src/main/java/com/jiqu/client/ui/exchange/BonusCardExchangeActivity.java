package com.jiqu.client.ui.exchange;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.jiqu.client.R;
import com.jiqu.client.di.component.ApplicationComponent;
import com.jiqu.client.ui.BaseActivity;
import com.jiqu.client.widget.AlertCheckCode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BonusCardExchangeActivity extends BaseActivity {

    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.id_text)
    TextView idText;
    @Bind(R.id.text_bean)
    TextView textBean;
    @Bind(R.id.self_name_id)
    TextView selfNameId;
    @Bind(R.id.friend_id)
    EditText friendId;
    @Bind(R.id.card_code)
    EditText cardCode;
    @Bind(R.id.card_pwd)
    EditText cardPwd;
    @Bind(R.id.trade_pwd)
    EditText tradePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_card_exchange);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.check_code)
    public void checkCode() {
        AlertCheckCode.newInstance().show(this);
    }

    @Override
    public ApplicationComponent getApplicationComponent() {
        return super.getApplicationComponent();
    }
}
