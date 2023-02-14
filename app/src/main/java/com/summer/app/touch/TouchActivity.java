package com.summer.app.touch;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.XActivity;

public class TouchActivity extends XActivity<TouchUI, DE, DA> implements View.OnClickListener {

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity", "onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity", "onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity", "onTouchEvent:ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity", "dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity", "dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity", "dispatchTouchEvent:ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
