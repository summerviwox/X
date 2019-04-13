package com.summer.record.ui.albums.addalbum;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.AddAlbumReq;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.albums.bean.AlbumItem;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAlbumVA extends VA {

    private ArrayList<PictureB> datas = new ArrayList<>();

    /**
     * 创建相册参数
     * @param name
     * @param datas
     * @return
     */
    public AddAlbumReq getAddAlbumReq(String name,ArrayList<PictureB> datas){
        AddAlbumReq addAlbumReq = new AddAlbumReq();
        addAlbumReq.setName(name);
        ArrayList<String> localpaths = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            localpaths.add(datas.get(i).getLocpath());
        }
        addAlbumReq.setAlbumItems(localpaths);
        return addAlbumReq;
    }
}
