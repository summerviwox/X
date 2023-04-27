package com.summer.app.a;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.summer.app.test1.Test1CT;

public class AViewModelBusiImp extends ViewModel implements BusiInf{
    private MutableLiveData<ABean> mABeanMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<BBean> mBBeanMutableLiveData = new MutableLiveData<>();
    private ANetModel mANetModel = new ANetModel();
    private BTransModel mBTransModel = new BTransModel();

    public LiveData<ABean> getABeanLiveData() {
        return mABeanMutableLiveData;
    }

    public LiveData<BBean> getBBeanLiveData() {
        return mBBeanMutableLiveData;
    }

    @Override
    public void startBusi() {
        precessUserInfo();
        precessPlanContent();
    }

    @Override
    public void precessUserInfo() {
        mANetModel.getAData(new ANetModel.AModelCallBack<ABean>() {
            @Override
            public void onResult(ABean data) {
                mBTransModel.transformABean(data);
                mABeanMutableLiveData.postValue(data);
            }
        });
    }

    @Override
    public void precessPlanContent() {
        mANetModel.getBData(new ANetModel.AModelCallBack<BBean>() {
            @Override
            public void onResult(BBean data) {
                mBTransModel.tranformBBean(data);
                mBBeanMutableLiveData.postValue(data);
            }
        });
    }

    @Override
    public void goToOnlineServer(Context context) {
        context.startActivity(new Intent(context, Test1CT.class));
    }
}
