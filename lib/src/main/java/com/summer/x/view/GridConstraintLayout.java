package com.summer.x.view;

//by summer on 2018-04-10.

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GridConstraintLayout extends ConstraintLayout {

    public GridConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
