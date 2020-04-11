package com.summer.x.data.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface BaseService {

    @GET
    @Streaming
    Call<ResponseBody> download(@Url String url);
}
