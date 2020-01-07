package com.summer.x.base.ui;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class UI<A extends ViewDataBinding>{

    private A ui;

    private Context context;

    public UI(){

    }

    public void initUI(){}

    /**
     * 绑定xml
     * @param context
     */
    public void bindUI(Context context) {
        this.context = context;
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
                    ui = (A) method.invoke(null, LayoutInflater.from(context));
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

    public void bindUI(Context context, ViewGroup viewGroup) {
        this.context = context;
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
                    ui = (A) method.invoke(null, LayoutInflater.from(context),viewGroup,false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LogUtils.e(e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(e.getMessage());
                }
            }
        }
    }

    public A getUI() {
        return ui;
    }

    public Context getContext() {
        return context;
    }
}
