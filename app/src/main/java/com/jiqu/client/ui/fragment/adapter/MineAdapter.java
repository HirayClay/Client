package com.jiqu.client.ui.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.client.R;
import com.jiqu.client.ui.fragment.adapter.viewholder.AbcViewHolder;
import com.jiqu.client.ui.fragment.adapter.viewholder.CoinBeanViewHolder;
import com.jiqu.client.ui.fragment.adapter.viewholder.GuessSalaryViewHolder;
import com.jiqu.client.ui.fragment.adapter.viewholder.Handler;
import com.jiqu.client.ui.fragment.adapter.viewholder.HeaderViewHolder;
import com.jiqu.client.ui.fragment.adapter.viewholder.TextItemViewHolder;
import com.jiqu.client.ui.fragment.adapter.viewholder.VipViewHolder;


/**
 * @author: CJJ
 * @date 2017/4/17
 */
public class MineAdapter extends RecyclerView.Adapter<AbcViewHolder> {
    private static final String TAG = "MineAdapter";
    private static final int ITEM_COUNT = 8;
    LayoutInflater inflater;
    Context context;
    Handler handler;

    public MineAdapter(Handler handler) {
        this.handler = handler;
    }

    @Override
    public AbcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
            inflater = LayoutInflater.from(context);
            Log.i(TAG, "onCreateViewHolder: " + context.getClass().getName());
        }
        switch (viewType) {
            case 0:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_mine_header, parent, false));
            case 1:
            case 2:
                return new CoinBeanViewHolder(inflater.inflate(R.layout.item_mine_coin_bean, parent, false));
            case 3:
                return new VipViewHolder(inflater.inflate(R.layout.item_mine_vip, parent, false));
            case 4:
                return new GuessSalaryViewHolder(inflater.inflate(R.layout.item_mine_guess_salary, parent, false));
            case 5:
            case 6:
            case 7:
                return new TextItemViewHolder(inflater.inflate(R.layout.item_mine_text, parent, false));
            default:
                return new HeaderViewHolder(new View(context));
        }

    }

    @Override
    public void onBindViewHolder(AbcViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

}
