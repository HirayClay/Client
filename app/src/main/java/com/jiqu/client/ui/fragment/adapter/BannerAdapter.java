package com.jiqu.client.ui.fragment.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author: CJJ
 * @date 2017/4/6
 */
public class BannerAdapter extends PagerAdapter {

    private List<View> views;

    public BannerAdapter(List<View> views) {

        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
//        String url = (String) view.getTag();
//        if (view.getParent() != null)
//            container.removeView(view);
//        Glide.with(container.getContext()).load(HomeAdapter.bannerUrls.get(position)).into((ImageView) view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
