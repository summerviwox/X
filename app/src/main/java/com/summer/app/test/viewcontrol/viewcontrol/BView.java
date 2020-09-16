package com.summer.app.test.viewcontrol.viewcontrol;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.summer.app.R;

public class BView extends ViewGroup {

    public BView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(getResources().getColor(R.color.yellow));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
