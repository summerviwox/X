package com.summer.x.util;

import java.io.Serializable;

public class StringUtil implements Serializable {

    public static String getStr(Object o){
        if(o==null){
            return "";
        }
        return o+"";
    }
}
