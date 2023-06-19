package com.summer.app.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.ScreenUtils;
import com.summer.app.R;


/**
 * 百分比最大高度ScrollView 即将移植到基础服务工程
 */
public class PercentMaxScrollView extends NestedScrollView {

    private float percentHeight;

    public PercentMaxScrollView(@NonNull Context context) {
        super(context, (AttributeSet)null);
        init(context,null,0);
    }

    public PercentMaxScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init(context,attrs,0);
    }

    public PercentMaxScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentMaxScrollView);
        percentHeight= typedArray.getFloat(R.styleable.PercentMaxScrollView_height_percent,ScreenUtils.getScreenHeight()/2);
        percentHeight = ((percentHeight>1)||(percentHeight<=0))? ScreenUtils.getScreenHeight()/2:percentHeight;
        typedArray.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (ScreenUtils.getScreenHeight()*percentHeight), MeasureSpec.AT_MOST));
    }
}