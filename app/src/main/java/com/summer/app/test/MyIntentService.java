package com.summer.app.test;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

/**
 * tangjie 2023/2/28 15:09
 **/
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String num = intent.getExtras().getString("num");
        try {
            Thread.sleep(5*1000);
            LogUtils.e(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
