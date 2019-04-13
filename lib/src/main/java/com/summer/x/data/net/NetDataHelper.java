package com.summer.x.data.net;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具
 */
public class NetDataHelper {

    private static NetDataHelper instance;

    private Retrofit retrofit;

    public static NetDataHelper getInstance(){
        if(instance==null){
            instance = new NetDataHelper();
        }
        return instance;
    }


    public void init(String url){
        OkHttpClient client = new OkHttpClient.Builder().
//                addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        request.newBuilder().addHeader("mediaType","application/json");
//                        return chain.proceed(request);
//                    }
//                })
//                .
                        connectTimeout(60, TimeUnit.SECONDS).
                        readTimeout(600, TimeUnit.SECONDS).
                        writeTimeout(600, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
