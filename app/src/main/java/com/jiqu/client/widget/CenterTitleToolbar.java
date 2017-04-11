package com.jiqu.client.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author: dj
 * @date 2017/4/6
 */
public class CenterTitleToolbar extends Toolbar {

    private TextView mCenterTitle;

    public CenterTitleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CenterTitleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.setTitle("");
        TypedArray a = context.obtainStyledAttributes(attrs, android.support.v7.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        String title = a.getString(android.support.v7.appcompat.R.styleable.Toolbar_title);
        a.recycle();
        mCenterTitle = new TextView(context);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mCenterTitle.setLayoutParams(layoutParams);
        mCenterTitle.setTextAppearance(context, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Title);
        mCenterTitle.setText(title);
        addView(mCenterTitle);
    }


    @Override
    public void setTitle(CharSequence title) {
        if (mCenterTitle != null) {
            mCenterTitle.setText(title);
        }
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getResources().getString(resId));
    }
}
