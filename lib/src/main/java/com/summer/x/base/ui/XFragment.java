package com.summer.x.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class XFragment<A extends UI,B extends DE,C extends VA> extends SupportFragment implements View.OnClickListener {

    private Ope<A,B,C> ope;

    private XActivity activity;

    private XFragment fragment = this;

    public XFragment(){
        setArguments(new Bundle());
        initDEVA();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof XActivity){
            activity = (XActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI(container);
        return getUI().getUI().getRoot();
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
        ButterKnife.bind(this,view);
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
        if(initImmersionBar()){
            if(getUI().SetTitleBar()!=null){
                ImmersionBar.with(this).transparentStatusBar().keyboardEnable(true).titleBar(getUI().SetTitleBar()).init();//默认状态栏透明
            }else{
                ImmersionBar.with(this).transparentStatusBar().keyboardEnable(true).init();//默认状态栏透明
            }
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getUI().lazyInitUI();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(initImmersionBar()){
            ImmersionBar.with(this).destroy();
        }
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
                aa.bindUI(getXFragment(),viewGroup);
                aa.initUI();
                getOpe().setUI(aa);
            } catch (Exception e) {
                e.printStackTrace();
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

    protected boolean initImmersionBar(){
        return true;
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

    public XFragment getXFragment() {
        return fragment;
    }
}
