package com.summer.x.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


public class XApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
