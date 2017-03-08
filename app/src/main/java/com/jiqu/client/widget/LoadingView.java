package com.jiqu.client.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiqu.client.R;

/**
 * Created by CJJ on 2017/3/8.
 *
 */
public class LoadingView extends ViewGroup{

    ContentLoadingProgressBar progressCircleView;
    TextView errorMsg;
    ImageView errorIcon;
    TextView  retryText;

    public interface OnStatusChangeListener{
        void onReload();
    }

    private OnStatusChangeListener onStatusChangeListener;

    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        this.onStatusChangeListener = onStatusChangeListener;
    }

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.loading_view_layout, this, true);
        initView(rootView);
    }

    private void initView(View rootView) {
        progressCircleView = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_view);
        errorMsg = (TextView) findViewById(R.id.error_msg);
        errorIcon = (ImageView) findViewById(R.id.error_icon);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
