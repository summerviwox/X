package com.summer.record.tool;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.summer.record.data.db.AppDataBase;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.model.PictureB_Table;
import com.summer.x.base.i.OnProgressI;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class DBTool {

    /**
     * 异步保存
     * @param records
     * @param onProgressI
     */
    public static void asynicSaveRecords(final ArrayList<PictureB> records, final OnProgressI onProgressI){
        if(records==null||records.size()==0){
            onProgressI.onProgress("",OnProgressI.END,null);
            return;
        }
        FlowManager.getDatabase(AppDataBase.class).beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                for(int i=0;i<records.size();i++){
                    records.get(i).save();
                }
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                onProgressI.onProgress("",OnProgressI.ERROR,null);
                onProgressI.onProgress("",OnProgressI.END,null);
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                onProgressI.onProgress("",OnProgressI.SUCCESS,null);
                onProgressI.onProgress("",OnProgressI.END,null);
            }
        }).build().executeSync();
    }

    /**
     * 同步保存
     * @param records
     */
    public static void savePictures(final ArrayList<PictureB> records){
        for(int i=0;i<records.size();i++){
            records.get(i).save();
        }
    }


    public static void savePictures(final ArrayList<PictureB> records,OnProgressI progressI){
        for(int i=0;i<records.size();i++){
            records.get(i).save();
            progressI.onProgress("savePictures",OnProgressI.DOING,records.get(i).getName());
        }
    }

    /**
     * 获取时间段内所有记录
     * @param ctime
     * @return
     */
    public static ArrayList<PictureB> getAllRecord(long[] ctime){
        ArrayList<PictureB> records = (ArrayList<PictureB>) SQLite.select().from(PictureB.class)
                .where(PictureB_Table.ctime.greaterThan(ctime[0]*1000))
                .and(PictureB_Table.ctime.lessThan(ctime[1]*1000))
                .queryList();
        return records;
    }


    /**
     * 获取数据库记录列表在新的保存时间
     * @return
     */
    public static Long getLasPictureTime(){
        FlowCursor cursor = SQLite.select(Method.max(PictureB_Table.ctime)).from(PictureB.class).query();
        long max = 0;
        while (cursor.moveToNext()){
            max = cursor.getLong(0);
        }
        return max;
    }

    public void updateIsUpload(PictureB pictureB){
        pictureB.update();
    }

    /**
     * 是否初始化过
     */
    public static boolean isInit(){
        FlowCursor cursor = SQLite.select(Method.count(PictureB_Table.id)).from(PictureB.class).query();
        long max = 0;
        while (cursor.moveToNext()){
            max = cursor.getInt(0);
        }
        return max!=0;
    }

    public static void upDataByLocalPath(String locpath,String netpath){
        SQLite.update(PictureB.class).set(PictureB_Table.netpath.eq(netpath)).where(PictureB_Table.locpath.eq(locpath)).execute();
    }
}
