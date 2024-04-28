package com.eanco.fitivation.dal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.eanco.fitivation.dal.dao.ExerciseDetailDao;
import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.dal.model.ExerciseResult;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {ExerciseDetail.class, ExerciseResult.class}, version = 6, exportSchema = false)
public abstract class FitivationDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DB_NAME = "fitivationDatabase";
    private static FitivationDatabase sInstance;

    public abstract ExerciseDetailDao exerciseDao();

    public static FitivationDatabase init(Context context) {
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(), FitivationDatabase.class, FitivationDatabase.DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }

    public static FitivationDatabase getSInstance() {
        return sInstance;
    }

    public static class DiskExecutor {

        private static final Object LOCK = new Object();
        private static DiskExecutor sInstance;
        private final Executor diskIO;

        private DiskExecutor(Executor diskIO) {
            this.diskIO = diskIO;
        }

        public static DiskExecutor getsInstance(){
            synchronized (LOCK){
                sInstance = new DiskExecutor(Executors.newSingleThreadExecutor());
            }
            return sInstance;
        }

        public Executor getDiskIO() {
            return diskIO;
        }

    }
}
