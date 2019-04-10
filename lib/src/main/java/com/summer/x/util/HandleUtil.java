package com.summer.x.util;

import android.os.Handler;

import java.io.Serializable;

public class HandleUtil extends Handler implements Serializable {

    private static HandleUtil instance;


    public static HandleUtil getInstance() {
        if (instance == null) {
            instance = new HandleUtil();
        }
        return instance;
    }


}