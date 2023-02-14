package com.summer.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

import com.summer.app.R;
import com.summer.app.test.TestActivity;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.XActivity;

import butterknife.OnClick;

public class MainActivity extends XActivity<MainUI, MainDE, DA> implements View.OnClickListener,GetMsgData {

    public static final String TAG = "MainActivity";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDE().getImageData();
        getTextMsg();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        }, 2000);
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

    @OnClick({R.id.text})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.above:
                getTextMsg();
                break;
            case R.id.text:
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
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
