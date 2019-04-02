package com.summer.app.main;

import android.os.Bundle;

import com.summer.app.R;
import com.summer.app.fragment.FragMain;
import com.summer.x.base.ui.XActivity;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Response;

public class MainAct extends XActivity<MainUI,MainDE,MainVA>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Net.getInstance().getAllRecords("video").enqueue(new BaseCallBack<ListData<RecordBean>>(){
            @Override
            public void onResponse(Call<ListData<RecordBean>> call, Response<ListData<RecordBean>> response) {
                super.onResponse(call, response);
                response.body().getData();
            }
        });

        loadRootFragment(R.id.mainroot,new FragMain());
    }
}
