package com.summer.app.a;

public class ANetModel {

    public interface AModelCallBack<T>{
        void onResult(T data);
    }

    public void getAData(AModelCallBack<ABean> aModelCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ABean aBean = new ABean();
                aBean.name = "abean-name";
                aModelCallBack.onResult(aBean);
            }
        }).start();
    }


    public void getBData(AModelCallBack<BBean> aModelCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                BBean bBean = new BBean();
                bBean.type = "bbean-type";
                aModelCallBack.onResult(bBean);
            }
        }).start();
    }
}
