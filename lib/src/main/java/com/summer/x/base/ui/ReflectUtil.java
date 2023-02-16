package com.summer.x.base.ui;

import android.content.Intent;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * tangjie 2023/2/14 17:24
 **/
public class ReflectUtil<A extends UI, B extends DE, C extends DA> {

    public Ope<A, B, C> ope;
    public AppCompatActivity activity;


    public ReflectUtil(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * 自动化反射生成UI,DA,VA文件
     */
    public void init(AppCompatActivity activity, Fragment fragment, ViewGroup viewGroup) {
        if (ope != null) {
            return;
        }
        this.activity = activity;
        ope = new Ope<>(null, null, null);
        Type[] types = null;
        if(fragment!=null){
            if (!(fragment.getClass().getGenericSuperclass() instanceof ParameterizedType)) {
                return;
            }
            types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        }else{
            if (!(activity.getClass().getGenericSuperclass() instanceof ParameterizedType)) {
                return;
            }
            types = ((ParameterizedType) activity.getClass().getGenericSuperclass()).getActualTypeArguments();
        }


        //生成DA文件
        Class<C> daClass = (Class<C>) types[2];
        C va = new ViewModelProvider(fragment == null ? activity : fragment).get(daClass);
        ope.setVA(va);
        Intent intent = activity.getIntent();
        if (fragment != null) {
            intent.putExtras(fragment.getArguments());
        }
        va.initVA(intent);

        //生成UI文件
        Class<A> uiClass = (Class<A>) types[0];
        Constructor<A> uiConstructor = null;
        A aa = null;
        try {
            uiConstructor = uiClass.getConstructor(XActivity.class, XFragment.class, ViewGroup.class);
            aa = uiConstructor.newInstance(activity, fragment, viewGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ope.setUI(aa);

        //生成DE文件
        Class<B> deClass = (Class<B>) types[1];
        Constructor<B> deConstructor = null;
        B de = null;
        try {
            deConstructor = deClass.getConstructor(daClass);
            de = deConstructor.newInstance(ope.getVA());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ope.setDE(de);

    }
}
