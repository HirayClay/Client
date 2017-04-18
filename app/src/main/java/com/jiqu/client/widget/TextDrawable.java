package com.jiqu.client.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.jiqu.client.R;

/**
 * @author: CJJ
 * @date 2017/4/17
 */
public class TextDrawable extends TextView {


    private static final String TAG = "TextDrawable";
    private int corner;
    private Paint paint;

    public TextDrawable(Context context) {
        super(context);
    }

    public TextDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextDrawable);
        corner = (int) array.getDimension(R.styleable.TextDrawable_corner, 0);
        int backgroundColor = array.getColor(R.styleable.TextDrawable_background_color, Color.parseColor("#cccccc"));
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);
        array.recycle();
    }

    public TextDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left = 0;
        float top = 0;
        float right = getMeasuredWidth();
        float bottom = getMeasuredHeight();
        Path path = RoundedRect(left, top, right, bottom, corner, corner, true, true, true, true);
        Log.i(TAG, "TextDrawable: corner" + corner);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }

    public static Path RoundedRect(float left, float top, float right, float bottom, float rx, float ry,
                                   boolean tl, boolean tr, boolean br, boolean bl) {
        Path path = new Path();
        if (rx < 0) {
            rx = 0;
        }
        if (ry < 0) {
            ry = 0;
        }
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) {
            rx = width / 2;
        }
        if (ry > height / 2) {
            ry = height / 2;
        }
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        if (tr)
            path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        else {
            path.rLineTo(0, -ry);
            path.rLineTo(-rx, 0);
        }
        path.rLineTo(-widthMinusCorners, 0);
        if (tl)
            path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        else {
            path.rLineTo(-rx, 0);
            path.rLineTo(0, ry);
        }
        path.rLineTo(0, heightMinusCorners);

        if (bl)
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        else {
            path.rLineTo(0, ry);
            path.rLineTo(rx, 0);
        }

        path.rLineTo(widthMinusCorners, 0);
        if (br)
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        else {
            path.rLineTo(rx, 0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);
        path.close();//Given close, last lineto can be removed.

        return path;
    }
}
