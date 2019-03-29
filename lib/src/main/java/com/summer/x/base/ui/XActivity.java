package com.summer.x.base.ui;

import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class XActivity<A extends UI,B extends DE,C extends VA> extends SupportActivity implements View.OnClickListener {

    private Ope<A,B,C> ope;

    private XActivity activity;

    /**
     * 完成UI,DE,VA的初始化
     * 注解初始化
     * eventbus初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ImmersionBar.with(this).transparentBar().transparentNavigationBar().transparentStatusBar().init();//默认状态栏透明
        initOpe();
        setContentView(getOpe().getUI().getUI().getRoot());
        ButterKnife.bind(getOpe().getUI().getUI().getRoot());
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
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

    /**
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(Object event) {

    }

    protected boolean isRegistEvent(){
        return true;
    }

    public Ope<A, B, C> getOpe() {
        return ope;
    }

    public XActivity getActivity() {
        return activity;
    }
}
