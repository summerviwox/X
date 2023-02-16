package com.summer.app.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.summer.app.test.TestCT;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.XActivity;

public class MainActivity extends XActivity<MainUI, MainDE, MainDA> {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, TestCT.class));
    }
}
