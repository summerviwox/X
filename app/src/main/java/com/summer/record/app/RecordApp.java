package com.summer.record.app;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.summer.record.BuildConfig;

import me.yokeyword.fragmentation.Fragmentation;

public class RecordApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
        FlowManager.init(this);
    }
}
