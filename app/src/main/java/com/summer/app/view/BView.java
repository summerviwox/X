package com.summer.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

public class BView extends View {

    public BView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("View:onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("View:onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("View:onTouchEvent:ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e( "View:dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("View:dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("View:dispatchTouchEvent:ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
