package com.summer.x.base.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class UI<A extends ViewDataBinding,B extends DA>{

    public static HashMap<String,ViewDataBinding> uiMap = new HashMap<>();

    private A ui;

    private B da;

    private XActivity xActivity;

    private XFragment xFragment;

    private XView xView;

    public UI(@NonNull XActivity xActivity, @Nullable XFragment xFragment,@Nullable ViewGroup viewGroup) {
        this.xActivity = xActivity;
        this.xFragment = xFragment;

        if(this.xFragment!=null){
            da = (B) this.xFragment.getVA();
        }else{
            da = (B) this.xActivity.getVA();
        }

        if (ui == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    if(viewGroup==null){
                        method = a.getMethod("inflate", LayoutInflater.class);
                    }else{
                        method = a.getMethod("inflate", LayoutInflater.class,ViewGroup.class,boolean.class);
                    }
                } catch (NoSuchMethodException e) {
                    LogUtils.e(e.getMessage());
                    e.printStackTrace();
                }
                try {
                    if(viewGroup==null){
                        ui = (A) method.invoke(null, LayoutInflater.from(xActivity));
                    }else{
                        ui = (A) method.invoke(null, LayoutInflater.from(xActivity),viewGroup,false);
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

    public B getDA(){
        return da;
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
