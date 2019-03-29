package com.summer.x.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class XFragment<A extends UI,B extends DE,C extends VA> extends SupportFragment implements View.OnClickListener {

    private Ope<A,B,C> ope;

    private XActivity activity;

    private XFragment fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof XActivity){
            activity = (XActivity) context;
        }
    }

    /**
     *完成UI,DE,VA的初始化
     *注解初始化
     *eventbus初始化
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOpe();
        ButterKnife.bind(getOpe().getUI().getUI().getRoot());
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isRegistEvent()){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 自动化反射生成UI,DA,VA文件
     */
    private void initOpe(){
        if(getOpe()==null){
            ope =  new Ope<>(null,null,null);
            //生成UI文件
            if(getClass().getGenericSuperclass() instanceof ParameterizedType){
                try {
                    Class<A> ui = (Class<A>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                    Constructor<A> uic =ui.getConstructor();
                    A aa = uic.newInstance();
                    aa.bindUI(getActivity());
                    aa.initUI();
                    getOpe().setUI(aa);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                }
            }

        }
    }

    protected boolean isRegistEvent(){
        return true;
    }

    public Ope<A, B, C> getOpe() {
        return ope;
    }

    public XActivity getAct() {
        return activity;
    }

    public XFragment getFragment() {
        return fragment;
    }


}
