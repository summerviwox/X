package com.summer.app;

import com.summer.x.data.net.ObjectData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ZXService {

//    @GET("record/getRecordsWithTypeSize")
//    Call<ListData<PictureB>> getRecordsWithTypeSize(@Query("type") String type, @Query("index") String index);

    @GET("alarm/selectAllAlarms")
    Call<ObjectData<String>> onLogin(@Query("name")String name,@Query("pwd")String pwd);


    @POST("event/getDurationEvent")
    Call<ObjectData<String>> getDurationEvent(@Body EventReq eventReq);

}