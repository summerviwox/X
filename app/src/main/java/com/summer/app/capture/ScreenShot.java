package com.summer.app.capture;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

/**
 * 系统截屏监听工具，监听系统截屏，然后对截图进行处理
 */
public class ScreenShot {
    private static final String TAG = "ScreenShot";
    private static final String[] MEDIA_PROJECTIONS = {
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.VideoColumns.DATE_TAKEN,
            MediaStore.Video.VideoColumns.DATE_ADDED,
    };

    /**
     * 截屏依据中的路径判断关键字
     */
    private static final String[] KEYWORDS = {
            "screenshot", "screen_shot", "screen-shot", "screen shot", "screencapture",
            "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap",
            "screen-cap", "screen cap", "截屏"
    };

    private ContentResolver mContentResolver;
    private CallbackListener mCallbackListener;
    private MediaContentObserver mInternalObserver;
    private MediaContentObserver mExternalObserver;
    private static ScreenShot mInstance;

    private long mExitTime;

    private ScreenShot() {
    }

    /**
     * 获取 ScreenShot 对象
     *
     * @return ScreenShot对象
     */
    public static ScreenShot getInstance() {
        if (mInstance == null) {
            synchronized (ScreenShot.class) {
                mInstance = new ScreenShot();
            }
        }
        return mInstance;
    }

    /**
     * 注册
     *
     * @param context          上下文
     * @param callbackListener 回调监听
     */
    public void register(Context context, CallbackListener callbackListener) {
        mContentResolver = context.getContentResolver();
        mCallbackListener = callbackListener;

        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        mInternalObserver = new MediaContentObserver(MediaStore.Video.Media.INTERNAL_CONTENT_URI, handler);
        mExternalObserver = new MediaContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, handler);

        mContentResolver.registerContentObserver(MediaStore.Video.Media.INTERNAL_CONTENT_URI,
                false, mInternalObserver);
        mContentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                false, mExternalObserver);
    }

    /**
     * 注销
     */
    public void unregister() {
        if (mContentResolver != null) {
            mContentResolver.unregisterContentObserver(mInternalObserver);
            mContentResolver.unregisterContentObserver(mExternalObserver);
        }
    }

    private void handleMediaContentChange(Uri uri) {
        Cursor cursor = null;
        try {
            // 数据改变时，查询数据库中最后加入的一条数据
            cursor = mContentResolver.query(uri, MEDIA_PROJECTIONS, null, null,
                    MediaStore.Video.VideoColumns.DATE_ADDED + " desc limit 1");
            if (cursor == null) {
                return;
            }
            if (!cursor.moveToFirst()) {
                return;
            }
            int dataIndex = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
            int dateAddedIndex = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED);
            // 处理获取到的第一行数据
            handleMediaRowData(cursor.getString(dataIndex), cursor.getLong(dateAddedIndex));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

//    private Lock lock = new ReentrantLock();

    /**
     * 处理监听到的资源
     */
    private void handleMediaRowData(String path, long dateAdded) {

        if (checkScreenShot(path,dateAdded)) {
            if (mCallbackListener != null) {
//                lock.lock();// 得到锁
                System.out.println(Thread.currentThread().getName()+" begin time: "+System.currentTimeMillis());
                mCallbackListener.onShot(path);
                System.out.println(Thread.currentThread().getName()+" end time: "+System.currentTimeMillis());
            }
        }
    }
    /**
     * 判断是否是截屏
     */
    private boolean checkScreenShot(String data, long dateTaken) {

        data = data.toLowerCase();
        // 判断图片路径是否含有指定的关键字之一, 如果有, 则认为当前截屏了
        for (String keyWork : KEYWORDS) {
            if (data.contains(keyWork)) {
                return true;
            }
        }
        return false;
    }

    private static long lastTime = 0;
    /**
     * 媒体内容观察者
     */
    private class MediaContentObserver extends ContentObserver {
        private Uri mUri;

        MediaContentObserver(Uri uri, Handler handler) {
            super(handler);
            mUri = uri;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            LogUtils.e(selfChange);
            long current = System.currentTimeMillis();
            if (current - lastTime>1000){
                lastTime = current;
                Log.d("ScreenShot", "图片数据库发生变化：" + current);
                handleMediaContentChange(mUri);
            }
        }
    }

    /**
     * 回调监听器
     */
    public interface CallbackListener {
        /**
         * 截屏
         *
         * @param path 图片路径
         */
        void onShot(String path);
    }
}
