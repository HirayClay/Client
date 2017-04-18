package com.jiqu.client.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

/**
 * @author: CJJ
 * @date 2017/4/10
 */
public class ExpanedGridlayoutManager extends GridLayoutManager {
    private static final String TAG = "ExpanedGridlayoutManage";

    public ExpanedGridlayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ExpanedGridlayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public ExpanedGridlayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int height;
        int childCount = getItemCount();
        int lines = (childCount) / getSpanCount() + (childCount % getSpanCount() == 0 ? 0 : 1);

        View child = recycler.getViewForPosition(1);
        measureChild(child, widthSpec, heightSpec);
        height = child.getMeasuredHeight() * lines;
        setMeasuredDimension(MeasureSpec.getSize(widthSpec), height);
    }
}
