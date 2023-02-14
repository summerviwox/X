package com.summer.x.data.net;

import com.summer.x.base.ui.DA;

import java.util.ArrayList;

public class ListResult<T> extends DA {
    ArrayList<T> list;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}