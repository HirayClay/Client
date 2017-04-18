package com.jiqu.client.ui.fragment.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.jiqu.client.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: CJJ
 * @date 2017/4/18
 */
public class CoinBeanViewHolder extends AbcViewHolder {
    @Bind(R.id.text_my_coin)
    TextView textMyCoin;
    @Bind(R.id.coin_number)
    TextView coinNumber;
    @Bind(R.id.exchange_bean)
    TextView exchangeBean;

    public CoinBeanViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.exchange_bean)
    public void action() {
        int pos = getAdapterPosition();
        if (pos == 1) {
            //todo 兑换趣豆
        } else if (pos == 2) {
            //todo 福利卡充值
        }
    }

    @Override
    public void bind() {
        int pos = getAdapterPosition();
        if (pos == 1) {
            textMyCoin.setText("我的趣币");
            //todo 设置趣币数量
            exchangeBean.setText("兑换趣豆");
        } else if (pos == 2) {
            textMyCoin.setText("我的趣豆");
            exchangeBean.setText("福利卡充值");
        }
    }
}
