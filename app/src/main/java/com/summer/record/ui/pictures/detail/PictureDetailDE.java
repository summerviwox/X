package com.summer.record.ui.pictures.detail;

import android.text.TextUtils;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.DE;

import java.util.ArrayList;

public class PictureDetailDE extends DE {

    /**
     * 去掉填充的数据
     * @param ori
     * @return
     */
    public ArrayList<PictureB> init(ArrayList<PictureB> ori){
        ArrayList<PictureB> datas = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(!TextUtils.isEmpty(ori.get(i).getAtype())){
                datas.add(ori.get(i));
            }
        }
        return datas;
    }

    public int getPos(ArrayList<PictureB> ori,String locpath){
        int pos = 0;
        for(int i=0;i<ori.size();i++){
            if(ori.get(i).getLocpath().equals(locpath)){
                pos = i;
                break;
            }
        }
        return pos;
    }

}
