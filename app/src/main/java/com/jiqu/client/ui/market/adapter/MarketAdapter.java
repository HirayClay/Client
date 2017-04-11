package com.jiqu.client.ui.market.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author: CJJ
 * @date 2017/4/10
 */
public class MarketAdapter extends FragmentPagerAdapter {
    List<String> title;
   Fragment[] fragments;

    public MarketAdapter(FragmentManager fm, List<String> title, Fragment[] fragments) {
        super(fm);
        this.title = title;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
