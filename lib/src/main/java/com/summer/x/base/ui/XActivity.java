package com.summer.x.base.ui;

import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.summer.x.R;
import com.summer.x.base.i.OnProgressI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class XActivity<A extends UI,B extends DE,C extends VA> extends AppCompatActivity implements View.OnClickListener, OnProgressI {

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
        initOpe();
        setContentView(getOpe().getUI().getUI().getRoot());
        ButterKnife.bind(this,getOpe().getUI().getUI().getRoot());
        if(isRegistEvent()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
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
                va.initVA(getActivity().getIntent());
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
                aa.bindUI(getActivity());
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


    public A getUI(){
        return getOpe().getUI();
    }

    public B getDE(){
        return getOpe().getDE();
    }

    public C getVA(){
        return getOpe().getVA();
    }

    private Ope<A, B, C> getOpe() {
        return ope;
    }

    public XActivity getActivity() {
        return activity;
    }

    @Override
    public void onProgress(String tag, int status, Object data) {

    }
}
