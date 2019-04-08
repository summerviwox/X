package com.summer.record.tool;

//by summer on 2018-03-28.

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.record.data.model.PictureB;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;

import java.io.File;
import java.util.ArrayList;

import androidx.loader.content.CursorLoader;

public class FileTool {

    /**
     * 获取图片和视频
     * @param context
     * @param timeduraion
     * @param onProgressI
     * @return
     */
    public static void getPictures(Context context, String[] timeduraion, OnProgressI onProgressI){
        ArrayList<PictureB> pictureBS = new ArrayList<>();
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DISPLAY_NAME};
        String selection = "("+MediaStore.Files.FileColumns.MEDIA_TYPE + "="+ MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO+")"
                +" AND "
                +MediaStore.Files.FileColumns.DATE_ADDED+">=? AND "+MediaStore.Images.Media.DATE_ADDED+"< ? ";
        Uri queryUri = MediaStore.Files.getContentUri("external");
        CursorLoader cursorLoader = new CursorLoader(
                context,
                queryUri,
                projection,
                selection,
                timeduraion,
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );

        Cursor cursor = cursorLoader.loadInBackground();
        while (cursor.moveToNext()){
            PictureB pictureB = new PictureB(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED))*1000,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED))*1000,
                    0l,
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
            );
            //record.init();
            pictureBS.add(pictureB);
            onProgressI.onProgress("getPictures",OnProgressI.DOING,pictureB);
        }
        ToastUtils.showShort(pictureBS.size()+"");
        onProgressI.onProgress("getPictures",OnProgressI.END,pictureBS);
    }
}
