package com.summer.record.data.net;


import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.albums.bean.AlbumItem;
import com.summer.x.data.net.ListData;
import com.summer.x.data.net.ObjectData;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RecordService {

    @GET("record/getRecordsWithTypeSize")
    Call<ListData<PictureB>> getRecordsWithTypeSize(@Query("type") String type, @Query("index") String index);

    @Multipart
    @POST("picture/uploadPicture")
    Call<ListData<String>> uploadRecords(@Query("data")String data,@Part MultipartBody.Part file);

    @GET("picture/getAllPictures")
    Call<ListData<PictureB>> getAllPictures(@Query("startTime") String startTime, @Query("endTime") String endTime);

    @FormUrlEncoded
    @POST("album/addNewAlbum")
    Call<Boolean> addNewAlbum(@Field("data") String data);

    @GET("album/getAllAlbums")
    Call<ListData<Album>> getAllAlbums();


    @FormUrlEncoded
    @POST("album/deleteAlbum")
    Call<ObjectData<Boolean>> deleteAlbum(@Field("id") String id);

    @FormUrlEncoded
    @POST("albumitem/getAllAlbumItemsById")
    Call<ListData<PictureB>> getAllAlbumItemsById(@Field("id") String id);

    @FormUrlEncoded
    @POST("albumitem/addAlbums")
    Call<ObjectData<Boolean>> addAlbums(@Field("id") String id,@Field("datas") String datas);


    @FormUrlEncoded
    @POST("albumitem/deleteAlbumItem")
    Call<ObjectData<Boolean>> deleteAlbumItem(@Field("albumid") String albumid,@Field("recordid") String recordid);


    @GET("record/getMaxMinDateStamp")
    Call<ObjectData<Long[]>> getMaxMinDateStamp();

    @GET
    @Streaming
    Call<ResponseBody> download(@Url String url);

    @FormUrlEncoded
    @POST("album/setAlbumHead")
    Call<ObjectData<Boolean>> setAlbumHead(@Field("id") String id,@Field("head") String head);




}