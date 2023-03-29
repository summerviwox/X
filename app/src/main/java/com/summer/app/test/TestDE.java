package com.summer.app.test;

import com.summer.app.net.AppNet;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.NetDataHelper;
import com.summer.x.data.net.ObjectDA;
import com.summer.x.data.net.XNet;
import com.summer.x.exception.Crash;

/**
 * tangjie 2023/2/15 10:36
 **/
public class TestDE extends DE<TestDA> {

    public TestNetDE testNetDE;

    public TestDE(TestDA da) {
        super(da);
        testNetDE = new TestNetDE(da);
    }

    /**
     * 验证签名
     */
    protected void checkSign(OnProgressI onProgressI){
        //
        //
        //
        //
        //
        //
    }

}
