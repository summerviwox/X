package com.summer.app.test;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

import com.summer.app.R;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.XActivity;

import butterknife.OnClick;

/**
 * tangjie 2023/1/28 11:32
 **/
public class TestActivity extends XActivity<TestUI, DE, TestDA> {

    public static final String TAG = "TestActivity";
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runnable = new Runnable() {
            @Override
            public void run() {
                getVA().text.postValue(getVA().text.getValue()+1);
                if(isDestroyed()){
                    return;
                }
                handler.postDelayed(runnable,100);
            }
        };
        handler.post(runnable);
    }

    @OnClick({R.id.text})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.text:
                getVA().text.postValue(getVA().text.getValue()+1);
                break;
        }
    }
}
