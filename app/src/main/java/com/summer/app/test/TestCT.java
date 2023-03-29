package com.summer.app.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.app.R;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XActivity;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.OnClick;
import butterknife.Optional;

/**
 * tangjie 2023/2/15 10:36
 **/
public class TestCT extends XActivity<TestUI,TestDE,TestDA>  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDE().checkSign((tag, status, data) -> {
           if(status==ERROR){
               ToastUtils.showLong("");
               return;
           }
        });
        getDE().testNetDE.dialogReq((tag, status, data) -> {
            if(status==SUCCESS){
                getUI().showDialog();
            }
        });
        getDE().testNetDE.login((tag, status, data) -> {
            switch (status){
                case OnProgressI.SUCCESS:
                    getDE().testNetDE.sendCrash((status1, data1) -> {
                        getUI().initMidUI();
                    });
                    getDE().testNetDE.req1((status1, data1) -> {
                        getUI().setUI1();
                    });
                    getDE().testNetDE.req2((status1, data1) -> {
                        getUI().setUI2();
                    });
                    getDE().testNetDE.req3((status1, data1) -> {
                        getUI().setUI3();
                    });
                    getDE().testNetDE.req4((status1, data1) -> {
                        getUI().setUI4();
                    });
                    getDE().testNetDE.req5((status1, data1) -> {
                        getUI().setUI5();
                    });
                    break;
            }
        });
    }

    public void out1(){

    }

    @Optional
    @OnClick({R.id.button})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button:
                getUI().initMidUI();
                break;
        }
    }
}
