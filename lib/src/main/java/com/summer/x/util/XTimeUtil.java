package com.summer.x.util;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class XTimeUtil {



    public static String  sTo(String time,String format,String defaults){
        if(TextUtils.isEmpty(time)){
            return defaults;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Long l = null;
        try {
            l = Long.parseLong(time)*1000;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaults;
        }
        return TimeUtils.date2String(new Date(l),simpleDateFormat);
    }
}
