package com.summer.app.multiview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.summer.app.Net;
import com.summer.app.R;
import com.summer.app.model.Record;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.util.HandleUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BView extends RelativeLayout {


    public BView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.fragment_b,null),new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        TextView textView = findViewById(R.id.text);
        Net.getInstance().selectAll().enqueue(new BaseCallBack<ArrayList<Record>>() {
            @Override
            public void onSuccess(ArrayList<Record> records) {
                LogUtils.e(records.size());


                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        String text = GsonUtils.toJson(records);
                        emitter.onNext(text);
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String text) throws Exception {
                                LogUtils.e(" seccess"+ System.currentTimeMillis());
                                HandleUtil.getDefaultInstance().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(text);
                                        textView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        });
                                    }
                                }, 0);
                                LogUtils.e(" setText"+ System.currentTimeMillis());
                            }
                        })
                ;
            }

            @Override
            public void onError(int code, String error) {
                LogUtils.e(error);
            }
        });
    }
}
