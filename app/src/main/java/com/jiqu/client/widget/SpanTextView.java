package com.jiqu.client.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jiqu.client.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: CJJ
 * @date 2017/4/11
 * @attr spanColor
 * <p>
 * <SpanTextView
 * android:onClick="clickBoy"
 * app:text="i am a {boy}"
 * app:spanColor="@color/red"/>
 * <p>
 * the string “boy” will appear in red,and when "body" is click ,clickBoy(View view) will be invoked
 * that's how this widget work
 */
public class SpanTextView extends TextView {
    Method spanClickMethod;
    private static final String TAG = "SpanTextView";
    private SpannableString spannableString;
    private String handlerName;

    public SpanTextView(Context context) {
        super(context);
    }

    public SpanTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SpanTextView);
        final int color = array.getColor(R.styleable.SpanTextView_spanColor, Color.GRAY);
        handlerName = array.getString(R.styleable.SpanTextView_spanClick);
        array.recycle();
        String text = getText().toString();
        int start = 0;
        int end = 0;
        int length = text.length();
        start = text.indexOf("{");
        if (start != -1 && text.charAt(start - 1) != '\\')//没有转义字符
            end = text.indexOf("}");
        if (start >= 0 & end > 0) {
            String str = text.substring(0, start)
                    + text.substring(start + 1, end)
                    + (end == length - 1 ? "" : text.substring(end + 1));
            spannableString = new SpannableString(str);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
            spannableString.setSpan(colorSpan, start, end - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (spanClickMethod == null)
                        spanClickMethod = resolveMethod(handlerName);
                    if (spanClickMethod != null)
                        try {
                            spanClickMethod.invoke(getContext(), widget);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(color);
                }
            };
            setMovementMethod(LinkMovementMethod.getInstance());
            setHighlightColor(Color.parseColor("#00000000"));
            spannableString.setSpan(clickableSpan, start, end - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            setText(spannableString);
        }
    }

    private Method resolveMethod(String handlerName) {
        Context context = getContext();
        while (context != null) {
            try {
                if (!context.isRestricted()) {
                    return context.getClass().getMethod(handlerName, View.class);
                }
            } catch (NoSuchMethodException e) {
                // Failed to find method, keep searching up the hierarchy.
            }

            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                // Can't search up the hierarchy, null out and fail.
                context = null;
            }
        }
        return null;
    }

}

