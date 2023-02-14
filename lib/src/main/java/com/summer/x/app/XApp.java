package com.summer.x.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.data.net.XNet;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ObjectDA;
import com.summer.x.exception.Crash;
import com.summer.x.exception.CrashHander;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.multidex.MultiDex;


public class XApp extends Application implements OnFinishI {


    int d = 555;

    private static Application instance;

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
        crash.createdtime = System.currentTimeMillis();
        crash.error = o.toString();
        crash.platform = "android";
        crash.user = "summer";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        crash.timestr = simpleDateFormat.format(new Date());
        XNet.getInstance().sendCrash(crash).enqueue(new BaseCallBack<ObjectDA<Boolean>>(){
            @Override
            public void onSuccess(ObjectDA<Boolean> booleanObjectData) {
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


    public static Application getInstance(){
        return instance;
    }

    protected  boolean registCrash(){
        return true;
    }

}
