package com.summer.record.ui.pictures.home;

import android.text.TextUtils;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.DE;

import java.io.File;
import java.util.ArrayList;


public class PictureHomeDE extends DE {



    public ArrayList<PictureB> getUploadPictures(ArrayList<PictureB> ori){
        ArrayList<PictureB> pictureBS = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(TextUtils.isEmpty(ori.get(i).getAtype())){
                continue;
            }
            //未上传
            if(TextUtils.isEmpty(ori.get(i).getNetpath())){
                if(ori.get(i).getLocpath()!=null){
                    File file = new File(ori.get(i).getLocpath());
                    //本地有该文件
                    if(file.exists()){
                         pictureBS.add(ori.get(i));
                    }
                }
            }
        }
        return pictureBS;
    }


    /**
     * 去掉填充的数据
     * @param ori
     * @return
     */
    public ArrayList<PictureB> removeNullPictures(ArrayList<PictureB> ori){
        ArrayList<PictureB> datas = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(!TextUtils.isEmpty(ori.get(i).getAtype())){
                datas.add(ori.get(i));
            }
        }
        for(int i=0;i<datas.size();i++){
            datas.get(i).setPos(i);
        }
        return datas;
    }

}
