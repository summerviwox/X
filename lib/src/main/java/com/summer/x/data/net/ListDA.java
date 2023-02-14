package com.summer.x.data.net;

import com.summer.x.base.ui.DA;

import java.util.ArrayList;

public class ListDA<T> extends DA {

    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
