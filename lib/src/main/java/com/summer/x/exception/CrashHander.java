package com.summer.x.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.base.i.OnFinishI;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by ${viwmox} on 2016-07-20.
 */
public class CrashHander implements Thread.UncaughtExceptionHandler {

    private static CrashHander instance;

    Context context;

    Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    OnFinishI onFinishI;

    public static CrashHander getInstance() {
        if (instance == null) {
            instance = new CrashHander();
        }
        return instance;
    }

    public void init(Context context, OnFinishI onFinishI) {
        this.onFinishI = onFinishI;
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        if (true) {
            showException();
        }
        if (true) {
            String result = print(ex);
            saveInfo(ex, result);
        }
        if (true) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            uncaughtExceptionHandler.uncaughtException(thread, ex);
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
    }


    /**
     * 打印报错信息
     */
    private String print(Throwable ex) {
        //如果用户没有处理则让系统默认的异常处理器来处理
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        LogUtils.e(result);
        return result;
    }

    /**
     * 保存信息
     */
    public void saveInfo(Throwable ex, String result) {
        onFinishI.onFinished(result);
    }

    private boolean showException() {
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }


}
