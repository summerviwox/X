package com.summer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.x.base.ui.XActivity;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.NetDataHelper;
import com.summer.x.data.net.ObjectData;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NetDataHelper.DEBUG = true;
        Net.getInstance().onLogin().enqueue(new BaseCallBack<ObjectData<String>>() {
            @Override
            public void onSuccess(ObjectData<String> stringObjectData) {
                LogUtils.e(stringObjectData);
            }

            @Override
            public void onError(int code, String error) {
                LogUtils.e(error);
            }
        });
    }


    @OnClick({R.id.root})
    public void onClick(View v) {
        ToastUtils.showShort("dfdf");
    }
}
