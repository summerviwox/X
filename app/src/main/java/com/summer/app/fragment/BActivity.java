package com.summer.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.summer.app.Net;
import com.summer.app.R;
import com.summer.app.model.Record;
import com.summer.x.data.net.BaseCallBack;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BActivity extends FragmentActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.e("oncreate"+TimeUtils.date2String(new Date()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bfragment);
        textView = findViewById(R.id.text);
        LogUtils.e("beginselectall"+TimeUtils.date2String(new Date()));
        Net.getInstance().selectAll().enqueue(new BaseCallBack<ArrayList<Record>>() {
            @Override
            public void onSuccess(ArrayList<Record> records) {
                LogUtils.e(records.size());
                LogUtils.e("success"+TimeUtils.date2String(new Date()));

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
                            public void accept(String s) throws Exception {
                                LogUtils.e("gson cessess"+TimeUtils.date2String(new Date()));
                                textView.setText(s);
                                LogUtils.e("settext success"+TimeUtils.date2String(new Date()));
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
