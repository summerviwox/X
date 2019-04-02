package com.summer.x.data.net;

import com.summer.x.base.ui.VA;

import java.util.ArrayList;

public class ListData<T> extends VA {

    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
