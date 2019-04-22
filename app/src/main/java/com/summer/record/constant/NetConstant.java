package com.summer.record.constant;

import com.blankj.utilcode.util.SizeUtils;
import com.summer.record.data.model.PictureB;

public class NetConstant {

    public static final String URL = "http://222.186.36.75:8888/";

    public static int SPANCOUNT = 8;

    public static String getNetPath(PictureB pictureB){
        if(pictureB.getNetpath().startsWith("E:\\records")){
            return NetConstant.URL+pictureB.getNetpath().replace("E:\\records","records").replace("\\","/");
        }else{
            return NetConstant.URL+pictureB.getNetpath().replace("E:\\record","records").replace("\\","/");
        }
    }
    
}
