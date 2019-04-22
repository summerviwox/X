package com.summer.record.ui.pictures.picture;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.pictures.home.PictureHomeDE;
import com.summer.record.ui.pictures.home.PictureUploadDE;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.ArrayRes;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PictureVA extends VA {

    private PictureB picture;

    private boolean uploading = false;

    private PictureUploadDE pictureUploadDE= new PictureUploadDE();

    private PictureDownDE pictureDownDE = new PictureDownDE();

    private PictureHomeDE pictureHomeDE = new PictureHomeDE();

    public ArrayList<HashMap<String,String>> getMenu(ArrayList<Album> albums){
        ArrayList<HashMap<String,String>> menus = new ArrayList<>();
        for(int i=0;i<albums.size();i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",albums.get(i).getId()+"");
            hashMap.put("text",albums.get(i).getName()+"");
            menus.add(hashMap);
        }
        return menus;
    }
}
