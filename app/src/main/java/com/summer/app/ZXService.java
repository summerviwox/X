package com.summer.app;

import com.summer.x.data.net.ObjectData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ZXService {

//    @GET("record/getRecordsWithTypeSize")
//    Call<ListData<PictureB>> getRecordsWithTypeSize(@Query("type") String type, @Query("index") String index);

    @GET("alarm/selectAllAlarms")
    Call<ObjectData<String>> onLogin();

}