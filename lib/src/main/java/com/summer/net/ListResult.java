package com.summer.net;

import com.summer.x.base.ui.VA;

import java.util.ArrayList;

public class ListResult<T> extends VA {
    ArrayList<T> list;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}