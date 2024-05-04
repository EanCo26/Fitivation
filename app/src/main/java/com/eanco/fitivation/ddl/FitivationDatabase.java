package com.eanco.fitivation.ddl;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.eanco.fitivation.dal.dao.exercise.ExerciseDetailDao;
import com.eanco.fitivation.dal.dao.exercise.ExerciseResultDao;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ddl.model.exercise.ExerciseResult;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(
        entities = {ExerciseDetail.class, ExerciseResult.class},
        views = {},
        version = 1,
        exportSchema = false)
public abstract class FitivationDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DB_NAME = "fitivationDatabase";
    private static FitivationDatabase sInstance;

    public abstract ExerciseDetailDao exerciseDetailDao();
    public abstract ExerciseResultDao exerciseResultDao();

    public static FitivationDatabase init(Context context) {
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(), FitivationDatabase.class, FitivationDatabase.DB_NAME)
                        .addMigrations()
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
