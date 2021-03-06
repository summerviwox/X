package com.summer.x.data.net;

import com.blankj.utilcode.util.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallBack<T> implements Callback<T> {



    public abstract void onSuccess(T t);

    public abstract void onError(int code,String error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
           onSuccess(response.body());
        }else{
            onError(response.code(),response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(0,t.getMessage());
    }
}
