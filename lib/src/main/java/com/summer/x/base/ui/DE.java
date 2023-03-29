package com.summer.x.base.ui;

import java.io.Serializable;

public class DE<A extends DA> implements Serializable {

    public static final String add = "add";
    public static final String delete = "delete";
    public static final String update = "update";
    public static final String query = "query";
    public static final String refresh = "refresh";
    public static final String finish = "finish";
    public static final String none = "none";

    private A da;

    public DE(A da){
        this.da = da;
    }

    public A getDA() {
        return da;
    }
}
