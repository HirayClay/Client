package com.jiqu.client.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiqu.client.R;

/**
 * @author: CJJ
 * @date 2017/4/12
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private ShapeDrawable mDivider;
    private static final int thickness = 2;

    public GridItemDecoration(Context context) {
        ShapeDrawable drawable = new ShapeDrawable();
        drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorDivider), PorterDuff.Mode.ADD);
        drawable.setIntrinsicWidth(thickness);
        drawable.setIntrinsicHeight(thickness);
        mDivider = drawable;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();

        int left, right, top, bottom;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int pos = parent.getChildLayoutPosition(child);
            if (pos % 2 == 1) {//水平方向绘制
                left = child.getLeft() - mDivider.getIntrinsicWidth();
                right = child.getLeft();
                top = child.getTop();
                bottom = child.getBottom();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            //底部线条绘制
            left = child.getLeft();
            top = child.getBottom();
            right = child.getRight();
            bottom = child.getBottom()+ mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildLayoutPosition(view);
        outRect.top = thickness;
        outRect.left = 0;
        outRect.bottom = 0;
        if (pos % 2 == 0)
            outRect.left = thickness;
        if (pos % 2 == 1)
            outRect.left = 0;
        //最后两格底部线条
        if (parent.getLayoutManager().getItemCount() - pos <= 2) {
            outRect.bottom = thickness;
        }
    }
}

