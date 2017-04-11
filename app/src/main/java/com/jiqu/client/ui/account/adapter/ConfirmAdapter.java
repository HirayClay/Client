package com.jiqu.client.ui.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jiqu.client.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: CJJ
 * @date 2017/4/11
 */
public class ConfirmAdapter extends RecyclerView.Adapter {

    public static final int TYPE_CONTACT = 0;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_DISTRICT = 2;
    public static final int TYPE_STREET = 3;
    public static final int TYPE_DETAIL = 4;
    public static final int TYPE_ACTION = 5;

    public static final int ITEM_COUNT = 6;

    LayoutInflater inflater;
    Context context;

    String contact;
    String phone;
    String district;
    String street;
    String detail;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
            inflater = LayoutInflater.from(context);
        }
        switch (viewType) {
            case TYPE_CONTACT:
            case TYPE_PHONE:
                return new SimpleInputHolder(inflater.inflate(R.layout.item_simple_input, parent, false));
            case TYPE_DISTRICT:
            case TYPE_STREET:
                return new ChooseAddressHolder(inflater.inflate(R.layout.item_address_action, parent, false));
            case TYPE_DETAIL:
                return new DetailHolder(inflater.inflate(R.layout.item_detail, parent, false));
            case TYPE_ACTION:
                return new ActionHolder(inflater.inflate(R.layout.item_confirm_or_cancel, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    class SimpleInputHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_name)
        TextView itemName;
        @Bind(R.id.edit_text)
        EditText editText;

        public SimpleInputHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setModel(String name) {
            itemName.setText(name);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (getItemViewType() == TYPE_CONTACT)
                        contact = s.toString();
                    else if (getItemViewType() == TYPE_PHONE)
                        phone = s.toString();
                }
            });
        }
    }

    class ChooseAddressHolder extends RecyclerView.ViewHolder {

        public ChooseAddressHolder(View itemView) {
            super(itemView);
        }
    }

    class DetailHolder extends RecyclerView.ViewHolder {

        public DetailHolder(View itemView) {
            super(itemView);
        }
    }

    class ActionHolder extends RecyclerView.ViewHolder {

        public ActionHolder(View itemView) {
            super(itemView);
        }
    }

}
