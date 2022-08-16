package com.summerviwox.knowledge.ui.moudle.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * summer 2022/8/16 15:16
 **/
public class CustomerViewGroup extends ViewGroup {

    public CustomerViewGroup(Context context) {
        super(context);
    }

    public CustomerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
