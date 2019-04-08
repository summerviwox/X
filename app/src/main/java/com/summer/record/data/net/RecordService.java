package com.summer.record.data.net;


import com.summer.record.data.model.PictureB;
import com.summer.x.data.net.ListData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecordService {

    @GET("getRecordsWithTypeSize")
    Call<ListData<PictureB>> getRecordsWithTypeSize(@Query("type") String type, @Query("index") String index);

    @Multipart
    @POST("uploadPicture")
    Call<String> uploadRecords(@Path("localPath")String localPath,@Part MultipartBody.Part file);
}
