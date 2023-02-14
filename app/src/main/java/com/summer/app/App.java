package com.summer.app;

import android.os.Build;
import android.os.Process;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.app.XApp;

public class App extends XApp {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            LogUtils.e(getProcessName(), Process.myPid());
//        }
    }
}
