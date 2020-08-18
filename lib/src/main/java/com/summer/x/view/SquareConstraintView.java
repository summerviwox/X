package com.summer.x.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 正方形view
 */
public class SquareConstraintView extends ConstraintLayout {


    public SquareConstraintView(Context context, @Nullable AttributeSet attrs) {
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
