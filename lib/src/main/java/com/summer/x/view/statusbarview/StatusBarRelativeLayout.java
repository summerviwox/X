package com.summer.x.view.statusbarview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.summer.x.util.StatusBarDE;

public class StatusBarRelativeLayout extends RelativeLayout {


    public StatusBarRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(getPaddingLeft(),StatusBarDE.getStatusBarHeight(context),getPaddingRight(),getPaddingBottom());
    }
}
