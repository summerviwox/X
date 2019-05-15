package com.summer.x.back.alive;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.summer.x.R;
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
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onFinished(Object o) {

    }
}
