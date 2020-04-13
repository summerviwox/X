package com.summer.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XActivity;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.NetDataHelper;
import com.summer.x.data.net.ObjectData;
import com.summer.x.util.GlideImageEngine;
import com.summer.x.util.PermissionUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

import butterknife.OnClick;

public class MainActivity extends XActivity<MainUI, DE, VA> implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetDataHelper.DEBUG = true;
        Net.getInstance().onLogin().enqueue(new BaseCallBack<ObjectData<String>>() {
            @Override
            public void onSuccess(ObjectData<String> stringObjectData) {
                LogUtils.e(stringObjectData);
            }

            @Override
            public void onError(int code, String error) {
                LogUtils.e(error);
            }
        });
        ImageView imageView = findViewById(R.id.image);
        GlideApp.with(this).load(R.color.red).into(imageView);
    }
    MediaStoreCompat mediaStoreCompat = new MediaStoreCompat(this);
    @OnClick({R.id.root})
    public void onClick(View v) {
        if (!new PermissionUtil().requestPermissions(this, str)) {
            return;
        }

//        Matisse
//                .from(this)
//                .choose(MimeType.ofAll())
//                .maxSelectable(9)
//                .imageEngine(new GlideImageEngine())
//                .capture(true)
//                .captureStrategy(new CaptureStrategy(true,"PhotoPicker"))
//                .forResult(1);

        mediaStoreCompat.setCaptureStrategy(new CaptureStrategy(true,"PhotoPicker"));
        mediaStoreCompat.dispatchCaptureIntent(this,12);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //因为是指定Uri所以onActivityResult中的data为空 只能再这里获取拍照的路径
        String currentPhotoPath = mediaStoreCompat.getCurrentPhotoPath();
        ToastUtils.showLong(currentPhotoPath);
        //返回图片路径名
        //mView.getCurrentPhotoPath(currentPhotoPath);
    }

    public String[] str = {Manifest.permission.READ_EXTERNAL_STORAGE};

}
