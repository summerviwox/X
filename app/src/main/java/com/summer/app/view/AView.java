package com.summer.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

public class AView extends RelativeLayout {

    public AView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("ViewGroup:onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("ViewGroup:onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("ViewGroup:onTouchEvent:ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("ViewGroup:dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("ViewGroup:dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("ViewGroup:dispatchTouchEvent:ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("ViewGroup:onInterceptTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("ViewGroup:onInterceptTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("ViewGroup:onInterceptTouchEvent:ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
