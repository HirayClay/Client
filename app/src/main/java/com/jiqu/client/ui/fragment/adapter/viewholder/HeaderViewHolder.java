package com.jiqu.client.ui.fragment.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.jiqu.client.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: CJJ
 * @date 2017/4/18
 */
public class HeaderViewHolder extends AbcViewHolder {
    @Bind(R.id.avatar)
    RoundedImageView avatar;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.bean_number)
    TextView beanNumber;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind() {
        super.bind();
    }
}
