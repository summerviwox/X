package com.summer.app.test;

import com.summer.app.net.AppNet;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ObjectDA;
import com.summer.x.exception.Crash;

/**
 * tangjie 2023/2/28 17:42
 **/
public class TestNetDE extends DE<TestDA> {

    public interface TestNetInf{

        void onTestNetProgress(int status, Object data);
    }


    public TestNetDE(TestDA da) {
        super(da);
    }

    public void sendCrash(TestNetInf testNetInf){
        AppNet.getInstance().sendCrash(new Crash()).enqueue(new BaseCallBack<ObjectDA<Boolean>>() {
            @Override
            public void onSuccess(ObjectDA<Boolean> booleanObjectDA) {
                testNetInf.onTestNetProgress(OnProgressI.SUCCESS,"");
            }

            @Override
            public void onError(int code, String error) {
                testNetInf.onTestNetProgress(OnProgressI.ERROR,"");
            }
        });
    }

    public void login(OnProgressI onProgressI){
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //

    }

    public void dialogReq(OnProgressI onProgressI){
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
    }

    public void req1(TestNetInf testNetInf){


    }

    public void req2(TestNetInf testNetInf){


    }

    public void req3(TestNetInf testNetInf){


    }

    public void req4(TestNetInf testNetInf){


    }

    public void req5(TestNetInf testNetInf){


    }
}
