package com.summer.record.ui.pictures.home;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PictureHomeVA extends VA {

    private boolean isdoing = false;

    private int model;

    private ArrayList<PictureB> pictures = new ArrayList<>();

    private ArrayList<PictureB> selectPictures = new ArrayList<>();

    /**
     * 添加数据
     * @param pictures
     */
    public void setPictures(ArrayList<PictureB> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);
    }

    public ArrayList<PictureB> getSelectPictures(ArrayList<PictureB> datas){
        getSelectPictures().clear();
        for(int i=0;i<datas.size();i++){
            if(datas.get(i).isSelected()){
                getSelectPictures().add(datas.get(i));
            }
        }
        return getSelectPictures();
    }
}
