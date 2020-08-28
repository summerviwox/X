package com.summer.x.data.net;


import com.summer.x.data.net.ObjectData;
import com.summer.x.exception.Crash;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface XService {


    @POST("crash/crash")
    Call<ObjectData<Boolean>> sendCrash(@Body Crash crash);

}