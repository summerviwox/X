package com.summer.app.net;


import com.summer.x.data.net.ObjectDA;
import com.summer.x.exception.Crash;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {


    @POST("crash/crash")
    Call<ObjectDA<Boolean>> sendCrash(@Body Crash crash);

}