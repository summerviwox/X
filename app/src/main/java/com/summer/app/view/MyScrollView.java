package com.summer.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.blankj.utilcode.util.LogUtils;

/**
 * tangjie 2023/1/17 11:41
 **/
public class MyScrollView extends ScrollView {

    public static final String TAG = "MyScrollView";


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.e(TAG+"1",getChildAt(0).getMeasuredHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.e(TAG+"2",getChildAt(0).getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
