package com.jiqu.client.ui.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jiqu.client.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void fetch_captcha(View view) {
        Toast.makeText(this, "Click!", Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
