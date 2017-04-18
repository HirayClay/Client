package com.jiqu.client.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.client.R;
import com.jiqu.client.ui.BaseFragment;
import com.jiqu.client.ui.fragment.adapter.MineAdapter;
import com.jiqu.client.ui.fragment.adapter.viewholder.Handler;
import com.jiqu.client.widget.MineItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements Handler {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setAdapter(new MineAdapter(this));
        recyclerView.addItemDecoration(new MineItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /*兑换趣豆*/
    @Override
    public void exchangeBean() {

    }

    /*充值卡*/
    @Override
    public void rechargeCard() {

    }

    @Override
    public void claimVipSalary() {

    }

    @Override
    public void claimGuessSalary() {

    }

    @Override
    public void checkTodaySalary() {

    }

    @Override
    public void lookBeanDetail() {

    }

    @Override
    public void checkVipRecord() {

    }

    @Override
    public void checkExchangeRecord() {

    }
}
