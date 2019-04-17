package com.summer.record.ui.pictures.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.tool.DBTool;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;

import java.util.ArrayList;

import androidx.loader.content.CursorLoader;

public class PictureGetDE extends DE {

    /**
     * 获取图片
     * @param context
     * @param onProgressI
     * @return
     */
    @SuppressLint("StaticFieldLeak")
    public void asyncGetPicturesFromDB(Context context, Long[] time,OnProgressI onProgressI){

        new AsyncTask<String, String, ArrayList<PictureB>>() {
            @Override
            protected ArrayList<PictureB> doInBackground(String... strings) {
                ArrayList<PictureB> localPictures = DBTool.getAllRecord(new long[]{time[0]/1000,time[1]/1000});
                return localPictures;
            }

            @Override
            protected void onPostExecute(ArrayList<PictureB> pictureBS) {
                super.onPostExecute(pictureBS);
                onProgressI.onProgress("asyncGetPicturesFromDB",OnProgressI.SUCCESS,pictureBS);
            }
        }.execute();
    }



}
