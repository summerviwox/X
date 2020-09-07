package com.summer.app.fragment;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;
import com.summer.x.util.HandleUtil;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragementB extends Fragment implements View.OnClickListener {


    View view;

    String tag = UUID.randomUUID().toString();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_b,container,false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HandleUtil.getDefaultInstance().postDelayed(new Runnable() {
            @SuppressLint("CheckResult")
            @Override
            public void run() {
                TextView textView = view.findViewById(R.id.text);
                textView.setText("text is :" + MyFragmentActivity.count++);
                textView.setOnClickListener(FragementB.this::onClick);
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        String str = "";
                        for(int i=0;i<3000;i++){
                            str+= i;
                        }
                        emitter.onNext(str);
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                textView.setText(textView.getText()+"\n"+s);
                            }
                        });
            }
        }, 10);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                LogUtils.e(getClass().getSimpleName()+"--"+tag + "backstack:"+getChildFragmentManager().getBackStackEntryCount()+"fragments:"+getChildFragmentManager().getFragments().size());
                getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.h_fragment_enter,R.anim.h_fragment_exit,R.anim.h_fragment_enter,R.anim.h_fragment_exit).add(R.id.fragmentb_root,new FragementA()).addToBackStack(tag).commit();
                break;
        }
    }
}
