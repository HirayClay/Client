package com.jiqu.client.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * @author: CJJ
 * @date 2017/4/17
 */
public class MineItemDecoration extends RecyclerView.ItemDecoration {

    Context context;
    private final Paint paint;

    public MineItemDecoration() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#f6f6f6"));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int left = child.getLeft();
            int right = child.getRight();
            RecyclerView.LayoutParams lp  = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom()+ lp.bottomMargin;
            int bottom = top+getItemOffsetForChild(parent,parent.getLayoutManager().getPosition(child));
            c.drawRect(left,top,right,bottom,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (context == null)
            context = parent.getContext();
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildLayoutPosition(view);
        if (pos == 0)
            outRect.set(0, 0, 0, dp2px(2));
        else if (pos == 4)
            outRect.set(0, 0, 0, dp2px(4));
        else if (pos == parent.getLayoutManager().getItemCount() - 1)
            outRect.set(0, 0, 0, 0);
        else outRect.set(0, 0, 0, dp2px(1));
    }


    public int getItemOffsetForChild(RecyclerView parent, int pos) {
        if (pos == 0)
            return dp2px(2);
        else if (pos == 4)
            return dp2px(4);
        else if (pos == parent.getLayoutManager().getItemCount() - 1)
            return 0;
        else return dp2px(1);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
