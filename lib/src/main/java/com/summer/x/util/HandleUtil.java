package com.summer.x.util;

import android.os.Handler;

import androidx.fragment.app.Fragment;

import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.ui.XFragment;

import java.io.Serializable;

public class HandleUtil extends Handler implements Serializable {

    private static HandleUtil instance;

    XFragment xFragment;

    public static HandleUtil getInstance() {
        if (instance == null) {
            instance = new HandleUtil();
        }
        return instance;
    }

    public HandleUtil() {
    }

    public HandleUtil(XFragment xFragment){
        this.xFragment = xFragment;
    }

    Runnable runnable ;

    public boolean stop = false;

    public boolean pause = false;

    public void refresh(XFragment xFragment, int time, OnFinishI onFinishI){
        if(runnable==null){
            runnable= new Runnable() {
                @Override
                public void run() {
                    if(!xFragment.isAdded()||stop){
                        return;
                    }
                    if(!pause){
                        onFinishI.onFinished(this);
                    }
                    refresh(xFragment,time,onFinishI);
                }
            };
        }
        getInstance().postDelayed(runnable,time);
    }

    public void stopNow(){
        stop = true;
        if(runnable!=null){
            getInstance().removeCallbacks(runnable);
        }
    }

}