package com.eanco.fitivation.dal;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.eanco.fitivation.dal.model.ReadModel;
import com.eanco.fitivation.dal.model.ReadWriteModel;

import java.util.List;

public class FitivationRepository {

    public static <T> LiveData<List<T>> getAll(Class<? extends ReadModel> dbClass) {
        return DaoMapReduce.getReadDao(dbClass).getAll();
    }

    public static <T> LiveData<List<T>> getLatest(Class<? extends ReadModel> dbClass) {
        return DaoMapReduce.getReadDao(dbClass).getLatest();
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
