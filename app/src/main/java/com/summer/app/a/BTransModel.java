package com.summer.app.a;

public class BTransModel {

    public void transformABean(ABean aBean){
        aBean.name = System.currentTimeMillis()+"=>"+aBean.name;
    }

    public void tranformBBean(BBean bBean){
        bBean.type = System.currentTimeMillis()+"=>"+bBean.type;
    }
}
