package com.summer.x.data.net;

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
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
