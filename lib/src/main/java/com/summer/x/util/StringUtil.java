package com.summer.x.util;

import android.text.TextUtils;

import com.summer.x.constant.Value;

import java.io.Serializable;

public class StringUtil implements Serializable {

    public static String getStr(Object o){
        if(o==null|| TextUtils.isEmpty(o.toString())){
            return "";
        }
        return o.toString();
    }

    public static String getStrWith(Object o,String defaults){
        if(o==null|| TextUtils.isEmpty(o.toString())){
            return defaults;
        }
        return o.toString();
    }

    public static String getStrWithDefault(Object o){
        if(o==null|| TextUtils.isEmpty(o.toString())){
            return Value.DEFAULTSTR;
        }
        return o.toString();
    }
}
