package com.summer.app.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XActivity;

public class MainActivity extends XActivity<MainUI, MainDE, VA> implements View.OnClickListener,GetMsgData {

    public static final String TAG = "MainActivity";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDE().getImageData();
        getTextMsg();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getUI().mainUIInitInf.ModuleBInit("onStart");
            }
        }, 5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getUI().mainUIConfigInf.ModuleAConfig();
        getTextMsg();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.above:
                getTextMsg();
                break;
        }
    }

    @Override
    public void getTextMsg() {
        getDE().getVideoData(new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                getUI().mainUIInitInf.ModuleAInit();
            }
        });
    }
}
