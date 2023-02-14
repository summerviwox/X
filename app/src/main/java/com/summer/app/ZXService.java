package com.summer.app;

import com.summer.app.model.Record;
import com.summer.x.data.net.ObjectDA;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ZXService {

//    @GET("record/getRecordsWithTypeSize")
//    Call<ListData<PictureB>> getRecordsWithTypeSize(@Query("type") String type, @Query("index") String index);

    @GET("alarm/selectAllAlarms")
    Call<ObjectDA<String>> onLogin(@Query("name")String name, @Query("pwd")String pwd);


    @POST("event/getDurationEvent")
    Call<ObjectDA<String>> getDurationEvent(@Body EventReq eventReq);

    @GET("record/selectAll")
    Call<ArrayList<Record>> selectAll();

}