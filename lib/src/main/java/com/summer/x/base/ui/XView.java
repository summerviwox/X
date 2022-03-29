package com.summer.x.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

public class XView<A extends UI,B extends DE,C extends VA> extends FrameLayout implements View.OnClickListener {

    private Ope<A,B,C> ope;

    private XActivity activity;

    public XView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof XActivity){
            activity = (XActivity) context;
        }
        initDEVA();
        BeforeInitUI();
        initUI(this);
        addView(getUI().getUI().getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
    }

    protected void BeforeInitUI(){

    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(isRegistEvent()){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(Object event) {

    }

    /**
     * 自动化反射生成UI,DA,VA文件
     */
    private void initUI(ViewGroup viewGroup){
        if(getOpe()==null) {
            ope = new Ope<>(null, null, null);
        }
        //生成UI文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            try {
                Class<A> ui = (Class<A>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Constructor<A> uic =ui.getConstructor();
                A aa = uic.newInstance();
                aa.bindUI(getXActivity(),this);
                aa.initUI();
                getOpe().setUI(aa);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(e.toString());
            }
        }
    }

    public void initDEVA(){
        if(getOpe()==null) {
            ope = new Ope<>(null, null, null);
        }
        //生成DE文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            try {
                Class<B> decl = (Class<B>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                Constructor<B> deco = decl.getConstructor();
                B de = deco.newInstance();
                de.initDE();
                getOpe().setDE(de);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(e.toString());
            }
        }
        //生成VA文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            try {
                Class<C> vacl = (Class<C>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
                Constructor<C> vaco = vacl.getConstructor();
                C va = vaco.newInstance();
                va.initVA();
                getOpe().setVA(va);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(e.toString());
            }
        }
    }


    protected boolean isRegistEvent(){
        return true;
    }

    public A getUI(){
        return getOpe().getUI();
    }

    public B getDE(){
        return getOpe().getDE();
    }

    public C getVA(){
        return getOpe().getVA();
    }

    public Ope<A, B, C> getOpe() {
        return ope;
    }

    public XActivity getXActivity() {
        return activity;
    }


}
