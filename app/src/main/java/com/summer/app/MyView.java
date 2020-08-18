package com.summer.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.ViewGroupCompat;

public class MyView extends ViewGroup {


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
