package com.summer.x.util;

import java.io.Serializable;

public class StringUtil implements Serializable {

    public static String getStr(Object o){
        if(o==null){
            return "";
        }
        return o.toString();
    }

    public static String getStrWith(Object o,String defaults){
        if(o==null){
            return defaults;
        }
        return o.toString();
    }

    public static String getStrWithDefault(Object o){
        if(o==null){
            return " - - ";
        }
        return o.toString();
    }
}
