package com.summer.record.ui.pictures.pictures;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class PictureHomeVA extends VA {

    @Getter
    private ArrayList<PictureB> pictures = new ArrayList<>();

    /**
     * 添加数据
     * @param pictures
     */
    public void setPictures(ArrayList<PictureB> pictures) {
        this.pictures.clear();
        for(int i=0;i<pictures.size();i++){
            if(pictures.get(i).getNetpath()!=null){
                String str = pictures.get(i).getNetpath().substring("E:\\".length(),pictures.get(i).getNetpath().length()).replace("\\","/");
                pictures.get(i).setNetpath(str);
            }
            this.pictures.add(pictures.get(i));
        }
    }
}
