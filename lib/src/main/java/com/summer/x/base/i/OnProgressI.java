package com.summer.x.base.i;

public interface OnProgressI {
    public static final int PREPARE = 0;
    public static final int DOING = 1;
    public static final int ERROR = 2;
    public static final int SUCCESS = 3;
    public static final int END = 4;

    public void onProgress(String tag, int status, Object data);
}
