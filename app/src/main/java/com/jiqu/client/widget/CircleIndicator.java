package com.jiqu.client.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.jiqu.client.R;

import java.lang.ref.WeakReference;

import static android.graphics.PorterDuff.Mode.SRC_IN;

/**
 * @author: CJJ
 * @date 2017/4/6
 */
public class CircleIndicator extends View {

    private static final String TAG = "CircleIndicator";
    PorterDuffXfermode SRC_INXfermode;
    int defaultColor = Color.parseColor("#000000");
    int bubbleMode = 0;
    int gap;
    int number;
    int gravity;
    int color;
    int radius;
    float ratio;
    int currentPos = 0;
    Paint paint;
    ViewPager viewPager;

    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;

    private static final int MODE_BACKGROUND = 0;
    private static final int MODE_OVERLAY = 1;


    public CircleIndicator(Context context) {
        super(context);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("Recycle") TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
        color = array.getColor(R.styleable.CircleIndicator_color, defaultColor);
        radius = array.getDimensionPixelSize(R.styleable.CircleIndicator_bubble_radius, 0);
        number = array.getInteger(R.styleable.CircleIndicator_number, 0);
        gap = array.getDimensionPixelSize(R.styleable.CircleIndicator_gap, 0);
        gravity = array.getInteger(R.styleable.CircleIndicator_bubble_gravity, CENTER);
        array.recycle();
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        SRC_INXfermode = new PorterDuffXfermode(SRC_IN);
        paint.setAntiAlias(true);
    }


    public void setNumber(int number) {
        this.number = number;
        invalidate();
    }

    public void setViewPager(ViewPager v) {
        WeakReference<ViewPager> wk = new WeakReference<>(v);
        viewPager = wk.get();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPos = position;
                ratio = positionOffset;
                invalidate();

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST)
            width = 2 * radius * number + gap * (number - 1);

        height = Math.max(height, radius * 2);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = 0;
        float offset;
        switch (gravity) {
            case LEFT:
                centerX = radius;
                break;
            case CENTER:
                centerX = getMeasuredWidth() / 2 - (2 * radius * number + gap * (number - 1)) / 2;
                break;
            case RIGHT:
                centerX = radius + (getMeasuredWidth() - (2 * radius * number + gap * (number - 1)));
                break;
        }
        offset = centerX;
        float centerY = getMeasuredHeight() / 2;
        paint.setXfermode(null);
        canvas.drawARGB(0, 0, 0, 0);
        int id = 0;
        if (bubbleMode == MODE_BACKGROUND)
            id = canvas.save(Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.YELLOW);
        for (int i = 0; i < number; i++) {
            canvas.drawCircle(centerX, centerY, radius, paint);
            centerX += (2 * radius + gap);
        }
        paint.setColor(Color.RED);
        if (bubbleMode == MODE_BACKGROUND)
            paint.setXfermode(SRC_INXfermode);
        else paint.setXfermode(null);
        centerX = offset + (ratio + currentPos) * (2 * radius + gap);
        canvas.drawCircle(centerX, centerY, radius, paint);
        if (bubbleMode == MODE_BACKGROUND)
            canvas.restoreToCount(id);
    }
}
