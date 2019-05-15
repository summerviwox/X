package com.summer.x.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.GsonUtils;
import com.summer.net.XNet;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ObjectData;
import com.summer.x.exception.Crash;
import com.summer.x.exception.CrashHander;

import androidx.multidex.MultiDex;


public class XApp extends Application implements OnFinishI {

    private static XApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if(registCrash()){
            CrashHander.getInstance().init(this,this);
        }
    }


    @Override
    public void onFinished(Object o) {
        Crash crash = new Crash();
        crash.setCreatedtime(System.currentTimeMillis());
        crash.setError(o.toString());
        crash.setPlatform("android");
        crash.setUser("summer");
        XNet.getInstance().sendCrash(GsonUtils.toJson(crash)).enqueue(new BaseCallBack<ObjectData<Boolean>>(){});
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static XApp getInstance(){
        return instance;
    }

    protected  boolean registCrash(){
        return true;
    }

}
