package com.summer.app.main;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XActivity;

public class MainActivity extends XActivity<MainUI, DE, VA> implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onResume() {
        super.onResume();
//        getUI().getUI().getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                LogUtils.e(TAG,getUI().getUI().textview.getMeasuredHeight());
//            }
//        });

    }
}
