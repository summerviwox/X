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

    private XView xView;

    public UI(){

    }


    public void initUI(VA va) {

    }

    public void refreshUI(VA va){

    }

    public boolean checkUI(VA va){
        return true;
    }

    /**
     * 不会自动调用 统一方法名称 部分数据映射到ui
     * @param va
     */
    public void VAToUI(VA va){

    }
    /**
     * 不会自动调用 统一方法名称 ui映射到数据
     * @param va
     */
    public void UIToVA(VA va){

    }

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
                    LogUtils.e(getClass().getName()+":"+e.getCause().getLocalizedMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    LogUtils.e(getClass().getName()+":"+e.getTargetException().getLocalizedMessage());
                }
            }
        }
    }

    public void bindUI(XActivity xActivity,XView xView) {
        this.xView = xView;
        bindUI(xActivity);
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


    public XView getxView() {
        return xView;
    }
}
