package com.summer.x.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.summer.x.data.net.XNet;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ObjectData;
import com.summer.x.exception.Crash;
import com.summer.x.exception.CrashHander;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.multidex.MultiDex;


public class XApp extends Application implements OnFinishI {


    int d = 555;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        crash.setTimestr(simpleDateFormat.format(new Date()));
        XNet.getInstance().sendCrash(crash).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> booleanObjectData) {
                LogUtils.e(booleanObjectData.getData());
            }

            @Override
            public void onError(int code, String error) {
                LogUtils.e(error);
            }
        });
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
