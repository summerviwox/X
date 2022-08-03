package com.summer.app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.app.frag.FragCT;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XActivity;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.NetDataHelper;
import com.summer.x.data.net.ObjectData;
import com.summer.x.util.GlideImageEngine;
import com.summer.x.util.HandleUtil;
import com.summer.x.util.PermissionUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MainActivity extends XActivity<MainUI, DE, VA> implements View.OnClickListener {

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity","onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity","onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity","onTouchEvent:ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity","dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity","dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity","dispatchTouchEvent:ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
