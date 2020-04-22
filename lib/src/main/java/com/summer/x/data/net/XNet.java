package com.summer.x.data.net;

public class XNet {

    private static XService xService;

    public static XService getInstance(){
        if(xService ==null){
            NetDataHelper.getInstance().init("http://222.186.36.75:8888/"+"record/");
            xService = NetDataHelper.getInstance().getRetrofit().create(XService.class);
        }
        return xService;
    }
}
