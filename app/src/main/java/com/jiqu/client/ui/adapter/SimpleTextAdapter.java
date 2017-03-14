package com.jiqu.client.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiqu.client.R;
import com.jiqu.domain.entity.Service;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.SimpleTextHolder> {


    LayoutInflater inflater;
    List<Service> services;


    public SimpleTextAdapter(List<Service> services) {
        this.services = services;
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return new SimpleTextHolder(inflater.inflate(R.layout.simple_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, int position) {
        holder.textView.setText(services.get(position).getTypeName());
    }

    @Override
    public int getItemCount() {
        return services == null ? 0 : services.size();
    }

    class SimpleTextHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.simple_text)
        TextView textView;

        public SimpleTextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
