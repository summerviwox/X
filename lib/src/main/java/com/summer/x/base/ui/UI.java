package com.summer.x.base.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class UI<A extends ViewDataBinding>{

    public static HashMap<String,ViewDataBinding> uiMap = new HashMap<>();

    private A ui;

    private XActivity xActivity;

    private XFragment xFragment;

    public UI(){

    }

    public void initUI(){}


    /**
     * 绑定xml
     * @param xActivity
     */
    public void bindUI(XActivity xActivity) {
        this.xActivity = xActivity;
        if(uiMap.get(getClass().getName())!=null){
            ui = (A) uiMap.get(getClass().getName());
            return;
        }
        if (ui == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    LogUtils.e(e.getMessage());
                    e.printStackTrace();
                }
                try {
                    ui = (A) method.invoke(null, LayoutInflater.from(xActivity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LogUtils.e(getClass().getName()+":"+e.getCause().getLocalizedMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(getClass().getName()+":"+e.getTargetException().getLocalizedMessage());
                }
            }
        }
    }

    public void bindUI(XFragment xFragment, ViewGroup viewGroup) {
        this.xFragment = xFragment;
        this.xActivity = xFragment.getXActivity();
        if(uiMap.get(getClass().getName())!=null&&isCache()){
            ui = (A) uiMap.get(getClass().getName());
            return;
        }
        if (ui == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class,ViewGroup.class,boolean.class);
                } catch (NoSuchMethodException e) {
                    LogUtils.e(e.getMessage());
                    e.printStackTrace();
                }
                try {
                    ui = (A) method.invoke(null, LayoutInflater.from(xActivity),viewGroup,false);
                    if(isCache()){
                        uiMap.put(getClass().getName(),ui);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LogUtils.e(getClass().getName()+":"+e.getCause().getLocalizedMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(getClass().getName()+":"+e.getTargetException().getLocalizedMessage());
                }
            }
        }
    }

    public A getUI() {
        return ui;
    }

    public XActivity getXActivity() {
        return xActivity;
    }

    public XFragment getXFragment() {
        return xFragment;
    }

    public boolean isCache(){
        return false;
    }

}
