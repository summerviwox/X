package com.summer.x.data.net;


import com.summer.x.data.net.ObjectData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface XService {


    @FormUrlEncoded
    @POST("crash/sendCrash")
    Call<ObjectData<Boolean>> sendCrash(@Field("data") String data);

}