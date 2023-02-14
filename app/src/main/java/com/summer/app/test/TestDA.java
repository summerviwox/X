package com.summer.app.test;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.summer.x.base.ui.DA;

/**
 * tangjie 2023/1/29 15:10
 **/
public class TestDA extends DA {

    public MutableLiveData<Integer> text = new MutableLiveData<>();

    @Override
    public void initVA(Intent intent) {
        super.initVA(intent);
        text.postValue(0);
    }
}
