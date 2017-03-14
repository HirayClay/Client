package com.jiqu.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jiqu.client.R;
import com.jiqu.client.di.component.DaggerAccountComponent;
import com.jiqu.client.di.module.AccountModule;
import com.jiqu.client.mvp.presenter.TestPresenter;
import com.jiqu.client.mvp.view.ServiceView;
import com.jiqu.client.ui.adapter.SimpleTextAdapter;
import com.jiqu.domain.entity.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends BaseActivity implements ServiceView {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;

    @Inject
    TestPresenter testPresenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.loadData)
    Button loadData;
    @Bind(R.id.clearRecy)
    Button clearRecy;

    List<Service> datas = new ArrayList<>();
    private SimpleTextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerAccountComponent.builder()
                .applicationComponent(applicationComponent())
                .accountModule(new AccountModule(this))
                .build().inject(this);
        initRecyclerView();
    }

    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SimpleTextAdapter(datas));
    }

    public void showProgressDialog(View view) {
        showLoading();
    }

    public void nav(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onServiceLoad(List<Service> services) {

        datas.clear();
        datas.addAll(services);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.loadData, R.id.clearRecy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loadData:
                testPresenter.getSingleService();
                break;
            case R.id.clearRecy:
                datas.clear();
                adapter.notifyDataSetChanged();
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.delete(Service.class);
                realm.commitTransaction();
                break;
        }
    }
}
