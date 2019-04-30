package com.summer.record.app;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.summer.record.BuildConfig;
import com.summer.record.constant.NetConstant;
import com.summer.x.app.XApp;

import me.yokeyword.fragmentation.Fragmentation;

public class RecordApp extends XApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                //.stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
        FlowManager.init(this);
        NetConstant.SPANCOUNT = SPUtils.getInstance().getInt("imagenum",6);
    }
}
