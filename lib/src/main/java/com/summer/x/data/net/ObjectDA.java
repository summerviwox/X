package com.summer.x.data.net;

import com.summer.x.base.ui.DA;

public class ObjectDA<T> extends DA {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
