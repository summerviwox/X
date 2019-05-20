package com.summer.x.back.alive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.greenrobot.eventbus.EventBus;

public class LiveReceiver extends BroadcastReceiver {

    private static LiveReceiver liveReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmNotice alarmNotice = new AlarmNotice();
        alarmNotice.setO(intent.getIntExtra("num",0));
        EventBus.getDefault().post(alarmNotice);
    }

    public static void regist(Context context){
        liveReceiver = new LiveReceiver();
        IntentFilter intentFilter = new IntentFilter("com.summer.livereceiver");
         context.registerReceiver(liveReceiver,intentFilter);
    }

    public static void unRegist(Context context){
        if(liveReceiver!=null){

            context.unregisterReceiver(liveReceiver);
        }
    }
}
