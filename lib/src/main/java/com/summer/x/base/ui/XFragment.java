package com.summer.x.base.ui;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.summer.x.R;
import com.summer.x.base.i.OnProgressI;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class XFragment<A extends UI,B extends DE,C extends VA> extends Fragment implements View.OnClickListener, OnProgressI {

    private Ope<A,B,C> ope;

    private XActivity activity;

    private XFragment fragment = this;

    public boolean loadedData =false;

    public XFragment(){
        if(getArguments()==null){
            setArguments(new Bundle());
        }
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
        LogUtils.e("onCreateView:"+getClass().getName());
        initOpe(container);
        View view = getUI().getUI().getRoot();
        ButterKnife.bind(this,getUI().getUI().getRoot());
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
        return view;
    }


    public void onBeforeReturnView(View  view,@Nullable Bundle savedInstanceState){

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
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(Object event) {

    }

    private void initOpe(ViewGroup container){
        if(getOpe()==null) {
            ope = new Ope<>(null, null, null);
        }
        //生成VA文件
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            Class<C> vacl = (Class<C>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
            Constructor<C> vaco = null;
            C va = null;
            try {
                vaco = vacl.getConstructor();
                va = vaco.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            getOpe().setVA(va);
            Intent intent = new Intent();
            intent.putExtras(getArguments());
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
            aa.bindUI(getXFragment(),container);
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

    public XFragment getXFragment() {
        return fragment;
    }

    @Override
    public void onProgress(String tag, int status, Object data) {

    }
}
