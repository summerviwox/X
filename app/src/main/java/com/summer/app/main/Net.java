package com.summer.app.main;

import com.summer.x.data.net.NetDataHelper;

public class Net  {

    private static RecordService recordService;

    public static RecordService getInstance(){
        if(recordService==null){
            NetDataHelper.getInstance().init("http://222.186.36.75:8888/record/record/");
            recordService = NetDataHelper.getInstance().getRetrofit().create(RecordService.class);
        }
        return recordService;
    }
}
