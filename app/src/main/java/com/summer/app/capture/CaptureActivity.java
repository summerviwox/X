package com.summer.app.capture;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.app.R;

import java.util.Collection;

public class CaptureActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capture);
        beforeInit();
       // init();
       // init2();
        init3();
        init4();



    }

    private void init4() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        LogUtils.e(networkInfo.getType());
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
            // 当前网络为以太网连接
        } else {
            // 当前不是以太网连接
        }
    }

    private void init3() {
        ContentResolver resolver = getContentResolver();
        resolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true,
                new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        super.onChange(selfChange, uri);
                        LogUtils.e(uri);
                        }
                });

        resolver.registerContentObserver(MediaStore.Video.Media.INTERNAL_CONTENT_URI, true,
                new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        super.onChange(selfChange, uri);
                        LogUtils.e(uri);
                    }
                });
    }

    private void init2() {

        // 定义视频文件路径
        String videoPath = Environment.getExternalStorageDirectory().getPath() + "/";

        // 创建 VideoFileObserver 对象并启动监听
        FileObserver videoFileObserver = new FileObserver(videoPath) {
            @Override
            public void onEvent(int event, @Nullable String path) {
                LogUtils.e(path);
            }
        };
        videoFileObserver.startWatching();
    }

    private void beforeInit() {
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    private void init() {
        Handler handler = new Handler(Looper.getMainLooper());
        getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, new ContentObserver(handler) {
            @Override
            public void onChange(boolean selfChange, @Nullable Uri uri) {
                super.onChange(selfChange, uri);
                LogUtils.e(uri);
            }

            @Override
            public void onChange(boolean selfChange, @NonNull Collection<Uri> uris, int flags) {
                super.onChange(selfChange, uris, flags);
                LogUtils.e(uris);
            }

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                LogUtils.e(selfChange);
            }

            @Override
            public void onChange(boolean selfChange, @Nullable Uri uri, int flags) {
                super.onChange(selfChange, uri, flags);
                LogUtils.e(selfChange);
            }
        });

        getContentResolver().registerContentObserver(MediaStore.Video.Media.INTERNAL_CONTENT_URI, true, new ContentObserver(handler) {
            @Override
            public void onChange(boolean selfChange, @Nullable Uri uri) {
                super.onChange(selfChange, uri);
                LogUtils.e(uri);
            }

            @Override
            public void onChange(boolean selfChange, @NonNull Collection<Uri> uris, int flags) {
                super.onChange(selfChange, uris, flags);
                LogUtils.e(uris);
            }

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                LogUtils.e(selfChange);
            }

            @Override
            public void onChange(boolean selfChange, @Nullable Uri uri, int flags) {
                super.onChange(selfChange, uri, flags);
                LogUtils.e(selfChange);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
