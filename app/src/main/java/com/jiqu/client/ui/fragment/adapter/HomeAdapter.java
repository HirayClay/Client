package com.jiqu.client.ui.fragment.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiqu.client.R;
import com.jiqu.client.widget.CircleIndicator;
import com.jiqu.client.widget.ExpanedGridlayoutManager;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: CJJ
 * @date 2017/4/6
 */
public class HomeAdapter extends RecyclerView.Adapter {

    public static final int BANNER = 0;//轮播
    public static final int PERSON = 1;//用户信息
    public static final int HOT = 2;//热门
    public static final int NEWBEE = 3;//ic_newbee
    public static final int JOY = 4;//娱乐大厅

    public static List<String> bannerUrls = Arrays.asList(
            "http://img15.3lian.com/2015/h1/317/d/42.jpg",
            "http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg",
            "http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg",
            "http://img1.3lian.com/2015/a1/113/d/10.jpg");
    Context context;
    LayoutInflater layoutInflater;

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER;
            case 1:
                return PERSON;
            case 2:
                return HOT;
            case 3:
                return NEWBEE;
            case 4:
                return JOY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            context = parent.getContext();
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(layoutInflater.inflate(R.layout.item_banner, parent, false));
            case PERSON:
                return new PersonViewHolder(layoutInflater.inflate(R.layout.item_home_personinfo, parent, false));
            case HOT:
                return new HotViewHolder(layoutInflater.inflate(R.layout.item_home_hot, parent, false));
            case NEWBEE:
                return new NewBeeViewHolder(layoutInflater.inflate(R.layout.item_newbee, parent, false));
            case JOY:
                JoyViewHolder joyViewHolder = new JoyViewHolder(layoutInflater.inflate(R.layout.item_home_joy, null));
                joyViewHolder.setIsRecyclable(false);
                return joyViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof JoyViewHolder) {
            JoyViewHolder joyViewHolder = (JoyViewHolder) holder;
            if (joyViewHolder.recyclerView.getLayoutManager() != null)
                return;
            RecyclerView recyclerView = joyViewHolder.recyclerView;
            recyclerView.setLayoutManager(new ExpanedGridlayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new GridAdapter());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.viewpager)
        ViewPager viewPager;
        @Bind(R.id.circle_indicator)
        CircleIndicator circleIndicator;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindBanner(bannerUrls);
        }


        void bindBanner(List<String> bannerUrls) {
            int count = bannerUrls.size();
            List<View> views = new ArrayList<>();
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.height = ViewPager.LayoutParams.MATCH_PARENT;
            lp.width = ViewPager.LayoutParams.MATCH_PARENT;
            for (int i = 0; i < count; i++) {
                ImageView iv = new ImageView(context);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(lp);
                views.add(iv);
                Glide.with(context).load(bannerUrls.get(i)).into(iv);
            }
            viewPager.setAdapter(new BannerAdapter(views));
            circleIndicator.setViewPager(viewPager);
        }
    }


    class PersonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.avatar)
        RoundedImageView avatar;
        @Bind(R.id.fortune)
        TextView fortune;
        @Bind(R.id.vip)
        TextView vip;
        @Bind(R.id.clock_in)
        TextView clockIn;

        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.h5game)
        TextView h5game;
        @Bind(R.id.daily_share)
        TextView dailyShaer;
        @Bind(R.id.trade_market)
        TextView tradeMarket;
        @Bind(R.id.guess_board)
        TextView guessBoard;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NewBeeViewHolder extends RecyclerView.ViewHolder {

        public NewBeeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class JoyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.joy_hall)
        RecyclerView recyclerView;

        public JoyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class GridAdapter extends RecyclerView.Adapter<JoyHolder> {

        private final DisplayMetrics dm;

        public GridAdapter() {
            dm = new DisplayMetrics();
            ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        }

        @Override
        public JoyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (layoutInflater == null)
                layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.sub_item_joy, parent, false);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(dm.widthPixels / 2, dp2px(76));
            view.setLayoutParams(lp);
            return new JoyHolder(view);
        }

        @Override
        public void onBindViewHolder(JoyHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

    class JoyHolder extends RecyclerView.ViewHolder {

        public JoyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
