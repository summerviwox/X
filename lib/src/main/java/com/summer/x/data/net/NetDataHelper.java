package com.summer.x.data.net;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.summer.x.BuildConfig;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(600, TimeUnit.SECONDS).
                writeTimeout(600, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .build();
            builder.addInterceptor(httpLoggingInterceptor);
        }

        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
