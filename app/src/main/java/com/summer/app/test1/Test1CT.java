package com.summer.app.test1;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.app.R;
import com.summer.javalib.annotation.MyTestAnnotation;
import com.summer.x.base.ui.XActivity;

import butterknife.OnClick;
import butterknife.Optional;
//import com.summer.javalib.annotation.TestAnnotation;

/**
 * tangjie 2023/3/1 15:39
 **/
@MyTestAnnotation(value = "test1ct")
public class Test1CT extends XActivity<Test1UI,Test1DE,Test1DA> {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Test1CTHelper
        ToastUtils.showLong(Test1CTHelper.getValue());
    }

    @Optional
    @OnClick({R.id.button1,R.id.button2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button1:
                getUI().smoothScrollTo();
                break;
            case R.id.button2:
                getUI().smoothScrollBack();
                break;
        }
    }

}
