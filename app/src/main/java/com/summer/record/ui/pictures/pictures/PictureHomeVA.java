package com.summer.record.ui.pictures.pictures;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class PictureHomeVA extends VA {

    @Setter
    @Getter
    private boolean isdoing = false;

    @Getter
    private ArrayList<PictureB> pictures = new ArrayList<>();

    /**
     * 添加数据
     * @param pictures
     */
    public void setPictures(ArrayList<PictureB> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);
    }
}
