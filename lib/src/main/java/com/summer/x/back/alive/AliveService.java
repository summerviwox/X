package com.summer.x.back.alive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.summer.x.R;
import com.summer.x.app.XApp;
import com.summer.x.base.i.OnFinishI;

import androidx.annotation.Nullable;

public class AliveService extends Service implements OnFinishI {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alive);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.start();
                onFinished(mp);
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        NotificationManager notificationManager = (NotificationManager) XApp.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel("summer-record", "summer", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), "summer-record").build();
            startForeground(1, notification);
        }else{
            startForeground(1, new Notification());
        }
        return START_STICKY;
    }

    @Override
    public void onFinished(Object o) {

    }


    public static void startService(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context,AliveService.class));
        } else {
            context.startService(new Intent(context,AliveService.class));
        }
    }

    public static void stopService(Context context){
        context.stopService(new Intent(context,AliveService.class));
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}
