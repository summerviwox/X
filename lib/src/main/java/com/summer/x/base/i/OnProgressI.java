package com.summer.x.base.i;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface OnProgressI {

    public static final int PREPARE = 0;
    public static final int DOING = 1;
    public static final int ERROR = 2;
    public static final int SUCCESS = 3;
    public static final int END = 4;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER})
    @IntDef({PREPARE,DOING,ERROR,SUCCESS,END})
    public @interface Status{

    }


    public void onProgress(String tag,@Status int status, Object data);
}
