package com.summer.app;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XActivity;

public class MainActivity extends XActivity<MainUI, DE, VA> implements View.OnClickListener {

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
