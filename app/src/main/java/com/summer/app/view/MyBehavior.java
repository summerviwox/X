package com.summer.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;

/**
 * tangjie 2023/2/27 17:11
 **/
public class MyBehavior extends CoordinatorLayout.Behavior<View> implements NestedScrollView.OnScrollChangeListener {


    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if(dependency instanceof NestedScrollView){
            NestedScrollView nsv = (NestedScrollView) dependency;
            nsv.setOnScrollChangeListener(this);
            nsv.setTag(R.id.data,child);
        }
        return true;
    }

    @Override
    public void onScrollChange(NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        View child = (View) nsv.getTag(R.id.data);
        float scorllHeight = child.getHeight();
        if(scorllHeight<=0||nsv.getScrollY()>=scorllHeight){
            return;
        }
        float alpha =1- ((float)nsv.getScrollY())/scorllHeight;
        LogUtils.e(alpha,nsv.getScrollY(),nsv.getChildAt(0).getHeight());
        child.setAlpha(alpha);
    }
}
