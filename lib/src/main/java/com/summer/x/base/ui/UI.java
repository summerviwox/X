package com.summer.x.base.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class UI<A extends ViewDataBinding>{

    private A ui;

    private XActivity xActivity;

    private XFragment xFragment;

    public UI(){

    }

    public void initUI(){}

    public void lazyInitUI(){}

    /**
     * 绑定xml
     * @param xActivity
     */
    public void bindUI(XActivity xActivity) {
        this.xActivity = xActivity;
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
                    LogUtils.e(e.toString());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(e.getTargetException().getMessage());
                }
            }
        }
    }

    public void bindUI(XFragment xFragment, ViewGroup viewGroup) {
        this.xFragment = xFragment;
        this.xActivity = xFragment.getXActivity();
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
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LogUtils.e(e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(e.getTargetException().getMessage());
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

    public View SetTitleBar(){
        return null;
    }
}
