package com.eanco.fitivation.dal;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.eanco.fitivation.ddl.model.ReadModel;
import com.eanco.fitivation.ddl.model.ReadWriteModel;
import com.eanco.fitivation.ddl.FitivationDatabase;

import java.util.List;

public class FitivationRepository {

    public static <T> LiveData<List<T>> getAll(Class<? extends ReadModel> dbClass) {
        return DaoMapReduce.getReadDao(dbClass).getAll();
    }

    public static <T> void insertAll(Class<? extends ReadWriteModel> dbClass, List<T> entities) {
        executeThread(() -> DaoMapReduce.getReadWriteDao(dbClass).insertAll(entities));
    }

    public static <T> void updateAll(Class<? extends ReadWriteModel> dbClass, List<T> entities) {
        executeThread(() -> DaoMapReduce.getReadWriteDao(dbClass).updateAll(entities));
    }

    public static <T> void deleteAll(Class<? extends ReadWriteModel> dbClass, List<T> entities) {
        executeThread(() -> DaoMapReduce.getReadWriteDao(dbClass).deleteAll(entities));
    }

    private static void executeThread(Runnable run) {
        try {
            FitivationDatabase.DiskExecutor.getsInstance().getDiskIO().execute(run);
        }
        catch (Exception ex) {
            Log.e("FitivationWriteRepository", "executeThread: ", ex);
        }
    }
}
