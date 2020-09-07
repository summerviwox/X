package com.summer.x.util;

import android.content.Context;

import com.blankj.utilcode.util.SizeUtils;

public class StatusBarDE {

    private static int statusBarHeight = 0;

    public static  int getStatusBarHeight(Context context){
        if(statusBarHeight == 0 ){
            statusBarHeight =  getStatusBarByResId(context);
        }
        return statusBarHeight;
    }

    private static int getStatusBarByResId(Context context) {
        int height = 0;
        //获取状态栏资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            try {
                height = context.getResources().getDimensionPixelSize(resourceId);
            } catch (Exception e) {
                height = SizeUtils.dp2px(24);
            }
        }else{
            height = getStatusBarByReflex(context);
            if(height==0){
                height = SizeUtils.dp2px(24);
            }
        }
        return height;
    }

    /**
     * 通过反射获取状态栏高度
     *
     * @param context
     * @return
     */
    private static int getStatusBarByReflex(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
