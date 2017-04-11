package com.jiqu.client.ui.market;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiqu.client.R;
import com.jiqu.client.ui.market.adapter.MarketAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MarketActivity extends AppCompatActivity {

    @Bind(R.id.tab_layout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    List<String> title = Arrays.asList("全部", "实物兑换", "虚拟兑换");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Fragment[] fragments = new Fragment[3];
        fragments[0] = MarketAllFragment.newInstance();
        fragments[1] = GoodsExchangeFragment.newInstance();
        fragments[2] = VirtualExchangeFragment.newInstance();
        MarketAdapter marketAdapter = new MarketAdapter(getSupportFragmentManager(), title, fragments);
        viewpager.setAdapter(marketAdapter);
        tablayout.setupWithViewPager(viewpager);
    }
}
