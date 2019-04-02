package com.summer.app.main;


import com.summer.x.data.net.ListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecordService {

    @GET("getAllRecords")
    Call<ListData<RecordBean>> getAllRecords(@Query("atype") String atype);
}
