package com.summer.app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.ToastUtils;

//桌面时间小部件
public class TimeWidget extends AppWidgetProvider {

    public static final String myaction = "summer.timewidget";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("TimeWidget","onReceive"+intent.getAction()+""+intent.toString());
        if(intent==null||intent.getAction()==null){
            return;
        }
        switch (intent.getAction()){
            case myaction:
                ToastUtils.showLong("123");
                break;
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("TimeWidget","onUpdate");
        for(int i=0;i<appWidgetIds.length;i++){//appWidgetIds里面装的是拖拽出来的所有widget
            //不能直接设置监听，所以需要通过远程视图进行设置,appwidget_layout为显示在桌面的视图
            RemoteViews rv=new RemoteViews(context.getPackageName(), R.layout.layout_time);
            Intent intent = new Intent().setAction(myaction);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);//转到
            rv.setOnClickPendingIntent(R.id.text, pendingIntent);//为text设置单击事件为intent
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);//更新数据到这个小部件
        }//为每个拖拽出来的小部件设置单击事件
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.e("TimeWidget","onEnabled");
    }
}
