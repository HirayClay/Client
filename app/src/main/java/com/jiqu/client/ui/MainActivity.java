package com.jiqu.client.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiqu.client.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showProgressDialog(View view) {
        showLoading();
    }

    public void nav(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
