package com.summer.app.net;

import com.summer.x.data.net.NetDataHelper;
import com.summer.x.data.net.XService;

public class AppNet {

    private static XService xService;

    public static XService getInstance(){
        if(xService ==null){
            NetDataHelper.getInstance().init("http://222.186.36.75:9999/"+"record-b/");
            xService = NetDataHelper.getInstance().getRetrofit().create(XService.class);
        }
        return xService;
    }
}
