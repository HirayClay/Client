package com.jiqu.client.ui.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.jiqu.client.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConfirmAddressInfoActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_address_info);
        ButterKnife.bind(this);
    }
}
