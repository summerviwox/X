package com.summer.x.view.statusbarview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.summer.x.util.StatusBarDE;
import com.summer.x.view.SimpleTitleView;

public class StatusBarTitleView extends SimpleTitleView {
    public StatusBarTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPadding(getPaddingLeft(), StatusBarDE.getStatusBarHeight(context),getPaddingRight(),getPaddingBottom());
    }
}
