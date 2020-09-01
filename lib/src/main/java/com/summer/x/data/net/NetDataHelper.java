package com.summer.x.data.net;

import com.summer.x.base.ui.VA;
import com.summer.x.data.net.logging.Level;
import com.summer.x.data.net.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具
 */
public class NetDataHelper {

    public static boolean DEBUG = false;

    private static NetDataHelper instance;

    private Retrofit retrofit;

    private Config config = new Config();

    public static NetDataHelper getInstance(){
        if(instance==null){
            instance = new NetDataHelper();
        }
        return instance;
    }


    public void init(String url){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(getConfig().connectTimeout, TimeUnit.SECONDS).
                readTimeout(getConfig().readTimeout, TimeUnit.SECONDS).
                writeTimeout(getConfig().writeTimeout, TimeUnit.SECONDS);

        if (DEBUG) {
            LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                    .loggable(true)
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
                .addConverterFactory(getConfig().converter)
                .addCallAdapterFactory(getConfig().callAdapter)
                .build();

    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Getter
    @Setter
    public static class Config extends VA{

        long connectTimeout = 60;

        long readTimeout = 600;

        long writeTimeout =600;

        Converter.Factory converter =GsonConverterFactory.create();

        CallAdapter.Factory callAdapter = RxJava2CallAdapterFactory.create();


    }
}
