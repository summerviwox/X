package com.summer.x.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 正方形view
 */
public class SquareLinearView extends LinearLayout {


    public SquareLinearView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                MeasureSpec.makeMeasureSpec(getMeasuredWidth(),MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(getMeasuredWidth(),MeasureSpec.getMode(widthMeasureSpec))
        );
    }
}
