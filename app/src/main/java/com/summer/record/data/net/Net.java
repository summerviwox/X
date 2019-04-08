package com.summer.record.data.net;

import com.summer.record.constant.NetConstant;
import com.summer.x.data.net.NetDataHelper;

public class Net  {

    private static RecordService recordService;

    public static RecordService getInstance(){
        if(recordService==null){
            NetDataHelper.getInstance().init(NetConstant.URL+"record/");
            recordService = NetDataHelper.getInstance().getRetrofit().create(RecordService.class);
        }
        return recordService;
    }
}
