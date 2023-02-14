package com.summer.x.base.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.summer.x.base.i.OnProgressI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

public class XView<A extends UI,B extends DE,C extends DA> extends FrameLayout implements View.OnClickListener, OnProgressI {

    private Ope<A,B,C> ope;

    private XActivity activity;

    public XView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof XActivity){
            activity = (XActivity) context;
        }
        initOpe(this);
        addView(getUI().getUI().getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
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

    private void initOpe(XView container){
        if(getOpe()==null) {
            ope = new Ope<>(null, null, null);
        }
        //生成VA文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            Class<C> vacl = (Class<C>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
            Constructor<C> vaco = null;
            C va = null;
//            try {
//                vaco = vacl.getConstructor();
//                va = vaco.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            va = new ViewModelProvider(getXActivity()).get(vacl);
            getOpe().setVA(va);
            Intent intent = getXActivity().getIntent();
            va.initVA(intent);
        }
        //生成UI文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            Class<A> ui = (Class<A>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Constructor<A> uic = null;
            A aa = null;
            try {
                uic = ui.getConstructor();
                aa = uic.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            aa.bindUI(getXActivity(),container);
            getOpe().setUI(aa);
            aa.initUI(getVA());
        }
        //生成DE文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            Class<B> decl = (Class<B>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor<B> deco = null;
            B de = null;
            try {
                deco = decl.getConstructor();
                de = deco.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            getOpe().setDE(de);
            de.initDE(getVA(),this::onProgress);
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


    @Override
    public void onProgress(String tag, int status, Object data) {

    }
}
