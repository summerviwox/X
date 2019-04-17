package com.summer.record.ui.pictures.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.loading.LoadingFrag;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PictureHomeVA extends VA {

    private boolean isdoing = false;

    private int model;

    private Long[] times;

    private LoadingFrag loadingfrag;

    private PictureGetDE pictureGetDE = new PictureGetDE();

    private ArrayList<PictureB> pictures = new ArrayList<>();

    private ArrayList<PictureB> selectPictures = new ArrayList<>();

    private ArrayList<MultiItemEntity> multiItemEntities = new ArrayList<>();

    private PictureUploadDE pictureUploadDE = new PictureUploadDE();

    /**
     * 添加数据
     * @param pictures
     */
    public void setPictures(ArrayList<PictureB> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);
    }

    public void addMultiItemEntities(ArrayList<MultiItemEntity> multiItemEntities){
        this.multiItemEntities.clear();
        this.multiItemEntities.addAll(multiItemEntities);
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


    public void setTimes(Long[] times) {
        if(times==null){
            this.times = new Long[]{0l,System.currentTimeMillis()};
            return;
        }
        this.times = times;
    }
}
