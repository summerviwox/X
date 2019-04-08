package com.summer.record.tool;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.summer.record.data.db.AppDataBase;
import com.summer.record.data.model.PictureB;
import com.summer.x.base.i.OnProgressI;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class DBTool {

    public static void saveRecords(final ArrayList<PictureB> records, final OnProgressI onProgressI){
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


//    public static ArrayList<PictureB> getAllRecord(String type,long[] ctime){
//        ArrayList<PictureB> records = (ArrayList<PictureB>) SQLite.select().from(PictureB.class)
//                .where(Record_Table.atype.is(type))
//                .and(Record_Table.ctime.greaterThan(ctime[0]*1000))
//                .and(Record_Table.ctime.lessThan(ctime[1]*1000))
//                .queryList();
//        return records;
//    }
}
