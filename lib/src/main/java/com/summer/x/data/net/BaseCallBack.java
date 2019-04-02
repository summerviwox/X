package com.summer.x.data.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallBack<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        response.body().toString();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
