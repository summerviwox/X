package com.summer.x.util;

import android.os.Handler;

import androidx.fragment.app.Fragment;

import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.ui.XFragment;

import java.io.Serializable;

public class HandleUtil extends Handler implements Serializable {

    private static HandleUtil instance;

    XFragment xFragment;

    public static HandleUtil getDefaultInstance() {
        if (instance == null) {
            instance = new HandleUtil();
        }
        return instance;
    }

    public HandleUtil() {

    }


    Runnable runnable ;

    public boolean stop = false;

    public boolean pause = false;

    public void refresh(Fragment xFragment, int time, OnFinishI onFinishI){
        if(!xFragment.isAdded()||stop){
            return;
        }
        if(!pause){
            onFinishI.onFinished(this);
        }
        if(runnable==null){
            runnable= new Runnable() {
                @Override
                public void run() {
                    refresh(xFragment,time,onFinishI);
                }
            };
        }
        getDefaultInstance().postDelayed(runnable,time);
    }

    public void refreshDelay(Fragment xFragment, int time, OnFinishI onFinishI){
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
                    refreshDelay(xFragment,time,onFinishI);
                }
            };
        }
        getDefaultInstance().postDelayed(runnable,time);
    }

    public void stopNow(){
        stop = true;
        if(runnable!=null){
            getDefaultInstance().removeCallbacks(runnable);
        }
    }

}