package com.summer.app.empire;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.summer.x.base.i.OnProgressI;

/**
 * tangjie 2023/2/28 17:12
 **/
public class ControlActivity extends AppCompatActivity implements OnProgressI {

    AAgent aAgent;
    BAgent bAgent;
    DataAgent dataAgent;
    NetAgent netAgent;
    UIAgent uiAgent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        processA();
        processB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        processC();
    }

    private void init(){
        aAgent = new AAgent();
        bAgent = new BAgent();
        dataAgent = new DataAgent();
        netAgent = new NetAgent();
        uiAgent = new UIAgent();
    }

    public void processA(){
        aAgent.Aaa(this);
        bAgent.Bbb(this);
        uiAgent.initUI(this);
    }

    public void processB(){

    }

    public void processC(){

    }

    @Override
    public void onProgress(String tag, int status, Object data) {

    }
}
