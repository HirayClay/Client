package com.jiqu.client.ui.fragment.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.jiqu.client.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: CJJ
 * @date 2017/4/18
 */
public class VipViewHolder extends AbcViewHolder {

    @Bind(R.id.text_my_vip)
    TextView textMyVip;
    @Bind(R.id.rank_icon)
    TextView rankIcon;
    @Bind(R.id.vip)
    TextView vip;
    @Bind(R.id.get_vip_paid)
    TextView getVipPaid;

    public VipViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind() {

    }
}
