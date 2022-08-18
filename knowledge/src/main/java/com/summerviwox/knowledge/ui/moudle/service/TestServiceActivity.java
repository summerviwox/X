package com.summerviwox.knowledge.ui.moudle.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.summerviwox.knowledge.R;

/**
 * summer 2022/8/18 10:27
 **/
public class TestServiceActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_service);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                startService(new Intent(this,TestService.class));
                break;
            case R.id.stop:
                stopService(new Intent(this,TestService.class));
                break;
            case R.id.bind:
                bindService(new Intent(this, TestService.class),
                        serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(serviceConnection);
                break;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e("onServiceDisconnected");
        }
    };
}
