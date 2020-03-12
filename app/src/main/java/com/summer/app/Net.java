package com.summer.app;

import com.summer.x.data.net.NetDataHelper;

public class Net {

    //public static final String URL = "http://hi.siweisoft.cn/zhongxin/";
    public static final String URL = "http://222.186.36.75:8888/record/";
    //public static final String URL = "http://sx.siweisoft.cn:8079/zhongxin/";

    private static ZXService ZXService;

    public static ZXService getInstance() {
        if (ZXService == null) {
            NetDataHelper.getInstance().init(URL);
            ZXService = NetDataHelper.getInstance().getRetrofit().create(ZXService.class);
        }
        return ZXService;
    }

}